package com.assignment.todo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.assignment.todo.model.UserModel;


public interface UserRepository extends JpaRepository<UserModel, String> {
    
    Boolean existsByEmail(String email);
	Optional<UserModel> findByUsername(String userName);
	Optional<UserModel> findByEmail(String email);
	
}
