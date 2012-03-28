package com.kyle.route66.web.model.user;

import javax.faces.event.ActionEvent;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.kyle.route66.web.constants.StateConstants;

@Service("StatusBean")
@Scope("session")
public class StatusBean {
        
	private boolean isLoggedIn = false;
	private int state = StateConstants.HOME;
	
	public boolean isLoggedIn() {
		return isLoggedIn;
	}
	
	public boolean getIsLoggedIn() {
		return isLoggedIn();
	}

	public void setLoggedIn(boolean isLoggedIn) {
		this.isLoggedIn = isLoggedIn;
	}
	
	public boolean getIsLogin() {
		return state == StateConstants.LOGIN;
	}

	public void setHome(ActionEvent ae) {
		state = StateConstants.HOME;
	}
	
	public void setEvents(ActionEvent ae) {
		state = StateConstants.EVENTS;
	}
	
	public void setHistory(ActionEvent ae) {
		state = StateConstants.HISTORY;
	}
	
	public void setLinks(ActionEvent ae) {
		state = StateConstants.LINKS;
	}
	
	public void setLogin(ActionEvent ae) {
		state = StateConstants.LOGIN;
	}
	
	public boolean getShowLeftPanel() {
		switch (state) {
		case StateConstants.HOME:
			return false;
		case StateConstants.EVENTS:
			return true;
		case StateConstants.HISTORY:
			return false;
		case StateConstants.LINKS:
			return false;
		case StateConstants.LOGIN:
			return false;
		default:
			return false;
		}
	}
}

