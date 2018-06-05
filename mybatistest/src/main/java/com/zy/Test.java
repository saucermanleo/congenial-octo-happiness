package com.zy;

import com.alibaba.fastjson.JSONObject;
import com.zy.dao.BaseDao;
import com.zy.dao.UserInfoMapper;
import com.zy.pojo.Card;
import org.apache.ibatis.session.SqlSession;

public class Test {
	public static void main(String[] args) {
		SqlSession sqlsession = BaseDao.openSession();
		UserInfoMapper uidao = sqlsession.getMapper(UserInfoMapper.class);

		Card u = uidao.selectcard(4);
		printcard(u);
		System.out.println(JSONObject.toJSON(u).toString());
	}
	
	private static void printcard(Card c) {
		System.out.println(c);
		if(c.getCards().isEmpty()) {
			return;
		}else {
			for(Card card :c.getCards()) {
				printcard(card);
			}
		}
	}
}
