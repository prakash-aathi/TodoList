// package com.assignment.todo.controller;

// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.mockito.ArgumentMatchers.eq;
// import static org.mockito.Mockito.doReturn;
// import static org.mockito.Mockito.times;
// import static org.mockito.Mockito.verify;

// import java.util.Arrays;
// import java.util.List;

// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.TestInfo;
// import org.junit.jupiter.api.extension.ExtendWith;
// import org.mockito.InjectMocks;
// import org.mockito.Mock;
// import org.mockito.junit.jupiter.MockitoExtension;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;

// import com.assignment.todo.dto.request.TodoItemRequest;
// import com.assignment.todo.model.TodoItem;
// import com.assignment.todo.service.TodoItemService;

// @ExtendWith(MockitoExtension.class)
// public class TodoItemControllerTest {

//     @InjectMocks
//     private TodoItemController todoItemController;

//     @Mock
//     private TodoItemService todoItemService;

//     @Test
//     void todoItem_createTodoItem_success(TestInfo testInfo) {

//         System.out.println("Test name: " + testInfo.getDisplayName());
        
//         // Arrange
//         TodoItemRequest todoItemRequest = new TodoItemRequest();
//         todoItemRequest.setTitle("My Todo Item");

//         Long todoListId = 1L;

//         TodoItemRequest expectedResponse = new TodoItemRequest();
//         expectedResponse.setTitle(todoItemRequest.getTitle());

//         doReturn(ResponseEntity.created(null).body(expectedResponse)).when(todoItemService)
//                 .createTodoItem(eq(todoListId), eq(todoItemRequest));

//         // Act
//         ResponseEntity<?> responseEntity = todoItemController.createTodoItem(todoListId, todoItemRequest);

//         // Assert
//         assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
//         assertEquals(expectedResponse, responseEntity.getBody());
//         verify(todoItemService, times(1)).createTodoItem(eq(todoListId), eq(todoItemRequest));
//     }

//     @Test
//     void todoItem_updateTodoItem_success(TestInfo testInfo) {

//         System.out.println("Test name: " + testInfo.getDisplayName());

//         // Arrange
//         Long todoListId = 1L;
//         Long todoItemId = 2L;

//         ResponseEntity<String> expectedResponse = ResponseEntity.ok("TodoItem deleted successfully");

//         doReturn(expectedResponse).when(todoItemService).deleteTodoItem(eq(todoListId), eq(todoItemId));

//         // Act
//         ResponseEntity<?> responseEntity = todoItemController.deleteTodoItem(todoItemId, todoListId);

//         // Assert
//         assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
//         assertEquals(expectedResponse.getBody(), responseEntity.getBody());
//         verify(todoItemService, times(1)).deleteTodoItem(eq(todoListId), eq(todoItemId));
//     }

//     @Test
//     void todoItem_deleteTodoItem_success(TestInfo testInfo) {

//         System.out.println("Test name: " + testInfo.getDisplayName());

//         // Arrange
//         Long todoListId = 1L;
//         List<TodoItem> todoItems = Arrays.asList(
//                 new TodoItem(1L, "Task 1","assignment" ,false,"Monday"),
//                 new TodoItem(2L, "Task 2","leetcode" ,true,"tuesday"));

//         doReturn(ResponseEntity.ok(todoItems)).when(todoItemService).getAllTodoItemsForTodoList(eq(todoListId));

//         // Act
//         ResponseEntity<?> responseEntity = todoItemController.getAllTodoItemsForTodoList(todoListId);

//         // Assert
//         assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
//         assertEquals(todoItems, responseEntity.getBody());
//         verify(todoItemService, times(1)).getAllTodoItemsForTodoList(eq(todoListId));
//     }

//     @Test
//     void todoItem_getAllTodoItemsForTodoList_success(TestInfo testInfo) {

//         System.out.println("Test name: " + testInfo.getDisplayName());

//         // Arrange
//         Long todoItemId = 1L;
//         TodoItem todoItem = new TodoItem(1L, "My Task", "clean ", false, "Monday");

//         doReturn(ResponseEntity.ok(todoItem)).when(todoItemService).getTodoItemById(eq(todoItemId));

//         // Act
//         ResponseEntity<?> responseEntity = todoItemController.getTodoItemById(todoItemId);

//         // Assert
//         assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
//         assertEquals(todoItem, responseEntity.getBody());
//         verify(todoItemService, times(1)).getTodoItemById(eq(todoItemId));
//     }

//     @Test
//     void todoItem_getTodoItemById_success(TestInfo testInfo) {

//         System.out.println("Test name: " + testInfo.getDisplayName());

//         // Arrange
//         Long todoItemId = 1L;

//         TodoItem updatedTodoItem = new TodoItem(1L, "My Task", "clean ", true, "Monday");

//         doReturn(ResponseEntity.ok(updatedTodoItem)).when(todoItemService).markTodoItemAsCompleted(eq(todoItemId));

//         // Act
//         ResponseEntity<?> responseEntity = todoItemController.markTodoItemAsCompleted(todoItemId);

//         // Assert
//         assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
//         assertEquals(updatedTodoItem, responseEntity.getBody());
//         verify(todoItemService, times(1)).markTodoItemAsCompleted(eq(todoItemId));
//     }

// }
