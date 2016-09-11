package com.extra.domain;

import javax.persistence.Entity;

@Entity
public abstract class User extends Person{
	
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	private String account;
	private String password;

}
