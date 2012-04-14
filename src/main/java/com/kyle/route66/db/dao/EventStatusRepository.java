package com.kyle.route66.db.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.kyle.route66.db.model.Event;
import com.kyle.route66.db.model.EventStatus;
import com.kyle.route66.db.model.EventType;
import com.kyle.route66.db.model.State;
import com.kyle.route66.db.model.Users;

public interface EventStatusRepository extends CrudRepository<EventStatus, String>{

	EventType findByCode(String code);
	List<EventStatus> findAll();
}
