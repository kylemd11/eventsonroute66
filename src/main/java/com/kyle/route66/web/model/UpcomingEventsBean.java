package com.kyle.route66.web.model;


import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.kyle.route66.db.dao.EventRepository;
import com.kyle.route66.db.model.Event;
import com.kyle.route66.service.EventService;
import com.kyle.route66.service.model.EventCriteria;
import com.kyle.route66.web.model.user.UserSession;

@Service("UpcomingEventsBean")
@Scope("session")
public class UpcomingEventsBean {
	private static final Log log = LogFactory.getLog(UpcomingEventsBean.class);

	@Autowired
	@Qualifier("UserSession")
	private UserSession session;

	@Autowired
	private EventService eventService;
	
	@Autowired
	private EventRepository eventRepository;
	
	private LazyDataModel<Event> lazyModel = null;
	
	public UpcomingEventsBean() {
		lazyModel = new LazyDataModel<Event>() {
			
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

//			@Override
//			public int getRowCount() {
//				EventCriteria searchCriteria = new EventCriteria();
//				searchCriteria.setStartDate(DateUtils.truncate(new Date(), Calendar.HOUR));
//				
//				return eventService.getEvents(searchCriteria, false).size();
//			}

			@Override
			public List<Event> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String,String> filters) {
				
				EventCriteria searchCriteria = new EventCriteria();
				
				searchCriteria.setStartDate(DateUtils.truncate(new Date(), Calendar.HOUR));
				
				setRowCount(eventService.getEvents(searchCriteria, false).size());
				
				searchCriteria.setFirst(first);
				searchCriteria.setPageSize(pageSize);	
				
				log.debug("first: " + first);
				log.debug("pageSize: " + pageSize);
				
				
				return eventService.getEvents(searchCriteria, false);
			}
		};
	}
	
	public LazyDataModel<Event> getLazyModel() {
		return lazyModel;
	}

	public void setSession(UserSession session) {
		this.session = session;
	}

	public void setEventService(EventService eventService) {
		this.eventService = eventService;
	}

	public void setEventRepository(EventRepository eventRepository) {
		this.eventRepository = eventRepository;
	}
}
