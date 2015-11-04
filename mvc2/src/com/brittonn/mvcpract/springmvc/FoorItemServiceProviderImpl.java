package com.brittonn.mvcpract.springmvc;

import org.springframework.beans.factory.annotation.Autowired;

import com.brittonn.hibpract.dietlog.DietLogDao;
import com.brittonn.hibpract.dietlog.beans.FoodItem;

public class FoorItemServiceProviderImpl implements FoorItemServiceProvider {

	@Autowired
	private DietLogDao dietLogDao;
	
	@Override
	public void updateFoodItem(FoodItem foodItem) {
		dietLogDao.updateFoodItem(foodItem);
	}

}
