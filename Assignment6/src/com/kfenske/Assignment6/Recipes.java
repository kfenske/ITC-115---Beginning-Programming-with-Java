package com.kfenske.Assignment6;

import java.util.ArrayList;

public class Recipes implements ManageItems{

	private ArrayList<Recipe> recipes = new ArrayList<Recipe>();
	
	public ArrayList<Recipe> getRecipes()
	{
		return recipes;
	}
	
	@Override
	public void add(Recipe r)
	{
		recipes.add(r);
	}
}
