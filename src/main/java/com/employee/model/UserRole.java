package com.employee.model;

public enum UserRole {

	ROLE_ADMIN("ROLE_ADMIN"),
	ROLE_USER("ROLE_USER"),
	ROLE_SELLER("ROLE_SELLER");
	
	private String userRole;

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	private UserRole(String userRole) {
		this.userRole = userRole;
	}
	
	
}
