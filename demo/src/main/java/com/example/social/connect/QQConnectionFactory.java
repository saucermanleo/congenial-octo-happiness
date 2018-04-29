package com.example.social.connect;

import org.springframework.social.connect.support.OAuth2ConnectionFactory;

import com.example.social.QQ.QQ;

public class QQConnectionFactory extends OAuth2ConnectionFactory<QQ> {

	public QQConnectionFactory(String providerId, String appid,String clientSecret) {
		super(providerId, new QQServiceProvider(appid, clientSecret), new QQAdapter());
	}

}
