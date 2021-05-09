package com.innomind.vehiclesvc.mgmt.entity;

import java.io.Serializable;


import javax.persistence.*;


/**
 * The persistent class for the role database table.
 * 
 */
@Entity
@NamedQuery(name="Role.findAll", query="SELECT r FROM Role r")
public class Role implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Column(name="name")
	private String name;
	
	
	public Role() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRoleName() {
		return this.name;
	}

	public void setRoleName(String roleName) {
		this.name = roleName;
	}	

}