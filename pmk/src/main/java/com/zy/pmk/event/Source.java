package com.zy.pmk.event;

import java.util.ArrayList;
import java.util.List;

public class Source {
	
	
	public Source(String sourcename) {
		this.sourcename = sourcename;
	}

	private String sourcename;
	private List<TaskListener> listeners = new ArrayList<TaskListener>(5);
	
	public String getSourcename() {
		return sourcename;
	}
	public void setSourcename(String sourcename) {
		this.sourcename = sourcename;
	}
	
	public void addTaskListener(TaskListener taskListener) {
		listeners.add(taskListener);
	}
	
	public void removeTaskListener(TaskListener taskListener) {
		listeners.remove(taskListener);
	}
	
	public void notifies() {
		for(TaskListener t :listeners) {
			t.handle(new TaskEvent(this, "任务描述"));
		}
	}
}
