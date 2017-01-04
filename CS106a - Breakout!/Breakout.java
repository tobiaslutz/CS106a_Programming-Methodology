/*
 * File: Breakout.java
 * -------------------
 * Name:
 * Section Leader:
 * 
 * This file will eventually implement the game of Breakout.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class Breakout extends GraphicsProgram {

/** Width and height of application window in pixels */
	public static final int APPLICATION_WIDTH = 400;
	public static final int APPLICATION_HEIGHT = 600;

/** Dimensions of game board (usually the same) */
	private static final int WIDTH = APPLICATION_WIDTH;
	private static final int HEIGHT = APPLICATION_HEIGHT;

/** Dimensions of the paddle */
	private static final int PADDLE_WIDTH = 60;
	private static final int PADDLE_HEIGHT = 10;

/** Offset of the paddle up from the bottom */
	private static final int PADDLE_Y_OFFSET = 30;

/** Number of bricks per row */
	private static final int NBRICKS_PER_ROW = 10;

/** Number of rows of bricks */
	private static final int NBRICK_ROWS = 10;

/** Separation between bricks */
	private static final int BRICK_SEP = 4;

/** Width of a brick */
	private static final int BRICK_WIDTH =
	  (WIDTH - (NBRICKS_PER_ROW + 1) * BRICK_SEP) / NBRICKS_PER_ROW;

/** Height of a brick */
	private static final int BRICK_HEIGHT = 8;

/** Radius of the ball in pixels */
	private static final int BALL_RADIUS = 10;

/** Offset of the top brick row from the top */
	private static final int BRICK_Y_OFFSET = 70;

/** Number of turns */
	private static final int NTURNS = 3;
	
/** Animation cycle delay */
	   private static final int DELAY = 10;
	   
/** Animation cycle delay */
	   private static final int DELAY_BEFORE_NEW_BALL = 2000;


/** Runs the Breakout program. */
	public void run() {

		setSize(new Dimension(WIDTH, HEIGHT));
		
		setupBricks();
		setupPaddle();
		
		trialNrLeft = NTURNS;
		brickNrLeft = NBRICKS_PER_ROW * NBRICK_ROWS;
		newBall = true;
		
		while (trialNrLeft > 0) {
			
			if (newBall) 
				setupBall();
			
			moveBall();
			collider = getCollidingObject();
			handleCollidingObject(collider);
	        pause(DELAY);
	   }
		
		if (brickNrLeft > 0) {
			showMessage(brickNrLeft, trialNrLeft);
		}
	}
	
/**
 * Function: setupBricks()
 * Sets up a wall of bricks made up of 10 layers with NBRICKS_PER_ROW.
 */ 
	private void setupBricks()	{
		int xCoordinate;
		int yCoordinate;
		
	/** 1st and 2nd brick rows of initial setup */
		for (int i = 0; i < 2; i += 1)	{
			
			for (int j = 0; j < NBRICKS_PER_ROW; j += 1)	{
				
				xCoordinate = BRICK_SEP + j * (BRICK_WIDTH + BRICK_SEP);
				yCoordinate = BRICK_Y_OFFSET + i * (BRICK_HEIGHT + BRICK_SEP);
				
				brick = new GRect(BRICK_WIDTH, BRICK_HEIGHT);
				brick.setFilled(true);
				brick.setFillColor(Color.RED);
				brick.setColor(Color.RED);
				add(brick, xCoordinate, yCoordinate);
				
			}
		}
		
		/** 3rd and 4th brick rows of initial setup */
		for (int i = 0; i < 2; i += 1)	{
			
			for (int j = 0; j < NBRICKS_PER_ROW; j += 1)	{
				
				xCoordinate = BRICK_SEP + j * (BRICK_WIDTH + BRICK_SEP);
				yCoordinate = BRICK_Y_OFFSET + 2 * (BRICK_HEIGHT + BRICK_SEP) + i * (BRICK_HEIGHT + BRICK_SEP);
				
				brick = new GRect(BRICK_WIDTH, BRICK_HEIGHT);
				brick.setFilled(true);
				brick.setFillColor(Color.ORANGE);
				brick.setColor(Color.ORANGE);
				add(brick, xCoordinate, yCoordinate);
				
			}
		}
		
	/** 5th and 6th brick rows of initial setup */
		for (int i = 0; i < 2; i += 1)	{
			
			for (int j = 0; j < NBRICKS_PER_ROW; j += 1)	{
				
				xCoordinate = BRICK_SEP + j * (BRICK_WIDTH + BRICK_SEP);
				yCoordinate = BRICK_Y_OFFSET + 4 * (BRICK_HEIGHT + BRICK_SEP) + i * (BRICK_HEIGHT + BRICK_SEP);
				
				brick = new GRect(BRICK_WIDTH, BRICK_HEIGHT);
				brick.setFilled(true);
				brick.setFillColor(Color.YELLOW);
				brick.setColor(Color.YELLOW);
				add(brick, xCoordinate, yCoordinate);
				
			}
		}
		
	/** 7th and 8th brick rows of initial setup */
		for (int i = 0; i < 2; i += 1)	{
			
			for (int j = 0; j < NBRICKS_PER_ROW; j += 1)	{
				
				xCoordinate = BRICK_SEP + j * (BRICK_WIDTH + BRICK_SEP);
				yCoordinate = BRICK_Y_OFFSET + 6 * (BRICK_HEIGHT + BRICK_SEP) + i * (BRICK_HEIGHT + BRICK_SEP);
				
				brick = new GRect(BRICK_WIDTH, BRICK_HEIGHT);
				brick.setFilled(true);
				brick.setFillColor(Color.GREEN);
				brick.setColor(Color.GREEN);
				add(brick, xCoordinate, yCoordinate);
				
			}
		}
		
	/** 9th and 10th brick rows of initial setup */
		for (int i = 0; i < 2; i += 1)	{
			
			for (int j = 0; j < NBRICKS_PER_ROW; j += 1)	{
				
				xCoordinate = BRICK_SEP + j * (BRICK_WIDTH + BRICK_SEP);
				yCoordinate = BRICK_Y_OFFSET + 8 * (BRICK_HEIGHT + BRICK_SEP) + i * (BRICK_HEIGHT + BRICK_SEP);
				
				brick = new GRect(BRICK_WIDTH, BRICK_HEIGHT);
				brick.setFilled(true);
				brick.setFillColor(Color.CYAN);
				brick.setColor(Color.CYAN);
				add(brick, xCoordinate, yCoordinate);
				
			}
		}
	}
	
