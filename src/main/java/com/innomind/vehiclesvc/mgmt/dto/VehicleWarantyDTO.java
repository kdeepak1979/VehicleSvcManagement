package com.innomind.vehiclesvc.mgmt.dto;

public class VehicleWarantyDTO {

	private String chasisNum;
	private byte[] imageData;

	public String getChasisNum() {
		return chasisNum;
	}

	public void setChasisNum(String chasisNum) {
		this.chasisNum = chasisNum;
	}

	public byte[] getImageData() {
		return imageData;
	}

	public void setImageData(byte[] imageData) {
		this.imageData = imageData;
	}

}
