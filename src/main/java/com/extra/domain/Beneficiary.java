package com.extra.domain;

import javax.persistence.Entity;

@Entity
public class Beneficiary extends Person {
	
	private double benefitWeight = 0;

	public double getBenefitWeight() {
		return benefitWeight;
	}

	public void setBenefitWeight(double benefitWeight) {
		this.benefitWeight = benefitWeight;
	}
}
