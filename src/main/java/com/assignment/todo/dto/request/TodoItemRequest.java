package com.assignment.todo.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor 
public class TodoItemRequest {
    
    private String title;
    private String description;
    private Boolean completed;
    private String dueDate;
    
}
