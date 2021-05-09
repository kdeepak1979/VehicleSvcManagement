package com.innomind.vehiclesvc.mgmt.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.innomind.vehiclesvc.mgmt.entity.Vehicle;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, String>{
		

}
