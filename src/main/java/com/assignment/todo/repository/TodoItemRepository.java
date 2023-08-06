package com.assignment.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.assignment.todo.model.TodoItem;

public interface TodoItemRepository  extends JpaRepository<TodoItem, Long> {
    
}
