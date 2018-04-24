package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.security.MyProperties;
import com.example.security.ValidateCodeFilter;

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
			.antMatchers("/authentication/require",mp.getLogingpage(),"/code/image").permitAll()
			.anyRequest()
			.authenticated()
			.and()
			.csrf().disable();
	}

}
