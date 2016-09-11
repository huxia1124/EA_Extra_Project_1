package com.extra;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ProjectManager {

	private static EntityManagerFactory emf;
	private static EntityManager em;

	static {
		try {
			emf = Persistence.createEntityManagerFactory("cs544");
			em = emf.createEntityManager();
		} catch (Throwable ex) {
			ex.printStackTrace();
			throw new ExceptionInInitializerError(ex);
		}
	}
	
	public static EntityManager getEntityManager() {
		return em;
	}
	
	
}
