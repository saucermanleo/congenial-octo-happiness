package com.example.excelexport;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.pojo.User;

import jxl.write.WriteException;

public class Test {

	public static void main(String[] args) {
		List<User> list = new ArrayList<User>();
		
		User u = new User();
		u.setName("zy");
		u.setAge(27);
		u.setSex("男");
		
		list.add(u);
		
		User u1 = new User();
		u1.setName("wd");
		u1.setAge(25);
		u1.setSex("女");
		
		list.add(u1);
		
		Map<String,String> map = new HashMap<String,String>();
		map.put("name", "名字");
		map.put("age", "年龄");
		map.put("sex", "性别");
		
			try {
				ExcelParams ep = new ExcelParams(list, User.class);
				GerneralExcel.createExcel(ep, null);
			} catch (WriteException e) {
				e.printStackTrace();
			}
	}

}
