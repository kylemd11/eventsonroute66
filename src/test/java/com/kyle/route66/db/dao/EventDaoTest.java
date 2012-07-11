package com.kyle.route66.db.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.kyle.route66.db.model.Event;
import com.kyle.route66.db.model.State;
import com.kyle.route66.service.model.EventCriteria;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:/repository-test-context.xml"})
public class EventDaoTest {
	private static final Log log = LogFactory.getLog(EventDaoTest.class);	
	
	@Autowired
	private EventDao eventDao;
	
	@Autowired
	private EventRepository eventRepository;
	
	@Test
	public void getEventTest() {
		log.debug("getEventTest()");
		Iterable<Event> events = eventRepository.findAll();
		
		if(events.iterator().hasNext()) {
			Event event = events.iterator().next();
			
			assertNotNull(eventDao.getEvent(event.getEventSeqId()));
		}
		else {
			log.error("No events found!");
		}
		
	}
	
	@Test
	public void getEventsTest() {
		log.debug("getEventsTest() -- blank criteria");
		assertNotNull(eventDao.getEvents(new EventCriteria(), false));
		assertNotNull(eventDao.getEvents(new EventCriteria(), true));
		
		EventCriteria criteria = new EventCriteria();
		criteria.setState(new State("OK", "Oklahoma"));
		
		log.debug("getEventsTest() -- OK State criteria");
		assertNotNull(eventDao.getEvents(criteria, false));
		assertNotNull(eventDao.getEvents(criteria, true));
	}
	
	@Test
	public void getEventsCountTest() {
		log.debug("getEventsCountTest() -- blank criteria");
		assertNotNull(eventDao.getEventsCount(new EventCriteria(), false));
		assertNotNull(eventDao.getEventsCount(new EventCriteria(), true));
	}

	public void setEventDao(EventDao eventDao) {
		this.eventDao = eventDao;
	}

	public void setEventRepository(EventRepository eventRepository) {
		this.eventRepository = eventRepository;
	}
}
