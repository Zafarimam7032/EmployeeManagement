package com.employee.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.employee.entity.Employee;
import com.employee.service.EmployeeService;

public class EmployeeControllerTest {

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private EmployeeController employeeController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetEmployees_Success() {
        List<Employee> mockEmployees = new ArrayList<>();
        mockEmployees.add(new Employee("John Doe", "IT", "john.doe@example.com", "1234567890", 5000, null));
        mockEmployees.add(new Employee("Jane Smith", "HR", "jane.smith@example.com", "9876543210", 6000, null));

        when(employeeService.getmployee()).thenReturn(mockEmployees);

        ResponseEntity<List<Employee>> response = employeeController.getmployeeS();

        assertEquals(HttpStatus.FOUND, response.getStatusCode());
        assertEquals(2, response.getBody().size());
    }

    @Test
    public void testGetEmployees_Exception() {
        when(employeeService.getmployee()).thenThrow(new RuntimeException("Failed to fetch employees"));

        ResponseEntity<List<Employee>> response = employeeController.getmployeeS();

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNull(response.getBody());
    }

}
