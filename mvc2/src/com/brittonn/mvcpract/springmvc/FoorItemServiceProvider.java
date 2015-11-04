package com.brittonn.mvcpract.springmvc;

import org.springframework.stereotype.Component;

import com.brittonn.hibpract.dietlog.beans.FoodItem;

@Component
public interface FoorItemServiceProvider {

	void updateFoodItem(FoodItem foodItem);

	
}
