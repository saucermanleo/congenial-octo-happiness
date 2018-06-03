package com.zy.test;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import com.zy.dao.BaseDao;
import com.zy.dao.UserInfoMapper;

public class Testcacke {
	/**
	 * 同一个sqlseesion中的查询具有一级缓存
	 */
	//@Test
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
	
	/**
	 * 开启二级缓存后基于sqlsessionfactory的
	 */
	//@Test
	public void testsecendcache() {
		SqlSession sqlsession1 = BaseDao.openSession();
		UserInfoMapper uidao1 = sqlsession1.getMapper(UserInfoMapper.class);
		
		SqlSession sqlsession2 = BaseDao.openSession();
		UserInfoMapper uidao2 = sqlsession2.getMapper(UserInfoMapper.class);
		uidao1.select(2);
		uidao1.insertbyparam("zy","vvip");
		//提交之后才进入缓存
		sqlsession1.commit();
		uidao2.select(2);
		

	}
	@Test
	public void call() {
		SqlSession sqlsession = BaseDao.openSession();
		UserInfoMapper uidao = sqlsession.getMapper(UserInfoMapper.class);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id1", 1);
		uidao.call(map);
		System.out.println(map.get("SP_name"));
	}
}
