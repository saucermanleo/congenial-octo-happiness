package com.zy.dao;

import org.apache.ibatis.session.SqlSession;

import com.zy.pojo.UserInfo;

public class UserInfoImpl extends BaseDao implements UserInfoMapper {

	public UserInfo select(int id) {
		try (SqlSession session = this.openSession()) {
			return session.getMapper(UserInfoMapper.class).select(id);
		}

	}

}
