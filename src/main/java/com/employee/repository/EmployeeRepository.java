package com.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.employee.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	
	public Employee  findByEmployeeName(String employeeName);
	
	public void deleteByEmployeeName(String employeeName);
	
	public Employee  findByEmailId(String emailId);

}
