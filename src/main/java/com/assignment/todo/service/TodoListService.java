package com.assignment.todo.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.assignment.todo.dto.request.TodoListRequest;
import com.assignment.todo.model.TodoList;

public interface TodoListService {

    ResponseEntity<?> createTodoList(TodoListRequest todoListRequest, String UserEmail );

    ResponseEntity<?> getTodoListById(Long id);

    List<TodoList> getAllTodoListsForUser(String name);

    ResponseEntity<?> updateTodoList(Long id, TodoListRequest todoListRequest);

    ResponseEntity<?> deleteTodoList(Long id);
    
}
