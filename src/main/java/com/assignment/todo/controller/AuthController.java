package com.assignment.todo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.todo.dto.LoginRequest;
import com.assignment.todo.dto.UserRequest;
import com.assignment.todo.service.AuthenticationService;


@RestController
public class AuthController {
	
	@Autowired
	AuthenticationService authenticationService;
	
	@PostMapping("/admin/signup")
	public ResponseEntity<?> saveAdmin(@RequestBody UserRequest userRequest) {
		
		return authenticationService.saveAdmin(userRequest);
	}
	
	@PostMapping("/user/signup")
	public ResponseEntity<?> saveUser(@RequestBody UserRequest userRequest) {

		return authenticationService.saveUser(userRequest);
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> authenticate(@RequestBody LoginRequest loginModel) {
		
		return authenticationService.authenticate(loginModel);			
	}
	
	
    
}
