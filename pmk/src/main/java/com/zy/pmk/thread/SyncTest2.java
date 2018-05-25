package com.zy.pmk.thread;
	 /**
	  *  可以被同时访问。因为isSyncA()是实例方法，x.isSyncA()使用的是对象x的锁；而cSyncA()是静态方法，
	  *  x.cSyncA()可以理解对使用的是“类的锁”。
	 * @author Administrator
	 *
	 */
	public class SyncTest2 {
	     Something x = new Something();
	     Something y = new Something();
	
	     // 比较(04) x.isSyncA()与Something.cSyncA()
	     private void test4() {
	         // 新建t41, t41会调用 x.isSyncA()
	        Thread t41 = new Thread(
	                 new Runnable() {
	                    @Override
	                    public void run() {
	                         x.isSyncA();
	                     }
	                 }, "t41");
	 
	         // 新建t42, t42会调用 x.isSyncB()
	         Thread t42 = new Thread(
	                 new Runnable() {
	                     @Override
	                     public void run() {
	                         x.cSyncA();
	                     }
	                 }, "t42");  
	 
	 
	         t41.start();  // 启动t41
	         t42.start();  // 启动t42
	     }
	 
	     public static void main(String[] args) {
	         SyncTest2 demo = new SyncTest2();
	 
	         demo.test4();
	     }
	 }
	 
	 
	 class Something {
	      public synchronized void isSyncA(){
	         try {  
	              for (int i = 0; i < 5; i++) {
	                  Thread.sleep(100); // 休眠100ms
	                  System.out.println(Thread.currentThread().getName()+" : isSyncA");
	              }
	          }catch (InterruptedException ie) {  
	         }  
	     }
	     public synchronized void isSyncB(){
	         try {  
	             for (int i = 0; i < 5; i++) {
	                 Thread.sleep(100); // 休眠100ms
	                 System.out.println(Thread.currentThread().getName()+" : isSyncB");
           }
	         }catch (InterruptedException ie) {  
	         }  
	     }
	     public static synchronized void cSyncA(){
	         try {  
	             for (int i = 0; i < 5; i++) {
	                 Thread.sleep(100); // 休眠100ms
	                 System.out.println(Thread.currentThread().getName()+" : cSyncA");
	             } 
	        }catch (InterruptedException ie) {  
	        }  
	    }
	    public static synchronized void cSyncB(){
	        try {  
	           for (int i = 0; i < 5; i++) {
	                Thread.sleep(100); // 休眠100ms
	                 System.out.println(Thread.currentThread().getName()+" : cSyncB");
	             } 
	         }catch (InterruptedException ie) {  
	        }  
	    }
	 }