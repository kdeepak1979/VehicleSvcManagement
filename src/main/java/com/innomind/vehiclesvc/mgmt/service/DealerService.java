package com.innomind.vehiclesvc.mgmt.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.innomind.vehiclesvc.mgmt.dto.Dealer;
import com.innomind.vehiclesvc.mgmt.entity.DealerDetail;
import com.innomind.vehiclesvc.mgmt.entity.User;
import com.innomind.vehiclesvc.mgmt.repository.DealerDetailRepository;
import com.innomind.vehiclesvc.mgmt.repository.UserRepository;
import com.innomind.vehiclesvc.mgmt.security.SchemaService;

@Service
public class DealerService {

	@Autowired
	private UserRepository tenanatRepository;
	@Autowired
	private DealerDetailRepository dealerDetailRepository;
	@Autowired
	private SchemaService schemaService;
	
	/**
	 * 
	 * @param dealerId
	 * @return
	 */
	public Dealer getDealer(String dealerId) {	
		System.out.println("DealerService.getDealer() tenanatRepository "+tenanatRepository);
		Optional<User> user = tenanatRepository.findByUserName(dealerId);
		Dealer tenant = null;
		if(user.isPresent()) {
			tenant = new Dealer();
			tenant.setDealerId(user.get().getUserName());
			Optional<DealerDetail> dealerDetail = dealerDetailRepository.findByDealer(user.get());
			if(dealerDetail.isPresent()) {
				tenant.setDealerName(dealerDetail.get().getName());
				tenant.setAddress(dealerDetail.get().getAddress());
				tenant.setPhone(dealerDetail.get().getPhone());
			}
		}						
		return tenant;
	}

	/**
	 * 
	 * @param dealer
	 */
	public boolean updateDealer(Dealer dealer) {
		boolean updated = false;
		Optional<User> user = tenanatRepository.findByUserName(dealer.getDealerId());
		if (user.isPresent()) {			
			Optional<DealerDetail> dealerDetail = dealerDetailRepository.findByDealer(user.get());
			if(dealer.getPassword() != null) {
				User tenantDealer = user.get();
				tenantDealer.setPassword(dealer.getPassword());
				tenanatRepository.save(tenantDealer);
			}
			if (dealerDetail.isPresent()) {
				DealerDetail detail = dealerDetail.get();
				if (dealer.getDealerName() != null) {
					detail.setName(dealer.getDealerName());
				}
				if (dealer.getAddress() != null) {
					detail.setAddress(dealer.getAddress());
				}
				if (dealer.getPhone() != null) {
					detail.setPhone(dealer.getPhone());
				}
				dealerDetailRepository.save(detail);
				updated = true;
			}
		}
		return updated;
	}

	/**
	 * 
	 * @param dealer
	 */
	public void deleteDeler(String dealerId) {
		tenanatRepository.deleteById(dealerId);
	}

	/**
	 * 
	 * @param dealer
	 */
	public User addDealer(Dealer dealer) {
		User tenantDealer = new User();
		tenantDealer.setUserName(dealer.getDealerId());
		tenantDealer.setPassword(dealer.getPassword());
				
		User savedTenant = tenanatRepository.save(tenantDealer);
		
		DealerDetail dealerDetail = new DealerDetail();
		dealerDetail.setDealer(savedTenant);
		dealerDetail.setName(dealer.getDealerName());
		dealerDetail.setPhone(dealer.getPhone());
		dealerDetail.setAddress(dealer.getAddress());
		
		dealerDetailRepository.save(dealerDetail);		
		schemaService.initSchema(dealer.getDealerId());
		return savedTenant;
	}

	/**
	 * 
	 * @return
	 */
	public List<Dealer> getAllDealers() {
		return tenanatRepository.findAll()
				.stream()
				.map(user -> buildDealer(user))
				.collect(Collectors.toList());
	}

	/**
	 * 
	 * @param user
	 * @return
	 */
	private Dealer buildDealer(User user) {
		Dealer dealer = new Dealer();
		dealer.setDealerId(user.getUserName());
		Optional<DealerDetail> dealerDetail = dealerDetailRepository.findByDealer(user);
		dealerDetail.ifPresent(dd -> {
			dealer.setDealerName(dd.getName());
			dealer.setAddress(dd.getAddress());
			dealer.setPhone(dd.getPhone());
		});
		return dealer;
	}

}
