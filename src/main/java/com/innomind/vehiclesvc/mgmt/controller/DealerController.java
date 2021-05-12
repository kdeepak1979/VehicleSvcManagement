package com.innomind.vehiclesvc.mgmt.controller;

import java.net.URI;
import java.util.List;

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

import com.innomind.vehiclesvc.mgmt.dto.CustomerDTO;
import com.innomind.vehiclesvc.mgmt.dto.Dealer;
import com.innomind.vehiclesvc.mgmt.entity.User;
import com.innomind.vehiclesvc.mgmt.service.DealerService;

import io.swagger.annotations.ApiOperation;

@RestController
public class DealerController {
	
	@Autowired
	private DealerService tenantService;
	
	@ApiOperation(value="Finds all Dealers", notes="It can be used to fetch all the dealers",response=List.class)
	@GetMapping(path="/dealer")
	public List<Dealer> getAllDealer(){
		return tenantService.getAllDealers();
	}
	
	/**
	 * 
	 * @param dealerId
	 * @return
	 */
	@ApiOperation(value="Finds dealer with given id", notes="Provide an id to look up specific dealer",response=CustomerDTO.class)
	@GetMapping(path="/dealer/{dealerId}")
	public Dealer getDealer(@PathVariable String dealerId) {
		return tenantService.getDealer(dealerId);
	}
	
	
	/**
	 * 
	 * @param dealer
	 */
	@ApiOperation(value="Updates the existing dealer", notes="Provide updated dealer details to be saved",response=Void.class)
	@PutMapping(path="/dealer")
	public ResponseEntity<Void> updateDealer(@RequestBody Dealer dealer) {
		ResponseEntity<Void> responseEntity;
		boolean updated = tenantService.updateDealer(dealer);
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
	@ApiOperation(value="Deletes dealer with given id", notes="Provide dealer id to be deleted",response=Void.class)
	@DeleteMapping(path="/dealer/{dealerId}")
	public ResponseEntity<Void>  deleteDealer(@PathVariable String dealerId) {
		tenantService.deleteDeler(dealerId);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	
	/**
	 * 
	 * @param dealer
	 */
	@ApiOperation(value="Adds new dealer", notes="Provide dealer details to be added in system",response=Void.class)
	@PostMapping(path="/dealer")
	public ResponseEntity<Void> addDealer(@RequestBody Dealer dealer) {
		User savedTenant = tenantService.addDealer(dealer);
		System.out.println("DealerController.addDealer() savedTenant "+savedTenant.getUserName());
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedTenant.getUserName())
				.toUri();
		return ResponseEntity.created(uri).build();
	}

}
