package com.kfenske.Assignment4;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
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
import javax.swing.JTextField;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class CardForm {

	private JFrame frame;
	private JPanel borderPanel;
	private JPanel deckPanel;
	private JPanel buttonPanel;
	private JLabel dealPrompt;
	private JTextField dealText;
	private JScrollPane scrollPane;
	private JList cardList;
	private JButton dealButton;
	private JButton exitButton;
	private Deck hand;
	
	private JTextField numberField;
	private JPanel panel;
	private JLabel pictureLabel;
	private JPanel picturePanel;
	private int number = 1;
	private final String IMG_PATH = "D:\\Documents\\Code\\Assignment4\\images\\";
	private final int IMAGE_WIDTH = 79;
	private final int IMAGE_HEIGHT = 123;
	
	public CardForm()
	{
		createFrame();
		hand = new Deck();
	}
	
	private void createFrame()
	{
		frame = new JFrame();
		frame.setBounds(100, 100, 700, 500);
		frame.add(createBorderPanel());
		frame.setVisible(true);
	}
	
	private JPanel createBorderPanel()
	{
		borderPanel = new JPanel();
		borderPanel.setLayout(new BorderLayout());
		borderPanel.add(createDeckPanel(), BorderLayout.NORTH);
		borderPanel.add(createPicturePanel(), BorderLayout.CENTER);
		borderPanel.add(createButtonPanel(), BorderLayout.SOUTH);
		return borderPanel;
	}
	
	private JPanel createPicturePanel()
	{
		panel = new JPanel();
		panel.setBounds(20, 20, 600, 400);
		picturePanel = new JPanel();
		picturePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		panel.add(picturePanel);
		return panel;
	}
	
	private JPanel createDeckPanel()
	{
		deckPanel = new JPanel();
		deckPanel.setLayout(new GridLayout(2,2));
		dealPrompt = new JLabel("Deal a hand");

		deckPanel.add(dealPrompt);
		return deckPanel;
	}
	
	private JPanel createButtonPanel()
	{
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout());
		dealButton = new JButton("Deal");
		dealButton.addActionListener(new PictureListener());
		exitButton = new JButton("Exit");
		exitButton.addActionListener(new ExitListener());
		buttonPanel.add(dealButton);
		buttonPanel.add(exitButton);
		  
		return buttonPanel;
	}
	
	private class ExitListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			System.exit(0);
		}
	}
	
	private class PictureListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			//When Deal button is clicked, calls the methods in Deck and 
			//displays shuffled array with images below.
			
			int i = 1;
			ArrayList<Card> cards = hand.populateDeck();

			BufferedImage img = null;
			
			try 
			{
				for(Card c: cards)
				{
					img = ImageIO.read(new File(IMG_PATH + c.toString() + ".png"));
					ImageIcon icon = new ImageIcon(img);
					JLabel label = new JLabel(icon);
					picturePanel.add(label, BorderLayout.WEST);
					panel.add(picturePanel, BorderLayout.CENTER);
					frame.revalidate();
					frame.repaint();
					i++;
				}
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}	
}
