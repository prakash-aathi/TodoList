package com.assignment.todo.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.assignment.todo.dto.TodoListRequest;
import com.assignment.todo.dto.TodoListResponse;
import com.assignment.todo.model.TodoList;
import com.assignment.todo.service.TodoListService;

@ExtendWith(MockitoExtension.class)
public class TodoListControllerTest {

    @InjectMocks
    private TodoListController todoListController;

    @Mock
    private TodoListService todoListService;

    @Test
    void todoList_getAllTodoListsForUser_success(TestInfo testInfo) {

        System.out.println("Test name: " + testInfo.getDisplayName());

        // Arrange
        Principal principal = mock(Principal.class);
        when(principal.getName()).thenReturn("user@gmail.com");

        List<TodoList> todoLists = new ArrayList<>();
        todoLists.add(new TodoList(1L, "My Todo List", "user@gmail.com", null));

        when(todoListService.getAllTodoListsForUser(principal.getName())).thenReturn(todoLists);

        // Act
        ResponseEntity<List<TodoList>> responseEntity = todoListController.getAllTodoListsForUser(principal);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(todoLists, responseEntity.getBody());
        verify(todoListService, times(1)).getAllTodoListsForUser(principal.getName());
    }

    @Test
    void todoList_getAllTodoListsForUser_noTodoListsFound(TestInfo testInfo) {

        System.out.println("Test name: " + testInfo.getDisplayName());

        // Arrange
        Long todoListId = 1L;

        TodoListResponse expectedResponse = new TodoListResponse();
        expectedResponse.setId(todoListId);
        expectedResponse.setName("My Todo List");

        doReturn(ResponseEntity.ok(expectedResponse)).when(todoListService).getTodoListById(todoListId);

        // Act
        ResponseEntity<?> responseEntity = todoListController.getTodoListById(todoListId);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedResponse, responseEntity.getBody());
        verify(todoListService, times(1)).getTodoListById(todoListId);
    }

    @Test
    void todoList_getTodoListById_success(TestInfo testInfo) {

        System.out.println("Test name: " + testInfo.getDisplayName());

        // Arrange
        Long todoListId = 1L;

        TodoListRequest todoListRequest = new TodoListRequest();
        todoListRequest.setName("Updated Todo List");

        TodoListResponse expectedResponse = new TodoListResponse();
        expectedResponse.setId(todoListId);
        expectedResponse.setName(todoListRequest.getName());

        doReturn(ResponseEntity.ok(expectedResponse)).when(todoListService).updateTodoList(todoListId, todoListRequest);

        // Act
        ResponseEntity<?> responseEntity = todoListController.updateTodoList(todoListId, todoListRequest);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedResponse, responseEntity.getBody());
        verify(todoListService, times(1)).updateTodoList(todoListId, todoListRequest);
    }

    @Test
    void todoList_updateTodoList_success(TestInfo testInfo) {

        System.out.println("Test name: " + testInfo.getDisplayName());

        // Arrange
        Long todoListId = 1L;

        ResponseEntity<?> expectedResponse = ResponseEntity.ok("TodoList deleted successfully");

        doReturn(expectedResponse).when(todoListService).deleteTodoList(todoListId);

        // Act
        ResponseEntity<?> responseEntity = todoListController.deleteTodoList(todoListId);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedResponse.getBody(), responseEntity.getBody());
        verify(todoListService, times(1)).deleteTodoList(todoListId);
    }
}
