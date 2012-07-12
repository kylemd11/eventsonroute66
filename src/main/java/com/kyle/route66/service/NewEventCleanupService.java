package com.kyle.route66.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service("NewEventCleanupService")
@Scope("singleton")
public class NewEventCleanupService {
	private static final Log log = LogFactory.getLog(NewEventCleanupService.class);
	
	@Autowired
	private EventService eventService;

	@Scheduled(cron = "1 1 * * * ?")
	public void cleanupNewEvents() {
		log.info("Cleaning up new events!");
		
		eventService.deleteBlankEvents();
	}

	public void setEventService(EventService eventService) {
		this.eventService = eventService;
	}
}
