package com.zy.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Card {
	private int id;
	private int userId;
	private int cardNumber;
	private Date createTime;
	private Card card;
	private int parentId;
	private List<Card> cards= new ArrayList<Card>();
	
	public List<Card> getCards() {
		return cards;
	}
	public void setCards(List<Card> cards) {
		this.cards = cards;
	}
	public Card getCard() {
		return card;
	}
	public void setCard(Card card) {
		this.card = card;
	}
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
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
				+ ", card=" + card + ", parentId=" + parentId + ", cards=" + cards + "]";
	}
	
	
	
}
