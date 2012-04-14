package com.kyle.route66.web.model.member;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Service;

import com.kyle.route66.db.dao.UserAccountRepository;
import com.kyle.route66.db.dao.UsersRepository;
import com.kyle.route66.db.model.UserAccount;
import com.kyle.route66.db.model.Users;
import com.kyle.route66.web.model.account.NewAccountBean;
import com.kyle.route66.web.model.user.UserSession;

@Service("PasswordUpdateBean")
@Scope("request")
public class PasswordUpdateBean {
	private static final Log log = LogFactory.getLog(PasswordUpdateBean.class);

	@Autowired
	@Qualifier("UserSession")
	private UserSession session;
	
	@Autowired
	private ShaPasswordEncoder passwordEncoder;

	private String passwordOne;
	private String passwordTwo;

	@Autowired
	private UsersRepository usersRepository;

	public String getPasswordOne() {
		return passwordOne;
	}

	public void setPasswordOne(String passwordOne) {
		this.passwordOne = passwordOne;
	}

	public String getPasswordTwo() {
		return passwordTwo;
	}

	public void setPasswordTwo(String passwordTwo) {
		this.passwordTwo = passwordTwo;
	}

	public void update(ActionEvent ae) {
		log.debug("update()");

		if (validatePasswords()) {
			Users user = usersRepository.findByUsername(
					session.getUserAccount().getUsername());
			user.setPassword(passwordEncoder.encodePassword(passwordOne,null));
			usersRepository.save(user);

			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage("accountTabs:changePasswordForm",
					new FacesMessage(FacesMessage.SEVERITY_INFO,
							"Password successfully updated.",
							"Password successfully updated."));
		}
	}

	private boolean validatePasswords() {
		if (passwordOne.equals(passwordTwo)) {
			return true;
		} else {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage("accountTabs:changePasswordForm:passwordOneInput",
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Passwords do not match", "Passwords do not match"));
			return false;
		}
	}

	public void setUsersRepository(UsersRepository usersRepository) {
		this.usersRepository = usersRepository;
	}

	public void setSession(UserSession session) {
		this.session = session;
	}
	
	public void setPasswordEncoder(ShaPasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}
}
