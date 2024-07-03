package com.employee.model;

public enum UserRoleCheck {

	ADMIN("ADMIN"),
	USER("USER"),
	SELLER("SELLER");
	
	String checkRole;

	public String getCheckRole() {
		return checkRole;
	}

	public void setCheckRole(String checkRole) {
		this.checkRole = checkRole;
	}

	private UserRoleCheck(String checkRole) {
		this.checkRole = checkRole;
	}
	
	
}
