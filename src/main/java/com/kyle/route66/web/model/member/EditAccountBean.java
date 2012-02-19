package com.kyle.route66.web.model.member;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.kyle.route66.db.dao.UserAccountRepository;
import com.kyle.route66.db.model.UserAccount;
import com.kyle.route66.web.model.account.NewAccountBean;
import com.kyle.route66.web.model.user.UserSession;

@Service("EditAccountBean")
@Scope("request")
public class EditAccountBean {
	private static final Log log = LogFactory.getLog(EditAccountBean.class);

	@Autowired
	@Qualifier("UserSession")
	private UserSession session;

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
	private UserAccountRepository userAccountRepository;

	@Autowired
	public EditAccountBean(UserSession session) {
		this.session = session;

		UserAccount account = session.getUserAccount();

		firstName = account.getFirstName();
		lastName = account.getLastName();
		username = account.getUsername();
		emailAddressOne = account.getEmailAddr();
		emailAddressTwo = account.getEmailAddr();
		addressOne = account.getStreetAddr1();
		addressTwo = account.getStreetAddr2();
		city = account.getCity();
		state = account.getState();
		zip = account.getZip();
	}

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

	public void update(ActionEvent ae) {
		log.debug("update()");

		if (validateEmailAddresses()) {
			if ((emailAddressOne.equals(session.getUserAccount().getEmailAddr()) && emailAddressTwo.equals(session.getUserAccount().getEmailAddr())) || validateUniqueEmailAddress()) {
				UserAccount account = session.getUserAccount();

				account.setUsername(username);
				account.setFirstName(firstName);
				account.setLastName(lastName);
				account.setEmailAddr(emailAddressOne);
				account.setStreetAddr1(addressOne);
				account.setStreetAddr2(addressTwo);
				account.setCity(city);
				account.setState(state);
				account.setZip(zip);

				session.setUserAccount(account);
				userAccountRepository.save(account);

				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage("accountTabs:accountDetailsForm",
						new FacesMessage(FacesMessage.SEVERITY_INFO,
								"Information successfully updated.",
								"Information successfully updated."));
			}
		}
	}

	private boolean validateUniqueEmailAddress() {
		if (userAccountRepository.findByEmailAddr(emailAddressOne).size() > 0) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage("accountTabs:accountDetailsForm", new FacesMessage(
					FacesMessage.SEVERITY_ERROR,
					"Email addresse already in use",
					"Email addresse already in use"));
			return false;
		} else {
			return true;
		}
	}

	private boolean validateEmailAddresses() {
		if (emailAddressOne.equals(emailAddressTwo)) {
			return true;
		} else {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(
					"accountTabs:accountDetailsForm:emailAddressOneInput",
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Email addresses do not match",
							"Email addresses do not match"));
			return false;
		}
	}

	public void setUserAccountRepository(
			UserAccountRepository userAccountRepository) {
		this.userAccountRepository = userAccountRepository;
	}

	public void setSession(UserSession session) {
		this.session = session;
	}
}
