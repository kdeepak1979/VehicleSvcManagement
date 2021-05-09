package com.innomind.vehiclesvc.mgmt.dto;

public class Dealer {

	private String dealerId;
	private String dealerName;
	private String address;
	private String phone;
	private String password;
	
	
	public String getDealerId() {
		return dealerId;
	}
	
	public void setDealerId(String dealerId) {
		this.dealerId = dealerId;
	}
	
	public String getDealerName() {
		return dealerName;
	}
	
	public void setDealerName(String dealerName) {
		this.dealerName = dealerName;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Dealer [dealerId=" + dealerId + ", dealerName=" + dealerName + ", address=" + address + ", phone="
				+ phone + "]";
	}
	
	
	
}
