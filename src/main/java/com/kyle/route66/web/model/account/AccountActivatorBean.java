package com.kyle.route66.web.model.account;

import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import org.apache.commons.lang.time.DateUtils;
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
import com.kyle.route66.db.dao.UserAccountRequestRepository;
import com.kyle.route66.db.dao.UsersRepository;
import com.kyle.route66.db.model.UserAccount;
import com.kyle.route66.db.model.UserAccountRequest;
import com.kyle.route66.db.model.Users;
import com.kyle.route66.service.EmailService;
import com.kyle.route66.service.RandomStringService;
import com.kyle.route66.service.UserService;
import com.kyle.route66.web.model.user.StatusBean;
import com.kyle.route66.web.model.user.UserSession;

@Service("AccountActivatorBean")
@Scope("request")
public class AccountActivatorBean {
	private static final Log log = LogFactory.getLog(AccountActivatorBean.class);
	
	@Autowired
	private UsersRepository usersRepository;
	
	@Autowired
	private UserAccountRequestRepository userAccountRequestRepository;
	
	@Autowired
	private UserAccountRepository userAccountRepository;
	
	@Autowired
	private UserService userService;
	
	public void setUserAccountRequestRepository(
			UserAccountRequestRepository userAccountRequestRepository) {
		this.userAccountRequestRepository = userAccountRequestRepository;
	}
	
	public void setUsersRepository(UsersRepository usersRepository) {
		this.usersRepository = usersRepository;
	}
	
	public void setUserAccountRepository(
			UserAccountRepository userAccountRepository) {
		this.userAccountRepository = userAccountRepository;
	}

	public String activate() {
		log.debug("activate()");
		
		String requestId = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("requestId");
		
		log.debug("requestId=" + requestId);
		
		UserAccountRequest userAccountRequest = userAccountRequestRepository.findByRequestId(requestId);
		
		if(userAccountRequest != null) {
			Date now = new Date();
			Users user = getUserByUsername(userAccountRequest);
			UserAccount account = getUserAccountByUsername(userAccountRequest);
			
			if(now.before(userAccountRequest.getExpirationDate())) {
				user.setEnabled(1);
				
				usersRepository.save(user);
				userAccountRequestRepository.delete(userAccountRequest);
				return "success";
			}
			else {				
				userAccountRequestRepository.delete(userAccountRequest);
				usersRepository.delete(user);
				userAccountRepository.delete(account);
				
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage("activationForm", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Account activation has expired.  Please create a new account.", "Account activation has expired.  Please create a new account."));
				return "";
			}
		}
		else {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage("activationForm", new FacesMessage(FacesMessage.SEVERITY_FATAL, "Invalid account request.", "Invalid account request."));
			return "";
		}
	}

	public UserAccount getUserAccountByUsername(UserAccountRequest userAccountRequest) {
		return userAccountRepository.findByUsername(userAccountRequest.getUsername());
	}

	public Users getUserByUsername(UserAccountRequest userAccountRequest) {
		return usersRepository.findByUsername(userAccountRequest.getUsername());
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	
}
