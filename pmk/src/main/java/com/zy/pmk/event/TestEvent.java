package com.zy.pmk.event;

public class TestEvent {

	public static void main(String[] args) {
		Source s = new Source("zy");
		s.addTaskListener(new TaskListener());
		s.notifies();
	}

}
