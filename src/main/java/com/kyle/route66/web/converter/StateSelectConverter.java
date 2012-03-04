package com.kyle.route66.web.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.kyle.route66.db.model.State;
import com.kyle.route66.web.model.event.EventFilterBean;

public class StateSelectConverter implements Converter {
	private static final Log log = LogFactory.getLog(StateSelectConverter.class);
	
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		log.debug("getAsObject()");
		log.debug(arg2);
		return arg2;
	}

	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		log.debug("getAsString()");
		log.debug(arg2);
		return ((State)arg2).getCode();
	}

}
