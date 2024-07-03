package com.employee.controller;

import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.RestController;

import com.employee.api.UserApi;
import com.employee.exception.BussinessException;
import com.employee.model.JwtRequest;
import com.employee.model.JwtResponse;
import com.employee.service.impl.CustomUserDetailsService;
import com.employee.util.JwtUtil;


@RestController
public class JwtController implements UserApi {
	
	private static final Logger LOGGER=LoggerFactory.getLogger(JwtController.class);

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private CustomUserDetailsService customUserDetailsService;

	@Autowired
	private JwtUtil jwtUtil;

	@Override
	public JwtResponse generateToken(JwtRequest request) {
		UserDetails userDetails = null;
		JwtResponse jwtResponse = null;
		try {

			this.authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

		} catch (UsernameNotFoundException e) {
			LOGGER.error("Bad Credentials : {}",e.getMessage());
			throw new BussinessException("Bad Credentials",e);
		} catch (BadCredentialsException e) {
			throw new BussinessException("Bad Credentials",e);
		}
		try {
			userDetails = customUserDetailsService.loadUserByUsername(request.getUsername());
			if (Objects.nonNull(userDetails)) {
				String token = jwtUtil.generateToken(userDetails);
				if (Objects.nonNull(token)) {
					jwtResponse = new JwtResponse();
					jwtResponse.setJwtToken(token);
				}
			} else {
				throw new BussinessException("user deatials not found");
			}
		} catch (Exception e) {
			throw new BussinessException("unable to get user deatials");
		}
		return jwtResponse;
	}

}
