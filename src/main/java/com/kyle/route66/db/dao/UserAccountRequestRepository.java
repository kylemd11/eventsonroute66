package com.kyle.route66.db.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.kyle.route66.db.model.UserAccountRequest;

public interface UserAccountRequestRepository extends CrudRepository<UserAccountRequest, Long>{

	List<UserAccountRequest> findByUsername(String username);
	
	UserAccountRequest findByRequestId(String requestId);
}
