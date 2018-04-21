package com.example.controller;

import javax.validation.Valid;

import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.pojo.User;

@RestController
public class ValidTestController {
	
	@PostMapping(value = "testvalid" , consumes = "application/json")
	public User testvalid(@Valid @RequestBody User u,BindingResult errers) {
		if(errers.hasErrors()) {
			for(ObjectError e :errers.getAllErrors()) {
				/*FieldError fe = (FieldError)e ;
				String message = fe.getField()+" "+e.getDefaultMessage();
				System.out.println(message);*/
				System.out.println(e.getDefaultMessage());
			}
		}
		return u;
	}
}
