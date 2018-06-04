package com.zy.pojo;

import java.util.Date;

public class Card {
	private int id;
	private int userId;
	private int cardNumber;
	private Date createTime;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(int cardNumber) {
		this.cardNumber = cardNumber;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	@Override
	public String toString() {
		return "Card [id=" + id + ", userId=" + userId + ", cardNumber=" + cardNumber + ", createTime=" + createTime
				+ "]";
	}
	
	
}
