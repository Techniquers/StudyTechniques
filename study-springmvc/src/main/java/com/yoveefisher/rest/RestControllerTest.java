package com.yoveefisher.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/restServices")
public class RestControllerTest {
	
	@RequestMapping("/sayHello")
	public String sayHello(String name){
		if("".equals(name) || name == null) 
			name = "world";
		return "Hello " + name + " !";
	}

}
