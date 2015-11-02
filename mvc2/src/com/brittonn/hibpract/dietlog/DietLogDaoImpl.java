package com.brittonn.hibpract.dietlog;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.brittonn.hibpract.dietlog.beans.FoodItem;

@Component
public class DietLogDaoImpl implements DietLogDao {
	private static Logger log = Logger.getLogger(DietLogDaoImpl.class);
	
	@Autowired
	private MyHibernateSessionFactory sessionFactory;
	
	@Override
	public void addFoodItem(FoodItem foodItem) {
		Session session = null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			session.persist(foodItem);
			session.getTransaction().commit();
			log.debug("Added food item " + foodItem.getName());
		}
		finally {
			if(session != null) session.close();
		}
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<FoodItem> getAllFoodItems() {
		Session session = null;
		List<FoodItem> foodItems = Collections.emptyList();
		try {
			session = sessionFactory.openSession();
			foodItems = session.createCriteria(FoodItem.class).list(); 
		}
		finally {
			if(session != null) session.close();
		}
		
		return foodItems.stream().filter(Objects::nonNull).collect(Collectors.toCollection(ArrayList::new))
				;
	}

	@Override
	public FoodItem getNamedFoodItem(String name) {
		Session session = null;
		FoodItem foodItem = null;
		try {
			session = sessionFactory.openSession();
			foodItem = (FoodItem)session.get(FoodItem.class, name); 
		}
		finally {
			if(session != null) session.close();
		}
		
		return foodItem;
	}

	@Override
	public void updateFoodItem(FoodItem foodItem) {
		Session session = null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			session.update(foodItem);
			session.getTransaction().commit();
		}
		finally {
			if(session != null) session.close();
		}
	}

	@Override
	public void deleteFoodItem(String name) {
		Session session = null;
		try {
			session = sessionFactory.openSession();
			FoodItem foodItem = getNamedFoodItem(name);
			session.beginTransaction();
			session.delete(foodItem);
			session.getTransaction().commit();
		}
		finally {
			if(session != null) session.close();
		}
	}
}
