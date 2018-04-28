package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.properties.MyProperties;
import com.example.security.ValidateCodeFilter;
import com.example.security.sms.SMSAuthenticationFilter;
import com.example.security.sms.SMSAuthenticationProvider;

@Configuration
public class SecruityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private MyProperties mp;
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	@Autowired
	private AuthenticationSuccessHandler myAutenticationsuccessHandler;
	@Autowired
	private AuthenticationFailureHandler authenticationFailureHandler;
	@Autowired
	private UserDetailsService userservice;
	@Autowired
	private SMSAuthenticationFilter sMSAuthenticationFilter;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		ValidateCodeFilter filter = new ValidateCodeFilter();
		filter.setFailhande(authenticationFailureHandler);
		http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class)
			.formLogin()
			.loginPage("/authentication/require")
			.loginProcessingUrl("/authentication/form")
			.successHandler(myAutenticationsuccessHandler)
			.failureHandler(authenticationFailureHandler)
			.and()
			.authorizeRequests()
			.antMatchers("/authentication/require","/authentication/mobile",mp.getLogingpage(),"/code/image").permitAll()
			.anyRequest()
			.authenticated()
			.and()
			.csrf().disable();
		
		AuthenticationManager maager = http.getSharedObject(AuthenticationManager.class);
		sMSAuthenticationFilter.setAuthenticationManager(maager);
		SMSAuthenticationProvider smsprovider = new SMSAuthenticationProvider();
		smsprovider.setUserdetailService(userservice);
		sMSAuthenticationFilter.setAuthenticationSuccessHandler(myAutenticationsuccessHandler);
		sMSAuthenticationFilter.setAuthenticationFailureHandler(authenticationFailureHandler);
		http.authenticationProvider(smsprovider);
		http.addFilterAfter(sMSAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
	}

}
