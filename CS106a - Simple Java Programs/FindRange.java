/*
 * File: FindRange.java
 * Name: 
 * Section Leader: 
 * --------------------
 * This file is the starter file for the FindRange problem.
 */

import acm.program.*;

public class FindRange extends ConsoleProgram {
	
	private static final int SENTINEL = 0;
	
	public void run() {
		
		println("This program finds the largest and smallest integer!");
		
		int in = readInt("? ");
		int max = in;
		int min = in;
		
		if (in == SENTINEL)	
			println("No value was entered!");
		else	{
			
			while (in != SENTINEL)	{
			
				in = readInt("? ");
			
				if (in != SENTINEL)	{
				
					if (in > max)
						max = in;
					else if (in < min)
						min = in;
				
				}
				else	{
				
					println("Smallest: " + min);
					println("Largest: " + max);
				}	
			}
		}
	}
}

