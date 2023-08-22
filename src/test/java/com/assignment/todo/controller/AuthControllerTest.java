package com.assignment.todo.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.assignment.todo.dto.ERole;
import com.assignment.todo.dto.UserRequest;
import com.assignment.todo.dto.request.LoginRequest;
import com.assignment.todo.model.UserModel;
import com.assignment.todo.service.AuthenticationService;

@ExtendWith(MockitoExtension.class)
public class AuthControllerTest {

    @Mock
    private AuthenticationService authenticationService;

    @InjectMocks
    private AuthController authController;

    @Test
    public void auth_saveAdmin_success(TestInfo testInfo) {

        System.out.println("Test ran: " + testInfo.getDisplayName());

        // Arrange
        UserRequest userRequest = new UserRequest();
        userRequest.setEmail("admin@gmail.com");
        userRequest.setUsername("admin");
        userRequest.setPassword("123456");

        UserModel savedUser = new UserModel();
        savedUser.setEmail(userRequest.getEmail());
        savedUser.setUserRole(ERole.ADMIN);

        ResponseEntity<?> expectedResponse = ResponseEntity.ok(savedUser);

        // mocking the service
        doReturn(expectedResponse).when(authenticationService).saveAdmin(userRequest);

        // Act
        ResponseEntity<?> responseEntity = authController.saveAdmin(userRequest);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(savedUser, responseEntity.getBody());
        verify(authenticationService, times(1)).saveAdmin(userRequest);
    }

    @Test
    public void auth_saveAdmin_emailAlreadyInUse(TestInfo testInfo) {
        System.out.println("Test ran: " + testInfo.getDisplayName());

        // Arrange
        UserRequest userRequest = new UserRequest();
        userRequest.setEmail("admin@gmail.com");
        userRequest.setUsername("admin");
        userRequest.setPassword("123456");

        UserModel savedUser = new UserModel();
        savedUser.setEmail(userRequest.getEmail());
        savedUser.setUserRole(ERole.ADMIN);

        ResponseEntity<?> expectedResponse = ResponseEntity.badRequest().body("Error: Email is already in use!");

        // mocking the service
        doReturn(expectedResponse).when(authenticationService).saveAdmin(userRequest);

        // Act
        ResponseEntity<?> responseEntity = authController.saveAdmin(userRequest);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals("Error: Email is already in use!", responseEntity.getBody());
        verify(authenticationService, times(1)).saveAdmin(userRequest);

    }

    @Test
    public void auth_authenticate_successfulLogin(TestInfo testInfo) {
        System.out.println("Test ran: " + testInfo.getDisplayName());

        // Arrange
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("user@gmail.com");
        loginRequest.setPassword("123456");

        UserModel userModel = new UserModel();
        userModel.setEmail(loginRequest.getEmail());

        ResponseEntity<?> expectedResponse = ResponseEntity.ok("User logged in successfully");

        // mocking the service
        doReturn(expectedResponse).when(authenticationService).authenticate(loginRequest);

        // Act
        ResponseEntity<?> responseEntity = authController.authenticate(loginRequest);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("User logged in successfully", responseEntity.getBody());
        verify(authenticationService, times(1)).authenticate(loginRequest);
    }

    @Test
    public void auth_authenticate_invalidLogin(TestInfo testInfo) {
        System.out.println("Test ran: " + testInfo.getDisplayName());

        // Arrange
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("user@gmail.com");
        loginRequest.setPassword("903489");

        ResponseEntity<?> expectedResponse = ResponseEntity.badRequest().body("Invalid credentials");

        // mocking the service
        doReturn(expectedResponse).when(authenticationService).authenticate(loginRequest);

        // Act
        ResponseEntity<?> responseEntity = authController.authenticate(loginRequest);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals("Invalid credentials", responseEntity.getBody());
        verify(authenticationService, times(1)).authenticate(loginRequest);
    }

}