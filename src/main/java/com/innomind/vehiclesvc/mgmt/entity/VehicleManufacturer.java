package com.innomind.vehiclesvc.mgmt.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;


/**
 * The persistent class for the vehicle_manufacturer database table.
 * 
 */
@Entity
@Table(name="vehicle_manufacturer")
@NamedQuery(name="VehicleManufacturer.findAll", query="SELECT v FROM VehicleManufacturer v")
public class VehicleManufacturer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private String name;
	
	@OneToMany(mappedBy="vehicleManufacturer")
	private List<Vehicle> vehicles = new ArrayList<>();

	public VehicleManufacturer() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "VehicleManufacturer [id=" + id + ", name=" + name + "]";
	}
	
	

}