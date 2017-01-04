/*
 * File: Hailstone.java
 * Name: 
 * Section Leader: 
 * --------------------
 * This file is the starter file for the Hailstone problem.
 */

import acm.program.*;

public class Hailstone extends ConsoleProgram {
	
	public void run() {
		
		int n = readInt("Enter a positive Integer: ");
		int k = 0;
		
		while ((n != 1) || (k == 0))	{
			
			if (n < 1)	{
				println("Integer is not positive.");
				break;
			}
			
			k += 1;
			
			if (n % 2 == 0)	{		
				n = n / 2;
				println("Step " + k + ": " + 2 * n + " is even, so take half: " + n);
			}
			else	{		
				n = 3 * n + 1;
				println("Step " + k + ": " + (n - 1) / 3 + " is odd, so make 3n + 1: " + n);
			}	
		}
	}
}

