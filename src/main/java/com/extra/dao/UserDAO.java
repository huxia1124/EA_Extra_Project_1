package com.extra.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.extra.ProjectManager;
import com.extra.domain.User;


public class UserDAO {
	public void create(User u) {		
		EntityManager em = ProjectManager.getEntityManager();
		em.persist(u);	
	}
	public User findByAccount(String account) {		
		EntityManager em = ProjectManager.getEntityManager();
		Query q = em.createQuery("Select u From User u where u.account=:account");
		q.setParameter("account", account);
		
		User u = (User)q.getSingleResult();
		return u;
	}
}
