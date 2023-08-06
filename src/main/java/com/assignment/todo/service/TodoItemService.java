package com.assignment.todo.service;

import org.springframework.http.ResponseEntity;

import com.assignment.todo.dto.TodoItemRequest;

public interface TodoItemService  {

    ResponseEntity<?> createTodoItem(Long todoListId, TodoItemRequest todoItemRequest);

    ResponseEntity<?> getTodoItemById(Long id);

    ResponseEntity<?> getAllTodoItemsForTodoList(Long todoListId);

    ResponseEntity<?> updateTodoItem(Long id, TodoItemRequest todoItemRequest);

    ResponseEntity<?> markTodoItemAsCompleted(Long id);

    ResponseEntity<?> deleteTodoItem(Long toDoListID, Long id);
    
}
