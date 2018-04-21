package com.example.pojo;

import com.example.excelexport.GeneralExcelConfig;

public class User {
	@GeneralExcelConfig(value="姓名")
	private String name;
	@GeneralExcelConfig(ingore=true)
	private int age;
	@GeneralExcelConfig(value="性別")
	private String sex;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	@Override
	public String toString() {
		return "User [name=" + name + ", age=" + age + ", sex=" + sex + "]";
	}
	
}
