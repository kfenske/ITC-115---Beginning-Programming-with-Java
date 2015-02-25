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
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class RecipeForm {
	
	private JFrame frame;
	private JPanel titlePanel;
	private JLabel recipeLabel;
	private JTextField recipeName;
	private JPanel borderPanel;
	private JPanel ingredientsPanel;
	private JTextArea txtIngr;
	private JPanel instructionsPanel;
	private JTextArea txtQuant;
	private JPanel quantityPanel;
	private JTextArea txtInstr;
	private JPanel buttonPanel;
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
		frame.setBounds(100, 100, 800, 400);
		frame.add(createBorderPanel());
		frame.setVisible(true);
	}

	private JPanel createBorderPanel() {
		borderPanel = new JPanel();
		borderPanel.setLayout(new BorderLayout());
		/*borderPanel.add(createRecipeTitle());
		borderPanel.add(createQuantityPanel());
		borderPanel.add(createIngredientsPanel());
		borderPanel.add(createInstructionsPanel());
		borderPanel.add(createButtonPanel());*/
		borderPanel.add(createRecipeTitle(), BorderLayout.NORTH);
		borderPanel.add(createQuantityPanel(), BorderLayout.WEST);
		borderPanel.add(createIngredientsPanel(), BorderLayout.CENTER);
		borderPanel.add(createInstructionsPanel(), BorderLayout.EAST);
		borderPanel.add(createButtonPanel(), BorderLayout.SOUTH);
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

	private JPanel createInstructionsPanel() {
		instructionsPanel = new JPanel();
		instructionsPanel.setBounds(20, 20, 100, 200);
		txtInstr = new JTextArea("Instructions: \n", 30, 30);
		txtInstr.setLineWrap(true);
		txtInstr.setWrapStyleWord(true);
		instructionsPanel.add(txtInstr);
		return instructionsPanel;
	}
	
	private JPanel createQuantityPanel() {
		quantityPanel  = new JPanel();
		quantityPanel.setBounds(20, 20, 100, 200);
		txtQuant = new JTextArea("Quantities: \n", 30, 7);
		txtQuant.setLineWrap(true);
		txtQuant.setWrapStyleWord(true);
		quantityPanel.add(txtQuant);
		return quantityPanel;
	}

	private JPanel createIngredientsPanel() {
		ingredientsPanel = new JPanel();
		ingredientsPanel.setBounds(20, 20, 100, 200);
		txtIngr = new JTextArea("Ingredients: \n", 30, 30);
		txtIngr.setLineWrap(true);
		txtIngr.setWrapStyleWord(true);
		ingredientsPanel.add(txtIngr);
		return ingredientsPanel;
	}

	private class AddRecipeListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent arg0) {			
			Recipe r = new Recipe();
			r.setName(recipeName.getText());
			r.setInstructions(txtInstr.getText());
			
			ArrayList<String> parts = new ArrayList<>(Arrays.asList(txtIngr.getText().substring(12).split("\n")));
			ArrayList<String> quants = new ArrayList<>(Arrays.asList(txtQuant.getText().substring(12).split("\n")));
			
			ArrayList<Item> recipeIngr = new ArrayList<Item>();
			for (int i = 0; i < parts.size(); i++)
			{
				Ingredient ingr = new Ingredient();
				ingr.setName(parts.get(i));
				ingr.setUnitSize(quants.get(i));
				recipeIngr.add(ingr);
			}
			r.setIngredients(recipeIngr);
			
			recipeName.setText("");
			txtQuant.setText("Quantities: \n");
			txtIngr.setText("Ingredients \n");
			txtInstr.setText("Instructions \n");

		}
	}
	
	private class ViewRecipesListener implements ActionListener
	{
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
