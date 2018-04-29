package com.example.social.connect;

import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;

import com.example.social.QQ.QQ;
import com.example.social.QQ.QQUserinfo;

public class QQAdapter implements ApiAdapter<QQ> {

	@Override
	public boolean test(QQ api) {
		return true;
	}

	@Override
	public void setConnectionValues(QQ api, ConnectionValues values) {
		QQUserinfo userinfo = api.getUserinfo();
		values.setDisplayName(userinfo.getNickname());
		values.setImageUrl(userinfo.getFigureurl_qq_1());
		values.setProfileUrl(null);
		values.setProviderUserId(userinfo.getOpenId());
	}

	@Override
	public UserProfile fetchUserProfile(QQ api) {
		return null;
	}

	@Override
	public void updateStatus(QQ api, String message) {
		
	}

}
