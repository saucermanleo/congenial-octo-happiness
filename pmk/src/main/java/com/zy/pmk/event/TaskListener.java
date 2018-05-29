package com.zy.pmk.event;

import java.util.EventListener;

public class TaskListener implements EventListener {
	public void handle(TaskEvent event) {
		Source s = (Source)event.getSource();
		System.out.println("程序员:"+s.getSourcename()+"开始"+event.getTakedescription());
	}
}
