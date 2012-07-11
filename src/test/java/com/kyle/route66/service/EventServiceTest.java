package com.kyle.route66.service;


import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.time.DateUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.kyle.route66.db.dao.EventDao;
import com.kyle.route66.db.dao.EventRepository;
import com.kyle.route66.db.model.Event;
import com.kyle.route66.db.model.State;
import com.kyle.route66.service.model.EventCriteria;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:/repository-test-context.xml"})
public class EventServiceTest {
	private static final Log log = LogFactory.getLog(EventServiceTest.class);	
	
	@Autowired
	private EventService eventService;
	
	@Autowired
	private EventRepository eventRepository;
	
	@Test
	public void getEventsTest() {
		log.debug("getEventsTest()");
		assertNotNull(eventService.getEvents());
		
		Date startDate = new Date();
		Date endDate = DateUtils.addYears(new Date(),-1);
		
		assertNotNull(eventService.getEvents(startDate, endDate));
		
		assertNotNull(eventService.getEvents(new EventCriteria(), false));
		assertNotNull(eventService.getEvents(new EventCriteria(), true));
	}
	
	@Test
	public void getEventTest() {
		log.debug("getEventTest()");
		Iterable<Event> events = eventRepository.findAll();
		
		if(events.iterator().hasNext()) {
			Event event = events.iterator().next();
			
			assertNotNull(eventService.getEvent(event.getEventSeqId()));
		}
		else {
			log.error("No events found!");
		}
		
	}
	
	@Test
	public void getEventsCountTest() {
		log.debug("getEventsCountTest() -- blank criteria");
		assertNotNull(eventService.getEventsCount(new EventCriteria(), false));
		assertNotNull(eventService.getEventsCount(new EventCriteria(), true));
	}

	public void setEventService(EventService eventService) {
		this.eventService = eventService;
	}

	public void setEventRepository(EventRepository eventRepository) {
		this.eventRepository = eventRepository;
	}
}