/**
 * Function: setupPaddle()
 * Constructs the paddle and places it in the middle of the screen at the specified offset from the bottom
 */ 	
	private void setupPaddle()	{
		paddle = new GRect(PADDLE_WIDTH, PADDLE_HEIGHT);
		paddle.setFilled(true);
		add(paddle,(WIDTH - PADDLE_WIDTH) / 2, HEIGHT - (PADDLE_Y_OFFSET + PADDLE_HEIGHT));
		
	/**	Midpoint of the paddle which serves as handle to navigate the paddle. In each iteration of the 
	 * 	animation, handle is used as a fix point to update the position of the paddle. 
	 */	
		handle = new GPoint((WIDTH - PADDLE_WIDTH) / 2 + PADDLE_WIDTH / 2, HEIGHT - (PADDLE_Y_OFFSET + PADDLE_HEIGHT));
		
		addMouseListeners();
	}
	
/**
 * Function: setupBall()
 * Constructs the ball and specifies the velocity of the ball. Shows initially a message, which tells 
 * the player how many trials he or she has left.
 */ 
	private void setupBall()	{
		
	/** Message indicating how many trials are left */
		showMessage(brickNrLeft, trialNrLeft);
		
	/** Create ball */
		ball = new GOval(2 * BALL_RADIUS, 2 * BALL_RADIUS);
		ball.setFilled(true);
		add(ball,(WIDTH - 2 * BALL_RADIUS) / 2, (HEIGHT - 2 * BALL_RADIUS) / 2);
		
	/** Specify velocity of the ball in x and y direction. Velocity in x direction is random. */
		vy = 3.0;
		vx = rgen.nextDouble(1.0, 3.0);
		if (rgen.nextBoolean(0.5)) 
			vx = -vx;
		
		newBall = false;
	}

/**
* 	Function: moveBall()
* 	Offsets the current position of the ball by vx pixels into the x-direction 
* 	and by vy pixels into the y-direction. If the ball leaves the game board,
* 	either vx or vy is multiplied by -1 (depending on the situation), which causes
* 	a reflection. In case the ball leaves the bottom edge, it is removed. 
*/ 
	private void moveBall()	{
		
		ball.move(vx,vy);
		
	/**	Ball has left upper edge of the game board*/
        if (ball.getY() < 0)
       	 vy = -vy;
    /**	Ball went below paddle */
        else if (ball.getY() + 2 * BALL_RADIUS > HEIGHT - PADDLE_Y_OFFSET)	{
        	
        /**	Move ball gracefully to the bottom edge of the game board*/
        	while(ball.getY() + 2 * BALL_RADIUS < HEIGHT)	{
        		ball.move(vx,vy);
        		pause(DELAY);
        	}
        	
       		remove(ball);
       		--trialNrLeft;
       		newBall = true;
       	}
    /**	Ball has left either the left or right vertical wall of the game board */
        else if (ball.getX() > WIDTH - 2 * BALL_RADIUS || ball.getX() < 0)
        	vx = -vx;
	}
	
