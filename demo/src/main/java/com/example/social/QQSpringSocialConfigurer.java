package com.example.social;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.social.security.SocialAuthenticationFilter;
import org.springframework.social.security.SpringSocialConfigurer;

public class QQSpringSocialConfigurer extends SpringSocialConfigurer {

	@Autowired
	@Qualifier(value="authorizeSuccessHandler")
	private AuthenticationSuccessHandler authorizeSuccessHandler;
	
	@SuppressWarnings("unchecked")
	@Override
	protected <T> T postProcess(T object) {
		SocialAuthenticationFilter filter =  (SocialAuthenticationFilter) super.postProcess(object);
		filter.setFilterProcessesUrl("/qqLogin");
		filter.setAuthenticationSuccessHandler(authorizeSuccessHandler);
		return (T) filter;
	}

}
