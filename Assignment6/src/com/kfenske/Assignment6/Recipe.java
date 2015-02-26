package com.kfenske.Assignment6;

import java.util.ArrayList;

public class Recipe extends Item {

	//Inherits from Item - creates an array list of Items that includes both name
	//and unit size which creates ingredients
	
	private ArrayList<Item> ingredients = new ArrayList<Item>();
	private String instructions;
	
	public void setInstructions(String instructions)
	{
		this.instructions = instructions;
	}
	
	public String getInstructions()
	{
		return instructions;
	}
	
	public void setIngredients(ArrayList<Item> ingredients)
	{
		this.ingredients = ingredients;
	}
	
	public ArrayList<Item> getIngredients()
	{
		return ingredients;
	}
}
