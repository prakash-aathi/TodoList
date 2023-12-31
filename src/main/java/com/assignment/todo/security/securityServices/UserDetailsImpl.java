package com.assignment.todo.security.securityServices;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.assignment.todo.model.UserModel;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class UserDetailsImpl implements UserDetails {

    private String email;
	private String userName;
	@JsonIgnore
	private String password;
    private Collection<? extends GrantedAuthority> authorities;
	
	
	public UserDetailsImpl(String email, String userName, String password, 
		     Collection<? extends GrantedAuthority> authorities) {
		super();
		this.email = email;
		this.userName = userName;
		this.password = password;
		this.authorities= authorities;
	}
	
	public static UserDetailsImpl build(UserModel userModel) {
		GrantedAuthority authority = new SimpleGrantedAuthority(userModel.getUserRole().name());
		return new UserDetailsImpl(
				userModel.getEmail(), 
				userModel.getUsername(),
				userModel.getPassword(), 
				Collections.singleton(authority));
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return email;
	}
	
	public String getName() {
		return userName;
	}
	
	
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}
    
}
