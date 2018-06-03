package com.zy.dao;

import java.util.Map;

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

	@Override
	public void insertbyparam(String name, String password) {
		try (SqlSession session = this.openSession()) {
			session.getMapper(UserInfoMapper.class).insertbyparam(name,password);
			//session.commit();
		}
	}

	@Override
	public void update(int id , String password) {
		try (SqlSession session = this.openSession()) {
			session.getMapper(UserInfoMapper.class).update(id,password);
			//session.commit();
		}
	}

	@Override
	public void call(Map<String,Object> map) {
		try (SqlSession session = this.openSession()) {
			session.getMapper(UserInfoMapper.class).call(map);
			//session.commit();
		}
	}

}
