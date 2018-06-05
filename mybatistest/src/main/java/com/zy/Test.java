package com.zy;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zy.dao.BaseDao;
import com.zy.dao.UserInfoMapper;
import com.zy.pojo.Card;
import com.zy.pojo.UserInfo;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class Test {
	public static void main(String[] args) {
		SqlSession sqlsession = BaseDao.openSession();
		UserInfoMapper uidao = sqlsession.getMapper(UserInfoMapper.class);

		Page page = PageHelper.startPage(1,10,true	);
		List<UserInfo> list = uidao.selectbypage();
		long total = page.getTotal();
		System.out.println("total = [" + total + "]");
		for(UserInfo u : list	){
			System.out.println(u);
		}

		/*Card u = uidao.selectcard(4);
		printcard(u);
		System.out.println(JSONObject.toJSON(u).toString());*/
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
