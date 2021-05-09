package com.innomind.vehiclesvc.mgmt.entity;

import java.io.Serializable;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

/**
 * The persistent class for the user_detail database table.
 * 
 */
@Entity
@Table(name = "app_user")
@NamedQuery(name = "User.findAll", query = "SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="user_name")
	private String userName;

	@Column(name = "password")
	private String password;

	@Column(name = "is_locked")
	private boolean isLocked;

	@Column(name = "is_expired")
	private boolean isExpired;

	@Column(name = "is_enabled")
	private boolean isEnabled;

	@OneToMany(mappedBy="user", fetch=FetchType.EAGER)
	private List<UserRole> userRoles = new ArrayList<>();

	public User() {
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



	public boolean isLocked() {
		return isLocked;
	}



	public void setLocked(boolean isLocked) {
		this.isLocked = isLocked;
	}



	public boolean isExpired() {
		return isExpired;
	}



	public void setExpired(boolean isExpired) {
		this.isExpired = isExpired;
	}



	public boolean isEnabled() {
		return isEnabled;
	}



	public void setEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}



	public List<UserRole> getUserRoles() {
		return this.userRoles;
	}

	public void setUserRoles(List<UserRole> userRoles) {
		this.userRoles = userRoles;
	}

	public UserRole addUserRole(UserRole userRole) {
		getUserRoles().add(userRole);
		userRole.setUser(this);

		return userRole;
	}

	public UserRole removeUserRole(UserRole userRole) {
		getUserRoles().remove(userRole);
		userRole.setUser(null);

		return userRole;
	}

}