package com.kyle.route66.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.primefaces.model.DefaultScheduleEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.kyle.route66.db.dao.EventDao;
import com.kyle.route66.db.dao.EventRepository;
import com.kyle.route66.db.model.Event;
import com.kyle.route66.db.model.EventType;
import com.kyle.route66.db.model.State;
import com.kyle.route66.service.model.EventCriteria;

@Service("EventService")
@Scope("singleton")
public class EventService {
	private static final Log log = LogFactory.getLog(EventService.class);

	@Autowired
	private EventRepository eventRepository;
	
	@Autowired
	private EventDao eventDao;
	
	@Autowired
	private DistanceService distanceService;
	
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
	
	public List<Event> getEvents(EventCriteria criteria, boolean isModerator) {
		if(isModerator) {
			log.debug("MODERATOR!!!!");
		}
		List<Event> events;
		
		events = eventDao.getEvents(criteria, isModerator);
		
		if(criteria.getZipCode() != null && criteria.getDistance() != null) {
			return distanceService.filterEventsByDistance(events, criteria);
		}
		else {
			return events;
		}
	}

	public void setEventRepository(EventRepository eventRepository) {
		this.eventRepository = eventRepository;
	}
	
	public void setEventDao(EventDao eventDao) {
		this.eventDao = eventDao;
	}

	public List<Event> getEventsByState(State state) {
		return eventRepository.findByState(state);
	}

	public List<Event> getEventsByEventType(EventType eventType) {
		return eventRepository.findByEventType(eventType);
	}

	public List<Event> getEventsByStateAndEventType(State state,
			EventType eventType) {
		return eventRepository.findByStateAndEventType(state, eventType);
	}

	public void setDistanceService(DistanceService distanceService) {
		this.distanceService = distanceService;
	}
	
	
}
