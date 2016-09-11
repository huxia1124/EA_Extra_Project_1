package com.extra;

import java.util.List;

import javax.persistence.EntityTransaction;

import com.extra.dao.ProjectDAO;
import com.extra.dao.UserDAO;
import com.extra.domain.Administrator;
import com.extra.domain.Project;
import com.extra.domain.User;

public class ProjectService {
	
	private ProjectDAO projectDAO = new ProjectDAO();
	private UserDAO userDAO = new UserDAO();
	private EntityTransaction tx;
	
	public void beginTransaction() {
		tx = ProjectManager.getEntityManager().getTransaction();
		tx.begin();
	}
	
	public void commitTransaction() {
		try {
			tx.commit();
		} catch (Throwable e) {
			e.printStackTrace();
			if ((tx != null) && (tx.isActive())) tx.rollback();
		}
		
	}
	
	public void createUser(User u) {
		userDAO.create(u);
	}
	
	public void createProject(Project p, User u) {
		if(u instanceof Administrator) {
			projectDAO.create(p);
		}
	}
	
	public void updateProject(Project p, User u) {
		if(u instanceof Administrator) {
			projectDAO.update(p);
		}
	}
	
	public User getUser(String account) {
		return userDAO.findByAccount(account);
	}
	
	public Project getProject(String title) {
		return projectDAO.findByTitle(title);
	}
	
	public List<Project> getAllProjects() {
		return projectDAO.findAllProjects();
	}
	
	public List<Project> getProjectsRequireSkill(String skill) {
		return projectDAO.findProjectsRequireSkill(skill);
	}
	
	public List<Project> getProjectsByKeyword(String keyword) {
		return projectDAO.findProjectsByKeyword(keyword);
	}
	
	public List<Project> getProjectsByVolunteer(String volunteer) {
		return projectDAO.findProjectsByVolunteer(volunteer);
	}	
}
