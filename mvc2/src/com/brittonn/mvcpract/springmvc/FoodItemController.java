package com.brittonn.mvcpract.springmvc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.brittonn.hibpract.dietlog.FoodItemService;
import com.brittonn.hibpract.dietlog.beans.FoodItem;

/**
 * @author Neil Britton
 *
 */
@Controller
@RequestMapping("/")
public class FoodItemController {

	@Autowired
	private FoodItemService foodItemService;
	
	@RequestMapping(value = "/getAllFoodItems", method = {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView getAllFoodItems(ModelMap model) {
		List<FoodItem> foodItems = foodItemService.getAllFoodItems();
		ModelAndView modelAndView = new ModelAndView("foodItemList");
		modelAndView.addObject("foodItems", foodItems);

		return modelAndView;
	}

	@Transactional
	@RequestMapping(value = "/showAddFoodItemForm", method = RequestMethod.GET)
	public static ModelAndView showAddFoodItemForm(ModelMap model) {
		ModelAndView modelAndView = new ModelAndView("addFoodItemForm");
		FoodItemForm foodItemForm = new FoodItemForm();
		modelAndView.addObject("foodItemForm", foodItemForm);

		return modelAndView;
	}

	@Transactional
	@RequestMapping(value = "/showUpdateFoodItemForm", method = RequestMethod.GET)
	public ModelAndView showUpdateFoodItemForm(@RequestParam("selectedItem")String selectedItem, ModelMap model) {
		ModelAndView modelAndView = new ModelAndView("updateFoodItemForm");
		try {
			FoodItem foodItem = foodItemService.getNamedFoodItem(selectedItem);
			FoodItemForm foodItemForm = new FoodItemForm();
			foodItemForm.setIsUpdate();
			foodItemForm.setName(foodItem.getName());
			foodItemForm.setUnits(foodItem.getUnits());
			foodItemForm.setCarbs(Double.toString(foodItem.getCarbs()));
			foodItemForm.setSugars(Double.toString(foodItem.getSugars()));
			foodItemForm.setFats(Double.toString(foodItem.getFats()));
			foodItemForm.setSaturates(Double.toString(foodItem.getSaturates()));
			foodItemForm.setFibre(Double.toString(foodItem.getFibre()));
			foodItemForm.setProtein(Double.toString(foodItem.getProtein()));
			foodItemForm.setSodium(Double.toString(foodItem.getSodium()));
			foodItemForm.setEnergy(Double.toString(foodItem.getEnergy()));
			
			modelAndView.addObject("foodItemForm", foodItemForm);
		} catch (Exception e) {
			modelAndView=getAllFoodItems(model);
			modelAndView.addObject("error",e.toString());
		}
		
		return modelAndView;
	}
	
	@Transactional
	@RequestMapping(value = "/addFoodItem", method = RequestMethod.POST)
	public ModelAndView addFoodItem(@ModelAttribute("SpringWeb") FoodItemForm foodItemForm, ModelMap model) {
		ModelAndView modelAndView;
		foodItemForm.setDirty(true);
		foodItemForm.setError("");

		try {
			if (foodItemForm.isInvalid()) {
				modelAndView = new ModelAndView("addFoodItemForm");
				modelAndView.addObject("foodItemForm", foodItemForm);
			} else {
				foodItemService.addFoodItem(foodItemForm.getFoodItem());
				modelAndView = getAllFoodItems(model);
			}
		} catch (Exception e) {
			foodItemForm.setError(e.toString());
			modelAndView = new ModelAndView("addFoodItemForm");
			modelAndView.addObject("foodItemForm", foodItemForm);
		}

		return modelAndView;
	}

	@Transactional
	@RequestMapping(value = "/updateFoodItem", method = RequestMethod.POST)
	public ModelAndView updateFoodItem(@ModelAttribute("SpringWeb") FoodItemForm foodItemForm, ModelMap model) {
		ModelAndView modelAndView;
		foodItemForm.setDirty(true);
		foodItemForm.setError("");

		try {
			if (foodItemForm.isInvalid()) {
				modelAndView = new ModelAndView("addFoodItemForm");
				modelAndView.addObject("foodItemForm", foodItemForm);
			} else {
				foodItemService.updateFoodItem(foodItemForm.getFoodItem());
				modelAndView = getAllFoodItems(model);
			}
		} catch (Exception e) {
			foodItemForm.setError(e.toString());
			modelAndView = new ModelAndView("addFoodItemForm");
			modelAndView.addObject("foodItemForm", foodItemForm);
		}

		return modelAndView;
	}

	@Transactional
	@RequestMapping(value="/deleteFoodItem", method=RequestMethod.GET)
	public ModelAndView addFoodItemForm(@RequestParam("selectedItem")String selectedItem, ModelMap model) {
		ModelAndView modelAndView = new ModelAndView("foodItemList");
		try {
			foodItemService.deleteFoodItem(selectedItem);
			modelAndView=getAllFoodItems(model);
		}
		catch(Exception e) {
			modelAndView=getAllFoodItems(model);
			modelAndView.addObject("error",e.toString());
		}
		
		return modelAndView;
	}
}