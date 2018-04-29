package com.example.properties;

import org.springframework.boot.autoconfigure.social.SocialProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="com.zy")
public class MyProperties extends SocialProperties{
	private String logingpage = "/logintest.html";
	
	
	private int remebermSecends = 3600*24*7;
	
	public int getRemebermSecends() {
		return remebermSecends;
	}

	public void setRemebermSecends(int remebermSecends) {
		this.remebermSecends = remebermSecends;
	}

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
