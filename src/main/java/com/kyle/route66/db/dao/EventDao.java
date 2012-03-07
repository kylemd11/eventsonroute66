package com.kyle.route66.db.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import com.kyle.route66.db.model.Event;
import com.kyle.route66.service.model.EventCriteria;
import com.kyle.route66.web.model.event.EventScheduleBean;

@Repository("EventDao")
public class EventDao {
	private static final Log log = LogFactory.getLog(EventDao.class); 
	
	@PersistenceContext
	private EntityManager em;
	
	public EventDao() {
	}

	public List<Event> getEvents(EventCriteria criteria) {
		List<Event> events = new ArrayList<Event>();
		String sql = "select e from Event e where ";

		if (criteria.getState() != null) {
			sql += "e.stateCd = :state ";
		}
		
		if(criteria.getEventType() != null) {
			sql += "e.eventTypeCd = :eventType ";
		}
		
		if(criteria.getStartDate() != null && criteria.getEndDate() != null) {
			sql += "(e.startDtg between :startDate and :endDate) or (e.endDtg between :startDate and :endDate)";
		}

		Query query = em.createQuery(sql);

		if (criteria.getState() != null) {
			query.setParameter("state", criteria.getState().getCode());
		}
		
		if (criteria.getEventType() != null) {
			query.setParameter("eventType", criteria.getEventType().getCode());
		}
		
		if(criteria.getStartDate() != null && criteria.getEndDate() != null) {
			query.setParameter("startDate", criteria.getStartDate());
			query.setParameter("endDate", criteria.getEndDate());
		}

		return query.getResultList();
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}
}
