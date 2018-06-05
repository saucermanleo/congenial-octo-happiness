package com.zy.dao;

import com.zy.pojo.Card;
import com.zy.pojo.UserInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserInfoMapper {
	UserInfo select(int id);
	
	int insert(UserInfo userinfo);
	
	void insertbyparam(@Param(value = "name") String name,@Param(value = "password") String password);

	void update(@Param(value = "id") int id, @Param(value = "password") String password);
	
	void call(Map<String,Object> map);
	
	UserInfo getUserCardByColleciotn(int id);
	
	UserInfo getUser(int id);
	
	UserInfo getUser1(int id);
	
	Card selectcard(int id);
	
	Card selectcard1(int id);
	
	UserInfo getUserMytable(int id);
	
	UserInfo getUserMytableByAsociation(int id);
	
	List<UserInfo> selectbypage();
	
	
}
