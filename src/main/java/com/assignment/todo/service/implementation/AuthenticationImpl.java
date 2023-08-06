package com.assignment.todo.service.implementation;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.assignment.todo.dto.ERole;
import com.assignment.todo.dto.LoginRequest;
import com.assignment.todo.dto.UserRequest;
import com.assignment.todo.model.UserModel;
import com.assignment.todo.repository.UserRepository;
import com.assignment.todo.security.securityConfig.JwtUtils;
import com.assignment.todo.security.securityServices.UserDetailsImpl;
import com.assignment.todo.service.AuthenticationService;

@Service
public class AuthenticationImpl implements AuthenticationService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    AuthenticationManager authenticationManager;
    
    @Autowired
	JwtUtils jwtUtils;

    private static final String USER_NAME_NOT_FOUND_EXCEPTION = "User email not found in database";


    @Override
    public ResponseEntity<?> saveUser(UserRequest userRequest) {

        if (userRepository.existsByEmail(userRequest.getEmail())) {
            return ResponseEntity.badRequest()
                    .body("Error: Email is already in use!");
        }
        UserModel user = UserModel.builder()
                .email(userRequest.getEmail())
                .username(userRequest.getUsername())
                .userRole(ERole.USER)
                .password(encoder.encode(userRequest.getPassword()))
                .build();

        return ResponseEntity.ok(userRepository.save(user));
    }

    @Override
    public ResponseEntity<?> saveAdmin(UserRequest userRequest) {

        if (userRepository.existsByEmail(userRequest.getEmail())) {
            return ResponseEntity.badRequest()
                    .body("Error: Email is already in use!");
        }
        UserModel user = UserModel.builder()
                .email(userRequest.getEmail())
                .userRole(ERole.ADMIN)
                .password(encoder.encode(userRequest.getPassword()))
                .build();

        return ResponseEntity.ok(userRepository.save(user));
    }

    @Override
    public ResponseEntity<?> authenticate(LoginRequest loginModel) {
        UserModel userModel = userRepository.findByEmail(loginModel.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException(USER_NAME_NOT_FOUND_EXCEPTION));

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginModel.getEmail(), loginModel.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        HashMap<String, Object> outResponse = new HashMap<>();
        outResponse.put("token", jwt);
        outResponse.put("type", "Bearer");
        outResponse.put("username", userDetails.getUsername());
        outResponse.put("email", userModel.getEmail());
        outResponse.put("roles", roles);

        return ResponseEntity.ok(outResponse);
    }

}
