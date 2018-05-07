package com.example.social;

import org.springframework.social.security.SocialAuthenticationFilter;
import org.springframework.social.security.SpringSocialConfigurer;

public class QQSpringSocialConfigurer extends SpringSocialConfigurer {

	@Override
	protected <T> T postProcess(T object) {
		SocialAuthenticationFilter filter =  (SocialAuthenticationFilter) super.postProcess(object);
		filter.setFilterProcessesUrl("/qqLogin");
		return (T) filter;
	}

}
