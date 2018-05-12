package com.example.authorization;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
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
@EnableResourceServer
public class ResourceServerconfig extends ResourceServerConfigurerAdapter{


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
	@Qualifier(value="authorizeSuccessHandler")
	private AuthenticationSuccessHandler authorizeSuccessHandler;
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
	public void configure(HttpSecurity http) throws Exception {
		//filter.setFailhande(authenticationFailureHandler);
		http//.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class)
			.formLogin()
				.loginPage("/authentication/require")
				.loginProcessingUrl("/authentication/form")
				.successHandler(authorizeSuccessHandler)
				.failureHandler(authenticationFailureHandler)
				.and()
			.authorizeRequests()
				.antMatchers("/authentication/require","/authentication/mobile","qqLogin/callback.do",
						"/signin",mp.getLogingpage(),"/code/image").permitAll()
				.anyRequest()
				.authenticated()
			.and()
				.csrf().disable()
			//.apply(smsSecruityConfig)
				//.and()
			.apply(springSocialConfigurer);
		/*.and()
			.rememberMe()
				.tokenValiditySeconds(mp.getRemebermSecends())
				.tokenRepository(persistentTokenRepository())
				.userDetailsService(userDetailsService);*/
		
	}
}
