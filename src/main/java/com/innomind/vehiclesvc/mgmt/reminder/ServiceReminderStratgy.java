package com.innomind.vehiclesvc.mgmt.reminder;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.innomind.vehiclesvc.mgmt.entity.Customer;
import com.innomind.vehiclesvc.mgmt.entity.User;
import com.innomind.vehiclesvc.mgmt.quartz.Mailer;
import com.innomind.vehiclesvc.mgmt.repository.CustomerRepository;
import com.innomind.vehiclesvc.mgmt.repository.UserRepository;
import com.innomind.vehiclesvc.mgmt.security.TenantResolver;

@Service
public class ServiceReminderStratgy implements IServiceReminderStrategy{

	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private TenantResolver tenantResolver;
	@Autowired
	private Mailer mailer;
	
	@Override
	public void execute() {
		List<User> dealers = userRepository.findAll();
		dealers.stream().forEach(d ->executeReminderTask(d));
		tenantResolver.setActiveTenant(null);
		
	}

	private void executeReminderTask(User d) {
		tenantResolver.setActiveTenant(d.getUserName());
		List<Customer> cutomers = customerRepository.findCustomerWithVehicleAgeLessThanOneYear();
		cutomers.stream().forEach(c -> mailer.sendReminder(c));

		cutomers = customerRepository.findCustomerWithVehicleAgeMoreThanOneYear();
		cutomers.stream().forEach(c -> mailer.sendReminder(c));
	}
	

}
