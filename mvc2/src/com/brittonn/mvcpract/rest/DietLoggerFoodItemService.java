package com.brittonn.mvcpract.rest;

import java.util.List;
import java.util.stream.Collectors;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.brittonn.hibpract.dietlog.DietLogDao;
import com.brittonn.hibpract.dietlog.beans.FoodItem;


@Path("fooditem")
@Component
/**
 * Resful service to add/update/delete/get and get all fooditemns
 * 
 * @author N Britton
 *
 */
public class DietLoggerFoodItemService {
	
	Logger log = Logger.getLogger(DietLoggerFoodItemService.class);
	
	// Dao used to update the underlying data store
	@Autowired
	private DietLogDao dietLogDao;
	

	@POST
	@Path("add")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	/**
	 * Service /fooditem/add persists the specified foodItem. Input is a json representation of the food item, 
	 * the service responses with a json representation of the food item 
	 * 
	 * Use HTTP Method POST
	 * 
	 * @param foodItem
	 * @return
	 */
	public Response addFoodItem(FoodItem foodItem) {
		
		dietLogDao.addFoodItem(foodItem);
		log.debug("Added food item " + foodItem.getName());
		return Response.ok(foodItem).status(200).build();
	}
	
	@GET
	@Path("getall")
	@Produces(MediaType.APPLICATION_JSON)
	/**
	 * Service /fooditem/getAll returns a collection of all the food items in the datastore ordered by name in a json represenation
	 * 
	 * Use HTTP Method GET
	 * 
	 * @return
	 */
	public Response getAllFoodItems() {
		
		List<FoodItem> foodItems = dietLogDao.getAllFoodItems().stream().sorted().collect(Collectors.toList());
		log.debug("Got " + foodItems.size() + " food items");
		return Response.ok(foodItems).status(200).build();
	}
	
	@GET
	@Path("getnamed")
	@Produces(MediaType.APPLICATION_JSON)
	/**
	 * Service /fooditem/getnamed?name=x returns a json representation of the food item identified by the name query paramter
	 * 
	 * Use HTTP Method GET
	 * 
	 * @return
	 */
	public Response getFoodItem(@QueryParam("name") String name) {
		
		FoodItem foodItem = dietLogDao.getNamedFoodItem(name);
		log.debug("Got food item " + name);
		return Response.ok(foodItem).status(200).build();
	}
	
	@PUT
	@Path("update")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	/**
	 * Service /fooditem/update  updates the specified foodItem. Input is a json representation of the food item, 
	 * the service responses with a json representation of the food item
	 *  
	 * Use HTTP Method PUT
	 * 
	 * @return
	 */
	public Response updateFoodItems(FoodItem foodItem) {
		
		dietLogDao.updateFoodItem(foodItem);
		log.debug("Updated food item " + foodItem.getName());
		return Response.ok(foodItem).status(200).build();
	}
	
	@DELETE
	@Path("delete")
	@Produces(MediaType.APPLICATION_JSON)
	/**
	 * Service /fooditem/delete?name=x deletes a the food item identified by the name query parameter
	 * Use HTTP Method DELETE
	 * 
	 * @return
	 */
	public Response deleteFoodItem(@QueryParam("name") String name) {
		
		dietLogDao.deleteFoodItem(name);
		log.debug("Deleted food item " + name);
		return Response.ok("deleted food item " + name).status(200).build();
	}
	

}	

