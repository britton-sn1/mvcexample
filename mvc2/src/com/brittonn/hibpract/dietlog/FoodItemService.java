package com.brittonn.hibpract.dietlog;

import java.util.List;

import com.brittonn.hibpract.dietlog.beans.FoodItem;

public interface FoodItemService {

	public void addFoodItem(FoodItem foodItem);
	public List<FoodItem> getAllFoodItems();	
	public FoodItem getNamedFoodItem(String name);
	public void updateFoodItem(FoodItem foodItem);
	public void deleteFoodItem(String name);

}
