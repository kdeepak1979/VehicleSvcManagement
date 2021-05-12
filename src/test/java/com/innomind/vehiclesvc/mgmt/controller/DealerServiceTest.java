package com.innomind.vehiclesvc.mgmt.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.innomind.vehiclesvc.mgmt.dto.Dealer;
import com.innomind.vehiclesvc.mgmt.entity.DealerDetail;
import com.innomind.vehiclesvc.mgmt.entity.User;
import com.innomind.vehiclesvc.mgmt.repository.DealerDetailRepository;
import com.innomind.vehiclesvc.mgmt.repository.UserRepository;
import com.innomind.vehiclesvc.mgmt.security.CustomUserDetailService;
import com.innomind.vehiclesvc.mgmt.security.SchemaService;
import com.innomind.vehiclesvc.mgmt.service.DealerService;


@ExtendWith(MockitoExtension.class)
class DealerServiceTest {
	
	@Mock
	private UserRepository tenanatRepository ;
	@Mock
	private DealerDetailRepository dealerDtaileRepository ;
	@Mock
	private SchemaService schemaService;
	@Mock
	private CustomUserDetailService userDetailService;
	
	@InjectMocks
	private DealerService dealerService;
	
	private List<User> userList = null;
	private List<Dealer> dealerList = null;
	private List<DealerDetail> dealerDetailList = null;
		
	
	/**
	 *  Setup test objects
	 */
	@BeforeEach
	public void setUp() {
		
		User user1 = new User();
		user1.setUserName("d1");
		
		User user2 = new User();
		user2.setUserName("d2");
		
		userList = new ArrayList<>(2);
		userList.add(user1);
		userList.add(user2);
		
		Dealer dealer1 = new Dealer();
		dealer1.setDealerId("d1");
		dealer1.setDealerName("d1 Name");
		dealer1.setAddress("d1 Adress");
		dealer1.setPhone("9898989898");
		
		Dealer dealer2 = new Dealer();
		dealer2.setDealerId("d2");
		dealer2.setDealerName("d2 Name");
		dealer2.setAddress("d2 Adress");
		dealer2.setPhone("8989898989");
		
		dealerList = new ArrayList<Dealer>(2);
		dealerList.add(dealer1);
		dealerList.add(dealer2);
		
		DealerDetail dealerDetail1 = new DealerDetail();
		dealerDetail1.setDealer(user1);
		dealerDetail1.setName(dealer1.getDealerName());
		dealerDetail1.setAddress(dealer1.getAddress());
		dealerDetail1.setPhone(dealer1.getPhone());
		
		DealerDetail dealerDetail2 = new DealerDetail();
		dealerDetail2.setDealer(user2);
		dealerDetail2.setName(dealer2.getDealerName());
		dealerDetail2.setAddress(dealer2.getAddress());
		dealerDetail2.setPhone(dealer2.getPhone());
		
		dealerDetailList = new ArrayList<>();
		dealerDetailList.add(dealerDetail1);
		dealerDetailList.add(dealerDetail2);
	}
	
	/**
	 * Reset test objects
	 */
	@AfterEach
	public void tearDown() {
		userList.clear();
		userList = null;
		
		dealerList.clear();
		dealerList = null;		
	}
	
	@Test
	void test() {
				
		Mockito.when(tenanatRepository.findByUserName("d1")).thenReturn(Optional.of(userList.get(0)));
		Mockito.when(dealerDtaileRepository.findByDealer(userList.get(0))).thenReturn(Optional.of(dealerDetailList.get(0)));
		
		//DealerService service = new DealerService();
		
		Dealer dealer = dealerService.getDealer("d1");
		
		assertEquals(dealerList.get(0).getDealerId(), dealer.getDealerId());
		assertEquals(dealerList.get(0).getDealerName(), dealer.getDealerName());
		assertEquals(dealerList.get(0).getAddress(), dealer.getAddress());
		assertEquals(dealerList.get(0).getPhone(), dealer.getPhone());
	}

}
