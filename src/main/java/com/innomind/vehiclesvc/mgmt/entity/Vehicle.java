package com.innomind.vehiclesvc.mgmt.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;


/**
 * The persistent class for the vehicle database table.
 * 
 */
@Entity
@NamedQuery(name="Vehicle.findAll", query="SELECT v FROM Vehicle v")
public class Vehicle implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="CHASSIS_NO")
	private String chasisNum;
	
	@Column(name="MODEL_ID")
	private String modelId;

	@Column(name="MODEL_NAME")
	private String modelName;
	
	@ManyToOne
	@JoinColumn(name="MANUFACTURER_ID")
	private VehicleManufacturer vehicleManufacturer;

	@Column(name="SEATING_CAPACITY")
	private int seatingCapacity;

	@Column(name="FUEL_TYPE")
	private String fuelType;
	
	@Column(name="CLASS")
	private String vehicleClass;	

	@Column(name="ENGINE_NO")
	private String engineNum;
	
	@OneToOne(fetch=FetchType.LAZY,mappedBy="vehicle")
	private VehicleWaranty waranty;	
	
	@OneToOne(fetch=FetchType.LAZY,mappedBy="vehicle")
	private Sale sale;

	public Vehicle() {
	}

	public String getModelId() {
		return this.modelId;
	}

	public void setModelId(String modelId) {
		this.modelId = modelId;
	}

	public String getClass_() {
		return this.vehicleClass;
	}

	public void setClass_(String class_) {
		this.vehicleClass = class_;
	}

	public String getFuelType() {
		return this.fuelType;
	}

	public void setFuelType(String fuelType) {
		this.fuelType = fuelType;
	}

	public String getModelName() {
		return this.modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public int getSeatingCapacity() {
		return this.seatingCapacity;
	}

	public void setSeatingCapacity(int seatingCapacity) {
		this.seatingCapacity = seatingCapacity;
	}

	public String getChasisNum() {
		return chasisNum;
	}

	public void setChasisNum(String chasisNum) {
		this.chasisNum = chasisNum;
	}

	public String getEngineNum() {
		return engineNum;
	}

	public void setEngineNum(String engineNum) {
		this.engineNum = engineNum;
	}

	public VehicleManufacturer getVehicleManufacturer() {
		return this.vehicleManufacturer;
	}

	public void setVehicleManufacturer(VehicleManufacturer vehicleManufacturer) {
		this.vehicleManufacturer = vehicleManufacturer;
	}

	public String getVehicleClass() {
		return vehicleClass;
	}

	public void setVehicleClass(String vehicleClass) {
		this.vehicleClass = vehicleClass;
	}

	public VehicleWaranty getWaranty() {
		return waranty;
	}

	public void setWaranty(VehicleWaranty waranty) {
		this.waranty = waranty;
	}
	

	public Sale getSale() {
		return sale;
	}

	public void setSale(Sale sale) {
		this.sale = sale;
	}
	

}