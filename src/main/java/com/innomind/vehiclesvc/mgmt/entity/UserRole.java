package com.innomind.vehiclesvc.mgmt.entity;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the user_role database table.
 * 
 */
@Entity
@Table(name="user_role")
@NamedQuery(name="UserRole.findAll", query="SELECT u FROM UserRole u")
public class UserRole implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
		
	@OneToOne(fetch=FetchType.EAGER)	
	@JoinColumn(name="id")
	private Role role;

	@ManyToOne
	@JoinColumn(name="user_name")
	private User user;

	public UserRole() {
	}

	/*public int getId() {
		return this.id;
	}*/

	

	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}	


}