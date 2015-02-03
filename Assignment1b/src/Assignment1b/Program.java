package Assignment1b;

import java.util.Scanner;

public class Program {
	
	//This program creates two dimensional arrays
	//to store book titles and authors.
	// 1/11/15 Kayla Fenske
	
	public static void main(String[] args) {
		Program p = new Program();
		p.bookArray();
	}
	
	private void bookArray() {
		String[][] books = new String[10][2];
		books[0][0]="Angels and Demons";
		books[0][1]="Dan Brown";
		books[1][0]="The Da Vinci Code";
		books[1][1]="Dan Brown";
		books[2][0]="Inferno";
		books[2][1]="Dan Brown";
		books[3][0]="Girl with a Pearl Earring";
		books[3][1]="Tracy Chevalier";
		books[4][0]="Jack Reacher: One Shot";
		books[4][1]="Lee Child";
		books[5][0]="Jack Reacher: Killing Floor";
		books[5][1]="Lee Child";
		books[6][0]="Fifty Shades of Grey";
		books[6][1]="E.L. James";
		books[7][0]="Gone Girl";
		books[7][1]="Gillian Flynn";
		books[8][0]="Wild";
		books[8][1]="Cheryl Strayed";
		books[9][0]="The Fault in our Stars";
		books[9][1]="John Green";
		
		System.out.println("Enter an author's name:");
		Scanner scan = new Scanner(System.in);
		String author = scan.nextLine();
		
		for (int i = 0; i < books.length; i++)
		{
			if (books[i][1].equals(author)) {
				System.out.println(books[i][0]);
			}
		}
	}
}
