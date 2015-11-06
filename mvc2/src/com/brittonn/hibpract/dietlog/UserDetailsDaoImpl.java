package com.brittonn.hibpract.dietlog;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.brittonn.hibpract.dietlog.beans.UserDetails;

@Service
public class UserDetailsDaoImpl implements UserDetailsDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	@Transactional
	public UserDetails getUserDetails(String userName) {
	
		Session session = sessionFactory.getCurrentSession();
		UserDetails userDetails = (UserDetails)session.get(UserDetails.class, userName);
		//session.close();
		return userDetails;
	}

	@Override
	public void updateUserDetails(UserDetails userDetails) {
		Session session = sessionFactory.getCurrentSession();
//		session.beginTransaction();
		session.update(userDetails);
//		session.getTransaction().commit();
	}
}
