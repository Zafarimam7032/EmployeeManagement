package com.employee.service;

import java.util.List;

import com.employee.entity.Employee;

public interface EmployeeService {

	public List<Employee> getmployee();

	public Employee getEmployeeByName(String name);

	public boolean updateEmployee(String empid, Employee employee);

	public boolean deleteEmployee(String empid);

	public void addEmployee(Employee employee);

}
