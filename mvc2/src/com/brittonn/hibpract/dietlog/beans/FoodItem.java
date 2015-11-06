package com.brittonn.hibpract.dietlog.beans;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "FoodItem")
public class FoodItem implements Cloneable, Comparable<FoodItem> {

	private String name;
	private String units;
	private double carbs;
	private double sugars;
	private double protein;
	private double fats;
	private double saturates;
	private double sodium;
	private double fibre;
	private double energy;

	public FoodItem(String name) {
		this.name = name;
	}

	public FoodItem() {
	}

	@Id
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUnits() {
		return units;
	}

	public void setUnits(String units) {
		this.units = units;
	}

	public double getCarbs() {
		return carbs;
	}

	public void setCarbs(double carbs) {
		this.carbs = carbs;
	}

	public double getSugars() {
		return sugars;
	}

	public void setSugars(double sugars) {
		this.sugars = sugars;
	}

	public double getProtein() {
		return protein;
	}

	public void setProtein(double protein) {
		this.protein = protein;
	}

	public double getFats() {
		return fats;
	}

	public void setFats(double fats) {
		this.fats = fats;
	}

	public double getSaturates() {
		return saturates;
	}

	public void setSaturates(double saturates) {
		this.saturates = saturates;
	}

	public double getSodium() {
		return sodium;
	}

	public void setSodium(double sodium) {
		this.sodium = sodium;
	}

	public void setFibre(double fibre) {
		this.fibre = fibre;
	}

	public double getFibre() {
		return fibre;
	}

	public double getEnergy() {
		return energy;
	}

	public void setEnergy(double energy) {
		this.energy = energy;
	}

	@Override
	public boolean equals(Object otherFoodItemO) {
		if (otherFoodItemO == null)
			return false;
		if (!(otherFoodItemO instanceof FoodItem))
			return false;
		FoodItem otherFoodItem = (FoodItem) otherFoodItemO;

		return name.equals(otherFoodItem.name) && units.equals(otherFoodItem.units) && carbs == otherFoodItem.carbs
				&& sugars == otherFoodItem.sugars && protein == otherFoodItem.protein && fats == otherFoodItem.fats
				&& saturates == otherFoodItem.saturates && sodium == otherFoodItem.sodium
				&& fibre == otherFoodItem.fibre && energy == otherFoodItem.energy;

	}

	@Override
	public Object clone() {
		FoodItem clone = new FoodItem();

		clone.name = name;
		clone.units = units;
		clone.carbs = carbs;
		clone.sugars = sugars;
		clone.protein = protein;
		clone.fats = fats;
		clone.saturates = saturates;
		clone.sodium = sodium;
		clone.fibre = fibre;
		clone.energy = energy;

		return clone;
	}

	@Override
	public int compareTo(FoodItem o) {
		return name.compareTo(o.getName());
	}

	@Override
	public int hashCode() {
		return name.hashCode();
	}

}
