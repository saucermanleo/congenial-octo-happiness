package com.example.interceptor;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/*@Configuration*/
public class InterceptorConfig extends WebMvcConfigurationSupport{
	
	@Autowired
	private TimeInterceptor timeinterceptor;
	
	@Override
	protected void addInterceptors(InterceptorRegistry registry) {
		super.addInterceptors(registry);
		registry.addInterceptor(timeinterceptor);
	}

	@Bean
	public FilterRegistrationBean<TimeFilter> timeFilter (){
		
		TimeFilter tf = new TimeFilter();
		FilterRegistrationBean<TimeFilter> fr = new FilterRegistrationBean<TimeFilter>();
		fr.setFilter(tf);
		List<String> urls = new ArrayList<String>();
		urls.add("/zy/*");
		fr.setUrlPatterns(urls);
		return fr;
		
	}
}
