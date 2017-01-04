/*
 * File: Hangman.java
 * ------------------
 * This program will eventually play the Hangman game from
 * Assignment #4.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.awt.*;

public class Hangman extends ConsoleProgram {
	
	public static final int MAX_TRIALS = 8;
	public static final int WIDTH = 700;
	public static final int HEIGHT = 600;
	
	public void init() {
        canvas = new HangmanCanvas();
        add(canvas);
        setSize(new Dimension(WIDTH, HEIGHT));
	}

/**	run() plays the game*/
    public void run() {
    	
    /**	Number of trials the user has left */
    	int trialsLeft = MAX_TRIALS;
    	setUpGame(trialsLeft);
    	
    	while(trialsLeft > 0 && !secretWord.equals(guessedWord))	{
    	/** User input*/
    		String letter = readLine("Your guess: ");
    		
    	/** Test whether user provided a valid character */
    		if(letter.length() != 1 ||
    				(letter.charAt(0) < 'A' || letter.charAt(0) > 'Z') &&
    					(letter.charAt(0) < 'a' || letter.charAt(0) > 'z'))	{	
    			
    		/** Adjust status variables*/
    			trialsLeft -= 1;
    			wrongGuesses += 1;
    	    	isWrongGuess = true;
    	    	
    			printErrorMessage(trialsLeft);
    	    
    	    /** Update canvas*/
    			canvas.noteIncorrectGuess(letter, MAX_TRIALS - trialsLeft, wrongGuesses, isWrongGuess);
    			
    		}
    	/** User input was valid*/
    		else {
    			
    			trialsLeft -= 1;
    			guessedWord = updateGuessedWord(letter, trialsLeft); 
    			canvas.displayWord(guessedWord, trialsLeft);
    			
    			if(guessedWord.equals(secretWord))	{
    	    		
    				trialsLeft = MAX_TRIALS +1;
    	    		showCurrentStatus(trialsLeft);
    	    		break;
    	    	}
    			else
    				showCurrentStatus(trialsLeft);  			
    		}
    	}
	}
    
/**
 * 	Function: setUpGame(int trLeft)
 * 	Input variable: int trLeft indicates how many trials the player has left
 * 	setUpGame(trLeft) builds up the game board, welcomes the user and chooses a secret word
 */
    private void setUpGame(int trLeft)	{
    	
    	canvas.reset();
    	println("Welcome to Hangman!");
    	HangmanLexicon lex = new HangmanLexicon();
    	secretWord = lex.getWord(rgen.nextInt(0,lex.getWordCount() - 1));
    	guessedWord = initialize(secretWord.length());
    	wrongGuesses = 0;
    	
    	canvas.displayWord(guessedWord, trLeft);
    	showCurrentStatus(trLeft);
    	
    }
    
/**	Function: initialize (int l)
 * 	Input variable: int l (length of secret word)
 *  initialize(l) generates a string of hyphens which has the same length than the secret word
 */
    private String initialize(int l)	{
    	
    	String init = "";
    	
    	for(int i = 0; i < l; i++)	{
    		init += "-";
    	}
    	
    	return init;
    }
    
/**	Function: showCurrentStatus (int trialsLeft)
 *	Input variable: int trialsLeft (number of trials the player has left)
 *	showCurrentStatus informs the player by means of console outputs about the current status of the game. 
 *	It prints the state of the the guessed word, informs the player how many 
 *	trials he/she has left and gives eventually feedback whether the player has won or lost.
 */
    private void showCurrentStatus(int trialsLeft)	{
    	
    	if (trialsLeft == MAX_TRIALS + 1)	{
    		
    		println("You guessed the word: " + secretWord);
    		println("You win.");
    		
    	} 
    	else if (trialsLeft == MAX_TRIALS)
    			println("You have " + trialsLeft + " guesses left.");
    		
    	else if (trialsLeft > 0)	{
    		
	    		println("The word now looks like this: " + guessedWord);
	        	println("You have " + trialsLeft + " guesses left.");
    		
    	}
    	else if (trialsLeft == 0) {
    		
    		println("You are completely hung.");
    		println("The word was: " + secretWord);
    		println("You lose.");
    	
    	}
    }
    
/**	Function: printErrorMessage(int trialsLeft)
  *	Input variable: int trialsLeft (number of trials the player has left)
  *	printErrorMessage informs the user about invalid input he has provided. Moreover, feedback about
  *	the number of remaining trials is given.
  */   
    private void printErrorMessage(int trialsLeft)	{
    		
    		println("Invalid input! Please type in a single letter.");
			println("You have " + trialsLeft + " guesses left.");
    		
    }
    
/**	Function: updateGuessedWord(String l, int trialsLeft)
 * 	Input variables: String l (= input letter provided by the player), int trialsleft
 * 	updateGuessedWord compares each character of the secret word with the letter provided by
 * 	the player. If the letter corresponds to a not yet identified letter of the secret word,
 *  the letter is stored in the variable guessedWord at the correct position. Moreover, the
 *  user is informed about the outcome of his guess.
 */
    private String updateGuessedWord(String l, int trialsLeft)	{
    	
    	int count = 0;
    	
    	for(int i = 0; i < secretWord.length(); i++)	{
			
    		if(secretWord.substring(i, i + 1).equals(l.toUpperCase()))	{
    	
				guessedWord = guessedWord.substring(0, i) + l.toUpperCase() + guessedWord.substring(i+1);
				count += 1;
    		}
		}
    	
    	if(count == 0)	{
    		
    		println("There are no " + l.toUpperCase() + "'s in the word.");
        	isWrongGuess = true;
        	wrongGuesses += 1;
    		canvas.noteIncorrectGuess(l,MAX_TRIALS - trialsLeft, wrongGuesses, isWrongGuess);
    		
    	}
    	else	{
    		
    		println("That guess is correct.");
        	isWrongGuess = false;
    		canvas.noteIncorrectGuess(l,MAX_TRIALS - trialsLeft, wrongGuesses, isWrongGuess);

    	}
    	
    	return guessedWord;
    }

/**	Private instance variables*/
    
    
    private HangmanCanvas canvas;
    private RandomGenerator rgen = RandomGenerator.getInstance();
    
/**	guessedWord stores the correct guesses of the player whereas each valid guess is placed at the 
 	position corresponding to the true position in the secret word */    
    private String guessedWord;
    
/**	secretWord is the word to be guessed by the player */
    private String secretWord;
    
/**	wrongGuesses tracks the number of wrong guesses made by the player */
    int wrongGuesses;
    
/** isWrongGuess is updated after each user trial. It is set to true if the user has provided a correct letter; 
 	otherwise it is set to false */
    boolean isWrongGuess;
}
