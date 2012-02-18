package com.kyle.route66.web.model.user;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

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

@Service("StatusBean")
@Scope("session")
public class StatusBean {
        
	private boolean logIn = false;
	
	
    public boolean isLogIn() {
		return logIn;
	}

	public void setLogIn(boolean loggedIn) {
		this.logIn = loggedIn;
	}
	
	public boolean getIsLogIn() {
		return logIn;
	}

	
}

