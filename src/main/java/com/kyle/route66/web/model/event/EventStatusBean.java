package com.kyle.route66.web.model.event;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.context.annotation.Scope;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.kyle.route66.db.model.UserAccount;
import com.kyle.route66.web.constants.EventStateConstants;
import com.kyle.route66.web.constants.StateConstants;

@Service("EventStatusBean")
@Scope("session")
public class EventStatusBean {
        
	@Autowired
	private EventFilterBean filter;
	
	private int state = EventStateConstants.CALENDAR;
		
	public void setCalendar(ActionEvent ae) {
		state = EventStateConstants.CALENDAR;
		filter.setDirty();
	}
	
	public void setTimeline(ActionEvent ae) {
		state = EventStateConstants.TIMELINE;
		filter.setDirty();
	}
	
	public void setList(ActionEvent ae) {
		state = EventStateConstants.LIST;
		filter.setDirty();
	}
	
	public void setMap(ActionEvent ae) {
		state = EventStateConstants.MAP;
		filter.setDirty();
	}
	
	public boolean getIsCalendarDisabled() {
		return state == EventStateConstants.CALENDAR;
	}
	
	public boolean getIsTimelineDisabled() {
		return state == EventStateConstants.TIMELINE;
	}
	
	public boolean getIsListDisabled() {
		return state == EventStateConstants.LIST;
	}
	
	public boolean getIsMapDisabled() {
		return state == EventStateConstants.MAP;
	}

	public void setFilter(EventFilterBean filter) {
		this.filter = filter;
	}
}

