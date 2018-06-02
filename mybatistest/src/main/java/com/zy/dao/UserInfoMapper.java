package com.zy.dao;

import org.apache.ibatis.annotations.Param;

import com.zy.pojo.UserInfo;

public interface UserInfoMapper {
	UserInfo select(int id);
	
	int insert(UserInfo userinfo);
	
	void insertbyparam(@Param(value = "name") String name,@Param(value = "password") String password);

	void update(@Param(value = "id") int id, @Param(value = "password") String password);
}
