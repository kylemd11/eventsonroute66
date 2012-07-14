package com.kyle.route66.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.primefaces.model.DefaultScheduleEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.kyle.route66.db.dao.EventDao;
import com.kyle.route66.db.dao.EventRepository;
import com.kyle.route66.db.dao.LinkDao;
import com.kyle.route66.db.dao.LinkRepository;
import com.kyle.route66.db.model.Event;
import com.kyle.route66.db.model.EventType;
import com.kyle.route66.db.model.Link;
import com.kyle.route66.db.model.State;
import com.kyle.route66.service.model.EventCriteria;
import com.kyle.route66.web.ui.LinkDto;

@Service("LinkService")
@Scope("singleton")
public class LinkService {
	private static final Log log = LogFactory.getLog(LinkService.class);

	@Autowired
	private LinkRepository linkRepository;
	
	@Autowired
	private LinkDao linkDao;
	
	@PersistenceContext
	private EntityManager em;
	

	public List<LinkDto> getLinks(int first, int pageSize) {
		List<Link> links = linkDao.getLinks(first, pageSize);
		
		log.debug("found " + links.size() + " links");
		
		return ConversionService.toDto(links);
	}

	public void setLinkRepository(LinkRepository linkRepository) {
		this.linkRepository = linkRepository;
	}

	public void setLinkDao(LinkDao linkDao) {
		this.linkDao = linkDao;
	}

	public int getCount() {		
		return (int) linkRepository.count();
	}

	public LinkDto find(int seqId) {
		return (LinkDto) ConversionService.toDto(linkRepository.findOne(seqId));
	}

	public void save(LinkDto link) {
		linkRepository.save((Link) ConversionService.toDb(link));
	}

	public void delete(Integer linkSeqId) {
		linkRepository.delete(linkSeqId);
	}

	public LinkDto findReference(Integer linkSeqId) {
		return (LinkDto) ConversionService.toDto(this.em.getReference(Link.class, linkSeqId));
	}
	
	public void setEm(EntityManager em) {
		this.em = em;
	}
}
