package com.innomind.vehiclesvc.mgmt.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.innomind.vehiclesvc.mgmt.dto.VehicleDTO;
import com.innomind.vehiclesvc.mgmt.entity.Vehicle;
import com.innomind.vehiclesvc.mgmt.service.VehicleService;

@RestController
public class VehicleController {
	
	@Autowired
	private VehicleService vehicleService;
	
	@GetMapping(path="/vehicle/")
	public List<VehicleDTO> getAllVehicles(){	
		return vehicleService.getAllVehicles();
	}
	
	@GetMapping(path="/vehicle/{chasisNum}")
	public ResponseEntity<VehicleDTO> getVehicleByID(@PathVariable String chasisNum) {
		ResponseEntity<VehicleDTO>  responseEntity = null;
		Optional<VehicleDTO> vehicle = vehicleService.getVehicle(chasisNum);
		if(vehicle.isPresent()) {
			responseEntity = new ResponseEntity<VehicleDTO>(vehicle.get(), HttpStatus.FOUND);
		}else {
			responseEntity = new ResponseEntity<VehicleDTO>(HttpStatus.NOT_FOUND);
		}
		return responseEntity;
	}
	
	
	@PostMapping(path="/vehicle/")
	public ResponseEntity<Void> addVehicle(@RequestBody VehicleDTO vehicle){
	    Vehicle savedVehicel = vehicleService.addVehicle(vehicle);	
	    URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedVehicel.getChasisNum())
				.toUri();
		return ResponseEntity.created(uri).build();
	}
	
	
	@PutMapping(path="/vehicle/")
	public ResponseEntity<Void> updateVehicle(@RequestBody VehicleDTO vehicle){
		boolean updated = vehicleService.updateVehicel(vehicle);
		ResponseEntity<Void> responseEntity;
		if (updated) {
			responseEntity = new ResponseEntity<Void>(HttpStatus.OK);
		} else {
			responseEntity = new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		return responseEntity;
		
	}
	
	@DeleteMapping(path="/vehicle/{chasisNum}")
	public ResponseEntity<Void> deleteVehicle(@PathVariable String chasisNum){
		vehicleService.delete(chasisNum);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

}
