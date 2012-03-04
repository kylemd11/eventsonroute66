package com.kyle.route66.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.kyle.route66.db.dao.EventRepository;
import com.kyle.route66.db.model.Event;
import com.kyle.route66.db.model.State;
import com.kyle.route66.service.model.EventCriteria;

@Service("EventService")
@Scope("singleton")
public class EventService {
	private static final Log log = LogFactory.getLog(EventService.class);

	@Autowired
	private EventRepository eventRepository;
	
	public List<Event> getEvents() {
		List<Event> events = new ArrayList<Event>();
		
		for(Event event : eventRepository.findAll()) {
			events.add(event);
		}
		
		return events;
	}
	
	public List<Event> getEvents(Date startDate, Date endDate) {
		List<Event> events = new ArrayList<Event>();
		
		return events;
	}
	
	public List<Event> getEvents(EventCriteria criteria) {
		List<Event> events = new ArrayList<Event>();
		
		return events;
	}

	public void setEventRepository(EventRepository eventRepository) {
		this.eventRepository = eventRepository;
	}

	public List<Event> getEventsByState(String string) {
		return eventRepository.findByStateCode(string);
	}

	public List<Event> getEventsByEventType(String eventTypeFilter) {
		return eventRepository.findByEventTypeCode(eventTypeFilter);
	}

	public List<Event> getEventsByStateAndEventType(String stateFilter,
			String eventTypeFilter) {
		return eventRepository.findByStateCodeAndEventTypeCode(stateFilter, eventTypeFilter);
	}
}
