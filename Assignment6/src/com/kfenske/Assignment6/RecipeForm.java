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
	private JTextArea txtInstr;
	private JPanel buttonPanel;
	private JButton addRecipe;
	private JButton exitButton;
	private Recipes meal;
	
	public RecipeForm()
	{
		createFrame();
		meal = new Recipes();
	}
	
	public void createFrame()
	{
		frame = new JFrame();
		frame.setBounds(100, 100, 700, 400);
		frame.add(createBorderPanel());
		frame.setVisible(true);
	}

	private JPanel createBorderPanel() {
		borderPanel = new JPanel();
		borderPanel.setLayout(new BorderLayout());
		borderPanel.add(createRecipeTitle(), BorderLayout.NORTH);
		borderPanel.add(createIngredientsPanel(), BorderLayout.WEST);
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
		
		buttonPanel.add(addRecipe);
		buttonPanel.add(exitButton);
		  
		return buttonPanel;
	}

	private JPanel createInstructionsPanel() {
		instructionsPanel = new JPanel();
		instructionsPanel.setBounds(20, 20, 100, 200);
		txtInstr = new JTextArea("Instructions: \n", 30, 30);
		instructionsPanel.add(txtInstr);
		return instructionsPanel;
	}

	private JPanel createIngredientsPanel() {
		ingredientsPanel = new JPanel();
		ingredientsPanel.setBounds(20, 20, 100, 200);
		txtIngr = new JTextArea("Ingredients: \n", 30, 30);
		ingredientsPanel.add(txtIngr);
		return ingredientsPanel;
	}

	private class AddRecipeListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent arg0) {			
			Recipe r = new Recipe();
			r.setName(recipeName.getText());
			ArrayList<Item> parts = new ArrayList<Item>(Arrays.<Item>asList(new Recipe(), (txtIngr.getText().split("\n"))));
			r.setInstructions(txtInstr.getText());
			
			recipeName.setText("");
			txtIngr.setText("");
			txtInstr.setText("");
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
