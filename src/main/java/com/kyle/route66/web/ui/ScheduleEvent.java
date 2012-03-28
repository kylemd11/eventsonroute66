package com.kyle.route66.web.ui;

import org.primefaces.model.DefaultScheduleEvent;

import com.kyle.route66.db.model.Event;

public class ScheduleEvent extends DefaultScheduleEvent {

	private Event event;
	
	public ScheduleEvent(){}
	
	public ScheduleEvent(Event event) {
		super(event.getTitle(),event.getStartDtg(), event.getEndDtg());
		this.event = event;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}
}
