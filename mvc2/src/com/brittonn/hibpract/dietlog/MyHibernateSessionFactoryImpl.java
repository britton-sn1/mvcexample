package com.brittonn.hibpract.dietlog;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Component;

@Component
public class MyHibernateSessionFactoryImpl implements MyHibernateSessionFactory {

	static private SessionFactory sessionFactory = null;

	@Override
	public Session openSession() {
		if(sessionFactory == null) {
			try {
				Configuration configuration = new Configuration().configure();
				StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().
						applySettings(configuration.getProperties());
				sessionFactory = configuration.buildSessionFactory(builder.build());	
			} catch (Exception ex) {
				System.err.println("Initial SessionFactory creation failed." + ex);
				throw new ExceptionInInitializerError(ex);
			}
		}
		return sessionFactory.openSession();
	}

	@Override
	public Session getCurrentSession() {
		if(sessionFactory == null) {
			try {
				Configuration configuration = new Configuration().configure();
				StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().
						applySettings(configuration.getProperties());
				sessionFactory = configuration.buildSessionFactory(builder.build());	
			} catch (Exception ex) {
				System.err.println("Initial SessionFactory creation failed." + ex);
				throw new ExceptionInInitializerError(ex);
			}
		}
		return sessionFactory.getCurrentSession();
	}
}
