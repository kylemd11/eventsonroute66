package com.kyle.route66.service;


import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.time.DateUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.kyle.route66.db.dao.EventDao;
import com.kyle.route66.db.dao.EventRepository;
import com.kyle.route66.db.model.Event;
import com.kyle.route66.db.model.State;
import com.kyle.route66.service.model.EventCriteria;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:/repository-test-context.xml"})
public class LinkServiceTest {
	private static final Log log = LogFactory.getLog(LinkServiceTest.class);	
	
	@Autowired
	private LinkService linkService;
	
	@Test
	public void getLinksTest() {
		log.debug("getLinksTest()");
		assertNotNull(linkService.getLinks(0, 5));
	}
	
	public void setLinkService(LinkService linkService) {
		this.linkService = linkService;
	}
}
