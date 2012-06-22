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
import com.kyle.route66.db.model.Link;
import com.kyle.route66.service.model.EventCriteria;
import com.kyle.route66.web.model.event.EventScheduleBean;

@Repository("LinkDao")
public class LinkDao {
	private static final Log log = LogFactory.getLog(LinkDao.class); 
	
	@PersistenceContext
	private EntityManager em;
	
	public LinkDao() {
	}

	public List<Link> getLinks(int first, int pageSize) {
		String sql = "select l from Link l";
		
		Query query = em.createQuery(sql);
		query.setFirstResult(first);
		query.setMaxResults(pageSize);

		return (List<Link>)query.getResultList();
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}
}
