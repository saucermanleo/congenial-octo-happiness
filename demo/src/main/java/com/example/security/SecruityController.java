package com.example.security;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.properties.MyProperties;

@RestController
public class SecruityController {
	private RedirectStrategy rs = new DefaultRedirectStrategy();
	private RequestCache requestCache = new HttpSessionRequestCache();
	@Autowired
	private MyProperties mp;

	@RequestMapping("authentication/require")
	@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
	public String authentication(HttpServletRequest request, HttpServletResponse response) {
		SavedRequest saverequest = requestCache.getRequest(request, response);
		if (saverequest != null) {
			String targetUrl = saverequest.getRedirectUrl();
			if (StringUtils.endsWithIgnoreCase(targetUrl, ".html")) {
				try {
					rs.sendRedirect(request, response, mp.getLogingpage());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return "请登陆";

	}
	@RequestMapping("me")
	public Object me(Authentication autnentication) {
		return autnentication.getPrincipal();
		
	}
	
	@RequestMapping("me1")
	public Object me1(@AuthenticationPrincipal UserDetails user) {
		return user;
		
	}
	
	@RequestMapping("me2")
	public Object me1() {
		return SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
	}
}
