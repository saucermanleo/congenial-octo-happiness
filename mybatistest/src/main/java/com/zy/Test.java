package com.zy;

import com.zy.dao.UserInfoImpl;
import com.zy.dao.UserInfoMapper;

public class Test {
	public static void main(String[] args) {
		UserInfoMapper infoMapper = new UserInfoImpl();
		System.out.println(infoMapper.select(1));
	}
}
