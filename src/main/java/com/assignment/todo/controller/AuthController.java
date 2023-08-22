package com.assignment.todo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.assignment.todo.dto.UserRequest;
import com.assignment.todo.dto.UserResponse;
import com.assignment.todo.dto.request.LoginRequest;
import com.assignment.todo.repository.UserRepository;
import com.assignment.todo.service.AuthenticationService;

@Controller
public class AuthController {

	@Autowired
	AuthenticationService authenticationService;

	@Autowired
	UserRepository userRepository;

	@QueryMapping
	public UserResponse saveAdmin(@Argument String email, @Argument String username, @Argument String password) {
		UserRequest userRequest = new UserRequest(email, username, password);
		return authenticationService.saveAdmin(userRequest);
	}

	@QueryMapping
	public UserResponse saveUser(@Argument String email, @Argument String username, @Argument String password) {
		UserRequest userRequest = new UserRequest(email, username, password);
		return authenticationService.saveUser(userRequest);
	}

	@QueryMapping
	public UserResponse authenticate(@Argument String email, @Argument String password) {
		LoginRequest loginModel = new LoginRequest(email, password);
		return authenticationService.authenticate(loginModel);
	}

}
