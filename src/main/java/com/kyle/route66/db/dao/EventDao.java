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

	public List<Event> getEvents(EventCriteria criteria, boolean isModerator) {
		boolean multiple = false;
		
		String sql = "select e from Event e where ";

		if (criteria.getState() != null) {
			sql += "e.stateCd = :state ";
			
			multiple = true;
		}
		
		if(criteria.getEventType() != null) {
			if(multiple) {
				sql += "and ";
			}
			
			sql += "e.eventTypeCd = :eventType ";
			
			multiple = true;
		}
		
		if(criteria.getStartDate() != null && criteria.getEndDate() != null) {
			if(multiple) {
				sql += "and ";
			}
			
			sql += "((e.startDtg between :startDate and :endDate) or (e.endDtg between :startDate and :endDate)) ";
			
			multiple = true;
		}
		
		if(criteria.getUsername() != null) {
			if(multiple) {
				sql += "and ";
			}
			
			if(isModerator) {
				sql += "e.isNew = false ";
			}
			else {
				sql += "(e.eventStatusCd = 'A' or (e.username = :username and e.isNew = false)) ";
			}
			
			multiple = true;
		}
		else {
			if(multiple) {
				sql += "and ";
			}
			
			sql += "e.eventStatusCd = 'A' ";
			
			multiple = true;
		}
		
		log.debug("JPQL: " + sql);

		Query query = em.createQuery(sql);
		
		if(criteria.getFirst() != null) {
			query.setFirstResult(criteria.getFirst());
		}
		
		if(criteria.getPageSize() != null) {
			query.setMaxResults(criteria.getPageSize());
		}

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
		
		if(criteria.getUsername() != null && !isModerator) {
			query.setParameter("username", criteria.getUsername());
		}

		return (List<Event>)query.getResultList();
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}
}
