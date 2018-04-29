package com.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.pojo.User;
import com.example.pojo.User.View2;
import com.fasterxml.jackson.annotation.JsonView;

@RestController
public class TestJsonviewController {
	
	@JsonView(View2.class)
	@GetMapping("testjsonview")
	public User testJsonview() {
		User u = new User();
		u.setName("zy");
		u.setAge(27);
		u.setSex("男");
		return u;
	}
}
