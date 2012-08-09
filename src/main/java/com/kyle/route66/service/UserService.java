package com.kyle.route66.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.primefaces.model.DefaultScheduleEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyle.route66.db.dao.AuthoritiesRepository;
import com.kyle.route66.db.dao.EventDao;
import com.kyle.route66.db.dao.EventRepository;
import com.kyle.route66.db.dao.UserAccountRepository;
import com.kyle.route66.db.dao.UsersRepository;
import com.kyle.route66.db.model.Authorities;
import com.kyle.route66.db.model.Event;
import com.kyle.route66.db.model.EventType;
import com.kyle.route66.db.model.State;
import com.kyle.route66.db.model.UserAccount;
import com.kyle.route66.db.model.Users;
import com.kyle.route66.service.model.EventCriteria;

@Service("UserService")
@Scope("singleton")
public class UserService {
	private static final Log log = LogFactory.getLog(UserService.class);

	@Autowired
	private UserAccountRepository userAccountRepository;
	
	@Autowired
	private UsersRepository usersRepository;
	
	@Autowired
	private AuthoritiesRepository authoritiesRepository;
	
	@Autowired
	private ShaPasswordEncoder passwordEncoder;
	
	@Transactional
	public String createFacebookAccount(String username) {
		String combined = username + "(facebook)eF6@vU8";
		String password = Base64.encodeBase64String(combined.getBytes());
		
		UserAccount newAccount = new UserAccount();
		newAccount.setUsername(username + "(facebook)");
		newAccount.setEmailAddr(username + "@facebook.com");
		
		Users newUser = new Users();
		newUser.setUsername(username + "(facebook)");
		newUser.setPassword(passwordEncoder.encodePassword(password, null));
		newUser.setEnabled(1);
		
		Authorities newAuthority = new Authorities();
		newAuthority.setUsername(username + "(facebook)");
		newAuthority.setAuthority("USER");
		
		newAccount.setUser(newUser);
		
		authoritiesRepository.save(newAuthority);
		usersRepository.save(newUser);			
		userAccountRepository.save(newAccount);
		
		return password;
	}

	public void setUserAccountRepository(UserAccountRepository userAccountRepository) {
		this.userAccountRepository = userAccountRepository;
	}

	public void setUsersRepository(UsersRepository usersRepository) {
		this.usersRepository = usersRepository;
	}

	public void setAuthoritiesRepository(AuthoritiesRepository authoritiesRepository) {
		this.authoritiesRepository = authoritiesRepository;
	}

	public void setPasswordEncoder(ShaPasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}
}
