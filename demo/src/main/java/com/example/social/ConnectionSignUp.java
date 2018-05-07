package com.example.social;

import org.springframework.social.connect.Connection;
import org.springframework.stereotype.Component;

@Component
public class ConnectionSignUp implements org.springframework.social.connect.ConnectionSignUp {

	@Override
	public String execute(Connection<?> connection) {
		
		return connection.getDisplayName();
	}

}
