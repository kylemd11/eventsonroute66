package com.kyle.route66.web.model.event;

import java.util.Date;

import javax.faces.event.ActionEvent;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.kyle.route66.db.model.Event;
import com.kyle.route66.service.EventService;

@Service("EventScheduleBean")
@Scope("request")
public class EventScheduleBean {
	private static final Log log = LogFactory.getLog(EventScheduleBean.class);
	
	@Autowired
	private EventFilterBean filter;
	
	@Autowired
	private EventService eventService;

	public DefaultScheduleModel getEventModel() {
		DefaultScheduleModel eventModel = new DefaultScheduleModel();  
		
		if(filter.isEmpty()) {
			log.debug("Empty Filter");
			for(Event event : eventService.getEvents()) {
				eventModel.addEvent(new DefaultScheduleEvent(event.getTitle(), event.getStartDtg(), event.getEndDtg()));
			}
		}
		else if(filter.isState()) {
			log.debug("State Filter");
			for(Event event : eventService.getEventsByState(filter.getStateFilter())) {
				eventModel.addEvent(new DefaultScheduleEvent(event.getTitle(), event.getStartDtg(), event.getEndDtg()));
			}
		}
		else if(filter.isEventType()) {
			log.debug("Event Type Filter");
			for(Event event : eventService.getEventsByEventType(filter.getEventTypeFilter())) {
				eventModel.addEvent(new DefaultScheduleEvent(event.getTitle(), event.getStartDtg(), event.getEndDtg()));
			}
		}
		else if(filter.isStateAndEventType()) {
			log.debug("State and Event Type Filter");
			for(Event event : eventService.getEventsByStateAndEventType(filter.getStateFilter(), filter.getEventTypeFilter())) {
				eventModel.addEvent(new DefaultScheduleEvent(event.getTitle(), event.getStartDtg(), event.getEndDtg()));
			}
		}
          
        return eventModel;
	}

	public void setEventService(EventService eventService) {
		this.eventService = eventService;
	}

	public EventFilterBean getFilter() {
		return filter;
	}

	public void setFilter(EventFilterBean filter) {
		this.filter = filter;
	}
	
	public void applyFilter(ActionEvent ae) {
		
	}
}
