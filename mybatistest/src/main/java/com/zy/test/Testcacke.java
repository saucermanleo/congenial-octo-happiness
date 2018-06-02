package com.zy.test;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import com.zy.dao.BaseDao;
import com.zy.dao.UserInfoMapper;

public class Testcacke {
	/**
	 * 同一个sqlseesion中的查询具有一级缓存
	 */
	@Test
	public void testcache() {
		SqlSession sqlsession = BaseDao.openSession();
		UserInfoMapper uidao = sqlsession.getMapper(UserInfoMapper.class);
		//从数据库查询
		uidao.select(2);
		//这条使用缓存
		uidao.select(2);
		//清空缓存
		uidao.insertbyparam("zy","vvip");
		//uidao.update(2,"修改密码");
		//从数据库查询
		uidao.select(2);
	}
}
