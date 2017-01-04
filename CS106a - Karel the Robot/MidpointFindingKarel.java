/*
 * File: MidpointFindingKarel.java
 * -------------------------------
 * When you finish writing it, the MidpointFindingKarel class should
 * leave a beeper on the corner closest to the center of 1st Street
 * (or either of the two central corners if 1st Street has an even
 * number of corners).  Karel can put down additional beepers as it
 * looks for the midpoint, but must pick them up again before it
 * stops.  The world may be of any size, but you are allowed to
 * assume that it is at least as tall as it is wide.
 */

/*
 * Algorithm to find midpoint 
 * ------------------------------------------------
 * 1.	Iteratively put a beeper at the two outermost positions on 1st street where no beepers are present.
 * 		That is,		
 * 		1a. put a beeper at the corner 1st avenue / 1st street and last avenue / 1st street
 * 		1b. put a beeper at the corner 2nd avenue / 1st street and second last avenue / 1st street
 * 			(if possible)
 * 		1c.	repeat until 
 * 			-	no positions without beepers are left (in case the number of avenues is even)
 * 			- 	one position without a beeper is left (in case the number of avenues is odd)
 * 
 * 2.	Mark the midpoint
 * 		2a.	Put another beeper at the position where the last beeper was placed 
 * 			(in case the number of avenues is even)
 * 		2b.	Put two beepers at the position without a beeper
 * 			(in case the number of avenues is odd)
 * 
 * 3.	Remove one beeper per corner on 1st street
 * 
 * 4.	Go to the corner on 1st street where you find beepers. You have found the midpoint.
 */



import stanford.karel.*;

public class MidpointFindingKarel extends SuperKarel {

	public void run()	{
		
		putInitialBeepers();
		while(noBeepersPresent())	{
			putBeepersInBetween();
		}
		putMidpointBeeper();
		cleanUp();
		goToMidpoint();

	}
	
	/*
	 * Function: putInitialBeepers()
	 * Puts a beeper at Karel's current position and sends him to the end of the street
	 * to place another beeper. Then Karel turns around and moves one avenue away.
	 * 
	 * In the run-method above, putInitalBeepers() is called when Karel is at 1st Avenue and 1st Street.
	 * Hence, Karel puts a beeper at the corner 1st street / 1st avenue and at the corner 
	 * 1st street / last avenue. 
	 */
	public void putInitialBeepers()	{
		putBeeper();
		while(frontIsClear())	{
			move();
		}
		putBeeper();
		turnAround();
		if(frontIsClear())	{
			move();
		}
		
	}
	
	/*
	 * Function: putBeepersInBetween()
	 * Puts a beeper at Karel's current position and sends him down the street to the position which is 
	 * one avenue before an existing beeper. There Karel places another beeper and moves one avenue away. 
	 * 
	 * In the run-method above, putBeepersInBetween() is used to iteratively place beepers at the two 
	 * outermost positions on 1st street without beepers.
	 */
	public void putBeepersInBetween()	{
		putBeeper();
		if(frontIsClear())	{
			move();
		}
		while(noBeepersPresent())	{
			move();
		}
		// Because the while-loop ends when Karel is at a position with a beeper, 
		// he is sent one avenue back
		turnAround();
		move();
		
		putBeeper();
		move();
	}
	
	/*
	 * Function: putMidpointBeeper()
	 * putMidpointBeeper() is called when Karel is one avenue away from the midpoint 
	 * (assuming that there are more than 1 avenues) and looks away from the midpoint.
	 * putMidpointBeeper() sends Karel back to the midpoint where he puts a beeper
	 */
	public void putMidpointBeeper()	{
		
		turnAround();
		if(frontIsClear())
			move();
		putBeeper();
	}
	
	/*
	 * Function: cleanUp()
	 * cleanUp() sends Karel to the beginning of the street (or the end depending on his current direction).
	 * From there Karel walks the street up (or down) and removes one beeper per avenue.
	 */
	public void cleanUp()	{
		//Go to start or end
		while(frontIsClear())	{
			move();
		}
		
		// Turn around and remove one beeper per avenue
		turnAround();
		while(frontIsClear())	{

			pickBeeper();
			move();
		}
		// Remove last beeper
		pickBeeper();
	}
	
	/*
	 * Function: goToMidpoint()
	 * goToMidpoint() turns Karel around and sends him to the next position in walking direction
	 * with a beeper. Subsequently, he removes at most two beepers and places a new beeper.
	 * 
	 * In the run-method above, goToMidpoint() is called when Karel is at the beginning or the end of 
	 * 1st street and the only existing beepers are at the midpoint.
	 */
	public void goToMidpoint()	{
		turnAround();
		while(noBeepersPresent())	{
			move();
		}
		pickBeeper();
		if (beepersPresent())
			pickBeeper();
		putBeeper();
		if(facingWest())	{
			turnAround();
		}
		
	}

}