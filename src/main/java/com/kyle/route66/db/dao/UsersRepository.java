package com.kyle.route66.db.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.kyle.route66.db.model.Users;

public interface UsersRepository extends CrudRepository<Users, Long>{

	List<Users> findByUsername(String username);
}
