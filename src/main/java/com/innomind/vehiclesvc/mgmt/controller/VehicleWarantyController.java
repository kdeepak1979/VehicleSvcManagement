package com.innomind.vehiclesvc.mgmt.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.innomind.vehiclesvc.mgmt.dto.Dealer;
import com.innomind.vehiclesvc.mgmt.dto.VehicleWarantyDTO;
import com.innomind.vehiclesvc.mgmt.entity.User;
import com.innomind.vehiclesvc.mgmt.entity.VehicleWaranty;
import com.innomind.vehiclesvc.mgmt.service.VehicleWarantyService;

@RestController
public class VehicleWarantyController {

	@Autowired
	private VehicleWarantyService warantyService;

	/**
	 * 
	 * @param dealerId
	 * @return
	 */
	@GetMapping(path = "/waranty/{chasisNo}")
	public VehicleWarantyDTO getWaranty(@PathVariable String chasisNo) {
		System.out.println("VehicleWarantyController.getWaranty() "+chasisNo);
		return warantyService.getWaranty(chasisNo);
	}

	/**
	 * 
	 * @param dealer
	 */
	@PutMapping(path = "/waranty/{chasisNo}")
	public ResponseEntity<Void> updateWaranty(@PathVariable String chasisNo,@RequestParam("imageFile") MultipartFile file) {
		ResponseEntity<Void> responseEntity;
		boolean updated = warantyService.updateWaranty(chasisNo,file);
		if (updated) {
			responseEntity = new ResponseEntity<Void>(HttpStatus.OK);
		} else {
			responseEntity = new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		return responseEntity;
	}

	/**
	 * 
	 * @param dealer
	 */
	@DeleteMapping(path = "/waranty/{chasisNo}")
	public ResponseEntity<Void> deleteWaranty(@PathVariable String chasisNo) {
		warantyService.deleteWaranty(chasisNo);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	/**
	 * 
	 * @param dealer
	 */
	@PostMapping(path = "/waranty/{chasisNo}")
	public ResponseEntity<Void> addWaranty(@PathVariable String chasisNo,@RequestParam("imageFile") MultipartFile file) {
		VehicleWaranty savedWaranty = warantyService.addWaranty(chasisNo,file);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedWaranty.getVehicle().getChasisNum()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
}
