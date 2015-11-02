package com.brittonn.hibpract.dietlog;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Component;

@Component
public class MyHibernateSessionFactoryImpl implements MyHibernateSessionFactory {

	static private SessionFactory sessionFactory = null;
	
	@Override
	public synchronized Session openSession() {
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
		return sessionFactory.openSession();
	
	}


}
