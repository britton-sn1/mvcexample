package com.brittonn.hibpract.dietlog;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.brittonn.hibpract.dietlog.beans.UserDetails;

@Component
public class UserDetailsDaoImpl implements UserDetailsDao {

	@Autowired
	private MyHibernateSessionFactory sessionFactory;
	
	@Override
	public UserDetails getUserDetails(String userName) {
	
		Session session = sessionFactory.openSession();
		UserDetails userDetails = (UserDetails)session.get(UserDetails.class, userName);
		
		return userDetails;
	}

	@Override
	public void updateUserDetails(UserDetails userDetails) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.update(userDetails);
		session.getTransaction().commit();
	}
}
