package com.brittonn.hibpract.dietlog;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.brittonn.hibpract.dietlog.beans.FoodItem;

@Service
public class DietLogDaoImpl implements DietLogDao {
	private static Logger log = Logger.getLogger(DietLogDaoImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	@Transactional
	public void addFoodItem(FoodItem foodItem) {
		Session session = null;
		try {
			session = sessionFactory.getCurrentSession();
			session.persist(foodItem);
			log.debug("Added food item " + foodItem.getName());
		}
		finally {
//			if(session != null) session.close();
		}
		
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<FoodItem> getAllFoodItems() {
		Session session = null;
		List<FoodItem> foodItems = Collections.emptyList();
		try {
			session = sessionFactory.getCurrentSession();
			foodItems = session.createCriteria(FoodItem.class).list(); 
		}
		finally {
			//if(session != null) session.close();
		}
		
		return foodItems.stream().filter(Objects::nonNull).collect(Collectors.toCollection(ArrayList::new))
				;
	}

	@Override
	@Transactional
	public FoodItem getNamedFoodItem(String name) {
		Session session = null;
		FoodItem foodItem = null;
		try {
			session = sessionFactory.getCurrentSession();
			foodItem = (FoodItem)session.get(FoodItem.class, name); 
		}
		finally {
			//if(session != null) session.close();
		}
		
		return foodItem;
	}

	@Override
	public void updateFoodItem(FoodItem foodItem) {
		Session session = null;
		try {
			session = sessionFactory.getCurrentSession();
//			session.beginTransaction();
			session.update(foodItem);
//			session.getTransaction().commit();
		}
		finally {
//			if(session != null) session.close();
		}
	}

	@Override
	@Transactional
	public void deleteFoodItem(String name) {
		Session session = null;
		try {
			session = sessionFactory.getCurrentSession();
			FoodItem foodItem = getNamedFoodItem(name);
//			session.beginTransaction();
			session.delete(foodItem);
//			session.getTransaction().commit();
		}
		finally {
			//if(session != null) session.close();
		}
	}
}
