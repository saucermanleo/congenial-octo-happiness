package com.example.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.UnapprovedClientAuthenticationException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.security.oauth2.provider.TokenRequest;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component(value = "authorizeSuccessHandler")
public class AuthorizeSuccessHandler implements AuthenticationSuccessHandler {

	@Autowired
	ObjectMapper objectMapper;
	@Autowired
	ClientDetailsService clientDetailsService;
	@Autowired
	AuthorizationServerTokenServices authorizationServerTokenServices;

	@SuppressWarnings("unchecked")
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		/*String header = request.getHeader("Authorization");

		if (header == null || !header.startsWith("Basic ")) {
			throw new UnapprovedClientAuthenticationException("请求头无client信息");
		}

		String[] tokens = extractAndDecodeHeader(header, request);
		assert tokens.length == 2;

		String clientid = tokens[0];
		String clientSecrte = tokens[1];
		ClientDetails clientDetails = clientDetailsService.loadClientByClientId(clientid);
		if (clientDetails == null) {
			throw new UnapprovedClientAuthenticationException("clentid 对应的信息不存在");
		} else if (!StringUtils.equals(clientSecrte, clientDetails.getClientSecret())) {
			throw new UnapprovedClientAuthenticationException("clentsecrete不正确");
		}
*/		
		String clientid = "test";
		ClientDetails clientDetails = clientDetailsService.loadClientByClientId(clientid);
		TokenRequest tokenRequest = new TokenRequest(MapUtils.EMPTY_MAP, clientid, clientDetails.getScope(), "custom");

		OAuth2Request auth2Request = tokenRequest.createOAuth2Request(clientDetails);

		OAuth2Authentication auth2Authentication = new OAuth2Authentication(auth2Request, authentication);

		OAuth2AccessToken accesstoken = authorizationServerTokenServices.createAccessToken(auth2Authentication);
		
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().write(objectMapper.writeValueAsString(accesstoken));
	}

	private String[] extractAndDecodeHeader(String header, HttpServletRequest request) throws IOException {

		byte[] base64Token = header.substring(6).getBytes("UTF-8");
		byte[] decoded;
		try {
			decoded = Base64.decode(base64Token);
		} catch (IllegalArgumentException e) {
			throw new BadCredentialsException("Failed to decode basic authentication token");
		}

		String token = new String(decoded, "UTF-8");

		int delim = token.indexOf(":");

		if (delim == -1) {
			throw new BadCredentialsException("Invalid basic authentication token");
		}
		return new String[] { token.substring(0, delim), token.substring(delim + 1) };
	}
}
