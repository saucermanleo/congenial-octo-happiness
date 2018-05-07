package com.example.social.connect;

import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;
import org.springframework.social.oauth2.OAuth2Template;

import com.example.social.QQ.QQ;
import com.example.social.QQ.QQImpl;
import com.example.social.QQ.QQOAuth2Template;

public class QQServiceProvider extends AbstractOAuth2ServiceProvider<QQ>{

	public static final String URL_AUTHORIZE = "https://graph.qq.com/oauth2.0/authorize";
	
	public static final String URL_ACCESSTOKEN = "https://graph.qq.com/oauth2.0/token";
	
	private String appid ;
	
	public QQServiceProvider(String appid,String clientSecret) {
		super(new QQOAuth2Template(appid, clientSecret, URL_AUTHORIZE, URL_ACCESSTOKEN));
		this.appid = appid;
	}

	@Override
	public QQ getApi(String accessToken) {
		return new QQImpl(appid, accessToken);
	}

}
