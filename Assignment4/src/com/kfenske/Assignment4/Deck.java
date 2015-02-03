package com.kfenske.Assignment4;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {

	private ArrayList<Card> cards;
	
	public Deck()
	{
		cards = new ArrayList<Card>();
	}
	
	public ArrayList<Card> populateDeck()
	{
		
		for(int i = 0; i < 4; i++)
		{
			String suit;
			String number = null;
			if(i == 0)
			{
				suit = "spades";
			}
			else if(i == 1)
			{
				suit = "hearts";
			}
			else if(i == 2)
			{
				suit = "diamonds";
			}
			else
			{
				suit = "clubs";
			}
			for(int x = 1; x <= 13; x++)
			{
				
				switch(x){
				case 1: number = "Ace";
				break;
				case 2: number = "Two";
				break;
				case 3: number = "Three";
				break;
				case 4: number = "Four";
				break;
				case 5: number = "Five";
				break;
				case 6: number = "Six"; 
				break;
				case 7: number = "Seven";
				break;
				case 8: number = "Eight";
				break;
				case 9: number = "Nine";
				break;
				case 10: number = "Ten";
				break;
				case 11: number = "Jack";
				break;
				case 12: number = "Queen";
				break;
				case 13: number = "King";
				break;
				}
				
				Card c = new Card();
				c.setSuit(suit);
				c.setNumber(number);
				cards.add(c);
			}
		}
		//once number and suit arrays are created, added to "cards" list
		//and passes the ArrayList into the deal method
		return deal(cards);
	}
	
	private ArrayList<Card> deal(ArrayList<Card> list)
	{
		Collections.shuffle(list);
		
		ArrayList<Card> hand = new ArrayList<Card>();
		
		//only 5 cards returned to create hand
		for (int i = 0; i < 5; i++)
		{
			hand.add(cards.get(i));
		}
		
		return hand;
	}
}
