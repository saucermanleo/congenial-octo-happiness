package com.zy;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.zy.dao.BaseDao;
import com.zy.dao.UserInfoImpl;
import com.zy.dao.UserInfoMapper;
import com.zy.pojo.UserInfo;

public class Test {
	public static void main(String[] args) {
		/*UserInfoMapper infoMapper = new UserInfoImpl();
		infoMapper.select(1);
		UserInfo userinfo = new UserInfo("张阳","vip");
		infoMapper.insert(userinfo);*/
		
		SqlSession sqlsession = BaseDao.openSession();
		UserInfoMapper uidao = sqlsession.getMapper(UserInfoMapper.class);
		/*//从数据库查询
		uidao.select(2);
		//这条使用缓存
		uidao.select(2);
		//清空缓存
		uidao.insertbyparam("zy","vvip");
		//uidao.update(2,"修改密码");
		//从数据库查询
		uidao.select(2);*/
		HashMap map = new HashMap();
		//map.put("SP_name", "");
		map.put("id1", 1);
		uidao.call(map);
		System.out.println(map.get("SP_name"));
		
	}
}
