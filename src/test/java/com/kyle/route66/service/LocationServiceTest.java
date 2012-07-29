package com.kyle.route66.service;


import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
public class LocationServiceTest {
	private static final Log log = LogFactory.getLog(LocationServiceTest.class);	
	
	@Autowired
	private LocationService locationService;
	
	@Test
	public void getAddressForGeoLocationTest() {
		log.debug("getAddressForGeoLocationTest()");
		
		Map<String, String> addressForGeoLocation = locationService.getAddressForGeoLocation(new BigDecimal("40.714224"), new BigDecimal("-73.961452"));
		
		assertEquals(addressForGeoLocation.get("address"), "285 Bedford Ave");
		assertEquals(addressForGeoLocation.get("city"), "Brooklyn");
		assertEquals(addressForGeoLocation.get("state"), "NY");
		assertEquals(addressForGeoLocation.get("zip"), "11211");
	}

	public void setLocationService(LocationService locationService) {
		this.locationService = locationService;
	}
}
