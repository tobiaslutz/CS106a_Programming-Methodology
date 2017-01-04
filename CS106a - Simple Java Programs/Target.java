/*
 * File: Target.java
 * Name: 
 * Section Leader: 
 * -----------------
 * This file is the starter file for the Target problem.
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class Target extends GraphicsProgram {	
	
	private static final int Outer_r = 72; 
	private static final int Middle_r = 47;
	private static final int Inner_r = 22;
	
	public void run() {
		
		int cx = getWidth() / 2;
		int cy = getHeight() / 2;
		
		GOval outer = new GOval(cx - Outer_r / 2, cy - Outer_r / 2, Outer_r, Outer_r);
		outer.setFilled(true);
		outer.setFillColor(Color.RED);
		outer.setColor(Color.RED);
		
		
		GOval middle = new GOval(cx - Middle_r / 2, cy - Middle_r / 2, Middle_r, Middle_r);
		middle.setFilled(true);
		middle.setFillColor(Color.WHITE);
		middle.setColor(Color.WHITE);
		
		GOval inner = new GOval(cx - Inner_r / 2, cy - Inner_r / 2, Inner_r, Inner_r);
		inner.setFilled(true);
		inner.setFillColor(Color.RED);
		inner.setColor(Color.RED);
		
		add(outer);
		add(middle);
		add(inner);
	}
}