/**
 * Function: getCollidingObject()
 * Checks whether one of the four corner points of the ball's boundary box has collided with an object.
 * If a collision has taken place, the object is returned. Otherwise null is returned.
 */
	private GObject getCollidingObject()	{
		
		GObject collObj = getElementAt(ball.getX(), ball.getY());
		if (collObj != null) return collObj;
		
		collObj = getElementAt(ball.getX() + 2 * BALL_RADIUS, ball.getY());
		if (collObj != null) return collObj;
		
		collObj = getElementAt(ball.getX(), ball.getY() + 2 * BALL_RADIUS);
		if (collObj != null) return collObj;
		
		collObj = getElementAt(ball.getX() + 2 * BALL_RADIUS, ball.getY() + 2 * BALL_RADIUS);
		if (collObj != null) return collObj; 
		else return null;
		
	}
	
/**
 * Function: handleCollidingObject(GObject coll)
 * Input variable: GObject coll is either null (no collision has taken place), the paddle or a brick
 * handlingCollidingObject(coll) causes a reflection of the ball at the colliding object coll (if coll 
 * is not null). Moreover, coll is removed if it is a brick.
 */
	private void handleCollidingObject(GObject coll)	{
		if (collider == paddle)	{
			bounceClip.play();
			vy = -vy;
			ball.move(vx, vy);
		}
		else if (collider != null)	{
			bounceClip.play();
			remove(collider);
			vy = -vy;
			--brickNrLeft;
			if (brickNrLeft == 0) {
				showMessage(brickNrLeft, trialNrLeft);
				return;
			}
		}
	}
	
/**
 * Function: showMessage(int brickNrLeft, int trialNrLeft)
 * Input variables: int brickNrLeft, int trialNrLeft
 * showMessage(brickNrLeft, trialNrLeft) tells the user about the current status of the game 
 * ("Nr. foo out of 3 trials", "Game over", "Congratulations! You broke out")
 */
	private void showMessage(int brickNrLeft, int trialNrLeft)	{
		
		if (trialNrLeft == 0 && brickNrLeft > 0)	{
			
			GLabel message = new GLabel("Game over");
			message.setFont("Helvetica-18");
			message.move((WIDTH - message.getWidth()) / 2, (HEIGHT - message.getHeight()) / 2);
			add(message);
		}
		else if (trialNrLeft > 0 && brickNrLeft == 0)	{
			GLabel message = new GLabel("Congratulations! You broke out");
			message.setFont("Helvetica-18");
			message.move((WIDTH - message.getWidth()) / 2, (HEIGHT - message.getHeight()) / 2);
			add(message);
			remove(ball);
			trialNrLeft = 0;
		}
		else if (trialNrLeft > 0 && brickNrLeft > 0)	{
			GLabel message = new GLabel("Nr. " + (4- trialNrLeft) + " out of 3 trials");
			message.setFont("Helvetica-18");
			message.move((WIDTH - message.getWidth()) / 2, (HEIGHT - message.getHeight()) / 2);
			add(message);
			pause(DELAY_BEFORE_NEW_BALL);
			remove(message);
		}
	}
	
/**
 * Function: mouseMoved(MouseEvent e)
 * Moves the midpoint of the paddle to the current mouse position
 */
	public void mouseMoved(MouseEvent e) {
		
		if (e.getX() <= WIDTH - PADDLE_WIDTH / 2 && e.getX() >= PADDLE_WIDTH / 2)	{
			
			paddle.move(e.getX() - handle.getX(), 0); 
			
			handle = new GPoint(e.getPoint());
			
		}
	}
	
	/* private instance variables */
	private GRect brick, paddle;
	
/** Midpoint of the paddle, which is used as a fix point to update the position of the paddle according
    to the mouse movement */
	private GPoint handle;
	
	private GOval ball;
	
/** collidier holds the object the ball has collided with in the current animation cycle */	
	private GObject collider;
	
/** vx and vy indicate the relative distance, which is added to the current position of the ball to
 	animate motion */
	private double vx, vy;
	
	private RandomGenerator rgen = RandomGenerator.getInstance();
	
/** trialNrLeft indicates how many trials are left for the player */
	int trialNrLeft;
	
/** brickNrLeft indicates how many bricks are left in the wall	*/
	int brickNrLeft;
	
/** Before each trial, newBall is set to true in order to indicate that a new ball must be created */
	boolean newBall;	
	
	AudioClip bounceClip = MediaTools.loadAudioClip("bounce.au");
}
