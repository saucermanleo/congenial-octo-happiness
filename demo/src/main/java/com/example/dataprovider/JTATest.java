package com.example.dataprovider;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.transaction.UserTransaction;

/**
 * 分布式事务
 * @author Administrator
 *
 */
public class JTATest {
	public void test() {
		Context ctx;
		try {
			ctx = new InitialContext();
			UserTransaction trans = (UserTransaction) ctx.lookup("javax. Transaction.UserTransaction");
		} catch (NamingException e) {
			e.printStackTrace();
		}
		
	}
}
