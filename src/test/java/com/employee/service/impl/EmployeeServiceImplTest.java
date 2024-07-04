package com.employee.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.employee.entity.Employee;
import com.employee.exception.BussinessException;
import com.employee.repository.EmployeeRepository;

public class EmployeeServiceImplTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetEmployee_Success() {
        List<Employee> mockEmployees = new ArrayList<>();
        Employee employee1 = new Employee("John Doe", "IT", "john.doe@example.com", "1234567890", 5000, null);
        Employee employee2 = new Employee("Jane Smith", "HR", "jane.smith@example.com", "9876543210", 6000, null);
        mockEmployees.add(employee1);
        mockEmployees.add(employee2);

        when(employeeRepository.findAll()).thenReturn(mockEmployees);

        List<Employee> result = employeeService.getmployee();

        verify(employeeRepository, times(1)).findAll();
        assertEquals(2, result.size());
    }

    @Test
    public void testGetEmployee_EmptyList() {
        when(employeeRepository.findAll()).thenReturn(new ArrayList<>());

        List<Employee> result = employeeService.getmployee();

        verify(employeeRepository, times(1)).findAll();
        assertNull(result);
    }

    @Test
    public void testGetEmployeeByName_Success() {
        Employee employee = new Employee("John Doe", "IT", "john.doe@example.com", "1234567890", 5000, null);

        when(employeeRepository.findByEmployeeName("John Doe")).thenReturn(employee);

        Employee result = employeeService.getEmployeeByName("John Doe");

        verify(employeeRepository, times(1)).findByEmployeeName("John Doe");
        assertNotNull(result);
        assertEquals("John Doe", result.getEmployeeName());
    }

    @Test
    public void testGetEmployeeByName_NotFound() {
        when(employeeRepository.findByEmployeeName(any())).thenReturn(null);

        Employee result = employeeService.getEmployeeByName("Nonexistent");

        verify(employeeRepository, times(1)).findByEmployeeName("Nonexistent");
        assertNull(result);
    }

    @Test
    public void testUpdateEmployee_Success() {
        Employee existingEmployee = new Employee("John Doe", "IT", "john.doe@example.com", "1234567890", 5000, null);
        Employee updatedEmployee = new Employee("John Doe", "IT", "john.doe@example.com", "1234567890", 6000, null);

        when(employeeRepository.findByEmailId("john.doe@example.com")).thenReturn(existingEmployee);
        when(employeeRepository.save(any(Employee.class))).thenReturn(updatedEmployee);

        boolean result = employeeService.updateEmployee("john.doe@example.com", updatedEmployee);

        verify(employeeRepository, times(1)).findByEmailId("john.doe@example.com");
        verify(employeeRepository, times(1)).save(any(Employee.class));
        assertTrue(result);
    }

    @Test
    public void testUpdateEmployee_NotFound() {
        when(employeeRepository.findByEmailId(any())).thenReturn(null);

        boolean result = employeeService.updateEmployee("nonexistent@example.com", new Employee());

        verify(employeeRepository, times(1)).findByEmailId("nonexistent@example.com");
        assertFalse(result);
    }

    @Test
    public void testDeleteEmployee_Success() {
        doNothing().when(employeeRepository).deleteByEmployeeName("John Doe");

        boolean result = employeeService.deleteEmployee("John Doe");

        verify(employeeRepository, times(1)).deleteByEmployeeName("John Doe");
        assertTrue(result);
    }

    @Test
    public void testAddEmployee_Success() {
        Employee employee = new Employee("John Doe", "IT", "john.doe@example.com", "1234567890", 5000, null);

        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);

        assertDoesNotThrow(() -> employeeService.addEmployee(employee));

        verify(employeeRepository, times(1)).save(any(Employee.class));
    }

    @Test
    public void testAddEmployee_Exception() {
        when(employeeRepository.save(any(Employee.class))).thenThrow(new RuntimeException("Failed to save"));

        BussinessException exception = assertThrows(BussinessException.class,
                () -> employeeService.addEmployee(new Employee()));

        verify(employeeRepository, times(1)).save(any(Employee.class));
        assertEquals("unable to  addEmployee", exception.getMessage());
    }
}
