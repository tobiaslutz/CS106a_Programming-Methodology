/*
 * File: StoneMasonKarel.java
 * --------------------------
 * The StoneMasonKarel subclass as it appears here does nothing.
 * When you finish writing it, it should solve the "repair the quad"
 * problem from Assignment 1.  In addition to editing the program,
 * you should be sure to edit this comment so that it no longer
 * indicates that the program does nothing.
 */

import stanford.karel.*;

public class StoneMasonKarel extends SuperKarel {

	public void run()	{
		
		while(frontIsClear())	{
			repairColumn();
			goToNextColumn();
		}
		
		// Repair last column of the quad (or very first column in case the Quad consists of only one column)
		repairColumn();
	}
	
	/*
	 * Function: repairColumn()
	 * Repairs the column at Karol's current position, sends him back to the column base and aligns him
	 * towards the next column
	 */
	private void repairColumn()	{
		// Inspect first stone of the column and do repairing if necessary
		turnLeft();
		condPutBeeper();
		
		// Walk up the column and repair damaged locations
		while(frontIsClear())	{
			move();
			condPutBeeper();
		}
		
		// Walk back to column base
		turnAround();
		while(frontIsClear())	{
			move();
		}
		turnLeft();
	}
	
	/*
	 * Function: goToNextColumn()
	 * Lets Karol walk four steps towards the direction he currently faces
	 */
	private void goToNextColumn()	{
		move();
		move();
		move();
		move();
	}
	/*
	 * Function: condPutBeeper()
	 * Lets Karol place a beeper at his current location in case no beeper is present
	 */
	private void condPutBeeper()	{
		
		if(noBeepersPresent())	{
			putBeeper();
		}
	}
}
