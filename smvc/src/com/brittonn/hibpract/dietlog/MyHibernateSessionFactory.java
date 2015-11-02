package com.brittonn.hibpract.dietlog;

import org.hibernate.Session;

public interface MyHibernateSessionFactory {

	Session openSession();
}
