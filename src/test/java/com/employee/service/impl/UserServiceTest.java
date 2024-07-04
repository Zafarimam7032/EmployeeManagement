package com.employee.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.GrantedAuthority;

import com.employee.entity.User;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private User mockUser;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAuthorities_SingleRole() {
       String role ="ROLE_USER";
        when(mockUser.getUserRole()).thenReturn(Collections.singletonList(role));

        List<? extends GrantedAuthority> authorities = (List<? extends GrantedAuthority>) userService.getAuthorities();

        assertEquals(1, authorities.size());
        assertEquals("ROLE_USER", authorities.get(0).getAuthority());
    }

    @Test
    public void testGetAuthorities_MultipleRoles() {
    	String role1 ="ROLE_USER";
    	String role2 ="ROLE_ADMIN";
        when(mockUser.getUserRole()).thenReturn(Arrays.asList(role1, role2));

        List<? extends GrantedAuthority> authorities = (List<? extends GrantedAuthority>) userService.getAuthorities();

        assertEquals(2, authorities.size());
        assertEquals("ROLE_USER", authorities.get(0).getAuthority());
        assertEquals("ROLE_ADMIN", authorities.get(1).getAuthority());
    }

    @Test
    public void testGetPassword() {
        when(mockUser.getPassword()).thenReturn("password");

        String password = userService.getPassword();

        assertEquals("password", password);
    }

    @Test
    public void testGetUsername() {
        when(mockUser.getUserName()).thenReturn("john.doe");

        String username = userService.getUsername();

        assertEquals("john.doe", username);
    }

}
