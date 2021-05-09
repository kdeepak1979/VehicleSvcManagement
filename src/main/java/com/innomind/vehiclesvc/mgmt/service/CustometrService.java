package com.innomind.vehiclesvc.mgmt.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.innomind.vehiclesvc.mgmt.dto.CustomerDTO;
import com.innomind.vehiclesvc.mgmt.entity.Customer;
import com.innomind.vehiclesvc.mgmt.repository.CustomerRepository;

@Service
public class CustometrService {

	@Autowired
	private CustomerRepository customerRepository;
	
	public List<CustomerDTO> getAllCustomers() {
		return customerRepository.findAll()
				.stream()
				.map(c -> buildDTO(c))
				.collect(Collectors.toList());
	}

	/**
	 * 
	 * @param c
	 * @return
	 */
	private CustomerDTO buildDTO(Customer c) {
		CustomerDTO customer = new CustomerDTO();
		customer.setCustomerId(c.getId());
		customer.setAadharNum(c.getAadharNo());
		customer.setDob(c.getDob());
		customer.setEmailId(c.getEmailID());
		customer.setFirstName(c.getFirstName());
		customer.setMiddleName(c.getMiddleName());
		customer.setLastName(c.getLastName());
		customer.setPanNum(c.getPanNo());
		customer.setPhone(c.getPhone());
		return customer;
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	public Optional<CustomerDTO> getCustomer(int id) {
		Optional<Customer> customer = customerRepository.findById(id);
		if(customer.isPresent()) {
			return Optional.of(buildDTO(customer.get()));
		}else {
			return Optional.empty();
		}
	}

	/**
	 * 
	 * @param customer
	 * @return
	 */
	public Customer addCustomer(CustomerDTO customer) {
		Customer c = new Customer();
		c.setFirstName(customer.getFirstName());
		c.setMiddleName(customer.getMiddleName());
		c.setLastName(customer.getLastName());
		c.setDob(customer.getDob());
		c.setEmailID(customer.getEmailId());
		c.setPhone(customer.getPhone());
		c.setPanNo(customer.getPanNum());
		c.setAadharNo(customer.getAadharNum());
		c = customerRepository.save(c);
		return c;
	}

	/**
	 * 
	 * @param customer
	 * @return
	 */
	public boolean updateCustomer(CustomerDTO customer) {
		boolean updated = false;
		Optional<Customer> c = customerRepository.findById(customer.getCustomerId());
		if (c.isPresent()) {
			if (customer.getFirstName() != null) {
				c.get().setFirstName(customer.getFirstName());
			}
			if (customer.getMiddleName() != null) {
				c.get().setMiddleName(customer.getMiddleName());
			}
			if (customer.getLastName() != null) {
				c.get().setLastName(customer.getLastName());
			}
			if (customer.getDob() != null) {
				c.get().setDob(customer.getDob());
			}
			if (customer.getEmailId() != null) {
				c.get().setEmailID(customer.getEmailId());
			}

			if (customer.getPanNum() != null) {
				c.get().setPanNo(customer.getPanNum());
			}

			c.get().setPhone(customer.getPhone());
			c.get().setAadharNo(customer.getAadharNum());
			customerRepository.save(c.get());
			updated = true;
		}
		return updated;
	}

	/**
	 * 
	 * @param id
	 */
	public void delete(int id) {
		customerRepository.deleteById(id);
	}

}
