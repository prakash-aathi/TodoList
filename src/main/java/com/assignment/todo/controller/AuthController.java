package com.assignment.todo.controller;

import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.todo.dto.ERole;
import com.assignment.todo.dto.LoginRequest;
import com.assignment.todo.dto.UserRequest;
import com.assignment.todo.model.UserModel;
import com.assignment.todo.repository.UserRepository;
import com.assignment.todo.service.AuthenticationService;


@RestController
public class AuthController {
	
	@Autowired
	UserRepository userRepository;

	@Autowired
	AuthenticationService authenticationService;

	private static final String USER_NAME_NOT_FOUND_EXCEPTION = "User email not found in database";
	
	@PostMapping("/admin/signup")
	private ResponseEntity<?> saveAdmin(@RequestBody UserRequest userRequest) {
		
		return authenticationService.saveAdmin(userRequest);
	}
	
	@PostMapping("/user/signup")
	private ResponseEntity<?> saveUser(@RequestBody UserRequest userRequest) {

		return authenticationService.saveUser(userRequest);
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> authenticate(@RequestBody LoginRequest loginModel) {
		
		return authenticationService.authenticate(loginModel);			
	}
	
	
	@GetMapping("/demo")
	public String demo() {
		return "home page";
	}
	
	@GetMapping("/dashboard")
	public ResponseEntity<?> dashboard(Principal principal) {
		UserModel userModel = userRepository.findByEmail(principal.getName())
				.orElseThrow(() -> new UsernameNotFoundException(USER_NAME_NOT_FOUND_EXCEPTION));
		if (!userModel.getUserRole().equals(ERole.ADMIN)) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN)
					.body("You are not authorized to access this resource.");
		}
		return ResponseEntity.ok("dashboard page");
	}
	

    
}
