package com.example.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.pojo.ImageCode;

public class ValidateCodeFilter extends OncePerRequestFilter {

	private AuthenticationFailureHandler failhande;

	public AuthenticationFailureHandler getFailhande() {
		return failhande;
	}

	public void setFailhande(AuthenticationFailureHandler failhande) {
		this.failhande = failhande;
	}

	private SessionStrategy st = new HttpSessionSessionStrategy();

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		boolean flag = StringUtils.equals("/authentication/form", request.getRequestURI())
				&& StringUtils.equalsIgnoreCase("post", request.getMethod());
		if (flag) {
			try {
				valiatecode(new ServletWebRequest(request));
			} catch (ValidateCodeException e) {
				failhande.onAuthenticationFailure(request, response, e);
				return;
			}
		}
		filterChain.doFilter(request, response);

	}

	private void valiatecode(ServletWebRequest request) throws ServletRequestBindingException {
		ImageCode imagecode = null;
		imagecode = (ImageCode) st.getAttribute(request, ValidateCodeController.SESSION_KEY);
		String code = ServletRequestUtils.getStringParameter(request.getRequest(), "imageCode");
		if (StringUtils.isBlank(code)) {
			throw new ValidateCodeException("验证码不能为空");
		}
		if (imagecode == null) {
			throw new ValidateCodeException("验证码不存在");
		}
		if (imagecode.isExpire()) {
			st.removeAttribute(request, ValidateCodeController.SESSION_KEY);
			throw new ValidateCodeException("验证码过期了");
		}
		if (!StringUtils.equals(imagecode.getCode(), code)) {
			throw new ValidateCodeException("验证码不正确");
		}

		st.removeAttribute(request, ValidateCodeController.SESSION_KEY);
	}

}
