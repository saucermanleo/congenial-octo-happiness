package com.zy.pmk.tree.stack;

import java.util.LinkedList;
import java.util.List;

public class MyBlockingQueue<E> {
	private int limit;
	private List<E> list = new LinkedList<E>();
	
	public MyBlockingQueue(int limit) {
		super();
		this.limit = limit;
	}
	
	public synchronized E dequeue() {
		while(list.size() == 0) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		E e = list.remove(0);
		this.notifyAll();
		return e;
	}
	
	public synchronized void enququ(E e) {
		while(list.size() == limit) {
			try {
				this.wait();
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		}
		list.add(e);
		this.notifyAll();
	}
	
}
