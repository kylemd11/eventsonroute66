package com.kyle.route66.db.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.kyle.route66.db.model.Event;
import com.kyle.route66.db.model.EventType;
import com.kyle.route66.db.model.State;
import com.kyle.route66.db.model.Users;

public interface EventRepository extends CrudRepository<Event, Long>{

	List<Event> findByUsername(String username);
	List<Event> findByState(State state);
	List<Event> findByStateCode(String code);
	List<Event> findByEventTypeCode(String eventTypeFilter);
	List<Event> findByStateCodeAndEventTypeCode(String stateFilter, String eventTypeFilter);
	List<Event> findByEventType(EventType eventType);
	List<Event> findByStateAndEventType(State state, EventType eventType);
}
