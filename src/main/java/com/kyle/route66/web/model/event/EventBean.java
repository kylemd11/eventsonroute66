package com.kyle.route66.web.model.event;

import java.util.Map;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.primefaces.event.ScheduleEntrySelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.kyle.route66.db.dao.CommentRepository;
import com.kyle.route66.db.dao.EventRepository;
import com.kyle.route66.db.model.Comment;
import com.kyle.route66.db.model.Event;
import com.kyle.route66.service.EventService;
import com.kyle.route66.web.model.user.UserSession;

@Service("EventBean")
@Scope("session")
public class EventBean {
	private static final Log log = LogFactory.getLog(EventBean.class);

	private String comment;
	
	@Autowired
	private EventRepository eventRepository;
	
	@Autowired
	private EventService eventService;
	
	@Autowired
	private CommentRepository commentRepository;
	
	@Autowired
	private UserSession session;
	
	private Integer eventSeqId;

	private Event event;

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public void setEventSelection(ScheduleEntrySelectEvent selectEvent) {
		log.debug("setEventSelection()");
		ScheduleEvent event = (DefaultScheduleEvent) selectEvent.getScheduleEvent();
		
		if(event == null) {
			log.debug("event is null");
		}
		else if(((Event)event.getData()) == null) {
			log.debug("event obj is null");
		}
		else {
			log.debug(((Event)event.getData()).getEventSeqId());
			this.eventSeqId = ((Event)event.getData()).getEventSeqId();
			this.event = eventRepository.findByEventSeqId(((Event)event.getData()).getEventSeqId());
			this.event.getComments();
			log.debug(this.event);
		}
		
	}
	
	public boolean getIsMine() {
		return this.event.getUsername().equals(session.getUserAccount().getUsername());
	}
	
	public String showEvent() {
		Map<String, String> params = FacesContext.getCurrentInstance()
				.getExternalContext().getRequestParameterMap();
		
		log.debug("id: " + params.get("id"));
//		this.event = eventRepository.findByEventSeqId(Integer
//				.valueOf((String) params.get("id")));
//		this.event.getComments();
		this.eventSeqId = Integer.valueOf((String) params.get("id"));
		this.event = eventService.getEvent(Integer.valueOf((String) params.get("id")));

		return "success";
	}

	public MapModel getEventMapModel() {
		DefaultMapModel simpleModel = new DefaultMapModel();

		if(this.event != null) {
		simpleModel.addOverlay(new Marker(new LatLng(this.event.getLatitude()
				.doubleValue(), this.event.getLongitude().doubleValue()),
				this.event.getTitle(), this.event.getCity()));
		}

		return simpleModel;
	}
	
	public void saveComment(ActionEvent ae) {
		if(this.comment != null && this.comment.trim().length() > 0) {
			Comment comment = new Comment();
			comment.setContent(this.comment);
			comment.setEventSeqId(this.event.getEventSeqId());
			comment.setUsername(session.getUserAccount().getUsername());
			comment.setEnabled(1);
			
			commentRepository.save(comment);
			
			this.event = eventRepository.findByEventSeqId(this.event.getEventSeqId());
			this.comment = "";
		}
	}

	public Event getEvent() {
		return event;
	}

	public void setEventRepository(EventRepository eventRepository) {
		this.eventRepository = eventRepository;
	}

	public void setCommentRepository(CommentRepository commentRepository) {
		this.commentRepository = commentRepository;
	}

	public void setSession(UserSession session) {
		this.session = session;
	}
	
	public boolean getHasComments() {
		if(this.event != null) {
			return this.event.getComments().size() > 0;
		}
		else {
			return false;
		}
	}

	public void setEventService(EventService eventService) {
		this.eventService = eventService;
	}
	
	public Integer getEventSeqId() {
		return eventSeqId;
	}

	public void setEventSeqId(Integer id) {
		this.eventSeqId = id;
		
		this.event = eventService.getEvent(id);
	}
	
}
