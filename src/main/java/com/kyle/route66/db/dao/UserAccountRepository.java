package com.kyle.route66.db.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.kyle.route66.db.model.UserAccount;

public interface UserAccountRepository extends CrudRepository<UserAccount, Long>{

	List<UserAccount> findByUsername(String username);
}
