package com.kyle.route66.web.model.event;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.event.ActionEvent;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.primefaces.event.ScheduleEntrySelectEvent;
import org.primefaces.event.map.OverlaySelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.LazyScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.kyle.route66.db.model.Event;
import com.kyle.route66.service.EventService;
import com.kyle.route66.service.model.EventCriteria;
import com.kyle.route66.web.model.user.UserSession;

@Service("EventScheduleBean")
@Scope("session")
public class EventScheduleBean {
	private static final Log log = LogFactory.getLog(EventScheduleBean.class);

	@Autowired
	private EventFilterBean filter;

	@Autowired
	private EventService eventService;
	
	@Autowired
	private UserSession session;

	private Marker marker;

	public ScheduleModel getEventModel() {
		ScheduleModel eventModel = new LazyScheduleModel() {			
			@Override
			public void loadEvents(Date start, Date end) {
				EventCriteria searchCriteria = filter.getSearchCriteria();
				searchCriteria.setStartDate(start);
				searchCriteria.setEndDate(end);
				
				if(session.isLoggedIn()) {
					searchCriteria.setUsername(session.getUserAccount().getUsername());
				}
				
				for (Event event : eventService.getEvents(searchCriteria)) {
					addEvent(new DefaultScheduleEvent(event.getTitle(),event.getStartDtg(),event.getEndDtg()));
				}
			}
		};

		return eventModel;
	}

	public List<Event> getEvents() {
		EventCriteria searchCriteria = filter.getSearchCriteria();
		if(session.isLoggedIn()) {
			searchCriteria.setUsername(session.getUserAccount().getUsername());
		}
		
		return eventService.getEvents(searchCriteria);
	}

	public MapModel getEventMapModel() {
		DefaultMapModel simpleModel = new DefaultMapModel();

		for (Event event : eventService.getEvents(filter.getSearchCriteria())) {
			simpleModel.addOverlay(new Marker(new LatLng(event.getLatitude()
					.doubleValue(), event.getLongitude().doubleValue()), event
					.getTitle(), event.getCity()));
		}

		return simpleModel;
	}
	
	public void setEventSelection(ScheduleEntrySelectEvent selectEvent) {
		log.debug("setEventSelection()");
		ScheduleEvent event = selectEvent.getScheduleEvent();
		
		if(event == null) {
			log.debug("event is null");
		}
		else if(((Event)event.getData()) == null) {
			log.debug("event obj is null");
		}
		else {
			log.debug(((Event)event.getData()).getEventSeqId());
			//this.event = eventRepository.findByEventSeqId(((Event)event.getData()).getEventSeqId());
			//log.debug(this.event);
		}
		
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

	public Marker getMarker() {
		return marker;
	}

	public void onMarkerSelect(OverlaySelectEvent event) {
		this.marker = (Marker) event.getOverlay();
	}
	
	public void setSession(UserSession session) {
		this.session = session;
	}
}
