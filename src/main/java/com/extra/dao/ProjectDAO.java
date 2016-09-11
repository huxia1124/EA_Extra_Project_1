package com.extra.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.extra.ProjectManager;
import com.extra.domain.Project;

public class ProjectDAO {
	
	public void create(Project p) {		
		EntityManager em = ProjectManager.getEntityManager();
		em.persist(p);	
	}
	
	public void update(Project p) {		
		EntityManager em = ProjectManager.getEntityManager();
		em.persist(p);	
	}
	
	public Project findByTitle(String title) {		
		EntityManager em = ProjectManager.getEntityManager();
		Query q = em.createQuery("Select p From Project p where p.title=:title");
		q.setParameter("title", title);
		
		Project p = (Project)q.getSingleResult();
		return p;
	}
	
	public List<Project> findAllProjects() {
		EntityManager em = ProjectManager.getEntityManager();
		Query q = em.createQuery("Select p From Project p");
		return q.getResultList();
	}
	
	public List<Project> findProjectsRequireSkill(String skill) {
		EntityManager em = ProjectManager.getEntityManager();
		Query q = em.createQuery("Select distinct p From Project p join p.tasks t join TREAT(t.requiredResources as Skill) s where s.name=:skill");
		q.setParameter("skill", skill);
		return q.getResultList();
	}
	
	public List<Project> findProjectsByKeyword(String keyword) {
		EntityManager em = ProjectManager.getEntityManager();
		Query q = em.createQuery("Select distinct p From Project p where p.title like :keyword");
		q.setParameter("keyword", "%" + keyword + "%");
		return q.getResultList();
	}
	
	public List<Project> findProjectsByVolunteer(String volunteer) {
		EntityManager em = ProjectManager.getEntityManager();
		Query q = em.createQuery("Select distinct p From Project p join p.tasks t join t.volunteers v where v.firstName=:volunteer");
		q.setParameter("volunteer", volunteer);
		return q.getResultList();
	}	
}
