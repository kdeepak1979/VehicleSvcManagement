package com.innomind.vehiclesvc.mgmt.dto;

public class VehicleDTO {

	private String chasisNum;
	private String engineNum;
	private String modelId;
	private String modelName;
	private int seatingCapacity;
	private String fuelType;
	private String vehicleClass;
	private String manufuturerName;

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

	public String getModelId() {
		return modelId;
	}

	public void setModelId(String modelId) {
		this.modelId = modelId;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public int getSeatingCapacity() {
		return seatingCapacity;
	}

	public void setSeatingCapacity(int seatingCapacity) {
		this.seatingCapacity = seatingCapacity;
	}

	public String getFuelType() {
		return fuelType;
	}

	public void setFuelType(String fuelType) {
		this.fuelType = fuelType;
	}

	public String getVehicleClass() {
		return vehicleClass;
	}

	public void setVehicleClass(String vehicleClass) {
		this.vehicleClass = vehicleClass;
	}

	public String getManufuturerName() {
		return manufuturerName;
	}

	public void setManufuturerName(String manufuturerName) {
		this.manufuturerName = manufuturerName;
	}

	@Override
	public String toString() {
		return "VehicleDTO [chasisNum=" + chasisNum + ", engineNum=" + engineNum + ", modelId=" + modelId
				+ ", modelName=" + modelName + ", seatingCapacity=" + seatingCapacity + ", fuelType=" + fuelType
				+ ", vehicleClass=" + vehicleClass + ", manufuturerName=" + manufuturerName + "]";
	}

	
}
