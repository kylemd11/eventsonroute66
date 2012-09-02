package com.kyle.route66.service.converter;


import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.time.DateUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.kyle.route66.db.dao.EventDao;
import com.kyle.route66.db.dao.EventRepository;
import com.kyle.route66.db.model.Event;
import com.kyle.route66.db.model.Link;
import com.kyle.route66.db.model.State;
import com.kyle.route66.service.model.EventCriteria;
import com.kyle.route66.web.ui.LinkDto;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:/repository-test-context.xml"})
public class LinkConverterTest {
	private static final Log log = LogFactory.getLog(LinkConverterTest.class);	
	
	private Converter converter;
	
	@Before
	public void init() {
		converter = new LinkConverter();
	}
	
	@Test
	public void toDtoTest() {
		log.debug("toDtoTest()");
		
		Link linkDb = new Link(1, "test title", "test url", "test summary");
		
		LinkDto linkDto = (LinkDto)converter.toDto(linkDb, false);
		
		assertEquals(linkDb.getLinkSeqId(), linkDto.getLinkSeqId());
		assertEquals(linkDb.getTitle(), linkDto.getTitle());
		assertEquals(linkDb.getUrl(), linkDto.getUrl());
		assertEquals(linkDb.getSummary(), linkDto.getSummary());
	}
	
	@Test
	public void toDbTest() {
		log.debug("toDbTest()");
		
		LinkDto linkDto = new LinkDto(1, "test title", "test url", "test summary");
		
		Link linkDb = (Link)converter.toDb(linkDto);
		
		assertEquals(linkDb.getLinkSeqId(), linkDto.getLinkSeqId());
		assertEquals(linkDb.getTitle(), linkDto.getTitle());
		assertEquals(linkDb.getUrl(), linkDto.getUrl());
		assertEquals(linkDb.getSummary(), linkDto.getSummary());
		
	}
}
