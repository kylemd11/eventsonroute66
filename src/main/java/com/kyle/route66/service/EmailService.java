package com.kyle.route66.service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service("EmailService")
@Scope("singleton")
public class EmailService {
	private static final Log log = LogFactory.getLog(EmailService.class);

	@Autowired
	@Qualifier("mailSender")
	private MailSender mailSender;
	
	@Autowired
	@Qualifier("passwordResetEmailTemplate")
	private SimpleMailMessage passwordResetTemplate;
	
	@Autowired
	@Qualifier("accountRequestEmailTemplate")
	private SimpleMailMessage accountRequestEmailTemplate;
	
	public boolean sendPasswordResetEmail(String username, String emailAddress, String password) {
		SimpleMailMessage msg = new SimpleMailMessage(this.passwordResetTemplate);
        msg.setTo(emailAddress);
        msg.setText(password);
        try{
            this.mailSender.send(msg);
        }
        catch(MailException ex) {
        	log.error(ex.getMessage());
            return false;           
        }
		
		return true;
	}
	
	public boolean sendAccountRequestEmail(String firstName, String emailAddress, String uuid) {
		SimpleMailMessage msg = new SimpleMailMessage(this.accountRequestEmailTemplate);
        msg.setTo(emailAddress);
        try {
			msg.setText("http://localhost:8080/route66/account/activate.html?id="+URLEncoder.encode(uuid,"UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
        try{
            this.mailSender.send(msg);
        }
        catch(MailException ex) {
        	log.error(ex.getMessage());
            return false;           
        }
		
		return true;
	}

	public void setMailSender(MailSender mailSender) {
		this.mailSender = mailSender;
	}

	public void setPasswordResetTemplate(SimpleMailMessage passwordResetTemplate) {
		this.passwordResetTemplate = passwordResetTemplate;
	}

	public void setAccountRequestEmailTemplate(
			SimpleMailMessage accountRequestEmailTemplate) {
		this.accountRequestEmailTemplate = accountRequestEmailTemplate;
	}
	
	
	
	
}
