package com.kyle.route66.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

@Service("LinkService")
@Scope("singleton")
public class LinkService {
	private static final Log log = LogFactory.getLog(LinkService.class);

	@Autowired
	private LinkRepository linkRepository;
	
	@Autowired
	private LinkDao linkDao;
	

	public List<Link> getLinks(int first, int pageSize) {
		return linkDao.getLinks(first, pageSize);
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

	public Link find(int seqId) {
		return linkRepository.findOne(seqId);
	}
}
