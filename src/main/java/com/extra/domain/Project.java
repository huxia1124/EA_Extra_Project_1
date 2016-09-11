package com.extra.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Project {
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public List<Description> getDescriptions() {
		return descriptions;
	}

	public void setDescriptions(List<Description> descriptions) {
		this.descriptions = descriptions;
	}
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
	private Date startDate;
	private Date endDate;
	private String location;
	
	@OneToMany(cascade=CascadeType.ALL)
	private List<Description> descriptions = new ArrayList<>();
	

	@OneToMany(cascade=CascadeType.ALL)
	private List<Beneficiary> beneficiaries = new ArrayList<>();

	@OneToMany(cascade=CascadeType.ALL)
	private List<Task> tasks = new ArrayList<>();

	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}

	public List<Beneficiary> getBeneficiaries() {
		return beneficiaries;
	}

	public void setBeneficiaries(List<Beneficiary> beneficiaries) {
		this.beneficiaries = beneficiaries;
	}
	
	
	public void addBeneficiary(Beneficiary p) {
		beneficiaries.add(p);
	}
	
	public void addTask(Task t) {
		t.setProject(this);
		tasks.add(t);
	}
	
	public void addDescription(Description d) {
		descriptions.add(d);
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
}
