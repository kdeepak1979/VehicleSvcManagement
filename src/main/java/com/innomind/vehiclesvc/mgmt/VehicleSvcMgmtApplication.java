package com.innomind.vehiclesvc.mgmt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableScheduling
@SpringBootApplication
@EnableSwagger2
public class VehicleSvcMgmtApplication {	
	
	@Bean
	public Docket swaggerConfiguration() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.innomind.vehiclesvc.mgmt.controller"))
				.build().apiInfo(getApiInfo());
	}
	
	/**
	 * 
	 * @return
	 */
	@SuppressWarnings("deprecation")
	private ApiInfo getApiInfo() {		
		return new ApiInfo("Vehicle Service Managment API", 
				"APIs to on board new dealers , to manage dealer customers & vehicle service history", 
				"1.0", 
				"Free",
				"Deepak Kumar" , 
				"Licensed", 
				"www.license.com");
	}
	
	public static void main(String[] args) {
		SpringApplication.run(VehicleSvcMgmtApplication.class, args);
	}

	

}
