package com.example.social;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.security.SocialAuthenticationFilter;
import org.springframework.social.security.SpringSocialConfigurer;

import com.example.security.MyAutenticationsuccessHandler;

public class QQSpringSocialConfigurer extends SpringSocialConfigurer {

	@Autowired
	MyAutenticationsuccessHandler myAutenticationsuccessHandler;
	
	@SuppressWarnings("unchecked")
	@Override
	protected <T> T postProcess(T object) {
		SocialAuthenticationFilter filter =  (SocialAuthenticationFilter) super.postProcess(object);
		filter.setFilterProcessesUrl("/qqLogin");
		filter.setAuthenticationSuccessHandler(myAutenticationsuccessHandler);
		return (T) filter;
	}

}
