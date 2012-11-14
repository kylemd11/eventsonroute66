package com.kyle.route66.db.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;

import org.apache.commons.lang3.time.DateUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
		
		log.debug("user: " + criteria.getUsername());
		
		String sql = "select e from Event e join fetch e.state join fetch e.eventType join fetch e.eventStatus join fetch e.user ua join fetch ua.user where ";

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
		
		if(criteria.getStartDate() != null && criteria.getEndDate() == null) {
			if(multiple) {
				sql += "and ";
			}
			
			sql += "(e.startDtg > :startDate) ";
			
			multiple = true;
		}
		
		if(criteria.getUsername() != null) {
			if(multiple) {
				sql += "and ";
			}
			
			if(isModerator) {
				sql += "e.isNew = false ";
				
				if(criteria.getEventStatus() != null) {
					sql += " and e.eventStatusCd = :eventStatus ";
				}
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

		Query query = em.createQuery(sql, Event.class);
		
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
		
		if(criteria.getStartDate() != null && criteria.getEndDate() == null) {
			query.setParameter("startDate", criteria.getStartDate());
		}
		
		if(criteria.getUsername() != null && !isModerator) {
			query.setParameter("username", criteria.getUsername());
		}
		
		if(criteria.getUsername() != null && isModerator && criteria.getEventStatus() != null) {
			query.setParameter("eventStatus", criteria.getEventStatus());
		}
		
		return query.getResultList();
	}
	
	public long getEventsCount(EventCriteria criteria, boolean isModerator) {
		boolean multiple = false;
		
		log.debug("user: " + criteria.getUsername());
		
		String sql = "select count(e) from Event e where ";

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
		
		if(criteria.getStartDate() != null && criteria.getEndDate() == null) {
			if(multiple) {
				sql += "and ";
			}
			
			sql += "(e.startDtg > :startDate) ";
			
			multiple = true;
		}
		
		if(criteria.getUsername() != null) {
			if(multiple) {
				sql += "and ";
			}
			
			if(isModerator) {
				sql += "e.isNew = false ";
				
				if(criteria.getEventStatus() != null) {
					sql += " and e.eventStatusCd = :eventStatus ";
				}
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

		Query query = em.createQuery(sql, Long.class);
		
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
		
		if(criteria.getStartDate() != null && criteria.getEndDate() == null) {
			query.setParameter("startDate", criteria.getStartDate());
		}
		
		if(criteria.getUsername() != null && !isModerator) {
			query.setParameter("username", criteria.getUsername());
		}
		
		if(criteria.getUsername() != null && isModerator && criteria.getEventStatus() != null) {
			query.setParameter("eventStatus", criteria.getEventStatus());
		}
		
		return (Long) query.getSingleResult();
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

	public Event getEvent(Integer eventSeqId) {
		String sql = "select e from Event e left outer join fetch e.comments join fetch e.state join fetch e.eventType join fetch e.eventStatus join fetch e.user ua join fetch ua.user where e.eventSeqId = :eventSeqId";
		
		Query query = em.createQuery(sql);
		
		query.setParameter("eventSeqId", eventSeqId);
		
		
		return (Event)query.getSingleResult();
	}

	@Transactional
	public int deleteBlankEvents() {
		Date deleteDtg = new Date();
		deleteDtg = DateUtils.addHours(deleteDtg, -2);
		
		String sql1 = "delete from EventArticleImage eai where eai.eventSeqId in (select e.eventSeqId from Event e where e.isNew = true and e.createDtg < :deleteDtg)";
		Query query1 = em.createQuery(sql1);
		query1.setParameter("deleteDtg", deleteDtg);
		query1.executeUpdate();
		
		String sql2 = "delete from Event e where e.isNew = true and e.createDtg < :deleteDtg";
		Query query2 = em.createQuery(sql2);
		query2.setParameter("deleteDtg", deleteDtg);
		
		return query2.executeUpdate();
	}
}
