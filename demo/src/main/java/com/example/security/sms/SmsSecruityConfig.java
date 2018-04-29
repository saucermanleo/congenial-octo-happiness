package com.example.security.sms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

@Component
public class SmsSecruityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

	/*@Autowired
	private MyProperties mp;*/
	@Autowired
	private Valiatecode valitecode;
	@Autowired
	private AuthenticationSuccessHandler myAutenticationsuccessHandler;
	@Autowired
	private AuthenticationFailureHandler authenticationFailureHandler;
	@Autowired
	private UserDetailsService userservice;

	@Override
	public void configure(HttpSecurity http) throws Exception {
		SMSAuthenticationFilter smsfilter = new SMSAuthenticationFilter();
		AuthenticationManager maager = http.getSharedObject(AuthenticationManager.class);
		smsfilter.setAuthenticationManager(maager);
		smsfilter.setValiatecode(valitecode);
		smsfilter.setFilterProcessesUrl("/authentication/mobile");
		SMSAuthenticationProvider smsprovider = new SMSAuthenticationProvider();
		smsprovider.setUserdetailService(userservice);
		smsfilter.setAuthenticationSuccessHandler(myAutenticationsuccessHandler);
		smsfilter.setAuthenticationFailureHandler(authenticationFailureHandler);
		http.authenticationProvider(smsprovider)
		.addFilterAfter(smsfilter, UsernamePasswordAuthenticationFilter.class);
	}

}
