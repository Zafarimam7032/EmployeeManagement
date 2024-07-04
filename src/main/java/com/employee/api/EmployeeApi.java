package com.employee.api;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.employee.entity.Employee;


public interface EmployeeApi {
	
	@GetMapping(path = "/get/all")
	public  ResponseEntity<List<Employee>> getmployeeS();

	@GetMapping(path = "/get/{name}")
	public ResponseEntity<Employee> getEmployeeByName(@PathVariable("name") String name);

	@PutMapping(path = "/update/{empid}")
	public ResponseEntity<Object> updateEmployee(@PathVariable("empid") String empid,@RequestBody Employee employee);

	@DeleteMapping(path = "/delete/{empid}")
	public ResponseEntity<Object> deleteEmployee(String empid);

	@PostMapping(path = "/add")
	public ResponseEntity<Object> addEmployee(@RequestBody Employee employee);

}
