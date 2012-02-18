package com.kyle.route66.web.model.user;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.kyle.route66.db.model.UserAccount;

@Service("UserSession")
@Scope("session")
public class UserSession {
        
	private boolean loggedIn = false;
	private UserAccount userAccount;
	
    public boolean isLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}
	
	public boolean getIsLoggedIn() {
		return loggedIn;
	}
	
	public void setUserAccount(UserAccount userAccount) {
		this.userAccount = userAccount;
	}

	public UserAccount getUserAccount() {
		return userAccount;
	}
}

