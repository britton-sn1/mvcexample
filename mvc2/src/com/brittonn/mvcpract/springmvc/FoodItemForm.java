package com.brittonn.mvcpract.springmvc;

import com.brittonn.hibpract.dietlog.beans.FoodItem;

/**
 * @author Neil Britton
 *
 */

public class FoodItemForm {

	private String name;
	private String units;
	private String carbs;
	private String sugars;
	private String protein;
	private String fats;
	private String saturates;
	private String sodium;
	private String fibre;
	private String energy;
	private boolean isDirty = false;
	private boolean isUpdate = false;

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

	public String getCarbs() {
		return carbs;
	}

	public void setCarbs(String carbs) {
		this.carbs = carbs;
	}

	public String getSugars() {
		return sugars;
	}

	public void setSugars(String sugars) {
		this.sugars = sugars;
	}

	public String getProtein() {
		return protein;
	}

	public void setProtein(String protein) {
		this.protein = protein;
	}

	public String getFats() {
		return fats;
	}

	public void setFats(String fats) {
		this.fats = fats;
	}

	public String getSaturates() {
		return saturates;
	}

	public void setSaturates(String saturates) {
		this.saturates = saturates;
	}

	public String getSodium() {
		return sodium;
	}

	public void setSodium(String sodium) {
		this.sodium = sodium;
	}

	public void setFibre(String fibre) {
		this.fibre = fibre;
	}

	public String getFibre() {
		return fibre;
	}

	public String getEnergy() {
		return energy;
	}

	public void setEnergy(String energy) {
		this.energy = energy;
	}
	
	public void setDirty(boolean isDirty) {
		this.isDirty = isDirty;
	}

	public boolean getNameNotSupplied() {
		return isDirty && (name == null || name.trim().length() == 0);
	}

	public boolean getUnitsNotSupplied() {
		return isDirty && (units == null || units.trim().length() == 0);
	}

	public boolean getCarbsNotSupplied() {
		return isDirty && (carbs == null || carbs.trim().length() == 0);
	}

	public boolean getSugarsNotSupplied() {
		return isDirty && (sugars == null || sugars.trim().length() == 0);
	}

	public boolean getFatsNotSupplied() {
		return isDirty && (fats == null || fats.trim().length() == 0);
	}

	public boolean getSaturatesNotSupplied() {
		return isDirty && (saturates == null || saturates.trim().length() == 0);
	}

	public boolean getProteinNotSupplied() {
		return isDirty && (protein == null || protein.trim().length() == 0);
	}

	public boolean getFibreNotSupplied() {
		return isDirty && (fibre == null || fibre.trim().length() == 0);
	}

	public boolean getSodiumNotSupplied() {
		return isDirty && (sodium == null || sodium.trim().length() == 0);
	}

	public boolean getEnergyNotSupplied() {
		return isDirty && (energy == null || energy.trim().length() == 0);
	}

	public boolean isInvalid() {
		return getNameNotSupplied() &&
			getCarbsNotSupplied() &&
			getSugarsNotSupplied() &&
			getFatsNotSupplied() &&
			getSaturatesNotSupplied() &&
			getProteinNotSupplied() &&
			getFibreNotSupplied() &&
			getSodiumNotSupplied() &&
			getEnergyNotSupplied();
	}

	public FoodItem getFoodItem() {
		FoodItem foodItem = new FoodItem();
		foodItem.setName(name);
		foodItem.setUnits(units);
		foodItem.setCarbs(Double.parseDouble(carbs));
		foodItem.setSugars(Double.parseDouble(sugars));
		foodItem.setFats(Double.parseDouble(fats));
		foodItem.setSaturates(Double.parseDouble(saturates));
		foodItem.setProtein(Double.parseDouble(protein));
		foodItem.setFibre(Double.parseDouble(fibre));
		foodItem.setSodium(Double.parseDouble(sodium));
		foodItem.setEnergy(Double.parseDouble(energy));
		
		return foodItem;
	}

	public void setIsUpdate() {
		this.isUpdate  = true;
	}

	public boolean getIsUpdate() {
		return isUpdate;
	}

}
