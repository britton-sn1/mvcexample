package com.brittonn.hibpract.dietlog;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.brittonn.hibpract.dietlog.beans.FoodItem;

@Component
public class DietLogDaoImpl implements DietLogDao {
	private static Logger log = Logger.getLogger(DietLogDaoImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void addFoodItem(FoodItem foodItem) {
		Session session = sessionFactory.getCurrentSession();
		session.persist(foodItem);
		log.debug("Added food item " + foodItem.getName());
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<FoodItem> getAllFoodItems() {
		Session  session = sessionFactory.getCurrentSession();
		List<FoodItem> foodItems = session.createCriteria(FoodItem.class).list(); 
		return foodItems.stream().filter(Objects::nonNull).collect(Collectors.toCollection(ArrayList::new));
	}

	@Override
	public FoodItem getNamedFoodItem(String name) {
		Session session = sessionFactory.getCurrentSession();
		FoodItem foodItem = (FoodItem)session.get(FoodItem.class, name); 
		return foodItem;
	}

	@Override
	public void updateFoodItem(FoodItem foodItem) {
		Session session = sessionFactory.getCurrentSession();
		session.update(foodItem);
	}

	@Override
	public void deleteFoodItem(String name) {
		Session session = sessionFactory.getCurrentSession();
		FoodItem foodItem = getNamedFoodItem(name);
		session.delete(foodItem);
	}
}
