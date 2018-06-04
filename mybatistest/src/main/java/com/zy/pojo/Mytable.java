package com.zy.pojo;

public class Mytable {
	private int id;
	private String name;
	private int age;
	private int sex;
	private String idnumber;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
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
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public String getIdnumber() {
		return idnumber;
	}
	public void setIdnumber(String idnumber) {
		this.idnumber = idnumber;
	}
	@Override
	public String toString() {
		return "Mytable [id=" + id + ", name=" + name + ", age=" + age + ", sex=" + sex + ", idnumber=" + idnumber
				+ "]";
	}
	
	
}
