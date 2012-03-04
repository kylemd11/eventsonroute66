package com.kyle.route66.web.model.event;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.kyle.route66.db.dao.EventTypeRepository;
import com.kyle.route66.db.dao.StateRepository;
import com.kyle.route66.db.model.Event;
import com.kyle.route66.db.model.EventType;
import com.kyle.route66.db.model.State;
import com.kyle.route66.service.EventService;

@Service("EventFilterBean")
@Scope("session")
public class EventFilterBean {
	private static final Log log = LogFactory.getLog(EventFilterBean.class);
	
	private boolean isStateFiltered = false;
	private String stateFilter = "";
	private String eventTypeFilter = "";
	
	@Autowired
	private StateRepository stateRepository;
	
	@Autowired
	private EventTypeRepository eventTypeRepository;

	public boolean getIsStateFiltered() {
		return isStateFiltered();
	}
	
	public boolean isStateFiltered() {
		return stateFilter.length() > 1;
	}
	
	public boolean getIsEventTypeFiltered() {
		return isEventTypeFiltered();
	}
	
	public boolean isEventTypeFiltered() {
		return eventTypeFilter.length() > 1;
	}
	
	public boolean getFiltersApplied() {
		return isStateFiltered() || isEventTypeFiltered();
	}
	
	public boolean getAllFiltersApplied() {		
		return isStateFiltered() && isEventTypeFiltered();
	}

	public void setStateFiltered(boolean isStateFiltered) {
		this.isStateFiltered = isStateFiltered;
	}

	public String getStateFilter() {
		return stateFilter;
	}
	
	public void setStateFilter(String stateFilter) {
		if(stateFilter.length() > 1) {
			this.stateFilter = stateFilter;
		}
	}
	
	public String getEventTypeFilter() {
		return eventTypeFilter;
	}

	public void setEventTypeFilter(String eventTypeFilter) {
		if(eventTypeFilter.length() > 1) {
			this.eventTypeFilter = eventTypeFilter;
		}
	}
	
	public String setEventTypeFilterAction() {
		log.debug("setEventTypeFilterAction()");
		 Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		this.eventTypeFilter = params.get("eventTypeCode");
		
		log.debug(this.eventTypeFilter);
		return "";
	}

	public List<State> getStates() { 
		List<State> states = new ArrayList<State>();
		for(State state : stateRepository.findAll()) {
			states.add(state);
		}
		
		return states;
	}
	
	public List<EventType> getEventTypes() { 
		List<EventType> eventTypes = new ArrayList<EventType>();
		for(EventType eventType : eventTypeRepository.findAll()) {
			eventTypes.add(eventType);
		}
		
		return eventTypes;
	}

	public void setStateRepository(StateRepository stateRepository) {
		this.stateRepository = stateRepository;
	}
	
	public void setEventTypeRepository(EventTypeRepository eventTypeRepository) {
		this.eventTypeRepository = eventTypeRepository;
	}

	public boolean isEmpty() {		
		return !isStateFiltered() && !isEventTypeFiltered();
	}

	public boolean isState() {
		return isStateFiltered() && !isEventTypeFiltered();
	}
	
	public boolean isEventType() {
		return !isStateFiltered() && isEventTypeFiltered();
	}
	
	public boolean isStateAndEventType() {
		return isStateFiltered() && isEventTypeFiltered();
	}
	
	public void removeStateFilter(ActionEvent ae) {
		this.stateFilter = "1";
	}
	
	public void removeEventTypeFilter(ActionEvent ae) {
		this.eventTypeFilter = "1";
	}
	
}
