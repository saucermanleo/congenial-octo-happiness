package com.example.pojo;

import com.example.excelexport.GeneralExcelConfig;
import com.fasterxml.jackson.annotation.JsonView;

public class User {
	
	public interface View1{} ;
	public interface View2 extends View1{};
	
	@GeneralExcelConfig(value="姓名")
	private String name;
	@GeneralExcelConfig(ingore=true)
	private int age;
	@GeneralExcelConfig(value="性別")
	private String sex;
	
	@JsonView(View1.class)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@JsonView(View2.class)
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
