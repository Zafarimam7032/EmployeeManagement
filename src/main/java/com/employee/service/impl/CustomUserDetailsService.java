package com.employee.service.impl;

import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.employee.entity.User;
import com.employee.exception.BussinessException;
import com.employee.repository.UserRepository;



@Service
public class CustomUserDetailsService implements UserDetailsService {
	
	private static final Logger LOGGER=LoggerFactory.getLogger(CustomUserDetailsService.class);
		
	@Autowired
	private UserRepository repository;
	

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = repository.findByuserName(username);
		if(Objects.nonNull(user)) {
			return new UserService(user);
		}else {
			LOGGER.error("user not faund");
			throw new BussinessException("user not faund");
		}
	}

}
