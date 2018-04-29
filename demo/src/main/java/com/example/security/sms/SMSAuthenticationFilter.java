package com.example.security.sms;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.context.request.ServletWebRequest;

import com.example.pojo.ImageCode;
import com.example.security.ValidateCodeException;

public class SMSAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

	private Valiatecode valiatecode;

	public static final String SPRING_SECURITY_FORM_USERNAME_KEY = "mobile";
	

	private String usernameParameter = SPRING_SECURITY_FORM_USERNAME_KEY;
	private String passwordParameter = ImageCode.SPRING_SECURITY_FORM_PASSWORD_KEY;
	private boolean postOnly = true;

	public SMSAuthenticationFilter() {
		super(new AntPathRequestMatcher("/authentication/mobile", "POST"));
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {
		if (postOnly && !request.getMethod().equals("POST")) {
			throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
		}

		String username = obtainUsername(request);
		String password = obtainPassword(request);

		if (username == null) {
			username = "";
		}

		if (password == null) {
			password = "";
		}

		username = username.trim();
		/*
		 * ImageCode imagecode = (ImageCode) sessionStrategy.getAttribute(new
		 * ServletWebRequest(request), ValidateCodeController.SESSION_KEY); String code
		 * = imagecode.getCode(); if (!StringUtils.equals(code, password)) { throw new
		 * InternalAuthenticationServiceException("验证码错误"); }
		 */

		try {
			valiatecode.valiate(new ServletWebRequest(request));
		} catch (ValidateCodeException e) {
			this.unsuccessfulAuthentication(request, response, e);
			return null;
		}

		SMSAuthenticationToken authRequest = new SMSAuthenticationToken(username, password);
		this.setDetails(request, authRequest);
		return this.getAuthenticationManager().authenticate(authRequest);
	}

	protected String obtainUsername(HttpServletRequest request) {
		return request.getParameter(usernameParameter);
	}

	protected String obtainPassword(HttpServletRequest request) {
		return request.getParameter(passwordParameter);
	}

	protected void setDetails(HttpServletRequest request, SMSAuthenticationToken authRequest) {
		authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
	}

	public String getUsernameParameter() {
		return usernameParameter;
	}

	public void setUsernameParameter(String usernameParameter) {
		this.usernameParameter = usernameParameter;
	}

	public String getPasswordParameter() {
		return passwordParameter;
	}

	public void setPasswordParameter(String passwordParameter) {
		this.passwordParameter = passwordParameter;
	}

	public boolean isPostOnly() {
		return postOnly;
	}

	public void setPostOnly(boolean postOnly) {
		this.postOnly = postOnly;
	}

	public Valiatecode getValiatecode() {
		return valiatecode;
	}

	public void setValiatecode(Valiatecode valiatecode) {
		this.valiatecode = valiatecode;
	}

}
