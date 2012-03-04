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
        
	private int state = EventStateConstants.CALENDAR;
		
	public void setCalendar(ActionEvent ae) {
		state = EventStateConstants.CALENDAR;
	}
	
	public void setTimeline(ActionEvent ae) {
		state = EventStateConstants.TIMELINE;
	}
	
	public void setList(ActionEvent ae) {
		state = EventStateConstants.LIST;
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
}

