package com.kyle.route66.db.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.kyle.route66.db.model.Event;
import com.kyle.route66.db.model.State;
import com.kyle.route66.db.model.Users;

public interface StateRepository extends CrudRepository<State, String>{
	
}
