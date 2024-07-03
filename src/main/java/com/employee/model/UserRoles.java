package com.employee.model;

import java.util.Objects;

public class UserRoles {
	
	private Integer id;
	private String userrole;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUserrole() {
		return userrole;
	}
	public void setUserrole(String userrole) {
		this.userrole = userrole;
	}
	@Override
	public int hashCode() {
		return Objects.hash(id, userrole);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserRoles other = (UserRoles) obj;
		return Objects.equals(id, other.id) && Objects.equals(userrole, other.userrole);
	}
	public UserRoles(Integer id, String userrole) {
		super();
		this.id = id;
		this.userrole = userrole;
	}
	public UserRoles() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "UserRoles [id=" + id + ", userrole=" + userrole + "]";
	}
	
	

}
