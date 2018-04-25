package com.example.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="com.zy")
public class MyProperties {
	private String logingpage = "/logintest.html";
	
	private ValidateCodeProperties vp = new ValidateCodeProperties();

	public ValidateCodeProperties getVp() {
		return vp;
	}

	public void setVp(ValidateCodeProperties vp) {
		this.vp = vp;
	}

	public String getLogingpage() {
		return logingpage;
	}

	public void setLogingpage(String logingpage) {
		this.logingpage = logingpage;
	}
}
