package com.kyle.route66.db.dao;

import org.springframework.data.repository.CrudRepository;

import com.kyle.route66.db.model.Authorities;

public interface AuthoritiesRepository extends CrudRepository<Authorities, String>{

	Authorities findByUsername(String username);
}
