package com.innomind.vehiclesvc.mgmt.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.innomind.vehiclesvc.mgmt.entity.Vehicle;
import com.innomind.vehiclesvc.mgmt.entity.VehicleWaranty;



@Repository
public interface VehicleWaranctRepository extends JpaRepository<VehicleWaranty, Integer>{

	public Optional<VehicleWaranty> findByVehicle(Vehicle vehicle);

	public void deleteByVehicle(Vehicle vehicle);
}
