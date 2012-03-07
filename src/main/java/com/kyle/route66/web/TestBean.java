package com.kyle.route66.web;

import java.util.Map;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service("TestBean")
@Scope("request")
public class TestBean {
	private static final Log log = LogFactory.getLog(TestBean.class);
	
	public String getValue() {
		return "blah";
	}
	
		
	public void testActionListener(ActionEvent ae) {
		log.debug("testActionListener()");
		//String action = (String)ae.getComponent().getAttributes().get("action");
		//log.debug("action: " + action);
	}
	
	public String testAction() {
		log.debug("testAction()");
		
		Map<String,String> params = 
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
	  String action = params.get("action");
	  log.debug("action: " + action);
		return "events";
	}
	
	public String testAction(String value) {
		log.debug("testAction(value)");
		
	  String action = value;
	  log.debug("action: " + action);
		return "events";
	}

}
