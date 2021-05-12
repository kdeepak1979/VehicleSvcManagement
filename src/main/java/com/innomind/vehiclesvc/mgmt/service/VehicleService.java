package com.innomind.vehiclesvc.mgmt.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.innomind.vehiclesvc.mgmt.dto.VehicleDTO;
import com.innomind.vehiclesvc.mgmt.entity.Vehicle;
import com.innomind.vehiclesvc.mgmt.entity.VehicleManufacturer;
import com.innomind.vehiclesvc.mgmt.repository.VehicleManufacturerRepository;
import com.innomind.vehiclesvc.mgmt.repository.VehicleRepository;

@Service
public class VehicleService {

	@Autowired
	private VehicleRepository vehicelRepositry;
	@Autowired
	private VehicleManufacturerRepository manufacturerRepository;
	
	/**
	 * 
	 * @return
	 */
	public List<VehicleDTO> getAllVehicles() {		
		List<VehicleDTO> vehicles = vehicelRepositry.findAll().stream().map(v -> buildDTO(v))
				.collect(Collectors.toList());
		return vehicles;
	}

	/**
	 * 
	 * @param v
	 * @return
	 */
	private VehicleDTO buildDTO(Vehicle v) {
		VehicleDTO vehicle = new VehicleDTO();
		vehicle.setChasisNum(v.getChasisNum());
		vehicle.setEngineNum(v.getEngineNum());
		vehicle.setFuelType(v.getFuelType());
		vehicle.setManufuturerName(v.getVehicleManufacturer().getName());
		vehicle.setModelId(v.getModelId());
		vehicle.setSeatingCapacity(v.getSeatingCapacity());
		vehicle.setVehicleClass(v.getClass_());
		
		return  vehicle;
	}

	/**
	 * 
	 * @param chasisNum
	 * @return
	 */
	public Optional<VehicleDTO> getVehicle(String chasisNum) {
		Optional<Vehicle> vehicle = vehicelRepositry.findById(chasisNum);
		if(vehicle.isPresent()) {
			return Optional.of(buildDTO(vehicle.get()));
		}else {
			return Optional.empty();
		}		
	}

	/**
	 * 
	 * @param vehicle
	 */
	public Vehicle addVehicle(VehicleDTO vehicle) {
		Optional<VehicleManufacturer> manufacturer = manufacturerRepository.findByName(vehicle.getManufuturerName());
		VehicleManufacturer vm;
		if (!manufacturer.isPresent()) {
			vm = new VehicleManufacturer();
			vm.setName(vehicle.getManufuturerName());
			manufacturerRepository.save(vm);
		} else {
			vm = manufacturer.get();
		}

		Vehicle v = new Vehicle();
		v.setChasisNum(vehicle.getChasisNum());
		v.setClass_(vehicle.getVehicleClass());
		v.setEngineNum(vehicle.getEngineNum());
		v.setFuelType(vehicle.getFuelType());
		v.setModelId(vehicle.getModelId());
		v.setModelName(vehicle.getModelName());
		v.setSeatingCapacity(vehicle.getSeatingCapacity());
		v.setVehicleManufacturer(vm);

		vehicelRepositry.save(v);
		return v;
	}

	/**
	 * 
	 * @param vehicle
	 * @return
	 */
	public boolean updateVehicel(VehicleDTO vehicle) {
		Optional<Vehicle> persistentVehicel = vehicelRepositry.findById(vehicle.getChasisNum());
		boolean upadted = false;
		if (persistentVehicel.isPresent()) {
			Vehicle v = persistentVehicel.get();
			if (vehicle.getVehicleClass() != null) {
				v.setClass_(vehicle.getVehicleClass());
			}
			if (vehicle.getEngineNum() != null) {
				v.setEngineNum(vehicle.getEngineNum());
			}
			if (vehicle.getFuelType() != null) {
				v.setFuelType(vehicle.getFuelType());
			}
			if (vehicle.getModelId() != null) {
				v.setModelId(vehicle.getModelId());
			}
			if (vehicle.getModelName() != null) {
				v.setModelName(vehicle.getModelName());
			}
			if (vehicle.getManufuturerName() != null) {
				Optional<VehicleManufacturer> manufacturer = manufacturerRepository
						.findByName(vehicle.getManufuturerName());
				if (manufacturer.isPresent()) {
					v.setVehicleManufacturer(manufacturer.get());
				}
			}
			vehicelRepositry.save(v);
			upadted = true;
		}
		return upadted;
	}

	/**
	 * 
	 * @param chasisNum
	 */
	public void delete(String chasisNum) {
		vehicelRepositry.deleteById(chasisNum);
	}
}
