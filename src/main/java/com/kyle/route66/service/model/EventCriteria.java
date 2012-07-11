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
	private String eventStatus;
	
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
	
	public EventCriteria() {
		// TODO Auto-generated constructor stub
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((distance == null) ? 0 : distance.hashCode());
		result = prime * result + ((endDate == null) ? 0 : endDate.hashCode());
		result = prime * result
				+ ((eventType == null) ? 0 : eventType.hashCode());
		result = prime * result + ((first == null) ? 0 : first.hashCode());
		result = prime * result
				+ ((pageSize == null) ? 0 : pageSize.hashCode());
		result = prime * result
				+ ((startDate == null) ? 0 : startDate.hashCode());
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		result = prime * result
				+ ((username == null) ? 0 : username.hashCode());
		result = prime * result + ((zipCode == null) ? 0 : zipCode.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EventCriteria other = (EventCriteria) obj;
		if (distance == null) {
			if (other.distance != null)
				return false;
		} else if (!distance.equals(other.distance))
			return false;
		if (endDate == null) {
			if (other.endDate != null)
				return false;
		} else if (!endDate.equals(other.endDate))
			return false;
		if (eventType == null) {
			if (other.eventType != null)
				return false;
		} else if (!eventType.equals(other.eventType))
			return false;
		if (first == null) {
			if (other.first != null)
				return false;
		} else if (!first.equals(other.first))
			return false;
		if (pageSize == null) {
			if (other.pageSize != null)
				return false;
		} else if (!pageSize.equals(other.pageSize))
			return false;
		if (startDate == null) {
			if (other.startDate != null)
				return false;
		} else if (!startDate.equals(other.startDate))
			return false;
		if (state == null) {
			if (other.state != null)
				return false;
		} else if (!state.equals(other.state))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		if (zipCode == null) {
			if (other.zipCode != null)
				return false;
		} else if (!zipCode.equals(other.zipCode))
			return false;
		return true;
	}

	public void setEventStatus(String eventStatus) {
		this.eventStatus = eventStatus;
	}
	
	public String getEventStatus() {
		return this.eventStatus;
	}

	@Override
	public String toString() {
		return "EventCriteria [state=" + state + ", eventType=" + eventType
				+ ", startDate=" + startDate + ", endDate=" + endDate
				+ ", zipCode=" + zipCode + ", distance=" + distance
				+ ", username=" + username + ", first=" + first + ", pageSize="
				+ pageSize + ", eventStatus=" + eventStatus + "]";
	}
	
	
}
