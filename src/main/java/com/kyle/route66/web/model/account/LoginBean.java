package com.kyle.route66.web.model.account;

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

import com.kyle.route66.web.model.user.StatusBean;
import com.kyle.route66.web.model.user.UserSession;

@Service("LoginBean")
@Scope("request")
public class LoginBean {

	private String username;
	private String password;

	@Autowired
	@Qualifier("UserSession")
	private UserSession session;

	@Autowired
	@Qualifier("StatusBean")
	private StatusBean status;

	@Autowired
	@Qualifier("authenticationManager")
	private AuthenticationManager am;

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

	public String startLogin() {
		status.setLogIn(true);
		return "startLogin";
	}

	public String login() {
		try {
			Authentication request = new UsernamePasswordAuthenticationToken(
					this.username, this.password);
			Authentication result = am.authenticate(request);
			SecurityContextHolder.getContext().setAuthentication(result);

			session.setLoggedIn(true);
			status.setLogIn(false);

		} catch (AuthenticationException e) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Invalid Login!", "Invalid Login!");
			FacesContext.getCurrentInstance().addMessage("loginForm", msg);
			return null;
		}
		return "success";
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

}
