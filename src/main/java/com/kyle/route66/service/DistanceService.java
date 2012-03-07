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

import com.javadocmd.simplelatlng.LatLng;
import com.javadocmd.simplelatlng.LatLngTool;
import com.javadocmd.simplelatlng.util.LengthUnit;
import com.kyle.route66.db.dao.EventDao;
import com.kyle.route66.db.dao.EventRepository;
import com.kyle.route66.db.dao.ZipCodeRepository;
import com.kyle.route66.db.model.Event;
import com.kyle.route66.db.model.EventType;
import com.kyle.route66.db.model.State;
import com.kyle.route66.db.model.ZipCode;
import com.kyle.route66.service.model.EventCriteria;

@Service("DistanceService")
@Scope("singleton")
public class DistanceService {
	private static final Log log = LogFactory.getLog(DistanceService.class);

	@Autowired
	private ZipCodeRepository zipCodeRepository;

	public List<Event> filterEventsByDistance(List<Event> events, EventCriteria criteria) {
		List<Event> filteredEvents = new ArrayList<Event>();
		
		ZipCode zip = zipCodeRepository.findByZipCode(criteria.getZipCode());
		
		for(Event event : events) {
			LatLng point1 = new LatLng(event.getLatitude().doubleValue(),event.getLongitude().doubleValue());
			LatLng point2 = new LatLng(zip.getLatitude().doubleValue(), zip.getLongitude().doubleValue());

			if(LatLngTool.distance(point1, point2, LengthUnit.MILE) <= criteria.getDistance()) {
				filteredEvents.add(event);
			}
		}

		return filteredEvents;
	}

	public void setZipCodeRepository(ZipCodeRepository zipCodeRepository) {
		this.zipCodeRepository = zipCodeRepository;
	}
}
