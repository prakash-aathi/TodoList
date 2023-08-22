package com.assignment.todo.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.assignment.todo.dto.ERole;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserModel {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotNull(message = "email not should be null")
	@Pattern(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$", message = "should be in email format")
	private String email;

	@NotNull(message = "username not should be null" )
	@Pattern(regexp = "^[A-Za-z ]{3,30}$", message = "Name must only be alphabets and whitespaces from 3 to 30 characters")
	private String username;

	@NotNull(message = "password not should be null")
	private String password;

	@NotNull(message = "role not should be null")
	@Enumerated(EnumType.STRING)
	private ERole userRole;


}
