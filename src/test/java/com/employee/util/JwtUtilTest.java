package com.employee.util;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.userdetails.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;

public class JwtUtilTest {

    private JwtUtil jwtUtil;

    private final String secret = "java";
    private final String username = "testUser";

    @BeforeEach
    public void setup() {
        jwtUtil = new JwtUtil();
    }

    @Test
    public void testGenerateToken() {
        UserDetails userDetails = new User(username, "password", new ArrayList<>());
        String token = jwtUtil.generateToken(userDetails);

        assertNotNull(token);
        assertTrue(token.length() > 0);
    }

    @Test
    public void testGetUsernameFromToken() {
        String token = jwtUtil.generateToken(new User(username, "password", new ArrayList<>()));
        String extractedUsername = jwtUtil.getUsernameFromToken(token);

        assertEquals(username, extractedUsername);
    }

    @Test
    public void testTokenExpiration() {
        String token = jwtUtil.generateToken(new User(username, "password", new ArrayList<>()));
        Date expirationDate = jwtUtil.getExpirationDateFromToken(token);

        assertFalse(jwtUtil.isTokenExpired(token));
        assertTrue(expirationDate.after(new Date()));
    }

    @Test
    public void testValidateToken() {
        UserDetails userDetails = new User(username, "password", new ArrayList<>());
        String token = jwtUtil.generateToken(userDetails);

        assertTrue(jwtUtil.validateToken(token, userDetails));
    }
}
