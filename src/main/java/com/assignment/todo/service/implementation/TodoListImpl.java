package com.assignment.todo.service.implementation;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.assignment.todo.dto.request.TodoListRequest;
import com.assignment.todo.dto.response.TodoListResponse;
import com.assignment.todo.mapstuct.mappers.MapperInterface;
import com.assignment.todo.model.TodoList;
import com.assignment.todo.repository.TodoListRepository;
import com.assignment.todo.service.TodoListService;

@Service
public class TodoListImpl implements TodoListService {

    @Autowired
    TodoListRepository todoListRepository;

    @Override
    public ResponseEntity<?> createTodoList(TodoListRequest todoListRequest, String UserEmail) {
        TodoList todoList = TodoList.builder()
                .name(todoListRequest.getName())
                .userEmail(UserEmail)
                .build();

        TodoList savedTodoList = todoListRepository.save(todoList);

        URI loaction = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedTodoList.getId())
                .toUri();

        TodoListResponse response = MapperInterface.INSTANCE
                .TodoModelToTodoResponse(savedTodoList);

        return ResponseEntity.created(loaction).body(response);
    }

    @Override
    public ResponseEntity<?> getTodoListById(Long id) {
        Optional<TodoList> todoListOptional = todoListRepository.findById(id);

        if (todoListOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("TodoList not found with ID: " + id);
        }

        TodoListResponse response = MapperInterface.INSTANCE
                .TodoModelToTodoResponse(todoListOptional.get());

        return ResponseEntity.ok(response);
    }

    @Override
    public List<TodoList> getAllTodoListsForUser(String name) {
        return todoListRepository.findByUserEmail(name);
    }

    @Override
    public ResponseEntity<?> updateTodoList(Long id, TodoListRequest todoListRequest) {
        Optional<TodoList> todoListOptional = todoListRepository.findById(id);

        if (todoListOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("TodoList not found with ID: " + id);
        }

        TodoList todoList = todoListOptional.get();
        todoList.setName(todoListRequest.getName());

        TodoList savedTodoList = todoListRepository.save(todoList);

        TodoListResponse response = MapperInterface.INSTANCE
                    .TodoModelToTodoResponse(savedTodoList);

        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<?> deleteTodoList(Long id) {
        Optional<TodoList> todoListOptional = todoListRepository.findById(id);

        if (todoListOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("TodoList not found with ID: " + id);
        }

        todoListRepository.deleteById(id);

        return ResponseEntity.ok("TodoList deleted successfully");
    }

}
