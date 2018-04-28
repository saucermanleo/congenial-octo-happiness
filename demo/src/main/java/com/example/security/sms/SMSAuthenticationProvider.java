package com.example.security.sms;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public class SMSAuthenticationProvider implements AuthenticationProvider {
	
	private UserDetailsService userdetailService;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		SMSAuthenticationToken authenticationToken = (SMSAuthenticationToken)authentication;
		User u = (User) userdetailService.loadUserByUsername(authenticationToken.getName());
		if(u == null) {
			throw new InternalAuthenticationServiceException("无法获取认证信息");
		}
		SMSAuthenticationToken SMSAuthenticationTokenresult = new SMSAuthenticationToken(authenticationToken.getPrincipal(),authenticationToken.getCredentials(),authenticationToken.getAuthorities());
		SMSAuthenticationTokenresult.setDetails(authenticationToken.getDetails());
		return SMSAuthenticationTokenresult;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return SMSAuthenticationToken.class.isAssignableFrom(authentication);
	}

	public UserDetailsService getUserdetailService() {
		return userdetailService;
	}

	public void setUserdetailService(UserDetailsService userdetailService) {
		this.userdetailService = userdetailService;
	}

}
