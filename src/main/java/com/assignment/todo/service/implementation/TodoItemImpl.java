package com.assignment.todo.service.implementation;

import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.assignment.todo.dto.TodoItemRequest;
import com.assignment.todo.model.TodoItem;
import com.assignment.todo.model.TodoList;
import com.assignment.todo.repository.TodoItemRepository;
import com.assignment.todo.repository.TodoListRepository;
import com.assignment.todo.service.TodoItemService;

@Service
public class TodoItemImpl implements TodoItemService {

    @Autowired
    TodoListRepository todoListRepository;

    @Autowired
    TodoItemRepository todoItemRepository;

    @Override
    public ResponseEntity<?> createTodoItem(Long todoListId, TodoItemRequest todoItemRequest) {

        Optional<TodoList> todoListOptional = todoListRepository.findById(todoListId);

        if (todoListOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("TodoList not found with ID: " + todoListId);
        }

        TodoList todoList = todoListOptional.get();

        TodoItem todoItem = TodoItem.builder()
                .description(todoItemRequest.getDescription())
                .completed(todoItemRequest.getCompleted())
                .title(todoItemRequest.getTitle())
                .dueDate(todoItemRequest.getDueDate())
                .build();

        todoList.getTodoItems().add(todoItem);

        todoListRepository.save(todoList);
        TodoItem savedTodoItem = todoList.getTodoItems().get(todoList.getTodoItems().size() - 1);

        URI loaction = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(todoItem.getId())
                .toUri();

        return ResponseEntity.created(loaction).body(savedTodoItem);
    }

    @Override
    public ResponseEntity<?> getTodoItemById(Long id) {

        Optional<TodoItem> todoItemOptional = todoItemRepository.findById(id);

        if (todoItemOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("TodoItem not found with ID: " + id);
        }

        TodoItem todoItem = todoItemOptional.get();

        return ResponseEntity.ok(todoItem);
    }

    @Override
    public ResponseEntity<?> getAllTodoItemsForTodoList(Long todoListId) {

        Optional<TodoList> todoListOptional = todoListRepository.findById(todoListId);

        if (todoListOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("TodoList not found with ID: " + todoListId);
        }

        TodoList todoList = todoListOptional.get();

        return ResponseEntity.ok(todoList.getTodoItems());
    }

    @Override
    public ResponseEntity<?> updateTodoItem(Long id, TodoItemRequest todoItemRequest) {

        Optional<TodoItem> todoItemOptional = todoItemRepository.findById(id);

        if (todoItemOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("TodoItem not found with ID: " + id);
        }

        TodoItem todoItem = todoItemOptional.get();

        todoItem.setDescription(todoItemRequest.getDescription());
        todoItem.setCompleted(todoItemRequest.getCompleted());
        todoItem.setTitle(todoItemRequest.getTitle());
        todoItem.setDueDate(todoItemRequest.getDueDate());

        todoItemRepository.save(todoItem);

        return ResponseEntity.ok(todoItem);
    }

    @Override
    public ResponseEntity<?> markTodoItemAsCompleted(Long id) {

        Optional<TodoItem> todoItemOptional = todoItemRepository.findById(id);

        if (todoItemOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("TodoItem not found with ID: " + id);
        }

        TodoItem todoItem = todoItemOptional.get();

        todoItem.setCompleted(true);

        todoItemRepository.save(todoItem);

        return ResponseEntity.ok(todoItem);
    }

    @Override
    public ResponseEntity<?> deleteTodoItem(Long toDoListID, Long id) {
            
        Optional<TodoList> todoListOptional = todoListRepository.findById(toDoListID);

        if (todoListOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("TodoList not found with ID: " + toDoListID);
        }

        TodoList todoList = todoListOptional.get();

        Optional<TodoItem> todoItemOptional = todoItemRepository.findById(id);

        if (todoItemOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("TodoItem not found with ID: " + id);
        }

        TodoItem todoItem = todoItemOptional.get();

        todoList.getTodoItems().remove(todoItem);

        todoListRepository.save(todoList);

        return ResponseEntity.ok(todoItem);
    }

}
