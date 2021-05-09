package com.innomind.vehiclesvc.mgmt.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.innomind.vehiclesvc.mgmt.entity.VehicleManufacturer;



@Repository
public interface VehicleManufacturerRepository extends JpaRepository<VehicleManufacturer, Integer>{
	
	public Optional<VehicleManufacturer> findByName(String name);

}
