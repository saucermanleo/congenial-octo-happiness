package com.example.security.sms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.properties.MyProperties;


public class SmsSecruityConfig extends  WebSecurityConfigurerAdapter {

	@Autowired
	private MyProperties mp;
	@Autowired
	private AuthenticationSuccessHandler myAutenticationsuccessHandler;
	@Autowired
	private AuthenticationFailureHandler authenticationFailureHandler;
	@Autowired
	private UserDetailsService userservice;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		SMSAuthenticationFilter smsfilter = new SMSAuthenticationFilter();
		AuthenticationManager maager = http.getSharedObject(AuthenticationManager.class);
		smsfilter.setAuthenticationManager(maager);
		SMSAuthenticationProvider smsprovider = new SMSAuthenticationProvider();
		smsprovider.setUserdetailService(userservice);
		smsfilter.setAuthenticationSuccessHandler(myAutenticationsuccessHandler);
		smsfilter.setAuthenticationFailureHandler(authenticationFailureHandler);
		http.authenticationProvider(smsprovider);
		http.addFilterAfter(smsfilter, UsernamePasswordAuthenticationFilter.class);
	}

}
