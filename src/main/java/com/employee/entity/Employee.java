package com.employee.entity;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "employee")
public class Employee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "employeeName")
	private String employeeName;
	@Column(name = "address")
	private String address;
	@Column(name = "email")
	private String emailId;
	@Column(name ="phoneNumber")
	private String phoneNumber;
	@Column(name ="salry")
	private double salry;
	@Column(name ="department")
	private String departmentName;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public double getSalry() {
		return salry;
	}
	public void setSalry(double salry) {
		this.salry = salry;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(address, departmentName, emailId, employeeName, id, phoneNumber, salry);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		return Objects.equals(address, other.address) && Objects.equals(departmentName, other.departmentName)
				&& Objects.equals(emailId, other.emailId) && Objects.equals(employeeName, other.employeeName)
				&& Objects.equals(id, other.id) && Objects.equals(phoneNumber, other.phoneNumber)
				&& Double.doubleToLongBits(salry) == Double.doubleToLongBits(other.salry);
	}

	@Override
	public String toString() {
		return "Employee [employeeName=" + employeeName + ", address=" + address + ", emailId=" + emailId
				+ ", phoneNumber=" + phoneNumber + ", salry=" + salry + ", departmentName=" + departmentName + "]";
	}
	public Employee( String employeeName, String address, String emailId, String phoneNumber, double salry,
			String departmentName) {
		super();
		
		this.employeeName = employeeName;
		this.address = address;
		this.emailId = emailId;
		this.phoneNumber = phoneNumber;
		this.salry = salry;
		this.departmentName = departmentName;
	}
	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	 

}
