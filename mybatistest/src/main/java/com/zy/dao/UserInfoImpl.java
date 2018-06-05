package com.zy.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.zy.pojo.Card;
import com.zy.pojo.UserInfo;

public class UserInfoImpl extends BaseDao implements UserInfoMapper {
	@Override
	public UserInfo select(int id) {
		try (SqlSession session = BaseDao.openSession()) {
			return session.getMapper(UserInfoMapper.class).select(id);
		}

	}

	@Override
	public int insert(UserInfo userinfo) {
		try (SqlSession session = BaseDao.openSession()) {
			int i =  session.getMapper(UserInfoMapper.class).insert(userinfo);
			//session.commit();
			return i;
		}
	}

	@Override
	public void insertbyparam(String name, String password) {
		try (SqlSession session = BaseDao.openSession()) {
			session.getMapper(UserInfoMapper.class).insertbyparam(name,password);
			//session.commit();
		}
	}

	@Override
	public void update(int id , String password) {
		try (SqlSession session = BaseDao.openSession()) {
			session.getMapper(UserInfoMapper.class).update(id,password);
			//session.commit();
		}
	}

	@Override
	public void call(Map<String,Object> map) {
		try (SqlSession session = BaseDao.openSession()) {
			session.getMapper(UserInfoMapper.class).call(map);
			//session.commit();
		}
	}

	@Override
	public UserInfo getUserCardByColleciotn(int id) {
		try (SqlSession session = BaseDao.openSession()) {
			return session.getMapper(UserInfoMapper.class).getUserCardByColleciotn(id);
			//session.commit();
		}
	}
	
	
	@Override
	public UserInfo getUserMytable(int id) {
		try (SqlSession session = BaseDao.openSession()) {
			return session.getMapper(UserInfoMapper.class).getUserMytable(id);
			//session.commit();
		}
	}

	@Override
	public UserInfo getUserMytableByAsociation(int id) {
		try (SqlSession session = BaseDao.openSession()) {
			return session.getMapper(UserInfoMapper.class).getUserMytableByAsociation(id);
			//session.commit();
		}
	}

	@Override
	public List<UserInfo> selectbypage() {
		return null;
	}

	@Override
	public UserInfo getUser(int id) {
		try (SqlSession session = BaseDao.openSession()) {
			return session.getMapper(UserInfoMapper.class).getUser(id);
			//session.commit();
		}
	}

	@Override
	public UserInfo getUser1(int id) {
		try (SqlSession session = BaseDao.openSession()) {
			return session.getMapper(UserInfoMapper.class).getUser1(id);
			//session.commit();
		}
	}

	@Override
	public Card selectcard(int id) {
		try (SqlSession session = BaseDao.openSession()) {
			return session.getMapper(UserInfoMapper.class).selectcard(id);
			//session.commit();
		}
	}

	@Override
	public Card selectcard1(int id) {
		try (SqlSession session = BaseDao.openSession()) {
			return session.getMapper(UserInfoMapper.class).selectcard1(id);
			//session.commit();
		}
	}


}
