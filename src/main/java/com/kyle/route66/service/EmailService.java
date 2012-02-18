package com.kyle.route66.service;

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
	
	public boolean sendPasswordResetEmail(String username, final String emailAddress) {
		SimpleMailMessage msg = new SimpleMailMessage(this.passwordResetTemplate);
        msg.setTo(emailAddress);
        msg.setText("");
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
	
	
}
