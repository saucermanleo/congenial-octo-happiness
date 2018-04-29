package com.example.social.QQ;

import java.io.IOException;

import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class QQImpl extends AbstractOAuth2ApiBinding implements QQ {

	public static final String URL_GET_OPENID = "https://graph.qq.com/oauth2.0/me?access_token=%s";

	public static final String URL_GET_USERINFO = "https://graph.qq.com/user/get_user_info?oauth_consumer_key=%s&openid=%s";

	private String appid;

	private String openId;
	
	private ObjectMapper objectMapper = new ObjectMapper();

	public QQImpl(String appid, String accessToken) {
		super(accessToken, TokenStrategy.ACCESS_TOKEN_PARAMETER);
		this.appid = appid;

		String url = String.format(URL_GET_OPENID, accessToken);
		String result = this.getRestTemplate().getForObject(url, String.class);
		JSONObject resultjson = (JSONObject) JSONObject.parse(result);
		this.openId = (String) resultjson.get("openid");

	}

	@Override
	public QQUserinfo getUserinfo() {

		String url = String.format(URL_GET_USERINFO, appid, openId);
		String result = this.getRestTemplate().getForObject(url, String.class);
		QQUserinfo userinfo = JSON.parseObject(result, new TypeReference<QQUserinfo>() {
		});
		try {
			userinfo = objectMapper.readValue(result,QQUserinfo.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return userinfo;

	}

}
