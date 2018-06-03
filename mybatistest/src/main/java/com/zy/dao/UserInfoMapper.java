package com.zy.dao;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.zy.pojo.UserInfo;

public interface UserInfoMapper {
	UserInfo select(int id);
	
	int insert(UserInfo userinfo);
	
	void insertbyparam(@Param(value = "name") String name,@Param(value = "password") String password);

	void update(@Param(value = "id") int id, @Param(value = "password") String password);
	
	void call(Map<String,Object> map);
}
