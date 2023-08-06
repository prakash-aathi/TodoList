package com.assignment.todo.service;

import org.springframework.http.ResponseEntity;

import com.assignment.todo.dto.LoginRequest;
import com.assignment.todo.dto.UserRequest;

public interface AuthenticationService {

    ResponseEntity<?> saveUser(UserRequest userRequest);

    ResponseEntity<?> saveAdmin(UserRequest userRequest);

    ResponseEntity<?> authenticate(LoginRequest loginModel);
    
}
