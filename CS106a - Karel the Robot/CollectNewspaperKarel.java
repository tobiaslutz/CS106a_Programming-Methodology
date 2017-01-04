/*
 * File: CollectNewspaperKarel.java
 * --------------------------------
 * At present, the CollectNewspaperKarel subclass does nothing.
 * Your job in the assignment is to add the necessary code to
 * instruct Karel to walk to the door of its house, pick up the
 * newspaper (represented by a beeper, of course), and then return
 * to its initial position in the upper left corner of the house.
 */

import stanford.karel.*;

public class CollectNewspaperKarel extends SuperKarel {

	public void run() {
        moveToNewspaper();
        pickUpNewspaper();	
        goBack();
	}
	
	/*
	 * Function: moveToNewspaper()
	 * Sends Karol to the position where the newspaper was placed
	 */
	private void moveToNewspaper() 	{
		turnRight();
		move();
		turnLeft();
		moveThreeSteps();
	}
	
	/*
	 * Function: pickUpNewspaper()
	 * Lets Karol pick up the newspaper
	 */
	private void pickUpNewspaper() 	{
		pickBeeper();
		turnAround();
	}

	/*
	 * Function: goBack()
	 * Sends Karol back to his original position
	 */
	private void goBack() 	{
		moveThreeSteps();
		turnRight();
		move();
		turnRight();
	}
	
	/*
	 * Function: moveThreeSteps()
	 * Lets Karol walk three steps towards the direction he is currently facing
	 */
	private void moveThreeSteps()	{
		move();
		move();
		move();
	}

}
