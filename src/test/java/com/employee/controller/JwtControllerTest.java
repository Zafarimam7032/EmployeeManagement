package com.employee.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.employee.exception.BussinessException;
import com.employee.model.JwtRequest;
import com.employee.model.JwtResponse;
import com.employee.service.impl.CustomUserDetailsService;
import com.employee.util.JwtUtil;

public class JwtControllerTest {

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private CustomUserDetailsService customUserDetailsService;

    @Mock
    private JwtUtil jwtUtil;

    @InjectMocks
    private JwtController jwtController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

//    @Test
//    public void testGenerateToken_Success() {
//        JwtRequest jwtRequest = new JwtRequest("username", "password");
//        UserDetails userDetails = new User("username", "password", new ArrayList<>());
//        String mockToken = "mockToken";
//
//        doNothing().when(authenticationManager).authenticate(
//                new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword()));
//
//        when(customUserDetailsService.loadUserByUsername("username")).thenReturn(userDetails);
//
//        // Mock JWT token generation
//        when(jwtUtil.generateToken(userDetails)).thenReturn(mockToken);
//
//        // Call controller method
//        JwtResponse jwtResponse = jwtController.generateToken(jwtRequest);
//
//        assertNotNull(jwtResponse);
//        assertEquals(mockToken, jwtResponse.getJwtToken());
//    }
//
//    @Test
//    public void testGenerateToken_BadCredentialsException() {
//        JwtRequest jwtRequest = new JwtRequest("username", "wrongpassword");
//
//        doThrow(new BadCredentialsException("Bad credentials")).when(authenticationManager).authenticate(
//                new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword()));
//
//        assertThrows(BussinessException.class, () -> jwtController.generateToken(jwtRequest));
//    }

    @Test
    public void testGenerateToken_UsernameNotFoundException() {
        JwtRequest jwtRequest = new JwtRequest("nonexistinguser", "password");

        doThrow(new UsernameNotFoundException("User not found")).when(authenticationManager).authenticate(
                new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword()));

        assertThrows(BussinessException.class, () -> jwtController.generateToken(jwtRequest));
    }

}
