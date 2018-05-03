package com.zy.pmk.pojo;

import java.util.List;

public class A implements InterfaceB,InterfaceA<String>{
	private String feild1;
	private String feild2;
	private User user;
	
	
	
	public A(String feild1, String feild2, User user) {
		super();
		this.feild1 = feild1;
		this.feild2 = feild2;
		this.user = user;
	}
	
	
	public A() {
		super();
	}
	
	@SuppressWarnings("unused")
	private String method(String name ,String password) throws Exception {
		throw new Exception();
		
	}
	
	public <T> String method1(String name ,String password,List<T> list) {
		return password;
	}

	public String getFeild1() {
		return feild1;
	}
	public void setFeild1(String feild1) {
		this.feild1 = feild1;
	}
	public String getFeild2() {
		return feild2;
	}
	public void setFeild2(String feild2) {
		this.feild2 = feild2;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	
}
