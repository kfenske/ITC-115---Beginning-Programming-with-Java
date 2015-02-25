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
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class RecipeDisplay {

	private JFrame frame;
	private JPanel panel;
	private JPanel borderPanel;
	private JLabel lblName;
	private JLabel lblIngredients;
	private JLabel lblInstructions;
	private JButton populate;
	private JList recipeList;
	private JScrollPane scrollPane;
	
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
		borderPanel.setLayout(new BorderLayout());
		borderPanel.add(createScrollPane(), BorderLayout.WEST);
		borderPanel.add(createPanel(), BorderLayout.EAST);
		return borderPanel;
	}
	
	private JPanel createPanel()
	{
		panel = new JPanel();
		panel.setLayout(new FlowLayout());
		lblName = new JLabel();
		lblIngredients = new JLabel();
		lblInstructions = new JLabel();
		populate = new JButton("Get Recipes");
		populate.addActionListener(new ButtonListener());
		
		panel.add(lblName);
		panel.add(lblIngredients);
		panel.add(lblInstructions);
		panel.add(populate);
		
		return panel;
	}
	
	private JScrollPane createScrollPane()
	{
		recipeList = new JList<String>();
		recipeList.addListSelectionListener(new SelectionListener());
		scrollPane = new JScrollPane(recipeList);
		ArrayList<String> cookbook = new ArrayList<String>();
		return scrollPane;
	}
	
	private class ButtonListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			ArrayList<Recipe> recList = menu.getRecipes();
			DefaultListModel<String> model = new DefaultListModel<String>();
			for (Recipe r : recList)
			{
				model.addElement(r.getName());
				//model.addElement(r.getIngredients());
				model.addElement(r.getInstructions());
			}
			recipeList.setModel(model);
		}
	}
	
	private class SelectionListener implements ListSelectionListener
	{
		@Override
		public void valueChanged(ListSelectionEvent arg0) {
			ArrayList<Recipe> recList = menu.getRecipes();
			for (Recipe r: recList)
			{
				String name = (String)recipeList.getSelectedValue();
				if(r.getName().equals(name))
				{
					lblName.setText(name);
					//lblIngredients.setText(r.getIngredients());
					lblInstructions.setText(r.getInstructions());
				}
			}
		}
	}
}
