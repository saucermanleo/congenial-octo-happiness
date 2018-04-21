package com.example.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.excelexport.ExcelParams;
import com.example.excelexport.GerneralExcel;
import com.example.pojo.User;

import jxl.write.WriteException;

@RestController
public class Testcontronller {

	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public String sayHello() {
		return "hello springboot";
	}

	@RequestMapping(value = "/excel", method = RequestMethod.GET)
	public void loadExcel(HttpServletResponse response) {
		try {
			String filename = "test";
			filename = URLEncoder.encode(filename, "utf-8");
			response.setContentType("application/x-msdownload");
			response.setHeader("Content-Disposition", "attachment; filename=" + filename + ".xls");
			OutputStream out = response.getOutputStream();
			
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
					GerneralExcel.createExcel(ep, out);
					out.flush();
				} catch (WriteException e) {
					e.printStackTrace();
				}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
