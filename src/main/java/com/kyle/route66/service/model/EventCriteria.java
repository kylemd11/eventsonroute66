package com.kyle.route66.service.model;

import java.util.Date;

import com.kyle.route66.db.model.EventType;
import com.kyle.route66.db.model.State;

public class EventCriteria {
	private State state = null;
	private EventType eventType = null;
	private Date startDate = null;
	private Date endDate = null;
	private String zipCode = null;
	private Integer distance;
	private String username;
	private Integer first = null;
	private Integer pageSize = null;
	
	public EventCriteria(State state, EventType eventType, Date startDate,
			Date endDate, String zipCode, Integer distance) {
		super();
		this.state = state;
		this.eventType = eventType;
		this.startDate = startDate;
		this.endDate = endDate;
		this.zipCode = zipCode;
		this.distance = distance;
	}
	
	public State getState() {
		return state;
	}
	public void setState(State state) {
		this.state = state;
	}
	public EventType getEventType() {
		return eventType;
	}
	public void setEventType(EventType eventType) {
		this.eventType = eventType;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	
	public void setDistance(Integer distance) {
		this.distance = distance;
	}

	public Integer getDistance() {
		return distance;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getUsername() {
		return this.username;
	}

	public void setFirst(Integer first) {
		this.first = first;
	}

	public Integer getFirst() {
		return first;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getPageSize() {
		return pageSize;
	}
}
