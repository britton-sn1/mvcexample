package com.brittonn.mvcpract.mock;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.brittonn.hibpract.dietlog.DietLogDao;
import com.brittonn.hibpract.dietlog.beans.FoodItem;

@Component
public class MockDietLogDao implements DietLogDao {

	private Map<String, FoodItem> foodItems = new HashMap<>();
	@Override
	public void addFoodItem(FoodItem foodItem) {
		FoodItem foodItemClone = (FoodItem)foodItem.clone();
		
		foodItems.put(foodItemClone.getName(), foodItemClone);
	}

	@Override
	public List<FoodItem> getAllFoodItems() {
		return foodItems.values().stream().collect(Collectors.toList());
	}

	@Override
	public FoodItem getNamedFoodItem(String name) {
		return foodItems.get(name);
	}

	@Override
	public void updateFoodItem(FoodItem foodItem) {
		foodItems.replace(foodItem.getName(), foodItem);

	}

	@Override
	public void deleteFoodItem(String name) {
		foodItems.remove(name);
	}

	public boolean contains(FoodItem foodItem) {
		return foodItems.get(foodItem.getName()).getName() != null;
	}

}
