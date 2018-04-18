package com.example.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Testcontronller {
	
	@RequestMapping(value="/hello" , method=RequestMethod.GET )
	public String sayHello() {
		return "hello springboot";
	}
}
