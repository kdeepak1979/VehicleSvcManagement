package com.innomind.vehiclesvc.mgmt.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	
	@GetMapping(path="/")
	public String home(){
		return "welcome";
	}
	
	@GetMapping(path="/user")
	public String user(){
		return "hello user";
	}
	
	//@PreAuthorize("hasAnyRole('ADMIN')")
	@GetMapping(path="/admin")
	public String hello_secure(){
		return "hello admin";
	}
}
