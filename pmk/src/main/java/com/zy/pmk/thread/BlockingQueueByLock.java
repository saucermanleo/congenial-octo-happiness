package com.zy.pmk.thread;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BlockingQueueByLock<E> {
	Lock lock = new ReentrantLock();
	List<E> list = new LinkedList<E>();
	Condition fullCondition = lock.newCondition();
	Condition emptyCondition = lock.newCondition();
	private int limit;

	public BlockingQueueByLock(int limit) {
		this.limit = limit;
	}

	public void enqueue(E e) {
		lock.lock();
		try {
			while (limit == list.size()) {
				try {
					fullCondition.await();
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
			list.add(e);
			emptyCondition.signal();
		} finally {
			lock.unlock();
		}
	}

	public E dequeue() {
		lock.lock();
		try {
			while (0 == list.size()) {
				try {
					emptyCondition.await();
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
			E e = list.remove(0);
			fullCondition.signal();
			return e;
		} finally {
			lock.unlock();
		}

	}
}
