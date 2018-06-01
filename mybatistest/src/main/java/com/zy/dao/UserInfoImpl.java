package com.zy.dao;

import org.apache.ibatis.session.SqlSession;

import com.zy.pojo.UserInfo;

public class UserInfoImpl extends BaseDao implements UserInfoMapper {
	@Override
	public UserInfo select(int id) {
		try (SqlSession session = this.openSession()) {
			return session.getMapper(UserInfoMapper.class).select(id);
		}

	}

	@Override
	public int insert(UserInfo userinfo) {
		try (SqlSession session = this.openSession()) {
			int i =  session.getMapper(UserInfoMapper.class).insert(userinfo);
			//session.commit();
			return i;
		}
	}

}
