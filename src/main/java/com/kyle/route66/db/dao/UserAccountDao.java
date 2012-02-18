package com.kyle.route66.db.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.kyle.route66.db.model.UserAccount;


@Service("UserAccountDao")
@Scope("singleton")
public class UserAccountDao {
	private static final Log log = LogFactory.getLog(UserAccountDao.class);

    @PersistenceContext private EntityManager entityManager;
    
    public void persist(UserAccount userAccount) {
        log.debug("persisting DbVersion instance");
        try {
            entityManager.persist(userAccount);
            log.debug("persist successful");
        }
        catch (RuntimeException re) {
            log.error("persist failed", re);
            throw re;
        }
    }
    
    public void remove(UserAccount userAccount) {
        log.debug("removing DbVersion instance");
        try {
            entityManager.remove(userAccount);
            log.debug("remove successful");
        }
        catch (RuntimeException re) {
            log.error("remove failed", re);
            throw re;
        }
    }
    
    public UserAccount merge(UserAccount userAccount) {
        log.debug("merging DbVersion instance");
        try {
        	UserAccount result = entityManager.merge(userAccount);
            log.debug("merge successful");
            return result;
        }
        catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }
    
    public UserAccount findById( int id) {
        log.debug("getting UserAccount instance with id: " + id);
        try {
        	UserAccount instance = entityManager.find(UserAccount.class, id);
            log.debug("get successful");
            return instance;
        }
        catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    public UserAccount findByUsername( String username) {
        log.debug("getting UserAccount instance with id: " + username);
        try {
        	//entityManager.createQuery(arg0)
        	UserAccount instance = entityManager.find(UserAccount.class, username);
            log.debug("get successful");
            return instance;
        }
        catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
}
