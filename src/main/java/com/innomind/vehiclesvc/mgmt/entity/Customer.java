package com.innomind.vehiclesvc.mgmt.entity;

import java.io.Serializable;
import javax.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the customer database table.
 * 
 */
@Entity
@NamedQuery(name="Customer.findAll", query="SELECT c FROM Customer c")
public class Customer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Column(name="FIRST_NAME")
	private String firstName;

	@Column(name="MIDDLE_NAME")
	private String middleName;

	@Column(name="LAST_NAME")
	private String lastName;	
	
	@Temporal(TemporalType.DATE)
	private Date dob;
	
	@Column(name="AADHAR_NO")
	private int aadharNo;
	
	@Column(name="PAN_NO")
	private String panNo;

	@Column(name="PHONE")
	private int phone;
	
	@Column(name="EMAIL_ID")
	private String emailID;
	
	@OneToMany(fetch=FetchType.LAZY,mappedBy="customer")
	private List<Sale> sales = new ArrayList<>();
	

	public Customer() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAadharNo() {
		return this.aadharNo;
	}

	public void setAadharNo(int aadharNo) {
		this.aadharNo = aadharNo;
	}

	public Date getDob() {
		return this.dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMiddleName() {
		return this.middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getPanNo() {
		return this.panNo;
	}

	public void setPanNo(String panNo) {
		this.panNo = panNo;
	}

	public int getPhone() {
		return this.phone;
	}

	public void setPhone(int phone) {
		this.phone = phone;
	}

	public String getEmailID() {
		return emailID;
	}

	public void setEmailID(String emailID) {
		this.emailID = emailID;
	}	
	
	

}