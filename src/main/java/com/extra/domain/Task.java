package com.extra.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Task {

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	@Id
	@GeneratedValue
	private long id;
	
	private String title;
	private int timeframe;
	
	@ManyToMany
	private List<Volunteer> volunteers = new ArrayList<>();
	
	public List<Volunteer> getVolunteers() {
		return volunteers;
	}
	public void setVolunteers(List<Volunteer> volunteers) {
		this.volunteers = volunteers;
	}

	@OneToMany(cascade=CascadeType.ALL)
	private List<Resource> requiredResources = new ArrayList<>();
	
	public List<Resource> getRequiredResources() {
		return requiredResources;
	}
	public void setRequiredResources(List<Resource> requiredResources) {
		this.requiredResources = requiredResources;
	}

	@ManyToOne
	private Project project;
	
	public Project getProject() {
		return project;
	}
	public void setProject(Project project) {
		this.project = project;
	}
	
	public void addRequiredResource(Resource r) {
		requiredResources.add(r);
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getTimeframe() {
		return timeframe;
	}
	public void setTimeframe(int timeframe) {
		this.timeframe = timeframe;
	}
	
	public void addVolunteer(Volunteer v) {
		volunteers.add(v);
		v.volunteerToTask(this); 
	}
}
