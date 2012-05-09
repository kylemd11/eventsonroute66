package com.kyle.route66.web.model.event;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.faces.event.ActionEvent;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.primefaces.event.ScheduleEntrySelectEvent;
import org.primefaces.event.map.OverlaySelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.LazyScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;
import org.primefaces.model.SortOrder;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.kyle.route66.db.dao.EventDao;
import com.kyle.route66.db.dao.EventRepository;
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
	private EventRepository eventRepository;
	
	@Autowired
	private UserSession session;

	
	
	private EventCriteria criteria = null;
	private MapModel advancedModel = null;
	
	private ScheduleModel scheduleModel = null;
	
	private Event event;
	private Marker marker;

	public ScheduleModel getEventModel() {
		if (filter.isDirty() || scheduleModel == null) {
			scheduleModel = new LazyScheduleModel() {
				@Override
				public void loadEvents(Date start, Date end) {
					EventCriteria searchCriteria = filter.getSearchCriteria();
					searchCriteria.setStartDate(start);
					searchCriteria.setEndDate(end);

					if (session.isLoggedIn()) {
						searchCriteria.setUsername(session.getUserAccount()
								.getUsername());
					}

					for (Event event : eventService.getEvents(searchCriteria,
							session.getIsModerator())) {
						addEvent(new DefaultScheduleEvent(event.getTitle(),
								event.getStartDtg(), event.getEndDtg(), event));
					}
				}
			};
			
			filter.clean();
		}

		return scheduleModel;
	}
	
	public LazyDataModel<Event> getLazyModel() { 
		LazyDataModel<Event> lazyModel = new LazyDataModel<Event>() {

			@Override
			public void setRowIndex(final int rowIndex) {
				if (rowIndex == -1 || getPageSize() == 0) {
					super.setRowIndex(-1);
				} else {
					super.setRowIndex(rowIndex % getPageSize());
				}
			}
			
			@Override
			public Event getRowData(String rowKey) {
				return eventRepository.findByEventSeqId(Integer.parseInt(rowKey));
			}

			@Override
			public Object getRowKey(Event evt) {
				return evt.getEventSeqId();
			}

			@Override
			public int getRowCount() {
				EventCriteria searchCriteria = filter.getSearchCriteria();
				return eventService.getEvents(searchCriteria, session.getIsModerator()).size();
			}

			@Override
			public List<Event> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String,String> filters) {
				EventCriteria searchCriteria = filter.getSearchCriteria();
				
				searchCriteria.setFirst(first);
				searchCriteria.setPageSize(pageSize);	
				
				log.debug("first: " + first);
				log.debug("pageSize: " + pageSize);
				
				if(session.isLoggedIn()) {
					searchCriteria.setUsername(session.getUserAccount().getUsername());
				}
				
				return eventService.getEvents(searchCriteria, session.getIsModerator());
			}
			
		};
		
        return lazyModel;  
    }

	public List<Event> getEvents() {
		EventCriteria searchCriteria = filter.getSearchCriteria();
		if(session.isLoggedIn()) {
			searchCriteria.setUsername(session.getUserAccount().getUsername());
		}
		
		return eventService.getEvents(searchCriteria, session.getIsModerator());
	}

	public MapModel getEventMapModel() {
		log.debug("getEventMapModel()");
		if(filter.isDirty() || advancedModel == null) {
			log.debug("getting new data!");
		
			EventCriteria searchCriteria = filter.getSearchCriteria();
			if(session.isLoggedIn()) {
				searchCriteria.setUsername(session.getUserAccount().getUsername());
			}
			
			if(searchCriteria.getStartDate() == null || searchCriteria.getEndDate() == null) {
				Date currDate = new Date();
				
				currDate = DateUtils.truncate(currDate, Calendar.DATE);
				searchCriteria.setStartDate(currDate);
				log.debug("startDTG: " + DateFormatUtils.ISO_DATETIME_TIME_ZONE_FORMAT.format(currDate));
				
				Date newDate = new Date();
				newDate = DateUtils.truncate(newDate, Calendar.DATE);
				newDate = DateUtils.addDays(newDate, 31);
				newDate = DateUtils.addMilliseconds(newDate, -1);
				
				searchCriteria.setEndDate(newDate);
				log.debug("endDTG: " + DateFormatUtils.ISO_DATETIME_TIME_ZONE_FORMAT.format(newDate));
			}		
		
			advancedModel = new DefaultMapModel();
			
			for (Event event : eventService.getEvents(searchCriteria, session.getIsModerator())) {
				
				LatLng latLng = new LatLng(event.getLatitude().doubleValue(), event.getLongitude().doubleValue());
				advancedModel.addOverlay(new Marker(latLng, event.getTitle(), event));
			}
			
			filter.clean();
		}
		else {
			log.debug("returning old data");
		}

		return advancedModel;
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
			this.event = (Event)event.getData();
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
		log.debug("getMarker()");
		return marker;
	}
	
	public Event getEvent() {
		return this.event;
	}

	public void onMarkerSelect(OverlaySelectEvent event) {
		log.debug("onMarkerSelect()");
		this.marker = (Marker) event.getOverlay();
	}
	
	public void setSession(UserSession session) {
		this.session = session;
	}

	public void setEventRepository(EventRepository eventRepository) {
		this.eventRepository = eventRepository;
	}
	
	
}
