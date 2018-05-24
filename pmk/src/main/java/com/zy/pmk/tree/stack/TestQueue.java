package com.zy.pmk.tree.stack;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


public class TestQueue {

	public static void main(String[] args) {
		MyBlockingQueue<Integer> queue = new MyBlockingQueue<Integer>(3);
		ExecutorService executor = Executors.newCachedThreadPool();
		executor.execute(()->{
			while(true) {
			queue.enququ(1);
			System.out.println(Thread.currentThread().getName()+":生产1");
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			}
		});
		executor.execute(()->{
			while(true) {
			try {
				TimeUnit.SECONDS.sleep(3);
				System.out.println(Thread.currentThread().getName()+":消费"+queue.dequeue());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			}
		});
		
	}
}
