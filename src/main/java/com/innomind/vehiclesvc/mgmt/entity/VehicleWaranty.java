package com.innomind.vehiclesvc.mgmt.entity;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the vehicle_waranty database table.
 * 
 */
@Entity
@Table(name="vehicle_waranty")
@NamedQuery(name="VehicleWaranty.findAll", query="SELECT w FROM VehicleWaranty w")
public class VehicleWaranty implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
		
	@OneToOne	
	@JoinColumn(name="CHASSIS_NO")
	private Vehicle vehicle;

	@Lob
	@Column(name = "WARANTY_CARD")
	//@Type(type = "org.hibernate.type.BinaryType")
	private byte[] warantyCard;

	public VehicleWaranty() {
	}

	public int getId() {
		return id;
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

	public byte[] getWarantyCard() {
		return warantyCard;
	}

	public void setWarantyCard(byte[] warantyCard) {
		this.warantyCard = warantyCard;
	}

	


}