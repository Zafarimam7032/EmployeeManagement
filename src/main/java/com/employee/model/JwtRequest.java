package com.employee.model;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JwtRequest{
	@JsonProperty("username")
	private String username;
	@JsonProperty("password")
	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public int hashCode() {
		return Objects.hash(password, username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		JwtRequest other = (JwtRequest) obj;
		return Objects.equals(password, other.password) && Objects.equals(username, other.username);
	}

	@Override
	public String toString() {
		return "JwtRequest [username=" + username + ", password=" + password + "]";
	}

	public JwtRequest(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public JwtRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

}
