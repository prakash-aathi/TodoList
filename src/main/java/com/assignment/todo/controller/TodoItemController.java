package com.assignment.todo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.todo.dto.request.TodoItemRequest;
import com.assignment.todo.service.TodoItemService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;


@RestController
@RequestMapping("api/v1/todoitem")
public class TodoItemController {

    @Autowired
    TodoItemService todoItemService;

    @PostMapping("{todolistId}")
    public ResponseEntity<?> createTodoItem(@PathVariable("todolistId") Long todoListId,
            @RequestBody TodoItemRequest todoItemRequest) {
        return todoItemService.createTodoItem(todoListId, todoItemRequest);
    }
    
    @GetMapping("{id}")
    public ResponseEntity<?> getTodoItemById(@PathVariable("id") Long id) {
        return todoItemService.getTodoItemById(id);
    }
    
    @GetMapping("todolist/{todolistId}")
    public ResponseEntity<?> getAllTodoItemsForTodoList(@PathVariable("todolistId") Long todoListId) {
        return todoItemService.getAllTodoItemsForTodoList(todoListId);
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateTodoItem(@PathVariable("id") Long id, @RequestBody TodoItemRequest todoItemRequest) {
        return todoItemService.updateTodoItem(id, todoItemRequest);
    }

    @PutMapping("/{id}/complete")
    public ResponseEntity<?> markTodoItemAsCompleted(@PathVariable Long id) {
        return todoItemService.markTodoItemAsCompleted(id);
    }
    
    @DeleteMapping("/{todolistId}/{id}")
    public ResponseEntity<?> deleteTodoItem(@PathVariable("id") Long id, @PathVariable("todolistId") Long todoListId) {
        return todoItemService.deleteTodoItem(todoListId, id);
    }
    
}
