package com.zy.pmk.event;

import java.util.EventObject;

public class TaskEvent extends EventObject {
	
	private static final long serialVersionUID = -5343264386704875804L;
	
	private String takedescription;

	public String getTakedescription() {
		return takedescription;
	}

	public void setTakedescription(String takedescription) {
		this.takedescription = takedescription;
	}

	public TaskEvent(Object source , String takedescription) {
		super(source);
		this.takedescription = takedescription;
	}
	
	
}
