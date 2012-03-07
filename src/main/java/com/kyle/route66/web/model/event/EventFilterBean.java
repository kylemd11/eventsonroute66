package com.kyle.route66.web.model.event;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
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
import com.kyle.route66.service.model.EventCriteria;

@Service("EventFilterBean")
@Scope("session")
public class EventFilterBean {
	private static final Log log = LogFactory.getLog(EventFilterBean.class);
	
	private State stateFilter = null;
	private EventType eventTypeFilter = null;
	private boolean shortStateList = true;
	private Date startDate = null;
	private Date endDate = null;
	private boolean datesEntered = false;
	private String zipCode = null;
	private int distance = -1;
	
	
	@Autowired
	private StateRepository stateRepository;
	
	@Autowired
	private EventTypeRepository eventTypeRepository;

	public boolean getIsStateFiltered() {
		return isStateFiltered();
	}
	
	public boolean isStateFiltered() {
		return stateFilter != null;
	}
	
	public boolean getIsEventTypeFiltered() {
		return isEventTypeFiltered();
	}
	
	public boolean isEventTypeFiltered() {
		return eventTypeFilter != null;
	}
	
	public boolean isDateRangeFiltered() {
		return datesEntered;
	}
	
	public boolean getIsDateRangeFiltered() {
		return isDateRangeFiltered();
	}
	
	public boolean isLocationFiltered() {
		return zipCode != null && distance > 0;
	}
	
	public boolean getIsLocationFiltered() {
		return isLocationFiltered();
	}
	
	public boolean getFiltersApplied() {
		return isStateFiltered() || isEventTypeFiltered() || isDateRangeFiltered() || isLocationFiltered();
	}
	
	public String getStateFilter() {
		return stateFilter.getName();
	}
	
	public String getEventTypeFilter() {
		return eventTypeFilter.getDescription();
	}
	
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public String setEventTypeFilterAction() {
		log.debug("setEventTypeFilterAction()");
		 Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		this.eventTypeFilter = eventTypeRepository.findByCode((String)params.get("eventTypeCode"));
		
		return "";
	}
	
	public String setStateCodeFilterAction() {
		log.debug("setStateCodeFilterAction()");
		 Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		 if(params.get("stateCode").equals("more")) {
			 this.shortStateList = false;
		 } else if (params.get("stateCode").equals("less")) {
			 this.shortStateList = true;
		 } else { 
			 this.stateFilter = stateRepository.findByCode((String)params.get("stateCode"));
		 }
		
		return "";
	}
	
	public String applyDateFilter() {
		if(startDate == null || endDate == null) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN,
					"Please fill in both dates.", "Please fill in both dates.");
			FacesContext.getCurrentInstance().addMessage("filterForm:startDateInput", msg);
		}
		else if(startDate != null && endDate != null) {
			if(startDate.after(endDate)) {
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN,
						"Start Date must be before End Date.", "Start Date must be before End Date.");
				FacesContext.getCurrentInstance().addMessage("filterForm:startDateInput", msg);
			}
			else {
				this.datesEntered = true;
			}
		}
		
		return "";
	}
	
	public String applyLocationFilter() {
		if(distance < 0) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN,
					"You must pick a distance", "You must pick a distance");
			FacesContext.getCurrentInstance().addMessage("locationForm:distanceInput", msg);
		}
		
		return "";
	}

	public List<State> getStates() { 
		List<State> states = new ArrayList<State>();
		
		if(this.shortStateList) {
			Iterator<State> stateIt = stateRepository.findAll().iterator();
			for(int i=0;i<4;i++) {
				states.add(stateIt.next());
			}
			states.add(new State("more", "More..."));
		}
		else {
			for(State state : stateRepository.findAll()) {
				states.add(state);
			}
						
			states.add(4,new State("less", "Less..."));
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
		this.stateFilter = null;
	}
	
	public void removeEventTypeFilter(ActionEvent ae) {
		this.eventTypeFilter = null;
	}
	
	public void removeDateRangeFilter(ActionEvent ae) {
		this.datesEntered = false;
		this.startDate = null;
		this.endDate = null;
	}
	
	public void removeLocationFilter(ActionEvent ae) {
		this.zipCode = null;
		this.distance = -1;
	}

	public State getStateFilterValue() {
		return this.stateFilter;
	}

	public EventType getEventTypeFilterValue() {
		return this.eventTypeFilter;
	}
	
	public EventCriteria getSearchCriteria() {
		return new EventCriteria(stateFilter, eventTypeFilter, startDate, endDate, zipCode, distance);
	}
	
}
