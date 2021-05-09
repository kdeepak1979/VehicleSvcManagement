package com.innomind.vehiclesvc.mgmt.dto;

import java.util.Date;

public class CustomerDTO {

	private int customerId;
	private String firstName;
	private String middleName;
	private String lastName;
	private Date dob;
	private int aadharNum;
	private String panNum;
	private String emailId;
	private int phone;

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public int getAadharNum() {
		return aadharNum;
	}

	public void setAadharNum(int aadharNum) {
		this.aadharNum = aadharNum;
	}

	public String getPanNum() {
		return panNum;
	}

	public void setPanNum(String panNum) {
		this.panNum = panNum;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public int getPhone() {
		return phone;
	}

	public void setPhone(int phone) {
		this.phone = phone;
	}

	
}
