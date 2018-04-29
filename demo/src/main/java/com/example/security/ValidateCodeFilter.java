package com.example.security;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.properties.MyProperties;
import com.example.security.sms.Valiatecode;

@Component
public class ValidateCodeFilter extends OncePerRequestFilter {

	@Autowired
	private Valiatecode valiatecode;
	@Autowired
	MyProperties mp;
	private AntPathMatcher am = new AntPathMatcher();
	
	private AuthenticationFailureHandler failhande;

	public AuthenticationFailureHandler getFailhande() {
		return failhande;
	}

	public void setFailhande(AuthenticationFailureHandler failhande) {
		this.failhande = failhande;
	}

	private Set<String> url= new HashSet<String>();
	
	
	@Override
	public void afterPropertiesSet() throws ServletException {
		super.afterPropertiesSet();
		String[] urls = StringUtils.splitByWholeSeparatorPreserveAllTokens(mp.getVp().getUrls(), ",");
		for(String s : urls) {
			url.add(s);
		}
		url.add("/authentication/form");
	}
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		boolean flag = false;
		for(String u : url) {
			if(am.match(u, request.getRequestURI())) {
				flag = true;
			}
		}
		if (flag) {
			try {
				valiatecode.valiate(new ServletWebRequest(request));
			} catch (ValidateCodeException e) {
				failhande.onAuthenticationFailure(request, response, e);
				return;
			}
		}
		filterChain.doFilter(request, response);

	}

	
}
