package com.innomind.vehiclesvc.mgmt.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the customer database table.
 * 
 */
@Entity
@Table(name="service_record")
@NamedQuery(name="ServiceRecord.findAll", query="SELECT r FROM ServiceRecord r")
public class ServiceRecord implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@ManyToOne
	@JoinColumn(name="CHASSIS_NO")
	private Vehicle vehicle;
	
	@Column(name="SERVICE_DATE")
	@Temporal(TemporalType.DATE)
	private Date serviceDate;
	
	@Column(name="SERVICE_CENTRE")
	private String serviceCentre;
	
	@Column(name="KM_RUN")
	private int kmRum;
	
		

	public ServiceRecord() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public Date getServiceDate() {
		return serviceDate;
	}

	public void setServiceDate(Date serviceDate) {
		this.serviceDate = serviceDate;
	}

	public String getServiceCentre() {
		return serviceCentre;
	}

	public void setServiceCentre(String serviceCentre) {
		this.serviceCentre = serviceCentre;
	}

	public int getKmRum() {
		return kmRum;
	}

	public void setKmRum(int kmRum) {
		this.kmRum = kmRum;
	}
	
	

}