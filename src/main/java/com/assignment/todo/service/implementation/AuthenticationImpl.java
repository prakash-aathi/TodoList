package com.assignment.todo.service.implementation;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.assignment.todo.dto.ERole;
import com.assignment.todo.dto.UserRequest;
import com.assignment.todo.dto.UserResponse;
import com.assignment.todo.dto.request.LoginRequest;
import com.assignment.todo.mapstuct.mappers.MapperInterface;
import com.assignment.todo.model.UserModel;
import com.assignment.todo.repository.UserRepository;
import com.assignment.todo.service.AuthenticationService;

@Service
public class AuthenticationImpl implements AuthenticationService {

        @Autowired
        UserRepository userRepository;

        private static final String USER_NAME_NOT_FOUND_EXCEPTION = "User email not found in database";

        @Override
        public UserResponse saveUser(UserRequest userRequest) {

                if (userRepository.existsByEmail(userRequest.getEmail())) {
                        throw new RuntimeException("Error: Email is already in use!");
                }

                UserModel user = MapperInterface.INSTANCE.UserRequestToUserModel(userRequest);
                user.setUserRole(ERole.USER);

                UserResponse userResponse = MapperInterface.INSTANCE
                                .UserModelToUserResponse(userRepository.save(user));

                return userResponse;
        }

        @Override
        public UserResponse saveAdmin(UserRequest userRequest) {

                if (userRepository.existsByEmail(userRequest.getEmail())) {
                        throw new RuntimeException("Error: Email is already in use!");
                }
                UserModel user = MapperInterface.INSTANCE.UserRequestToUserModel(userRequest);
                user.setUserRole(ERole.ADMIN);

                UserResponse userResponse = MapperInterface.INSTANCE
                                .UserModelToUserResponse(userRepository.save(user));

                return userResponse;
        }

        @Override
        public UserResponse authenticate(LoginRequest loginModel) {

                UserModel user = userRepository.findByEmail(loginModel.getEmail()).get();
                if (user == null) {
                        throw new RuntimeException(USER_NAME_NOT_FOUND_EXCEPTION);
                }

                if (!user.getPassword().equals(loginModel.getPassword())) {
                        throw new RuntimeException("Invalid password");
                }

                UserResponse userResponse = MapperInterface.INSTANCE.UserModelToUserResponse(user);
                return userResponse;

        }

}
