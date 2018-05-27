package com.example.dao;

import java.util.List;

import com.example.pojo.UserInfo;

public interface UserInfoMapper {
	 public List<UserInfo> findUserInfo();  
	    public int addUserInfo(UserInfo user);  
	    public int delUserInfo(int id);  
}
