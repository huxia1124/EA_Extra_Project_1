package com.extra.domain;

import javax.persistence.Entity;

@Entity
public class Skill extends Resource {

	public Skill(String name) {
		this.name = name;
	}


}
