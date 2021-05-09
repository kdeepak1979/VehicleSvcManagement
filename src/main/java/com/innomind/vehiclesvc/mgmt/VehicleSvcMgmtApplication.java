package com.innomind.vehiclesvc.mgmt;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.util.Optional;

import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;


import com.innomind.vehiclesvc.mgmt.dto.Dealer;
import com.innomind.vehiclesvc.mgmt.entity.DealerDetail;
import com.innomind.vehiclesvc.mgmt.entity.User;
import com.innomind.vehiclesvc.mgmt.repository.DealerDetailRepository;
import com.innomind.vehiclesvc.mgmt.repository.UserRepository;
import com.innomind.vehiclesvc.mgmt.service.DealerService;

//@EnableScheduling
@SpringBootApplication
public class VehicleSvcMgmtApplication implements CommandLineRunner{

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private DealerDetailRepository dealerDetailRepository;
	
	@Autowired
	private DealerService dealerService;
	
	public static void main(String[] args) {
		SpringApplication.run(VehicleSvcMgmtApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		/*Optional<User> user = userRepository.findByUserName("dkumar");
		
		System.out.println("@@@@@@@@@@@@@@@@@@ VehicleSvcMgmtApplication.run() "+user.get());
		
		DealerDetail dealerDetail = new DealerDetail();
		
		dealerDetail.setName("DKumar Pvt Ltd");
		dealerDetail.setAddress("Lakhimpur , UP, 262701");
		dealerDetail.setPhone("9818842986");
		dealerDetail.setDealer(user.get());
		
		dealerDetailRepository.save(dealerDetail);
		
		System.out.println("@@@@@@@@@@@@@@@@@ VehicleSvcMgmtApplication.run() saved dealer detail");
		
		Optional<DealerDetail> dealerInfo = dealerDetailRepository.findByDealer(user.get());
		
		System.out.println("@@@@@@@@@@@@@@@@@ VehicleSvcMgmtApplication.run() dealerInfo : "+dealerInfo);*/
		
		/*System.out.println("1- @@@@@@@@@@@@@@@@@@ VehicleSvcMgmtApplication.run() "+dealerService.getDealer("dkumar"));
		Dealer dealer = new Dealer();
		dealer.setDealerId("dkumar");
		dealer.setDealerName("dkumar & sons pvt ltd");
		dealer.setPhone("9910279834");
		dealer.setAddress("Lakimpur, 262701, U.P.");
		System.out.println("VehicleSvcMgmtApplication.run() "+dealerService.updateDealer(dealer));
		System.out.println("2- @@@@@@@@@@@@@@@@@@ VehicleSvcMgmtApplication.run() "+dealerService.getDealer("dkumar"));*/
		/*Dealer dealer = new Dealer();
		dealer.setDealerId("vkumar");
		dealer.setPassword("pass");
		dealer.setDealerName("vkumar & sons pvt ltd");
		dealer.setPhone("9910279834");
		dealer.setAddress("Lakimpur, 262701, U.P.");
		dealerService.addDealer(dealer);
		System.out.println("VehicleSvcMgmtApplication.run() "+dealerService.updateDealer(dealer));*/
		
		//testWaranty();
		
	}

	private void testWaranty() throws IOException {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.MULTIPART_FORM_DATA);
		
		MultiValueMap<String, Object> body = new LinkedMultiValueMap();
		Resource resource = new ClassPathResource("20140628_123233.jpg");
		URI uri = resource.getURI();  		
		File f = new File(uri);
		body.add("imageFile", f);
		
		HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

		String serverUrl = "http://localhost:8080/waranty/12ab12";

		RestTemplate restTemplate = new RestTemplate(getClientHttpRequestFactory());
		ResponseEntity<String> response = restTemplate.postForEntity(serverUrl, requestEntity, String.class);
	}
	
	
     
    private HttpComponentsClientHttpRequestFactory getClientHttpRequestFactory() {
        HttpComponentsClientHttpRequestFactory clientHttpRequestFactory
                          = new HttpComponentsClientHttpRequestFactory();
         
        clientHttpRequestFactory.setHttpClient(httpClient());
              
        return clientHttpRequestFactory;
    }
     
    private HttpClient httpClient() {
        CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
 
        credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials("dkumar", "pass"));
        HttpClient client = HttpClientBuilder
                                .create()
                                .setDefaultCredentialsProvider(credentialsProvider)
                                .build();
        return client;
    }

}
