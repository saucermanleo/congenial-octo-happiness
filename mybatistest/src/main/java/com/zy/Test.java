package com.zy;

import com.zy.dao.UserInfoImpl;
import com.zy.dao.UserInfoMapper;
import com.zy.pojo.UserInfo;

public class Test {
	public static void main(String[] args) {
		UserInfoMapper infoMapper = new UserInfoImpl();
		System.out.println(infoMapper.select(1));
		UserInfo userinfo = new UserInfo("wd","vip");
		System.out.println(infoMapper.insert(userinfo));
	}
}