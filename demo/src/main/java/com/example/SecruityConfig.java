package com.example;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.social.security.SpringSocialConfigurer;

import com.example.properties.MyProperties;
import com.example.security.ValidateCodeFilter;
import com.example.security.sms.SmsSecruityConfig;

@Configuration
public class SecruityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private MyProperties mp;
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	@Autowired
	DataSource datasource;
	@Autowired
	ValidateCodeFilter filter ;
	@Autowired
	private AuthenticationSuccessHandler myAutenticationsuccessHandler;
	@Autowired
	private AuthenticationFailureHandler authenticationFailureHandler;
	
	@Autowired
	SpringSocialConfigurer springSocialConfigurer;
	
	@Bean
	public PersistentTokenRepository persistentTokenRepository() {
		JdbcTokenRepositoryImpl jdbcTokenRepositoryImpl = new JdbcTokenRepositoryImpl();
		jdbcTokenRepositoryImpl.setDataSource(datasource);
		return jdbcTokenRepositoryImpl;
	}
	
	@Autowired
	UserDetailsService userDetailsService;
	
	@Autowired
	private SmsSecruityConfig smsSecruityConfig;
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		filter.setFailhande(authenticationFailureHandler);
		http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class)
			.formLogin()
				.loginPage("/authentication/require")
				.loginProcessingUrl("/authentication/form")
				.successHandler(myAutenticationsuccessHandler)
				.failureHandler(authenticationFailureHandler)
				.and()
			.authorizeRequests()
				.antMatchers("/authentication/require","/authentication/mobile","qqLogin/callback.do",
						"/signin",mp.getLogingpage(),"/code/image").permitAll()
				.anyRequest()
				.authenticated()
			.and()
				.csrf().disable()
			.apply(smsSecruityConfig)
				.and()
			.apply(springSocialConfigurer)
				.and()
			.rememberMe()
				.tokenValiditySeconds(mp.getRemebermSecends())
				.tokenRepository(persistentTokenRepository())
				.userDetailsService(userDetailsService);
		
	}

}
