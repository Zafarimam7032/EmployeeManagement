package com.employee.service.impl;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.employee.entity.User;


@Service
public class UserService implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private User user;

	public UserService() {

	}

	public UserService(User dbuser) {
		user=new User();
		this.user.setUserName(dbuser.getUserName());
		this.user.setPassword(dbuser.getPassword());
		this.user.setEmail(dbuser.getEmail());
		this.user.setAccountNonExpired(dbuser.getAccountNonExpired());
		this.user.setAccountNonLocked(dbuser.getAccountNonLocked());
		this.user.setCredentialsNonExpired(dbuser.getCredentialsNonExpired());
		this.user.setEnabled(dbuser.getEnabled());
		this.user.setUserRole(dbuser.getUserRole());
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		 List<GrantedAuthority> authorities = user.getUserRole().stream()
			        .map(role -> new SimpleGrantedAuthority(role))
			        .collect(Collectors.toList());
		 return authorities;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUserName();
	}

	@Override
	public boolean isAccountNonExpired() {
		return user.getAccountNonExpired();
	}

	@Override
	public boolean isAccountNonLocked() {
		return user.getAccountNonLocked();
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return user.getCredentialsNonExpired();
	}

	@Override
	public boolean isEnabled() {
		return user.getEnabled();
	}

}
