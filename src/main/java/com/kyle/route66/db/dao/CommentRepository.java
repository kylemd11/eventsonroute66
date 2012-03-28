package com.kyle.route66.db.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.kyle.route66.db.model.Comment;
import com.kyle.route66.db.model.Event;

public interface CommentRepository extends CrudRepository<Comment, Integer>{

	List<Event> findByUsername(String username);
	List<Event> findByEvent(Event event);
	List<Event> findByEventSeqId(Integer eventSeqId);
}
