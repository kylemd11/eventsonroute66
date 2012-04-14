package com.kyle.route66.web.model.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.kyle.route66.db.dao.AuthoritiesRepository;
import com.kyle.route66.db.model.Authorities;
import com.kyle.route66.db.model.UserAccount;
import com.kyle.route66.web.constants.RoleConstants;

@Service("UserSession")
@Scope("session")
public class UserSession {
        
	private boolean loggedIn = false;
	private UserAccount userAccount;
	
	private boolean isModerator = false;
	private boolean isAdmin = false;
	
	@Autowired
	private AuthoritiesRepository authoritiesRepository;
	
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
		
		setLoggedIn(this.userAccount != null);
		
		Authorities authority = authoritiesRepository.findByUsername(this.userAccount.getUsername());
		
		if(authority.getAuthority().equalsIgnoreCase(RoleConstants.MODERATOR)) {
			this.isModerator = true;
		}
		else if(authority.getAuthority().equalsIgnoreCase(RoleConstants.ADMINISTRATOR)) {
			this.isAdmin = true;
		}
	}

	public UserAccount getUserAccount() {
		return userAccount;
	}
	
	public boolean getIsModerator() {
		return isModerator;
	}
	
	public boolean getIsAdmin()	{
		return isAdmin;
	}

	public void setAuthoritiesRepository(AuthoritiesRepository authoritiesRepository) {
		this.authoritiesRepository = authoritiesRepository;
	}
	
	
 }

