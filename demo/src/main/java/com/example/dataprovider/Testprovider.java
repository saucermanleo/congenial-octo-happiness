package com.example.dataprovider;

import java.util.ArrayList;
import java.util.List;

public class Testprovider {

	public static void main(String[] args) {
		DataProvider p = new DataProvider();
		System.out.println("**********************************************");
		String sql = "select * from mytable";
		String s = p.getData(sql);
		System.out.println(s);
		
		String sql1 = "call myprocedure";
		p.callProcedure(sql1);
		
		String sql2 = "call myprocedure1(?)";
		p.callProcedurewithIN(sql2,"dyb");
		
		String sql3 = "call myprocedure2(?)";
		p.callProcedurewithOUT(sql3);
		
		String sql4 = "call myprocedure3(?,?,?)";
		p.callProcedurewithIN$OUT(sql4);
		
		/*List<String> sqls = new ArrayList<String>();
		sqls.add("INSERT INTO `cboard`.`mytable` (`id`, `name`, `age`, `sex`, `idnumber`) VALUES ('9', 'wq', '26', '0', '1234567891223')");
		sqls.add("INSERT INTO `cboard`.`mytable` (`id`, `name`, `age`, `sex`, `idnumber`) VALUES ('8', 'wq', '26', '0', '1234567891223')");
		p.Bach(sqls);*/
	}
	
}
