package com.zy.dao;

import com.zy.pojo.UserInfo;

public interface UserInfoMapper {
	UserInfo select(int id);
	
	int insert(UserInfo userinfo);
}
