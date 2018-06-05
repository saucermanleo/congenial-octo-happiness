package com.zy.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import com.zy.dao.BaseDao;
import com.zy.dao.UserInfoMapper;
import com.zy.pojo.Card;
import com.zy.pojo.Mytable;
import com.zy.pojo.UserInfo;

public class Testcacke {
	/**
	 * 同一个sqlseesion中的查询具有一级缓存
	 */
	private UserInfoMapper uidao = getUserMapper();

    /**
     * 造数据(每次100w)
     * @throws InterruptedException
     */
	//@Test
    public void gerateData( ) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        int count = 10;
        CountDownLatch countDownLatch = new CountDownLatch(count);
        for(int i = 0;i<count;i++){
            executorService.execute(()->{
                SqlSession sqlsession = BaseDao.openSession();
                UserInfoMapper uidao1 = sqlsession.getMapper(UserInfoMapper.class);
                for(int j=0;j<100000;j++){
                    UserInfo u = new UserInfo(getRandomString(44),getRandomString(44));
                    uidao1.insert(u);
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        System.out.println("ok");
    }

    public  String getRandomString(int length) { //length表示生成字符串的长度
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

	@Test
	public void testcache() {
		// 从数据库查询
		uidao.select(2);
		// 这条使用缓存
		uidao.select(2);
		// 清空缓存
		uidao.insertbyparam("zy", "vvip");
		// uidao.update(2,"修改密码");
		// 从数据库查询
		uidao.select(2);
	}

	/**
	 * 开启二级缓存后基于sqlsessionfactory的
	 */
	@Test
	public void testsecendcache() {
		SqlSession sqlsession1 = BaseDao.openSession();
		UserInfoMapper uidao1 = getUserMapper();

		SqlSession sqlsession2 = BaseDao.openSession();
		UserInfoMapper uidao2 = sqlsession2.getMapper(UserInfoMapper.class);
		uidao1.select(2);
		uidao1.insertbyparam("zy", "vvip");
		// 提交之后才进入缓存
		sqlsession1.commit();
		uidao2.select(2);

	}

	/**
	 * 调用存储过程
	 */
	@Test
	public void call() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id1", 1);
		uidao.call(map);
		System.out.println(map.get("SP_name"));
	}

	/**
	 * 关联查询
	 */
	@Test
	public void testAsociation() {

		// 一对多collection查询
		UserInfo u = uidao.getUserCardByColleciotn(2);
		pirntUserInfo(u);
		// 一对一普通查询
		u = uidao.getUserMytable(2);
		pirntUserInfo(u);
		// 一对一 association查询
		u = uidao.getUserMytableByAsociation(2);
		pirntUserInfo(u);

		u = uidao.getUser(2);
		pirntUserInfo(u);

		u = uidao.getUser1(2);
		pirntUserInfo(u);
	}

	/**
	 * 递归查询
	 */
	@Test
	public void RecursionSelect() {
		Card c = uidao.selectcard1(9);
		print(c);

		c = uidao.selectcard(4);
		printcard(c);
		System.out.println(JSONObject.toJSON(c).toString());
	}

	/**
	 * 分页插件测试
	 */
	@Test
	public void PageHelperTest(	){
		Page page = PageHelper.startPage(100000,10,true	);
		List<UserInfo> list = uidao.selectbypage();
		long tatal = page.getTotal();
		System.out.println(tatal);
		for(UserInfo u : list	){
			System.out.println(u);
		}

	}

    /**
     * 返回自动生成主键
     */
	@Test
    public void getGenerateKey(){
        UserInfo u  = new UserInfo("zy","wd" );
        int i = uidao.insert(u);
        System.out.println(i);
        System.out.println(u.getId());
    }




	private  void printcard(Card c) {
		System.out.println(c);
		if(c.getCards().isEmpty()) {
			return;
		}else {
			for(Card card :c.getCards()) {
				printcard(card);
			}
		}
	}
	private  void print(Card d) {
		System.out.println(d);
		if(d.getCard()==null) {
			return;
		}else {
			print(d.getCard());
		}
	}
	
	private void pirntUserInfo(UserInfo u) {
		System.out.println(u);
		Mytable m = u.getMytable();
		System.out.println(m);
		for (Card c : u.getCards()) {
			System.out.println(c);
		}
	}

	private UserInfoMapper getUserMapper() {
		SqlSession sqlsession = BaseDao.openSession();
		return sqlsession.getMapper(UserInfoMapper.class);
	}
}
