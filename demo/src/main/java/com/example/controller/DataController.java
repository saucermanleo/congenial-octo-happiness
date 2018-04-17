package com.example.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DataController {
	
	@RequestMapping("/hello" )
	public Object getData() {
		return "hello springboot";
	}
}
