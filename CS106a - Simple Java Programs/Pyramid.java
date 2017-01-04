/*
 * File: Pyramid.java
 * Name: 
 * Section Leader: 
 * ------------------
 * This file is the starter file for the Pyramid problem.
 * It includes definitions of the constants that match the
 * sample run in the assignment, but you should make sure
 * that changing these values causes the generated display
 * to change accordingly.
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class Pyramid extends GraphicsProgram {

/** Width of each brick in pixels */
	private static final int BRICK_WIDTH = 30;

/** Width of each brick in pixels */
	private static final int BRICK_HEIGHT = 12;

/** Number of bricks in the base of the pyramid */
	private static final int BRICKS_IN_BASE = 14;
	
	public void run() {

		int windowHeight = getHeight();
		int windowWidth = getWidth();
		
	/** Each iteration of the outer for-loop corresponds to a single layer of the pyramid.
	 *  The individual layers are constructed in the inner for-loop */
		for (int i = BRICKS_IN_BASE; i > 0; i -= 1)	{
			for (int j = 0; j < i; j += 1)	{
				
			/** Origin of the first brick in the ith layer */
				int xPosStart = (windowWidth - i * BRICK_WIDTH) / 2;
				int yPosStart = windowHeight - (14-(i-1)) * BRICK_HEIGHT;
				
				add(new GRect(xPosStart + j * BRICK_WIDTH, yPosStart, BRICK_WIDTH, BRICK_HEIGHT));
				
			}
		}
	}
}

