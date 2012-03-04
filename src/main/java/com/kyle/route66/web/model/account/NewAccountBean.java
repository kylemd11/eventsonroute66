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

import com.kyle.route66.db.dao.AuthoritiesRepository;
import com.kyle.route66.db.dao.UserAccountRepository;
import com.kyle.route66.db.dao.UserAccountRequestRepository;
import com.kyle.route66.db.dao.UsersRepository;
import com.kyle.route66.db.model.Authorities;
import com.kyle.route66.db.model.UserAccount;
import com.kyle.route66.db.model.UserAccountRequest;
import com.kyle.route66.db.model.Users;
import com.kyle.route66.service.EmailService;
import com.kyle.route66.service.RandomStringService;
import com.kyle.route66.web.model.user.StatusBean;
import com.kyle.route66.web.model.user.UserSession;

@Service("NewAccountBean")
@Scope("request")
public class NewAccountBean {
	private static final Log log = LogFactory.getLog(NewAccountBean.class);

	private String firstName;
	private String lastName;
	private String username;
	private String passwordOne;
	private String passwordTwo;
	private String emailAddressOne;
	private String emailAddressTwo;
	private String addressOne;
	private String addressTwo;
	private String city;
	private String state;
	private String zip;
	
	@Autowired
	private UsersRepository usersRepository;
	
	@Autowired
	private UserAccountRepository userAccountRepository;
	
	@Autowired
	private UserAccountRequestRepository userAccountRequestRepository;
	
	@Autowired
	private AuthoritiesRepository authoritiesRepository;
	
	@Autowired
	@Qualifier("RandomStringService")
	private RandomStringService randomStringService;
	
	@Autowired
	@Qualifier("EmailService")
	private EmailService emailService;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

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

	public String getEmailAddressOne() {
		return emailAddressOne;
	}

	public void setEmailAddressOne(String emailAddressOne) {
		this.emailAddressOne = emailAddressOne;
	}

	public String getEmailAddressTwo() {
		return emailAddressTwo;
	}

	public void setEmailAddressTwo(String emailAddressTwo) {
		this.emailAddressTwo = emailAddressTwo;
	}

	public String getAddressOne() {
		return addressOne;
	}

	public void setAddressOne(String addressOne) {
		this.addressOne = addressOne;
	}

	public String getAddressTwo() {
		return addressTwo;
	}

	public void setAddressTwo(String addressTwo) {
		this.addressTwo = addressTwo;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public void setUserAccountRepository(UserAccountRepository userAccountRepository) {
		this.userAccountRepository = userAccountRepository;
	}

	public UserAccountRepository getUserAccountRep() {
		return userAccountRepository;
	}

	public void setUserAccountRequestRepository(
			UserAccountRequestRepository userAccountRequestRepository) {
		this.userAccountRequestRepository = userAccountRequestRepository;
	}
	
	public void setUsersRepository(UsersRepository usersRepository) {
		this.usersRepository = usersRepository;
	}
	
	public void setAuthoritiesRepository(AuthoritiesRepository authoritiesRepository) {
		this.authoritiesRepository = authoritiesRepository;
	}

	public void setRandomStringService(RandomStringService randomStringService) {
		this.randomStringService = randomStringService;
	}

	public String submit() {
		log.debug("submit()");
		
		boolean passwordResult = validatePasswords();
		boolean emailAddressResult = validateEmailAddresses();
		
		if(passwordResult && emailAddressResult) {
			if(validateUniqueEmailAddress()) {
				Users users = new Users();
				users.setEnabled(0);
				users.setUsername(username);
				users.setPassword(passwordOne);
				
				Authorities authorities = new Authorities();
				authorities.setUsername(username);
				authorities.setAuthority("USER");
				
				UserAccount account = new UserAccount();
				account.setUsername(username);
				account.setFirstName(firstName);
				account.setLastName(lastName);
				account.setEmailAddr(emailAddressOne);
				account.setStreetAddr1(addressOne);
				account.setStreetAddr2(addressTwo);
				account.setCity(city);
				account.setState(state);
				account.setZip(zip);
				
				UserAccountRequest accountRequest = new UserAccountRequest();
				accountRequest.setUsername(username);
				accountRequest.setRequestId(randomStringService.requestHash());
				
				Date today = new Date();
				Date expirationDate = DateUtils.addDays(today, 1);
				accountRequest.setExpirationDate(expirationDate);
				
				usersRepository.save(users);
				authoritiesRepository.save(authorities);
				userAccountRepository.save(account);
				userAccountRequestRepository.save(accountRequest);
				
				emailService.sendAccountRequestEmail(firstName, emailAddressOne, accountRequest.getRequestId());
				
				return  "success";
			}
			else {
				return "";
			}
		}
		else {		
			return "";
		}
	}

	private boolean validateUniqueEmailAddress() {
		if(userAccountRepository.findByEmailAddr(emailAddressOne) != null) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage("newAccountForm", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Email addresses already in use", "Email addresses already in use"));
			return false;
		}
		else {
			return true;
		}
	}

	private boolean validateEmailAddresses() {	
		if(emailAddressOne.equals(emailAddressTwo)) {
			return true;
		}
		else {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage("newAccountForm:emailAddressOneInput", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Email addresses do not match", "Email addresses do not match"));
			return false;
		}
	}

	private boolean validatePasswords() {
		if(passwordOne.equals(passwordTwo)) {
			return true;
		}
		else {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage("newAccountForm:passwordOneInput", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Passwords do not match", "Passwords do not match"));
			return false;
		}
	}

	public void setEmailService(EmailService emailService) {
		this.emailService = emailService;
	}
	
	
	
}
