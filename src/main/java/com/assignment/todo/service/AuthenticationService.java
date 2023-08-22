package com.assignment.todo.service;

import org.springframework.http.ResponseEntity;

import com.assignment.todo.dto.UserRequest;
import com.assignment.todo.dto.UserResponse;
import com.assignment.todo.dto.request.LoginRequest;

public interface AuthenticationService {

    UserResponse saveUser(UserRequest userRequest);

    UserResponse saveAdmin(UserRequest userRequest);

    UserResponse authenticate(LoginRequest loginModel);
    
}
