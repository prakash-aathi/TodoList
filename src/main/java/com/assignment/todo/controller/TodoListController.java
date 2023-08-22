package com.assignment.todo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.todo.dto.request.TodoListRequest;
import com.assignment.todo.model.TodoList;
import com.assignment.todo.service.TodoListService;

@RestController
@RequestMapping("api/v1/todolist")
public class TodoListController {

    @Autowired
    TodoListService todoListService;

    @PostMapping
    public ResponseEntity<?> createTodoList(@RequestBody TodoListRequest todoListRequest, String userName) {
        return todoListService.createTodoList(todoListRequest, userName);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getTodoListById(@PathVariable("id") Long id) {
        return todoListService.getTodoListById(id);
    }

    @GetMapping
    public ResponseEntity<List<TodoList>> getAllTodoListsForUser(String userName) {
        List<TodoList> todoLists = todoListService.getAllTodoListsForUser(userName);
        return ResponseEntity.ok(todoLists);
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateTodoList(@PathVariable("id") Long id, @RequestBody TodoListRequest todoListRequest) {
        return todoListService.updateTodoList(id, todoListRequest);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteTodoList(@PathVariable("id") Long id) {
        return todoListService.deleteTodoList(id);
    }

}
