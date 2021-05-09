package com.innomind.vehiclesvc.mgmt.controller;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;

import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.innomind.vehiclesvc.mgmt.dto.Dealer;
import com.innomind.vehiclesvc.mgmt.entity.User;
import com.innomind.vehiclesvc.mgmt.repository.DealerDetailRepository;
import com.innomind.vehiclesvc.mgmt.repository.UserRepository;
import com.innomind.vehiclesvc.mgmt.security.CustomUserDetailService;
import com.innomind.vehiclesvc.mgmt.service.DealerService;

@WebMvcTest(DealerController.class)
public class DealerControllerTest {
	
	@Autowired
	private MockMvc mockMvc;	
	@Autowired
	private ObjectMapper objectMapper;	
	@MockBean
	private DealerService dealerService;	
	@MockBean
	private UserRepository userRepository;
	@MockBean
	private DealerDetailRepository dealerDetailRepository;
	@MockBean
	private CustomUserDetailService userDetailService;
	
	private List<Dealer> dealerList = null;
	
	/**
	 *  Setup test objects
	 */
	@BeforeEach
	public void setUp() {
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
	}
	
	/**
	 * Reset test objects
	 */
	@AfterEach
	public void tearDown() {
		dealerList.clear();
		dealerList = null;
	}
	
	/**
	 * Test find all dealer API
	 */
	@Test
	//@WithMockUser(username="test", roles = {"USER"})
	public void testGetAllDealer() {				
		Mockito.when(dealerService.getAllDealers()).thenReturn(dealerList);
		
		String url = "/dealer/";
		
		try {
			MvcResult result1 = mockMvc.perform(get(url).with(SecurityMockMvcRequestPostProcessors.user("test").roles("ADMIN")))
					.andReturn();
			assertEquals(HttpStatus.OK.value(), result1.getResponse().getStatus());
			
			String resposeString = result1.getResponse().getContentAsString();
			String expectedString = objectMapper.writeValueAsString(dealerList);
			
			assertEquals(expectedString, resposeString);
			
			MvcResult result2 = mockMvc.perform(get(url).with(SecurityMockMvcRequestPostProcessors.user("test").roles("USER")))
					.andReturn();
			assertEquals(HttpStatus.FORBIDDEN.value(), result2.getResponse().getStatus());			
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
		
	}
	
	/**
	 * Test find dealer by ID api
	 */
	@Test
	public void testGetDealerWithId() {
		
		Mockito.when(dealerService.getDealer("d1")).thenReturn(dealerList.get(0));
		
		String url = "/dealer/d1";
		
		try {
			MvcResult result1 = mockMvc.perform(get(url).with(SecurityMockMvcRequestPostProcessors.user("test").roles("ADMIN")))
					.andReturn();
			assertEquals(HttpStatus.OK.value(), result1.getResponse().getStatus());
			
			String resposeString = result1.getResponse().getContentAsString();
			String expectedString = objectMapper.writeValueAsString(dealerList.get(0));
			
			assertEquals(expectedString, resposeString);
			
			MvcResult result2 = mockMvc.perform(get(url).with(SecurityMockMvcRequestPostProcessors.user("test").roles("USER")))
					.andReturn();
			assertEquals(HttpStatus.FORBIDDEN.value(), result2.getResponse().getStatus());			
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
	
	/**
	 * Test api to add new dealer
	 */
	@Test
	public void testAddDealer() {
		
		User user = new User();
		user.setUserName(dealerList.get(0).getDealerName());
		Mockito.when(dealerService.addDealer(Mockito.any(Dealer.class))).thenReturn(user);
		
		String url = "/dealer/";
		
		try {
			MvcResult result1 = mockMvc.perform(post(url)
												.contentType("application/json")
												.content(objectMapper.writeValueAsString(dealerList.get(0)))
												.with(SecurityMockMvcRequestPostProcessors.user("test").roles("ADMIN"))
												.with(SecurityMockMvcRequestPostProcessors.csrf()))												
										.andReturn();
			assertEquals(HttpStatus.CREATED.value(), result1.getResponse().getStatus());			
			
			System.out.println("DealerControllerTest.testAddDealer() result1 "+result1.getResponse().getHeaderNames());
			System.out.println("DealerControllerTest.testAddDealer() result1 "+result1.getResponse().getHeader("Location"));
			
			
			MvcResult result2 = mockMvc.perform(post(url)
					.contentType("application/json")
					.content(objectMapper.writeValueAsString(dealerList.get(0)))
					.with(SecurityMockMvcRequestPostProcessors.user("test").roles("USER"))
					.with(SecurityMockMvcRequestPostProcessors.csrf()))												
			.andReturn();
			assertEquals(HttpStatus.FORBIDDEN.value(), result2.getResponse().getStatus());			
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
	
	/**
	 * Test api to update the dealer
	 */
	@Test
	public void testUpdateDealer() {
	
		Mockito.when(dealerService.updateDealer(Mockito.any(Dealer.class))).thenReturn(true);
		
		String url = "/dealer/";
		
		try {
			MvcResult result1 = mockMvc.perform(put(url)
												.contentType("application/json")
												.content(objectMapper.writeValueAsString(dealerList.get(0)))
												.with(SecurityMockMvcRequestPostProcessors.user("test").roles("ADMIN"))
												.with(SecurityMockMvcRequestPostProcessors.csrf()))												
										.andReturn();
			assertEquals(HttpStatus.OK.value(), result1.getResponse().getStatus());
			
			Mockito.when(dealerService.updateDealer(Mockito.any(Dealer.class))).thenReturn(false);
			
			MvcResult result2 = mockMvc.perform(put(url)
					.contentType("application/json")
					.content(objectMapper.writeValueAsString(dealerList.get(0)))
					.with(SecurityMockMvcRequestPostProcessors.user("test").roles("ADMIN"))
					.with(SecurityMockMvcRequestPostProcessors.csrf()))												
			.andReturn();
			
			assertEquals(HttpStatus.NOT_FOUND.value(), result2.getResponse().getStatus());
			
			MvcResult result3 = mockMvc.perform(put(url)
					.contentType("application/json")
					.content(objectMapper.writeValueAsString(dealerList.get(0)))
					.with(SecurityMockMvcRequestPostProcessors.user("test").roles("USER"))
					.with(SecurityMockMvcRequestPostProcessors.csrf()))												
			.andReturn();
			
			assertEquals(HttpStatus.FORBIDDEN.value(), result3.getResponse().getStatus());			
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
	
	/**
	 * Test api to delete the dealer
	 */
	@Test
	public void testDeletDealer() {
				
		String url = "/dealer/d1";
		
		try {
			MvcResult result1 = mockMvc.perform(delete(url)												
												.with(SecurityMockMvcRequestPostProcessors.user("test").roles("ADMIN"))
												.with(SecurityMockMvcRequestPostProcessors.csrf()))												
										.andReturn();
			assertEquals(HttpStatus.OK.value(), result1.getResponse().getStatus());
						
			MvcResult result2 = mockMvc.perform(delete(url)												
					.with(SecurityMockMvcRequestPostProcessors.user("test").roles("USER"))
					.with(SecurityMockMvcRequestPostProcessors.csrf()))												
			.andReturn();
			assertEquals(HttpStatus.FORBIDDEN.value(), result2.getResponse().getStatus());			
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

}
