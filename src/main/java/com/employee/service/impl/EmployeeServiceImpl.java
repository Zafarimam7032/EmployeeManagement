package com.employee.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.entity.Employee;
import com.employee.exception.BussinessException;
import com.employee.repository.EmployeeRepository;
import com.employee.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public List<Employee> getmployee() {
		logger.info("entered into getmployee");
		try {
			List<Employee> employees = employeeRepository.findAll();
			logger.debug("size of employees", employees.size());
			if (employees.size() > 0) {
				return employees;
			}
		} catch (Exception e) {
			logger.error("unable to find all employees", e.getMessage());
			throw new BussinessException("unable to find all employees", e.getMessage(), LocalDate.now());
		}
		logger.info("exit into getmployee");
		return null;
	}

	@Override
	public Employee getEmployeeByName(String name) {
		logger.info("entered into getEmployeeByName");
		try {
			Employee employees = employeeRepository.findByEmployeeName(name);
			logger.debug("employees", employees);
			if (employees != null) {
				return employees;
			}
		} catch (Exception e) {
			logger.error("unable to find getEmployeeByName", e.getMessage());
			throw new BussinessException("unable to find getEmployeeByName", e.getMessage(), LocalDate.now());
		}
		logger.info("exit into getEmployeeByName");
		return null;
	}

	@Override
	public boolean updateEmployee(String emailId, Employee employee) {
		logger.info("entered into updateEmployee");
		boolean check = false;
		try {
			if (emailId != null) {
				Employee employee2 = employeeRepository.findByEmailId(emailId);
				if (employee2 != null) {
					employee2.setEmployeeName(employee.getEmployeeName() != null ? employee.getEmployeeName()
							: employee2.getDepartmentName());
					employee2
							.setAddress(employee.getAddress() != null ? employee.getAddress() : employee2.getAddress());
					employee2.setDepartmentName(employee.getDepartmentName() != null ? employee.getDepartmentName()
							: employee2.getDepartmentName());
					employee2
							.setEmailId(employee.getEmailId() != null ? employee.getEmailId() : employee2.getEmailId());
					employee2.setPhoneNumber(
							employee.getPhoneNumber() != null ? employee.getPhoneNumber() : employee2.getPhoneNumber());
					employee2.setSalry(employee.getSalry() > 0 ? employee.getSalry() : employee2.getSalry());
					Employee savedEmployee = employeeRepository.save(employee2);
					if (savedEmployee != null) {
						check = true;
					}
				}
			}
		} catch (Exception e) {
			logger.error("unable to find updateEmployee", e.getMessage());
			throw new BussinessException("unable to find updateEmployee", e.getMessage(), LocalDate.now());
		}
		logger.info("exit into updateEmployee");
		return check;
	}

	@Override
	public boolean deleteEmployee(String employeeName) {
		logger.info("entered into getEmployeeByName");
		boolean check = false;
		try {
			employeeRepository.deleteByEmployeeName(employeeName);
			check = true;
		} catch (Exception e) {
			logger.error("unable to find getEmployeeByName", e.getMessage());
			throw new BussinessException("unable to find getEmployeeByName", e.getMessage(), LocalDate.now());
		}
		logger.info("exit into getEmployeeByName");
		return check;
	}

	@Override
	public void addEmployee(Employee employee) {
		logger.info("entered into addEmployee");
		try {
			employeeRepository.save(employee);
		} catch (Exception e) {
			logger.error("unable to addEmployee", e.getMessage());
			throw new BussinessException("unable to  addEmployee", e.getMessage(), LocalDate.now());
		}
		logger.info("exit into addEmployee");

	}
}
