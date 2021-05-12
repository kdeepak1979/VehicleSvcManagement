package com.innomind.vehiclesvc.mgmt.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "dealer_detail")
@NamedQuery(name = "DealerDetail.findAll", query = "SELECT d FROM DealerDetail d")
public class DealerDetail implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="dealer_name")
	private String dealerName;
	
	@Column(name="address")
	private String address;
	
	@Column(name="phone")
	private String phone;
	
	@OneToOne
	@JoinColumn(name="user_name")
	private User dealer;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return dealerName;
	}

	public void setName(String name) {
		this.dealerName = name;
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

	public User getDealer() {
		return dealer;
	}

	public void setDealer(User dealer) {
		this.dealer = dealer;
	}
	
	

}
