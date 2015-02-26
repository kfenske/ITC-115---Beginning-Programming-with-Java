package com.kfenske.Assignment6;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class RecipeDisplay {

	private JFrame frame;
	private JPanel panel;
	private JPanel borderPanel;
	private JTextArea ingredients;
	private JTextArea instructions;
	private JButton populate;
	private JList recipeList;
	private JScrollPane scrollPane;
	private JLabel ingredientsLabel;
	private JPanel ingredientsTitle;
	private JLabel recipeLabel;
	private JPanel recipeTitle;
	private JLabel instructionsLabel;
	private JPanel instructionsTitle;
	private JPanel ingredientsPanel;
	private JPanel instructionsPanel;
	private JPanel buttonPanel;
	private JButton exitButton;
	
	private Recipes menu;
	
	public RecipeDisplay(Recipes r)
	{
		menu = r;
		createFrame();
	}
	
	private void createFrame()
	{
		frame = new JFrame();
		frame.setBounds(100, 100, 1000, 700);
		frame.add(createBorderPanel());
		frame.setVisible(true);
	}
	
	private JPanel createBorderPanel()
	{
		borderPanel = new JPanel();
		JSplitPane vertical1 = new JSplitPane(JSplitPane.VERTICAL_SPLIT, createRecipeName(), createScrollPane());
		JSplitPane vertical2 = new JSplitPane(JSplitPane.VERTICAL_SPLIT, createIngredientsTitle(), createIngredientsPanel());
		JSplitPane vertical3 = new JSplitPane(JSplitPane.VERTICAL_SPLIT, createInstructionsTitle(), createInstructionsPanel());
		JSplitPane horizontal1 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, vertical2, vertical3);
		JSplitPane horizontal2 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, vertical1, horizontal1);
		JSplitPane vertical4 = new JSplitPane(JSplitPane.VERTICAL_SPLIT, horizontal2, createButtonPanel());
		borderPanel.add(vertical4);
		return borderPanel;
	}
	
	private JPanel createRecipeName()
	{
		recipeTitle = new JPanel();
		recipeLabel = new JLabel("Recipes:");
		recipeTitle.add(recipeLabel);
		return recipeTitle;
	}
	
	private JPanel createIngredientsTitle()
	{
		ingredientsTitle = new JPanel();
		ingredientsLabel = new JLabel("Ingredients:");
		ingredientsTitle.add(ingredientsLabel);
		return ingredientsTitle;
	}
	
	private JPanel createInstructionsTitle()
	{
		instructionsTitle = new JPanel();
		instructionsLabel = new JLabel("Instructions:");
		instructionsTitle.add(instructionsLabel);
		return instructionsTitle;
	}
		
	private JScrollPane createScrollPane()
	{
		recipeList = new JList<String>();
		recipeList.addListSelectionListener(new SelectionListener());
		scrollPane = new JScrollPane(recipeList);
		ArrayList<String> cookbook = new ArrayList<String>();
		return scrollPane;
	}
	
	private JPanel createIngredientsPanel()
	{
		ingredientsPanel = new JPanel();
		ingredients = new JTextArea("", 30, 20);
		ingredients.setEditable(false);
		ingredientsPanel.add(ingredients);
		return ingredientsPanel;
	}
	
	private JPanel createInstructionsPanel()
	{
		instructionsPanel = new JPanel();
		instructions = new JTextArea("", 30, 30);
		instructions.setEditable(false);
		instructionsPanel.add(instructions);
		return instructionsPanel;
	}
	
	private JPanel createButtonPanel()
	{
		panel = new JPanel();
		populate = new JButton("Get Recipes");
		populate.addActionListener(new ButtonListener());
		exitButton = new JButton("Exit");
		exitButton.addActionListener(new ExitListener());
		panel.add(populate);
		return panel;
	}
	
	private class ButtonListener implements ActionListener
	{
		//populates the recipe list with all recipes in memory
		@Override
		public void actionPerformed(ActionEvent arg0) {
			ArrayList<Recipe> recList = menu.getRecipes();
			DefaultListModel<String> model = new DefaultListModel<String>();
			for (Recipe r : recList)
			{
				model.addElement(r.getName());
			}
			recipeList.setModel(model);
		}
	}
	
	private class SelectionListener implements ListSelectionListener
	{
		//action that occurs when selecting a recipe from the list
		
		@Override
		public void valueChanged(ListSelectionEvent arg0) {
			//empties out the ingredients text area each time a new recipe is selected
			ingredients.setText(null);
			
			//gets the recipe for the selected recipe name
			ArrayList<Recipe> recList = menu.getRecipes();
			for (Recipe r: recList)
			{
				String name = (String)recipeList.getSelectedValue();
				if(r.getName().equals(name))
				{
					ArrayList<Item> selectedRecipe = r.getIngredients();
					
					//loops through the array list of ingredient items and attaches the unit size
					//to the ingredient name, then appends to the text area
					for (int i = 0; i < selectedRecipe.size(); i++)
					{
						Ingredient ing = (Ingredient)selectedRecipe.get(i);
						String str = ing.getUnitSize() + " " + ing.getName() + "\n";	
						ingredients.append(str);
					}
					ingredients.setLineWrap(true);
					ingredients.setWrapStyleWord(true);
					instructions.setText(r.getInstructions());
					instructions.setLineWrap(true);
					instructions.setWrapStyleWord(true);
				}
			}
		}
	}
	
	private class ExitListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			System.exit(0);
		}
	}
}
