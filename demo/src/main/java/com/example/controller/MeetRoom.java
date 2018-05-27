package com.example.controller;

import java.util.Date;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MeetRoom {
	private String name;
	private String atdrass;
	private String size;
	private Lock lock = new ReentrantLock();
	
	public MeetRoom(String name, String atdrass, String size) {
		super();
		this.name = name;
		this.atdrass = atdrass;
		this.size = size;
	}
	
	public boolean book(Date starttime , Date endtime) {
		lock.lock();
		try {
			
		}finally {
			lock.unlock();
		}
		
		
		
		return false;
		
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAtdrass() {
		return atdrass;
	}
	public void setAtdrass(String atdrass) {
		this.atdrass = atdrass;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	
	
}
