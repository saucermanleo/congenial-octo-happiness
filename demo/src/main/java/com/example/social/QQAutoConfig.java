package com.example.social;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.social.SocialAutoConfigurerAdapter;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.connect.ConnectionFactory;

import com.example.properties.MyProperties;
import com.example.social.connect.QQConnectionFactory;

@Configuration
@ConditionalOnProperty(prefix = "com.zy",name = "appId")
public class QQAutoConfig extends SocialAutoConfigurerAdapter{

	@Autowired
	private MyProperties mp;
	@Override
	protected ConnectionFactory<?> createConnectionFactory() {
		return new QQConnectionFactory("qq", mp.getAppId(), mp.getAppSecret());
	}

}
