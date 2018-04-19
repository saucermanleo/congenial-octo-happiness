package com.example.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.pojo.User;

public class Test1 {

	public static void main(String[] args) {
		List<User> list = new ArrayList<User>();
		
		User u = new User();
		u.setName("zy");
		u.setAge(27);
		u.setSex("男");
		
		list.add(u);
		
		User u1 = new User();
		u.setName("wd");
		u.setAge(25);
		u.setSex("女");
		
		list.add(u1);
		
		Map<String,String> map = new HashMap<String,String>();
		map.put("name", "名字");
		map.put("age", "年龄");
		map.put("sex", "性别");
		
		try {
			Test.createExcel(list, "D:\\\\test.xls", null, map, "花名册");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
