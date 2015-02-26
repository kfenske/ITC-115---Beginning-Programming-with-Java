package com.kfenske.Assignment6;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class RecipeForm {
	
	private JFrame frame;
	private JPanel titlePanel;
	private JLabel recipeLabel;
	private JLabel quantityLabel;
	private JLabel ingredientsLabel;
	private JLabel instructionsLabel;
	private JTextField recipeName;
	private JPanel borderPanel;
	private JPanel ingredientsPanel;
	private JTextArea txtIngr;
	private JPanel instructionsPanel;
	private JTextArea txtQuant;
	private JPanel quantityPanel;
	private JTextArea txtInstr;
	private JPanel buttonPanel;
	private JPanel quantityTitle;
	private JPanel ingredientsTitle;
	private JPanel instructionsTitle;
	private JButton addRecipe;
	private JButton exitButton;
	private JButton viewRecipes;
	private Recipes meal;

	Recipes recipes = new Recipes();
	
	public RecipeForm()
	{
		createFrame();
		meal = new Recipes();
	}
	
	public void createFrame()
	{
		frame = new JFrame();
		frame.setBounds(50, 50, 900, 700);
		frame.add(createBorderPanel());
		frame.setVisible(true);
	}

	private JPanel createBorderPanel() {
		borderPanel = new JPanel();
		//JSplitPane creates formatting that is easier to control the size of the panels.
		JSplitPane vertical3 = new JSplitPane(JSplitPane.VERTICAL_SPLIT, createQuantityLabel(), createQuantityPanel());
		JSplitPane vertical4 = new JSplitPane(JSplitPane.VERTICAL_SPLIT, createIngredientsLabel(), createIngredientsPanel());
		JSplitPane vertical5 = new JSplitPane(JSplitPane.VERTICAL_SPLIT, createInstructionsLabel(), createInstructionsPanel());
		JSplitPane horizontal1 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, vertical4, vertical5);
		JSplitPane horizontal2 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, vertical3, horizontal1);
		JSplitPane vertical1 = new JSplitPane(JSplitPane.VERTICAL_SPLIT, horizontal2, createButtonPanel());
		JSplitPane vertical2 = new JSplitPane(JSplitPane.VERTICAL_SPLIT, createRecipeTitle(), vertical1);
		borderPanel.add(vertical2);
		return borderPanel;
	}

	private JPanel createRecipeTitle() {
		titlePanel = new JPanel();
		titlePanel.setLayout(new FlowLayout());
		recipeLabel = new JLabel("Recipe name:");
		recipeName = new JTextField();
		recipeName.setColumns(30);
		titlePanel.add(recipeLabel);
		titlePanel.add(recipeName);
		return titlePanel;
	}

	private JPanel createButtonPanel() {
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout());
		addRecipe = new JButton("Add recipe");
		addRecipe.addActionListener(new AddRecipeListener());
		exitButton = new JButton("Exit");
		exitButton.addActionListener(new ExitListener());
		viewRecipes = new JButton("View recipes");
		viewRecipes.addActionListener(new ViewRecipesListener());
		
		buttonPanel.add(addRecipe);
		buttonPanel.add(exitButton);
		buttonPanel.add(viewRecipes);
		  
		return buttonPanel;
	}
	
	private JPanel createQuantityLabel()
	{
		quantityTitle = new JPanel();
		quantityLabel = new JLabel("Quantities:");
		quantityTitle.add(quantityLabel);
		return quantityTitle;
	}
	
	private JPanel createIngredientsLabel()
	{
		ingredientsTitle = new JPanel();
		ingredientsLabel = new JLabel("Ingredients:");
		ingredientsTitle.add(ingredientsLabel);
		return ingredientsTitle;
	}
	
	private JPanel createInstructionsLabel()
	{
		instructionsTitle = new JPanel();
		instructionsLabel = new JLabel("Instructions:");
		instructionsTitle.add(instructionsLabel);
		return instructionsTitle;
	}

	private JPanel createQuantityPanel() {
		quantityPanel  = new JPanel();
		quantityPanel.setBounds(20, 20, 100, 200);
		txtQuant = new JTextArea("", 30, 7);
		txtQuant.setLineWrap(true);
		txtQuant.setWrapStyleWord(true);
		quantityPanel.add(txtQuant);
		return quantityPanel;
	}
	
	private JPanel createIngredientsPanel() {
		ingredientsPanel = new JPanel();
		ingredientsPanel.setBounds(20, 20, 100, 200);
		txtIngr = new JTextArea("", 30, 30);
		txtIngr.setLineWrap(true);
		txtIngr.setWrapStyleWord(true);
		ingredientsPanel.add(txtIngr);
		return ingredientsPanel;
	}
	
	private JPanel createInstructionsPanel() {
		instructionsPanel = new JPanel();
		instructionsPanel.setBounds(20, 20, 100, 200);
		txtInstr = new JTextArea("", 30, 30);
		txtInstr.setLineWrap(true);
		txtInstr.setWrapStyleWord(true);
		instructionsPanel.add(txtInstr);
		return instructionsPanel;
	}

	private class AddRecipeListener implements ActionListener
	{
		//adds recipe to memory
		@Override
		public void actionPerformed(ActionEvent arg0) {			
			Recipe r = new Recipe();
			r.setName(recipeName.getText());
			r.setInstructions(txtInstr.getText());
			
			//creates array list of both quantities and ingredient names, splitting on the carriage return
			ArrayList<String> parts = new ArrayList<>(Arrays.asList(txtIngr.getText().split("\n")));
			ArrayList<String> quants = new ArrayList<>(Arrays.asList(txtQuant.getText().split("\n")));
			
			ArrayList<Item> recipeIngr = new ArrayList<Item>();
			
			//loops through both arrays to assign each part to the object Ingredient
			for (int i = 0; i < parts.size(); i++)
			{
				Ingredient ingr = new Ingredient();
				ingr.setName(parts.get(i));
				ingr.setUnitSize(quants.get(i));
				recipeIngr.add(ingr);
			}
			r.setIngredients(recipeIngr);
			recipes.add(r);
			
			recipeName.setText("");
			txtQuant.setText("");
			txtIngr.setText("");
			txtInstr.setText("");
		}
	}
	
	private class ViewRecipesListener implements ActionListener
	{
		//opens the RecipeDisplay swing form to view recipes
		@Override
		public void actionPerformed(ActionEvent arg0) {
			RecipeDisplay display = new RecipeDisplay(recipes);
		}
	}
	
	private class ExitListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
			
		}
	}
}
