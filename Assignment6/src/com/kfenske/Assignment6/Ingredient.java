package com.kfenske.Assignment6;

public class Ingredient extends Item {

	private String unitSize;
	private double calories;
	private double fat;
	private double protein;
	private double carbs;
	
	public String getUnitSize() 
	{
		return unitSize;
	}
	
	public void setUnitSize(String unitSize) 
	{
		this.unitSize = unitSize;
	}
}
