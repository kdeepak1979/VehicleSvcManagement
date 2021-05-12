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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.innomind.vehiclesvc.mgmt.dto.VehicleDTO;
import com.innomind.vehiclesvc.mgmt.dto.VehicleWarantyDTO;
import com.innomind.vehiclesvc.mgmt.entity.VehicleWaranty;
import com.innomind.vehiclesvc.mgmt.service.VehicleWarantyService;

import io.swagger.annotations.ApiOperation;

@RestController
public class VehicleWarantyController {

	@Autowired
	private VehicleWarantyService warantyService;

	/**
	 * 
	 * @param chasisNo
	 * @return
	 */
	@ApiOperation(value="Finds vehicle warranty with given chassis number", notes="Provide chassis number to look up specific vehicle waranty",response=VehicleDTO.class)
	@GetMapping(path = "/waranty/{chasisNo}")
	public VehicleWarantyDTO getWaranty(@PathVariable String chasisNo) {
		System.out.println("VehicleWarantyController.getWaranty() "+chasisNo);
		return warantyService.getWaranty(chasisNo);
	}

	/**
	 * 
	 * @param chasisNo
	 */
	@ApiOperation(value="Updates the existing waranty for specific vehicle", notes="Provide updated vehicle chassis number & warnty card to be saved",response=Void.class)
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
	 * @param chasisNo
	 */
	@ApiOperation(value="Deletes vehicle waranty with given chassis number", notes="Provide chassis number to be deleted",response=Void.class)
	@DeleteMapping(path = "/waranty/{chasisNo}")
	public ResponseEntity<Void> deleteWaranty(@PathVariable String chasisNo) {
		warantyService.deleteWaranty(chasisNo);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	/**
	 * 
	 * @param chasisNo
	 */
	@ApiOperation(value="Adds new vehicel waranty", notes="Provide vehicle chassis number & waranty card to be added in system",response=Void.class)
	@PostMapping(path = "/waranty/{chasisNo}")
	public ResponseEntity<Void> addWaranty(@PathVariable String chasisNo,@RequestParam("imageFile") MultipartFile file) {
		VehicleWaranty savedWaranty = warantyService.addWaranty(chasisNo,file);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedWaranty.getVehicle().getChasisNum()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
}
