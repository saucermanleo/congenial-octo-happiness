package com.example.security.sms;

import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.context.request.ServletWebRequest;

import com.example.pojo.ImageCode;

public interface Valiatecode {
	public void valiate(ServletWebRequest request) throws ServletRequestBindingException;
}
