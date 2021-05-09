package com.innomind.vehiclesvc.mgmt.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the sales database table.
 * 
 */
@Entity
@Table(name="sales")
@NamedQuery(name="Sale.findAll", query="SELECT s FROM Sale s")
public class Sale implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="INVOICE_ID")
	private String invoiceId;

	@Temporal(TemporalType.DATE)
	@Column(name="INVOICE_DATE")
	private Date invoiceDate;

	@Column(name="INVENTORY_ID")
	private String inventoryId;
	
	private float price;

	
	//uni-directional many-to-one association to Customer
	@ManyToOne
	@JoinColumn(name="customer_id")
	private Customer customer;

	

	public Sale() {
	}	
	
	public String getInventoryId() {
		return this.inventoryId;
	}

	public void setInventoryId(String inventoryId) {
		this.inventoryId = inventoryId;
	}

	public Date getInvoiceDate() {
		return this.invoiceDate;
	}

	public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	public String getInvoiceId() {
		return this.invoiceId;
	}

	public void setInvoiceId(String invoiceId) {
		this.invoiceId = invoiceId;
	}


	public float getPrice() {
		return this.price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}



}