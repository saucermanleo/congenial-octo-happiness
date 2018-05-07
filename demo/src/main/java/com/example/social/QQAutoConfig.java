package com.example.social;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.social.SocialAutoConfigurerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.connect.ConnectionFactory;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.security.SpringSocialConfigurer;

import com.example.properties.MyProperties;
import com.example.security.MyAutenticationsuccessHandler;
import com.example.social.connect.QQConnectionFactory;

@EnableSocial
@Configuration
@ConditionalOnProperty(prefix = "com.zy", name = "appId")
public class QQAutoConfig extends SocialAutoConfigurerAdapter {

	@Autowired
	DataSource datasource;
	
	

	@Autowired(required = false)
	ConnectionSignUp connectionSignUp;

	@Override
	public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
		JdbcUsersConnectionRepository jdbcUsersConnectionRepository = new JdbcUsersConnectionRepository(datasource,
				connectionFactoryLocator, Encryptors.noOpText());
		if (connectionSignUp != null) {
			jdbcUsersConnectionRepository.setConnectionSignUp(connectionSignUp);
		}
		return jdbcUsersConnectionRepository;
	}

	@Autowired
	private MyProperties mp;

	@Override
	protected ConnectionFactory<?> createConnectionFactory() {
		return new QQConnectionFactory("callback.do", mp.getAppId(), mp.getAppSecret());
	}

	@Bean
	public SpringSocialConfigurer socialConfigurer() {
		return new QQSpringSocialConfigurer();
	}

}
