package com.zy.dao;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class BaseDao {
	
	private volatile static SqlSessionFactory sqlSessionFactory = null;
	
	
	public  static SqlSessionFactory  SingotenInstence() throws IOException {
		if(sqlSessionFactory == null) {
			synchronized(BaseDao.class) {
				if(sqlSessionFactory == null) {
					String resource = "mybatis-config.xml";
					InputStream inputStream = Resources.getResourceAsStream(resource);
					sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
				}
			}
		}
		return sqlSessionFactory;
	}
	
	public static SqlSession openSession() {
		SqlSession sqlSession = null;
		try {
			sqlSession=  SingotenInstence().openSession();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sqlSession;
	}
	
}
