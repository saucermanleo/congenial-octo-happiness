package com.example.security;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="com.zy")
public class MyProperties {
	private String logingpage = "/logintest.html";

	public String getLogingpage() {
		return logingpage;
	}

	public void setLogingpage(String logingpage) {
		this.logingpage = logingpage;
	}
}
