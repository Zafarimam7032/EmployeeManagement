package com.employee.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.employee.entity.User;
import com.employee.exception.BussinessException;
import com.employee.repository.UserRepository;

public class CustomUserDetailsServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private CustomUserDetailsService userDetailsService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testLoadUserByUsername_UserFound() {
        User mockUser = new User();
        mockUser.setUserName("testuser");
        mockUser.setPassword("testpassword");

        when(userRepository.findByuserName("testuser")).thenReturn(mockUser);

        UserDetails userDetails = userDetailsService.loadUserByUsername("testuser");

        assertEquals("testuser", userDetails.getUsername());
    }

    @Test
    public void testLoadUserByUsername_ExceptionInRepository() {
        when(userRepository.findByuserName(anyString())).thenThrow(new RuntimeException("user not faund"));

        RuntimeException exception = assertThrows(
        		RuntimeException.class,
                () -> userDetailsService.loadUserByUsername("testuser")
        );

        assertEquals("user not faund", exception.getMessage());
    }
}
