package com.employee.entity;

import java.util.List;
import java.util.Objects;



import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "User")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonIgnore
	private long id;
	@Column(name = "userName",nullable = false,unique = true)
	@Size(min = 4,max = 12,message = "userName range Should be 4 to 12")
	@JsonProperty("username")
	private String userName;
	@Column(name = "password",nullable = false)
	@Size(min = 8 ,max = 14 ,message = "password range should be 8 to 14")
	@JsonProperty("password")
	@JsonIgnore
	private String password;
	@Column(name = "email",nullable = false)
	@Size(min = 6 ,max = 14 ,message = "email range should be 6 to 14")
	@JsonProperty("email")
	private String email;
	@JsonProperty("accountNonExpired")
	private Boolean accountNonExpired;
	@JsonProperty("accountNonLocked")
	private Boolean accountNonLocked;
	@JsonProperty("credentialsNonExpired")
	private Boolean credentialsNonExpired;
	@JsonProperty("enabled")
	private Boolean enabled;
	@JsonProperty("userRole")
	@ElementCollection(targetClass=String.class,fetch = FetchType.EAGER)
	private List<String> userRole;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Boolean getAccountNonExpired() {
		return accountNonExpired;
	}
	public void setAccountNonExpired(Boolean accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}
	public Boolean getAccountNonLocked() {
		return accountNonLocked;
	}
	public void setAccountNonLocked(Boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}
	public Boolean getCredentialsNonExpired() {
		return credentialsNonExpired;
	}
	public void setCredentialsNonExpired(Boolean credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}
	public Boolean getEnabled() {
		return enabled;
	}
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	
	public List<String> getUserRole() {
		return userRole;
	}
	public void setUserRole(List<String> userRole) {
		this.userRole = userRole;
	}
	public User(long id, @Size(min = 4, max = 12, message = "userName range Should be 4 to 12") String userName,
			@Size(min = 8, max = 14, message = "password range should be 8 to 14") String password,
			@Size(min = 6, max = 14, message = "email range should be 6 to 14") String email, Boolean accountNonExpired,
			Boolean accountNonLocked, Boolean credentialsNonExpired, Boolean enabled,List<String> userRole) {
		super();
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.email = email;
		this.accountNonExpired = accountNonExpired;
		this.accountNonLocked = accountNonLocked;
		this.credentialsNonExpired = credentialsNonExpired;
		this.enabled = enabled;
		this.userRole=userRole;
	}
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + userName + ", password=" + password + ", email=" + email
				+ ", accountNonExpired=" + accountNonExpired + ", accountNonLocked=" + accountNonLocked
				+ ", credentialsNonExpired=" + credentialsNonExpired + ", enabled=" + enabled +", userRole=" + userRole + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(accountNonExpired, accountNonLocked, credentialsNonExpired, email, enabled, id, password,
				userName, userRole);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(accountNonExpired, other.accountNonExpired)
				&& Objects.equals(accountNonLocked, other.accountNonLocked)
				&& Objects.equals(credentialsNonExpired, other.credentialsNonExpired)
				&& Objects.equals(email, other.email) && Objects.equals(enabled, other.enabled) && id == other.id
				&& Objects.equals(password, other.password) && Objects.equals(userName, other.userName)
				&& Objects.equals(userRole, other.userRole);
	}
	
	
	
}
