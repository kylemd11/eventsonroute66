package com.kyle.route66.web.model.account;

import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.kyle.route66.db.dao.AuthoritiesRepository;
import com.kyle.route66.db.dao.UserAccountRepository;
import com.kyle.route66.db.dao.UsersRepository;
import com.kyle.route66.db.model.Authorities;
import com.kyle.route66.db.model.UserAccount;
import com.kyle.route66.db.model.Users;
import com.kyle.route66.service.UserService;
import com.kyle.route66.web.model.user.StatusBean;
import com.kyle.route66.web.model.user.UserSession;

@Service("LoginBean")
@Scope("request")
public class LoginBean {
	private static final Log log = LogFactory.getLog(LoginBean.class);

	private String username;
	private String password;
	private String facebookUsername;

	@Autowired
	@Qualifier("UserSession")
	private UserSession session;

	@Autowired
	@Qualifier("StatusBean")
	private StatusBean status;

	@Autowired
	@Qualifier("authenticationManager")
	private AuthenticationManager am;
	
	@Autowired
	private UserService userService;
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFacebookUsername() {
		return facebookUsername;
	}

	public void setFacebookUsername(String facebookUsername) {
		this.facebookUsername = facebookUsername;
	}

	public String login() {
		log.debug("login()");
		try {
			Authentication request = new UsernamePasswordAuthenticationToken(
					this.username, this.password);
			Authentication result = am.authenticate(request);
			
			SecurityContextHolder.getContext().setAuthentication(result);

			session.setUserAccount(userService.findAccountByUsername(this.username));
			
		} catch (AuthenticationException e) {
			log.error(e.getMessage());
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Invalid Login!", "Invalid Login!");
			FacesContext.getCurrentInstance().addMessage("loginForm", msg);
			return null;
		}
		return "success";
	}
	
	public String facebookLogin() {
		UserAccount user = userService.findAccountByUsername(facebookUsername + "(facebook)");
		
		if(user == null) {
			String password = userService.createFacebookAccount(facebookUsername);
			
			this.username = this.facebookUsername + "(facebook)";
			this.password = password;
		}
		else {
			this.username = user.getUsername();
			this.password = userService.reverseEngineerPassword(facebookUsername);
		}
		
		return login();
	}

	public void setAm(AuthenticationManager am) {
		this.am = am;
	}

	public void setSession(UserSession session) {
		this.session = session;
	}

	public void setStatus(StatusBean status) {
		this.status = status;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
}
