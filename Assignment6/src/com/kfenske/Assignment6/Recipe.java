package com.kfenske.Assignment6;

import java.util.ArrayList;

public class Recipe extends Item {

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
	
	public void setIngredients()
	{
		this.ingredients = ingredients;
	}
	
	public ArrayList<Item> getIngredients(ArrayList<Item> ingredients)
	{
		return ingredients;
	}
}
