package com.brittonn.hibpract.dietlog;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Main {
	
	static private SessionFactory sessionFactory = null;
	private Main(){};
	public static void main(String[] args) {
	}
	
	public static SessionFactory getSessionFactory() {
		if(sessionFactory == null)
		try {
			// load from different directory
			Configuration conf = new Configuration();
			conf.configure("hibernate.cfg.xml");
			sessionFactory = conf.buildSessionFactory();


		} catch (Throwable ex) {
			// Make sure you log the exception, as it might be swallowed
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
		return sessionFactory;
	}

}
