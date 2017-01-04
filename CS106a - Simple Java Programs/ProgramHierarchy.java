/*
 * File: ProgramHierarchy.java
 * Name: 
 * Section Leader: 
 * ---------------------------
 * This file is the starter file for the ProgramHierarchy problem.
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class ProgramHierarchy extends GraphicsProgram {	
	
/** Width and height of a GRect in pixels */	
	private static final int WIDTH = 150;
	private static final int HEIGHT = 50;
	
/** Width and height of application window in pixels */
	public static final int APPLICATION_WIDTH = 600;
	public static final int APPLICATION_HEIGHT = 400;
	
	public void run() {
		
		setSize(new Dimension(APPLICATION_WIDTH, APPLICATION_HEIGHT));
		
	/** The geometry used in the following is explained in the ProgramHierarchyGeom.jpg */
		
		// upper box
		add(new GRect(APPLICATION_WIDTH * 3 / 8, APPLICATION_HEIGHT * 5 / 16, WIDTH, HEIGHT));
		// left box
		add(new GRect(APPLICATION_WIDTH / 12, APPLICATION_HEIGHT * 9 / 16, WIDTH, HEIGHT));
		// middle box
		add(new GRect(APPLICATION_WIDTH * 3 / 8, APPLICATION_HEIGHT * 9 / 16, WIDTH, HEIGHT));
		// right box
		add(new GRect(APPLICATION_WIDTH * 2 / 3, APPLICATION_HEIGHT * 9 / 16, WIDTH, HEIGHT));
		
		// left line
		add(new GLine(APPLICATION_WIDTH / 2, APPLICATION_HEIGHT * 5 / 16 + HEIGHT, APPLICATION_WIDTH * 5 / 24, 
				APPLICATION_HEIGHT * 9 / 16));
		// middle line
		add(new GLine(APPLICATION_WIDTH / 2, APPLICATION_HEIGHT * 5 / 16 + HEIGHT, APPLICATION_WIDTH / 2, 
				APPLICATION_HEIGHT * 9 / 16));
		// right line
		add(new GLine(APPLICATION_WIDTH / 2, APPLICATION_HEIGHT * 5 / 16 + HEIGHT, APPLICATION_WIDTH * 19 / 24, 
				APPLICATION_HEIGHT * 9 / 16));
		
		// label within upper box
		GLabel upper = new GLabel("Program");
		upper.setFont("Helvetica-18");
		upper.move(APPLICATION_WIDTH * 3 / 8 + WIDTH / 2 - upper.getWidth() / 2, APPLICATION_HEIGHT * 5 / 16 + HEIGHT / 2 
				+ upper.getAscent() / 2);
		add(upper);
		
		// label within left box
		GLabel left = new GLabel("GraphicsProgram");
		left.setFont("Helvetica-18");
		left.move(APPLICATION_WIDTH / 12 + WIDTH / 2 - left.getWidth() / 2, APPLICATION_HEIGHT * 9 / 16 + HEIGHT / 2 
				+ left.getAscent() / 2);
		add(left);
		
		// label within middle box
		GLabel middle = new GLabel("ConsoleProgram");
		middle.setFont("Helvetica-18");
		middle.move(APPLICATION_WIDTH * 3 / 8 + WIDTH / 2 - middle.getWidth() / 2, APPLICATION_HEIGHT * 9 / 16 + HEIGHT / 2 
				+ middle.getAscent() / 2);
		add(middle);
		
		// label within right box
		GLabel right = new GLabel("DialogProgram");
		right.setFont("Helvetica-18");
		right.move(APPLICATION_WIDTH * 2 / 3 + WIDTH / 2 - right.getWidth() / 2, APPLICATION_HEIGHT * 9 / 16 + HEIGHT / 2 
				+ right.getAscent() / 2);
		add(right);	
	}
}

