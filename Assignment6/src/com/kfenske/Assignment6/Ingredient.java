package com.kfenske.Assignment6;

public class Ingredient extends Item {

	//Inherits from Item and adds the unit size
	private String unitSize;
	
	public String getUnitSize() 
	{
		return unitSize;
	}
	
	public void setUnitSize(String unitSize) 
	{
		this.unitSize = unitSize;
	}
}
