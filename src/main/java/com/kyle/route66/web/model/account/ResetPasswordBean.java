package com.kyle.route66.web.model.account;

import java.util.List;

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

import com.kyle.route66.db.dao.UserAccountRepository;
import com.kyle.route66.db.dao.UsersRepository;
import com.kyle.route66.db.model.UserAccount;
import com.kyle.route66.db.model.Users;
import com.kyle.route66.service.EmailService;
import com.kyle.route66.service.RandomStringService;
import com.kyle.route66.web.model.user.StatusBean;
import com.kyle.route66.web.model.user.UserSession;

@Service("ResetPasswordBean")
@Scope("request")
public class ResetPasswordBean {
	private static final Log log = LogFactory.getLog(ResetPasswordBean.class);

	private String emailAddress;

	@Autowired
	@Qualifier("EmailService")
	private EmailService emailService;
	
	@Autowired
	@Qualifier("RandomStringService")
	private RandomStringService randomStringService;
	
	@Autowired
	private UserAccountRepository userAccountRepository;
	
	@Autowired
	private UsersRepository usersRepository;

	public void resetPassword(ActionEvent ae) {
		log.debug("resetPassword()");
		
		FacesContext context = FacesContext.getCurrentInstance();
		
		UserAccount account = userAccountRepository.findByEmailAddr(emailAddress).get(0);
		
		if(account != null) {
			Users user = usersRepository.findByUsername(account.getUsername()).get(0);
			
			String password = randomStringService.randomPassword();
			
			user.setPassword(password);
			usersRepository.save(user);
			
			context.addMessage("lostPasswordForm", new FacesMessage(FacesMessage.SEVERITY_INFO, "Successful", "An email has been sent to the address entered below with your new password."));
			emailService.sendPasswordResetEmail("test", emailAddress, password);
			emailAddress = "";
		}
		else {
			context.addMessage("lostPasswordForm", new FacesMessage(FacesMessage.SEVERITY_WARN, "Fail", "Unable to find the supplied email address."));
		}
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public void setEmailService(EmailService emailService) {
		this.emailService = emailService;
	}

	public void setUserAccountRepository(UserAccountRepository userAccountRepository) {
		this.userAccountRepository = userAccountRepository;
	}
	
	public void setUsersRepository(UsersRepository usersRepository) {
		this.usersRepository = usersRepository;
	}

	public void setRandomStringService(RandomStringService randomStringService) {
		this.randomStringService = randomStringService;
	}

	
	
}
