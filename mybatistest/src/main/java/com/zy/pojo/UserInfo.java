package com.zy.pojo;

import java.util.ArrayList;
import java.util.List;

public class UserInfo {
	private int id;
	private String name;
	private String password;
	private List<Card> cards = new ArrayList<Card>();
	private Mytable mytable;
	
	

	public Mytable getMytable() {
		return mytable;
	}

	public void setMytable(Mytable mytable) {
		this.mytable = mytable;
	}

	public List<Card> getCards() {
		return cards;
	}

	public void setCards(List<Card> cards) {
		this.cards = cards;
	}

	public UserInfo() {
	}

	public UserInfo(String name, String password) {
		this.name = name;
		this.password = password;
	}

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "UserInfo [id=" + id + ", name=" + name + ", password=" + password + "]";
	}

}
