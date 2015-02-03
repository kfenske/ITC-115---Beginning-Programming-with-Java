package com.kfenske.Assignment4;

public class Card {
	
	private String suit;
	private String number;
	
	public String getSuit() {
		return suit;
	}
	
	public void setSuit(String suit) {
		this.suit = suit;
	}
	
	public String getNumber() {
		return number;
	}
	
	public void setNumber(String number) {
		this.number = number;
	}
	
	public String toString()
	{
		return getNumber() + getSuit();
	}
}
