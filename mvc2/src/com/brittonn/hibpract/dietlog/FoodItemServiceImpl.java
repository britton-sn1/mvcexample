package com.brittonn.hibpract.dietlog;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.brittonn.hibpract.dietlog.beans.FoodItem;

@Service
public class FoodItemServiceImpl implements FoodItemService {

	@Autowired
	private DietLogDao dietLogDao;
	
	@Transactional
	@Override
	public void addFoodItem(FoodItem foodItem) {
		dietLogDao.addFoodItem(foodItem);
	}

	@Transactional
	@Override
	public List<FoodItem> getAllFoodItems() {
		return dietLogDao.getAllFoodItems();
	}

	@Transactional
	@Override
	public FoodItem getNamedFoodItem(String name) {
		return dietLogDao.getNamedFoodItem(name);
	}

	@Transactional
	@Override
	public void updateFoodItem(FoodItem foodItem) {
		dietLogDao.updateFoodItem(foodItem);
	}

	@Transactional
	@Override
	public void deleteFoodItem(String name) {
		dietLogDao.deleteFoodItem(name);
	}

	
}
