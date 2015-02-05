package com.kfenske.Assignment5;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class FileForm {
	
	private JFrame frame;
	private JPanel borderPanel;
	private JPanel promptPanel;
	private JLabel prompt;
	private JTextField filePath;
	private JLabel writeText;
	private JScrollPane scrollPane;
	private JTextArea output;
	private JPanel buttonPanel;
	private JButton writeButton;
	private JButton readButton;
	private JButton exitButton;
	
	public FileForm()
	{
		createFrame();
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
		borderPanel.add(createPromptPanel(), BorderLayout.NORTH);
		borderPanel.add(createScrollPane(), BorderLayout.CENTER);
		borderPanel.add(createButtonPanel(), BorderLayout.SOUTH);
		return borderPanel;
	}
	
	private JPanel createPromptPanel()
	{
		promptPanel = new JPanel();
		promptPanel.setLayout(new GridLayout(2,2));
		prompt = new JLabel("Choose your file path:");
		filePath = new JTextField("C:\\temp");
		writeText = new JLabel("Write your content here:");
		
		promptPanel.add(prompt);
		promptPanel.add(filePath);
		promptPanel.add(writeText);
		return promptPanel;
	}
	
	private JScrollPane createScrollPane()
	{
		output = new JTextArea();
		scrollPane = new JScrollPane(output);
		scrollPane.setBounds(20,20,100,200);
		return scrollPane;
	}
	
	private JPanel createButtonPanel()
	{
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout());
		writeButton = new JButton("Write File");
		writeButton.addActionListener(new WriteButtonListener());
		readButton = new JButton("Read File");
		readButton.addActionListener(new ReadButtonListener());
		exitButton = new JButton("Exit");
		exitButton.addActionListener(new ExitButtonListener());
		
		buttonPanel.add(writeButton);
		buttonPanel.add(readButton);
		buttonPanel.add(exitButton);
		
		return buttonPanel;
	}
	
	private class WriteButtonListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			try {
				WriteFile wf = new WriteFile(filePath.getText());
				wf.addText(output.getText());
				wf.closeFile();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	private class ReadButtonListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) {
			ReadFile rf = new ReadFile(filePath.getText());

			try {
				output.setText(rf.getText());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	private class ExitButtonListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) {
			System.exit(0);			
		}
	}
}
