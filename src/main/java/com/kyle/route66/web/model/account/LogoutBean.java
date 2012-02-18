package com.kyle.route66.web.model.account;

import java.io.IOException;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.kyle.route66.web.model.user.UserSession;

@Service("LogoutBean")
@Scope("request")
public class LogoutBean {
	@Autowired
    @Qualifier("UserSession")
    private UserSession session;
	
	public String logout() {				
		SecurityContextHolder.getContext().setAuthentication(null);
		
		this.session.setLoggedIn(false);
		
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
		session.invalidate();
		
		
		/*try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("/logout.jsp");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		return "success";
	}
	
	public void setSession(UserSession session) {
		this.session = session;
	}
}
