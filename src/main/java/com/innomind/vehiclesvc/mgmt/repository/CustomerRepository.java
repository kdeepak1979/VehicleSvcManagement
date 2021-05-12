package com.innomind.vehiclesvc.mgmt.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.innomind.vehiclesvc.mgmt.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer>{
		
	@Query(value="select cust.* \r\n" + 
			"from sales s \r\n" + 
			"inner join service_record sr on s.chassis_no = sr.chassis_no \r\n" + 
			"inner join customer cust on s.customer_id = cust.id \r\n" + 
			"where DATE_PART('day',now()-s.invoice_date) <= 365\r\n" + 
			"and DATE_PART('day',now()-sr.service_date) > 90",nativeQuery = true)
	public List<Customer> findCustomerWithVehicleAgeLessThanOneYear();
	
	@Query(value="select cust.* \r\n" + 
			"from sales s \r\n" + 
			"inner join service_record sr on s.chassis_no = sr.chassis_no \r\n" + 
			"inner join customer cust on s.customer_id = cust.id \r\n" + 
			"where DATE_PART('day',now()-s.invoice_date) > 365\r\n" + 
			"and DATE_PART('day',now()-sr.service_date) > 365",nativeQuery = true)
	public List<Customer> findCustomerWithVehicleAgeMoreThanOneYear();

}
