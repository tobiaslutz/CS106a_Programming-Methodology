/*
 * File: CheckerboardKarel.java
 * ----------------------------
 * When you finish writing it, the CheckerboardKarel class should draw
 * a checkerboard using beepers, as described in Assignment 1.  You
 * should make sure that your program works for all of the sample
 * worlds supplied in the starter folder.
 */

import stanford.karel.*;

public class CheckerboardKarel extends SuperKarel {

	public void run()	{
			fillRow();
			moveBack();
			// Checkerboard has at least two columns
			if(rightIsClear())	{
				while(frontIsClear())	{
					moveUp();
					fillRow();
					moveBack();
				}
			}
			// Checkerboard has one column
			else	{
				while(frontIsClear())	{
					move();
					if(frontIsClear())	{
						move();
						putBeeper();
					}
				}
			}
				
	}
	
	/* fillRow() fills a row with beepers in an alternate fashion; the first beeper
	 * is put at the position where Karel stands at the time when fillRow() is called
	 */
	private void fillRow()	{
		putBeeper();
		while(frontIsClear())	{
			move();
			if(frontIsClear())	{
				move();
				putBeeper();
			}
		}
	}
	
	/*
	 * moveBack() brings Karel to the beginning of the row from the position where Karel 
	 * stands at the time when moveBack() is called
	 */
	private void moveBack()	{
		turnAround();
		while(frontIsClear())	{
			move();
		}
		turnRight();
	}
	
	/*
	 * moveUp() moves Karel one row up if there was no beeper at the original position. In case there was 
	 * a beeper, Karel is moved one row up and one column to the right.
	 */
	private void moveUp()	{
			if(beepersPresent())	{
				move();
				turnRight();
				move();
			}
			else	{
				move();
				turnRight();
			}
		}
}