package com.kfenske.Assignment7;

import java.awt.Color;
import java.awt.event.MouseEvent;

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

/*
 * Java Breakout Game
 * Kayla Fenske 3/9/15
 */

public class Breakout extends GraphicsProgram{

	private static final int WINDOWWIDTH = 400;
	private static final int WINDOWHEIGHT = 600;
	private static final int WIDTH = WINDOWWIDTH;
	private static final int HEIGHT = WINDOWHEIGHT;
	private static final int PADDLEWIDTH = 60;
	private static final int PADDLEHEIGHT = 10;
	private static final int PADDLEOFFSET = 30;
	private static final int BRICKSPERROW = 10;
	private static final int BRICKROWS = 10;
	private static final int BRICKSEP = 4;
	private static final int BRICKWIDTH = WIDTH/BRICKSPERROW - BRICKSEP;
	private static final int BRICKHEIGHT = 8;
	private static final int BALLRADIUS = 10;
	private static final int BRICKOFFSET = 60;
	
	private static final Color[] BRICKCOLOR = { Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.BLUE };
	
	private GOval ball;
	private GRect brick;
	private GRect paddle;
	
	private double ballVX, ballVY;
	private int brickCounter = 100;
	private int scoreCounter = 0;
	private RandomGenerator random;
	
	public void run()
	{
		//Create random number to create a random direction for the ball to start moving
		random = RandomGenerator.getInstance();
		
		for (int i = 0; i < 1; i++)
		{
			setSize(WINDOWWIDTH, WINDOWHEIGHT);
			setUpGame();
			playGame();
			if(brickCounter == 0)
			{
				ball.setVisible(false);
				winner();
				break;
			}
		}
		if (brickCounter > 0)
		{
			removeAll();
			gameOver();
		}
	}
	
	private void setUpGame()
	{
		drawBricks(getWidth()/2, BRICKOFFSET);
		drawPaddle();
		drawBall();
	}
	
	private void drawBricks(double x, double y)
	{
		//get rows
		for (int r = 0; r < BRICKROWS; r++)
		{
			//get columns
			for (int c = 0; c < BRICKSPERROW; c++)
			{
				//create separation between bricks
				double brickXStart = ((BRICKSEP + BRICKWIDTH) * c);
				
				//start lower than the top of the app window
				double brickYStart = BRICKOFFSET + (BRICKSEP + BRICKHEIGHT) * r;
				
				brick = new GRect(brickXStart, brickYStart, BRICKWIDTH, BRICKHEIGHT);
				add(brick);
				brick.setFilled(true);
				brick.setColor(colorSelection(r, BRICKCOLOR));
			}
		}
	}
	
	private Color colorSelection(int i, Color[] c)
	{
		//using int will alternate colors every two rows
		return c[i/2];
	}
	
	private void drawPaddle()
	{
		double x = (getWidth() / 2) - (PADDLEWIDTH / 2);
		
		double y = getHeight() - PADDLEOFFSET - PADDLEHEIGHT;
		
		paddle = new GRect(x, y, PADDLEWIDTH, PADDLEHEIGHT);
		paddle.setFilled(true);
		add(paddle);
		addMouseListeners();
	}
	
	public void mouseMoved(MouseEvent e)
	{
		//connect mouse movement to paddle movement
		if ((e.getX() < getWidth() - PADDLEWIDTH / 2) && (e.getX() > PADDLEWIDTH / 2))
		{
			paddle.setLocation(e.getX() - PADDLEWIDTH / 2, getHeight() - PADDLEOFFSET - PADDLEHEIGHT);
		}
	}
	
	private void drawBall()
	{
		double x = getWidth() / 2 - BALLRADIUS;
		double y = getHeight() / 2 - BALLRADIUS;
		ball = new GOval(x, y, BALLRADIUS, BALLRADIUS);
		ball.setFilled(true);
		add(ball);
	}
	
	private void playGame()
	{
		waitForClick();
		getBallVelocity();
		while (true) {
			moveBall();
			if (ball.getY() >= getHeight())
			{
				break;
			}
			if(brickCounter == 0)
			{
				break;
			}
		}
	}
	
	private void getBallVelocity()
	{
		ballVY = 4.0;
		ballVX = random.nextDouble(1.0, 3.0);
		if (random.nextBoolean(0.5))
		{
			ballVX = -ballVY;
		}
	}
	
	private void moveBall()
	{
		//use coordinates to determine direction of the ball
		ball.move(ballVX, ballVY);
		
		if ((ball.getX() - ballVX <= 0 && ballVX < 0) || (ball.getX() + ballVX >= (getWidth() - BALLRADIUS * 2) && ballVX > 0))
		{
			ballVX = -ballVX;
		}
		if (ball.getY() - ballVY <= 0 && ballVY < 0)
		{
			ballVY = -ballVY;
		}
		
		GObject collider = getCollidingObject();
		
		//differentiate collision with paddle from collision with brick
		if (collider == paddle)
		{
				ballVY = -ballVY;
		}
		
		//each time ball collides with brick, remove brick and increase score counter, then change ball direction
		else if (collider != null)
		{
			remove(collider);
			brickCounter--;
			scoreCounter++;
			ballVY = -ballVY;
		}
		
		pause (10);
	}
	
	private GObject getCollidingObject()
	{
		//test for collision of ball from all sides of the ball
		if((getElementAt(ball.getX(), ball.getY())) != null)
		{
			return getElementAt(ball.getX(), ball.getY());
		}
		else if(getElementAt((ball.getX() + BALLRADIUS * 2), ball.getY()) != null)
		{
			return getElementAt(ball.getX() + BALLRADIUS * 2, ball.getY());
		}
		else if(getElementAt(ball.getX(), (ball.getY() + BALLRADIUS * 2)) != null)
		{
			return getElementAt(ball.getX(), ball.getY() + BALLRADIUS * 2);
		}
		else if(getElementAt((ball.getX() + BALLRADIUS * 2), (ball.getY() + BALLRADIUS * 2)) != null)
		{
			return getElementAt(ball.getX() + BALLRADIUS * 2, ball.getY() + BALLRADIUS * 2);
		}
		else
		{
			return null;
		}
	}
	
	private void gameOver()
	{
		GLabel gameOver = new GLabel("Game Over", getWidth() / 2, getHeight() / 2);
		GLabel score = new GLabel("Your score is " + scoreCounter, getWidth() / 2, (getHeight() / 2) + 20);
		GLabel playAgain = new GLabel("Click to play again", getWidth() / 2, (getHeight() / 2) + 40);
		gameOver.setColor(Color.RED);
		add(gameOver);
		add(score);
		add(playAgain);
		
		//prompt click to reset brick and score count and start game over
		waitForClick();
		brickCounter = 100;
		scoreCounter = 0;
		remove(gameOver);
		remove(score);
		remove(playAgain);
		run();
	}
	
	private void winner()
	{
		GLabel winner = new GLabel("You Win!", getWidth() / 2, getHeight() / 2);
		GLabel playAgain = new GLabel("Click to play again", getWidth() / 2, (getHeight() / 2) + 40);
		winner.setColor(Color.RED);
		add(winner);
		add(playAgain);
		
		//prompt click to reset brick and score count and start game over
		waitForClick();
		brickCounter = 100;
		scoreCounter = 0;
		remove(winner);
		remove(playAgain);
		run();
	}
}
