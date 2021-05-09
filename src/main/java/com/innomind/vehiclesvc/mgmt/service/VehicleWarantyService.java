package com.innomind.vehiclesvc.mgmt.service;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.innomind.vehiclesvc.mgmt.dto.VehicleWarantyDTO;
import com.innomind.vehiclesvc.mgmt.entity.Vehicle;
import com.innomind.vehiclesvc.mgmt.entity.VehicleWaranty;

import com.innomind.vehiclesvc.mgmt.repository.VehicleRepository;
import com.innomind.vehiclesvc.mgmt.repository.VehicleWaranctRepository;

@Service
public class VehicleWarantyService {

	@Autowired
	private VehicleRepository vehicleRepository;
	@Autowired
	private VehicleWaranctRepository warantyRepository;

	/**
	 * 
	 * @param chasisNo
	 * @return
	 */
	public VehicleWarantyDTO getWaranty(String chasisNo) {
		VehicleWarantyDTO warantyDto = null;
		Optional<Vehicle> vehicle = vehicleRepository.findById(chasisNo);
		System.out.println("VehicleWarantyService.getWaranty() vehicle : "+vehicle);
		if (vehicle.isPresent()) {
			Optional<VehicleWaranty> waranty = warantyRepository.findByVehicle(vehicle.get());
			if (waranty.isPresent()) {
				warantyDto = new VehicleWarantyDTO();
				warantyDto.setChasisNum(chasisNo);
				warantyDto.setImageData(waranty.get().getWarantyCard());
			}
		}
		return warantyDto;
	}


	/**
	 * 
	 * @param chasisNo
	 * @param file
	 * @return
	 */
	public boolean updateWaranty(String chasisNo, MultipartFile file) {
		deleteWaranty(chasisNo);
		addWaranty(chasisNo, file);
		return true;
	}


	/**
	 * 
	 * @param chasisNo
	 */
	public void deleteWaranty(String chasisNo) {		
		Optional<Vehicle> vehicle = vehicleRepository.findById(chasisNo);
		if (vehicle.isPresent()) {
			warantyRepository.deleteByVehicle(vehicle.get());
		}
	}

	/**
	 * 
	 * @param chasisNo
	 * @param file
	 * @return
	 */
	public VehicleWaranty addWaranty(String chasisNo, MultipartFile file) {
		VehicleWaranty vehicleWaranty = null;
		Optional<Vehicle> vehicle = vehicleRepository.findById(chasisNo);
		if (vehicle.isPresent()) {
			VehicleWaranty waranty = new VehicleWaranty();
			waranty.setVehicle(vehicle.get());
			try {
				waranty.setWarantyCard(file.getBytes());
				vehicleWaranty = warantyRepository.save(waranty);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return vehicleWaranty;
	}
	
}
