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

import com.innomind.vehiclesvc.mgmt.dto.CustomerDTO;
import com.innomind.vehiclesvc.mgmt.entity.Customer;
import com.innomind.vehiclesvc.mgmt.service.CustometrService;

import io.swagger.annotations.ApiOperation;

@RestController
public class CustomerController {
	
	@Autowired
	private CustometrService customerService;
	
	@ApiOperation(value="Finds all customers", notes="It can be used to fetch all the dealer's customer",response=List.class)
	@GetMapping(path="/customer")
	public List<CustomerDTO> getAllCustomers(){	
		return customerService.getAllCustomers();
	}
	
	@ApiOperation(value="Finds customer with given id", notes="Provide an id to look up specific customer",response=CustomerDTO.class)
	@GetMapping(path="/customer/{id}")
	public ResponseEntity<CustomerDTO> getCustomerByID(@PathVariable int id) {
		ResponseEntity<CustomerDTO>  responseEntity = null;
		Optional<CustomerDTO> customer = customerService.getCustomer(id);
		if(customer.isPresent()) {
			responseEntity = new ResponseEntity<CustomerDTO>(customer.get(), HttpStatus.FOUND);
		}else {
			responseEntity = new ResponseEntity<CustomerDTO>(HttpStatus.NOT_FOUND);
		}
		return responseEntity;
	}
	
	
	@ApiOperation(value="Adds new customer", notes="Provide custom details to be added in system",response=Void.class)
	@PostMapping(path="/customer")
	public ResponseEntity<Void> addCustomer(@RequestBody CustomerDTO customer){
	    Customer savedCustomer = customerService.addCustomer(customer);	
	    URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedCustomer.getId())
				.toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@ApiOperation(value="Updates the existing customer", notes="Provide updated custom details to be saved",response=Void.class)
	@PutMapping(path="/customer")
	public ResponseEntity<Void> updateCustomer(@RequestBody CustomerDTO customer){
		boolean updated = customerService.updateCustomer(customer);
		ResponseEntity<Void> responseEntity;
		if (updated) {
			responseEntity = new ResponseEntity<Void>(HttpStatus.OK);
		} else {
			responseEntity = new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		return responseEntity;
		
	}
	
	@ApiOperation(value="Deletes customer with given id", notes="Provide customer id to be deleted",response=Void.class)
	@DeleteMapping(path="/customer/{id}")
	public ResponseEntity<Void> deleteCustomer(@PathVariable int id){
		customerService.delete(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

}
