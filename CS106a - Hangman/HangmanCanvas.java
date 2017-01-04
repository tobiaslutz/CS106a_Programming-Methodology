/*
 * File: HangmanCanvas.java
 * ------------------------
 * This file keeps track of the Hangman display.
 */

import acm.graphics.*;

public class HangmanCanvas extends GCanvas {
	
/** Constants for the simple version of the picture (in pixels) */
	
	private static final int X0 = 56;
	private static final int Y0 = 50;
	private static final int SCAFFOLD_HEIGHT = 400;
	private static final int BEAM_LENGTH = 144;
	private static final int ROPE_LENGTH = 18;
	private static final int HEAD_RADIUS = 36;
	private static final int BODY_LENGTH = 144;
	private static final int ARM_OFFSET_FROM_HEAD = 28;
	private static final int UPPER_ARM_LENGTH = 72;
	private static final int LOWER_ARM_LENGTH = 44;
	private static final int HIP_WIDTH = 36;
	private static final int LEG_LENGTH = 108;
	private static final int FOOT_LENGTH = 28;

/** Function: reset() 
 * 	Resets the display so that only the scaffold appears 
 */
	public void reset() {
		
		this.removeAll();
		
	/** Scaffold*/
		add(new GLine(X0,Y0,X0,Y0 + SCAFFOLD_HEIGHT));
		add(new GLine(X0,Y0,X0 + BEAM_LENGTH,Y0));
		add(new GLine(X0 + BEAM_LENGTH,Y0,X0 + BEAM_LENGTH, Y0 + ROPE_LENGTH));
	}
	

/**
 * Function: displayWord(String guessedWord, int trialsLeft)
 * Input variables: String guessedWord (= current state of the guessed word), 
 * int trialsLeft (= number of trials the player has left)
 * displayWord initially prints a string of hyphens on the screen. Every time the user makes a correct guess, 
 * it replaces the corresponding hyphens with the correctly guessed letter. 
*/
	public void displayWord(String guessedWord, int trialsLeft) {
		
		if(trialsLeft == 8)	{
			
			currentState = new GLabel(guessedWord);
			currentState.setFont("Helvetica-24");
			currentState.move(X0, Y0 + SCAFFOLD_HEIGHT + 60);
			add(currentState);
			
		}
		else
			currentState.setLabel(guessedWord);
	}

/**
 * Function noteIncorrectGuess(String letter, int guesses, int wrongGuesses, boolean isWrongGuess)
 * Input variables: String letter (= input letter provided by the player), int guesses (= number of guesses
 * made so far), int wrongGuesses (= number of wrong guesses made so far), 
 * boolean isWrongGuess (= indicates whether String letter is a correct or wrong guess)
 * Calling noteIncorrectGuess causes the next body part to appear on the scaffold. Moreover, if the letter
 * corresponds to a wrong guess, it is added to the list of incorrect guesses. This list is then printed
 * at the bottom of the canvas.
 */
	public void noteIncorrectGuess(String letter, int guesses, int wrongGuesses, boolean isWrongGuess) {
		 
		switch(guesses)	{
		
		/** Head */
		case 1: 
			GOval head = new GOval(X0 + BEAM_LENGTH - HEAD_RADIUS, Y0 + ROPE_LENGTH, 
				2 * HEAD_RADIUS, 2 * HEAD_RADIUS);
			add(head);
			break;
			
		/** Body */
		case 2: 
			add(new GLine(X0 + BEAM_LENGTH,Y0 + ROPE_LENGTH + 2 * HEAD_RADIUS,
					X0 + BEAM_LENGTH,Y0 + ROPE_LENGTH + 2 * HEAD_RADIUS + BODY_LENGTH));
			break;
			
		/** Left arm */	
		case 3: 
			add(new GLine(X0 + BEAM_LENGTH - UPPER_ARM_LENGTH,
					Y0 + ROPE_LENGTH + 2 * HEAD_RADIUS + ARM_OFFSET_FROM_HEAD,
					X0 + BEAM_LENGTH,Y0 + ROPE_LENGTH + 2 * HEAD_RADIUS + ARM_OFFSET_FROM_HEAD));
			
			add(new GLine(X0 + BEAM_LENGTH - UPPER_ARM_LENGTH,
					Y0 + ROPE_LENGTH + 2 * HEAD_RADIUS + ARM_OFFSET_FROM_HEAD,
					X0 + BEAM_LENGTH - UPPER_ARM_LENGTH,
					Y0 + ROPE_LENGTH + 2 * HEAD_RADIUS + ARM_OFFSET_FROM_HEAD + LOWER_ARM_LENGTH ));
			break;
			
		/** Right arm */	
		case 4: 
			add(new GLine(X0 + BEAM_LENGTH + UPPER_ARM_LENGTH,
					Y0 + ROPE_LENGTH + 2 * HEAD_RADIUS + ARM_OFFSET_FROM_HEAD,
					X0 + BEAM_LENGTH,Y0 + ROPE_LENGTH + 2 * HEAD_RADIUS + ARM_OFFSET_FROM_HEAD));
			
			add(new GLine(X0 + BEAM_LENGTH + UPPER_ARM_LENGTH,
					Y0 + ROPE_LENGTH + 2 * HEAD_RADIUS + ARM_OFFSET_FROM_HEAD,
					X0 + BEAM_LENGTH + UPPER_ARM_LENGTH,
					Y0 + ROPE_LENGTH + 2 * HEAD_RADIUS + ARM_OFFSET_FROM_HEAD + LOWER_ARM_LENGTH));
			break;
			
		/** Left leg */	
		case 5: 
			add(new GLine(X0 + BEAM_LENGTH - HIP_WIDTH,
					Y0 + ROPE_LENGTH + 2 * HEAD_RADIUS + BODY_LENGTH,
					X0 + BEAM_LENGTH,Y0 + ROPE_LENGTH + 2 * HEAD_RADIUS + BODY_LENGTH));
			
			add(new GLine(X0 + BEAM_LENGTH - HIP_WIDTH,
					Y0 + ROPE_LENGTH + 2 * HEAD_RADIUS + BODY_LENGTH,
					X0 + BEAM_LENGTH - HIP_WIDTH,
					Y0 + ROPE_LENGTH + 2 * HEAD_RADIUS + BODY_LENGTH + LEG_LENGTH ));
			
			break;
		
		/** Right leg */	
		case 6: 
			add(new GLine(X0 + BEAM_LENGTH + HIP_WIDTH,
					Y0 + ROPE_LENGTH + 2 * HEAD_RADIUS + BODY_LENGTH,
					X0 + BEAM_LENGTH,Y0 + ROPE_LENGTH + 2 * HEAD_RADIUS + BODY_LENGTH));
			
			add(new GLine(X0 + BEAM_LENGTH + HIP_WIDTH,
					Y0 + ROPE_LENGTH + 2 * HEAD_RADIUS + BODY_LENGTH,
					X0 + BEAM_LENGTH + HIP_WIDTH,
					Y0 + ROPE_LENGTH + 2 * HEAD_RADIUS + BODY_LENGTH + LEG_LENGTH ));
			break;
			
		/** Left foot */
		case 7: 
			add(new GLine(X0 + BEAM_LENGTH - HIP_WIDTH,
					Y0 + ROPE_LENGTH + 2 * HEAD_RADIUS + BODY_LENGTH + LEG_LENGTH,
					X0 + BEAM_LENGTH - HIP_WIDTH - FOOT_LENGTH,
					Y0 + ROPE_LENGTH + 2 * HEAD_RADIUS + BODY_LENGTH + LEG_LENGTH ));
			break;
		
		/** Right foot */	
		case 8: 
			add(new GLine(X0 + BEAM_LENGTH + HIP_WIDTH,
					Y0 + ROPE_LENGTH + 2 * HEAD_RADIUS + BODY_LENGTH + LEG_LENGTH,
					X0 + BEAM_LENGTH + HIP_WIDTH + FOOT_LENGTH,
					Y0 + ROPE_LENGTH + 2 * HEAD_RADIUS + BODY_LENGTH + LEG_LENGTH ));
			break;
	
		}
		
		if(isWrongGuess)	{
			
		/** 1st wrong guess */
			if(wrongGuesses == 1)	{
				
				wrongLetters = letter.toLowerCase();
				wrongLettersLabel = new GLabel(wrongLetters);
				wrongLettersLabel.setFont("Helvetica-20");
				wrongLettersLabel.move(X0, Y0 + SCAFFOLD_HEIGHT + 100);
				add(wrongLettersLabel);
				
			}
			else if(wrongGuesses > 1) 	{
				
				int count = 0;
				
				/** Has the wrong letter already been displayed on the canvas?*/
				for(int i = 0; i < wrongLetters.length(); i++)	{
					
		    		if(wrongLetters.substring(i, i + 1).equals(letter.toLowerCase()))	count += 1;
		    		if (count > 0)	break;
				}
	
				/** Wrong letter is new. Hence, print wrong letter on the canvas*/
				if(count == 0)	{
					
			    	wrongLetters += ", " + letter.toLowerCase();
					wrongLettersLabel.setLabel(wrongLetters);
					
				}
			}
		}
		
	}

	//	Private instance variables
	
/** currentState visualizes the current state of the guessed word */
	private GLabel currentState;
	
/** wrongLettersLabel visualizes the wrong letters that have been provided by the user so far */
	private GLabel wrongLettersLabel;
	
/** wrongLetters stores the wrong letters that have been provided by the user so far */
	private String wrongLetters;

}
