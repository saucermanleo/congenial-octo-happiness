package com.zy.pmk.tree.stack;

import java.util.LinkedList;
import java.util.List;

public class BlockingQueue {  
	  
	  private List queue = new LinkedList();  
	  private int  limit = 10;  
	  
	  public BlockingQueue(int limit){  
	    this.limit = limit;  
	  }  
	  
	  
	  public synchronized void enqueue(Object item)  
	  throws InterruptedException  {  
	    while(this.queue.size() == this.limit) {   //注意要使用while循环，而不是if，因为下面的notifyAll可能把正在等待的入队线程给唤醒  
	      wait();  
	    }  
	    if(this.queue.size() == 0) {  
	      notifyAll();  
	    }  
	    this.queue.add(item);  
	  }  
	  
	  
	  public synchronized Object dequeue()  
	  throws InterruptedException{  
	    while(this.queue.size() == 0){  
	      wait();  
	    }  
	    if(this.queue.size() == this.limit){  
	      notifyAll();  
	    }  
	  
	    return this.queue.remove(0);  
	  }  
	  
	}  
