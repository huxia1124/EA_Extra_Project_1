package com.extra.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;

@Entity
public class Volunteer extends User {
	
	
	@ManyToMany(mappedBy="volunteers")
	private List<Task> tasks = new ArrayList<>();
	
	public void volunteerToTask(Task t) {
		tasks.add(t);
	}

}
