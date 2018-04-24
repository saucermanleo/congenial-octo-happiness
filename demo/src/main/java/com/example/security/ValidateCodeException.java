package com.example.security;

import org.springframework.security.core.AuthenticationException;

public class ValidateCodeException extends AuthenticationException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -585635849343012623L;

	public ValidateCodeException(String msg) {
		super(msg);
	}

}
