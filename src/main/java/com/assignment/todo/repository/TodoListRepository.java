package com.assignment.todo.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.assignment.todo.model.TodoItem;
import com.assignment.todo.model.TodoList;

public interface TodoListRepository extends JpaRepository<TodoList, Long> {

    List<TodoList> findByUserEmail(String name);

    Optional<TodoItem> findTodoItemById(Long id);
    
}
