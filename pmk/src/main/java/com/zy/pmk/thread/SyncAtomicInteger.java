package com.zy.pmk.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *原子操作存在aba问题
 * @author Administrator
 *
 */
public class SyncAtomicInteger {
	public static void main(String[] args) {
		// 在这里使用AtomicReference
		final AtomicInteger money = new AtomicInteger();
		CountDownLatch count = new CountDownLatch(2);
		// 初始卡余额小于20
		money.set(29);
		// 模拟多个线程更新数据库，为用户充值
		new Thread() {
			public void run() {
				for (int i = 0; i < 40; i++) {
					
					int m = money.get();
					//System.out.println(m);
					money.compareAndSet(m, m + 10);
					try {
						TimeUnit.MILLISECONDS.sleep(200);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				
					count.countDown();
				
			}
		}.start();
		// 用户消费进程，模拟消费行为
		new Thread() {
			@Override
			public void run() {
				for (int i = 0; i < 40; i++) {

					int m = money.get();
					money.compareAndSet(m, m - 10);
					try {
						TimeUnit.MILLISECONDS.sleep(200);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				count.countDown();
			}
		}.start();
		try {
			count.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		};
		System.out.println(money.get());
	}
	
}
