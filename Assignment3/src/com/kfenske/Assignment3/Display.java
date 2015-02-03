package com.kfenske.Assignment3;

import java.util.Scanner;

public class Display {
	
	private Calculate c;
	Scanner scan;
	
	public Display() 
	{
		c = new Calculate();
		scan = new Scanner(System.in);
		enterData();
		display();
	}

	private void enterData()
	{
		System.out.println("Enter the total number of bats: ");
		Scanner scan = new Scanner(System.in);
		c.setBats(scan.nextDouble());
		System.out.println("Enter the total number of hits: ");
		c.setHits(scan.nextDouble());
		System.out.println("Enter the total number of outs: ");
		c.setOuts(scan.nextDouble());
		System.out.println("Enter the total number of walks: ");
		c.setWalks(scan.nextDouble());
	}
	
	private void display()
	{
		System.out.println("The batting average is " + c.calculateBatAvg() + " and the on base average is " + c.calculateOnBaseAvg());
	}
}
