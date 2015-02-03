package Assignment2;

import java.util.Random;
import java.util.Scanner;

public class Program {
	
	final int MAXRANDOM = 500;

	public static void main(String[] args) {
		Program p = new Program();
		p.run();
	}

	private void run()
	{
		System.out.println("Welcome to the Guessing Game!");
		int[] games = new int[99];
		int counter = 0;
		boolean keepPlaying = true;
		
		while (keepPlaying)
		{
			int answer = generateAnswer();
			int score = playGame(answer);
			games[counter] = score;
			
			System.out.println("Would you like to play again? [yes] or [no]");
			Scanner scan = new Scanner(System.in);
			String response = scan.next();
			if (response.equals("yes"))
			{
				counter++;
			}
			else 
			{
				keepPlaying = false;
			}
		}
		
		double average = averageScore(games, counter);
		
		endProgram(games, counter, average);
	}
	
	private double averageScore(int[] games, int counter)
	{
		double result = 0;
		double total = 0;
		
		for (int i = 0; i <= counter; i++)
		{
			total += games[i];
		}
		
		result = total / (counter + 1);
		
		return result;
	}
	
	private int playGame(int answer)
	{
		int guess;
		int score;
		
		for (score = 10; score > 0; score--)
		{
			if (score > 1)
			{
				System.out.println("Guess a number between 1 and 500! You have " + score + " guesses left, choose wisely!");
			}
			else
			{
				System.out.println("Guess a number between 1 and 500! You have " + score + " guess left, choose wisely!");
			}
			
			Scanner scan = new Scanner(System.in);
			String response = scan.next();
			guess = Integer.parseInt(response);
			while (guess > 500 | guess < 1)
			{
				System.out.println("I'm sorry, that is not a valid guess. Please choose a number between 1 and 500:");
				scan = new Scanner(System.in);
				response = scan.next();
				guess = Integer.parseInt(response);
			}
			
			if (testGuess(guess, answer))
			{
				break;
			}
		}
		
		System.out.println("Your score is " + score + " points!");
		
		return score;
	}
	
	private boolean testGuess(int guess, int answer)
	{
		if (guess < answer)
		{
			System.out.println("I'm sorry, that is not correct. Your guess was too low.");
			return false;
		}
		else if (guess > answer)
		{
			System.out.println("I'm sorry, that is not correct. Your guess was too high.");
			return false;
		}
		else
		{
			System.out.println("Congratulations! You guessed " + guess + " and the answer was " + answer + "!!");
			return true;
		}
	}
	
	private void endProgram(int[] games, int counter, double average)
	{
		System.out.println("Here are your scores:");
		for (int i = 0; i <= counter; i++)
		{
			System.out.println("Game " + (Integer.toString(i + 1)) + " : " + games[i]);
		}
		
		System.out.println("Here is your average score: " + Double.toString(average));
		System.out.println("Thank you for playing!");
	}
	
	private int generateAnswer()
	{
		Random rand = new Random();
		int answer = rand.nextInt(MAXRANDOM);
		return answer;
	}
}
