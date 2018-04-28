package com.example.security.sms;

import java.util.Collection;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

public class SMSAuthenticationToken extends AbstractAuthenticationToken {

	private static final long serialVersionUID = 3871883418062228196L;

	private final Object principal;
	
	private Object credentials;

	public SMSAuthenticationToken(Object principal, Object credentials) {
		super(null);
		this.principal = principal;
		this.credentials = credentials;
		this.setAuthenticated(false);
	}

	public SMSAuthenticationToken(Object principal, Object credentials , Collection<? extends GrantedAuthority> authorities) {
		super(authorities);
		this.principal = principal;
		this.credentials = credentials;
		this.setAuthenticated(true);
	}

	@Override
	public Object getCredentials() {
		return credentials;
	}

	@Override
	public Object getPrincipal() {
		return principal;
	}
	
	public void setCredentials(Object credentials) {
		this.credentials = credentials;
	}


}
