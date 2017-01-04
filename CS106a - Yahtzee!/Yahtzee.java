/*
 * File: Yahtzee.java
 * ------------------
 * This program will eventually play the Yahtzee game.
 */

import acm.io.*;
import acm.program.*;
import acm.util.*;

public class Yahtzee extends GraphicsProgram implements YahtzeeConstants {
	
	public static void main(String[] args) {
		new Yahtzee().start(args);
	}
	
/**	Function run()	
 * 	Sets up the gui and asks for the number and names of the players
 */	
	public void run() {
		setSize(APPLICATION_WIDTH, APPLICATION_HEIGHT);
		IODialog dialog = getDialog();
		
		while (nPlayers < 1 || nPlayers > 4)	{
			nPlayers = dialog.readInt("Enter number of players");
			if (nPlayers < 1 || nPlayers > 4)
				dialog.print("Number of players must be between 1 and 4. ");
		}
		playerNames = new String[nPlayers];
		for (int i = 1; i <= nPlayers; i++) {
			playerNames[i - 1] = dialog.readLine("Enter name for player " + i);
		}
		display = new YahtzeeDisplay(getGCanvas(), playerNames);
		playGame();
	}

/**	Function run()	
 * 	Plays "N_SCORING_CATEGORIES" rounds of the game. Computes and displays all scores.
 */		
	private void playGame() {
		
		int[][] scoreInEachCategory = new int[nPlayers][N_CATEGORIES];
		
	/**	Each player is eligible for the bonus at the beginning of the game*/
		bonusNotYetAssigned = new boolean[nPlayers];
		for (int pl = 1; pl <= nPlayers; pl++)	
			bonusNotYetAssigned[pl - 1] = true;
			
		for (int rounds = 0; rounds < N_SCORING_CATEGORIES; rounds++)	{
			for (int pl = 1; pl <= nPlayers; pl++)	{
				display.printMessage(playerNames[pl - 1] + "'s trial! Press \"Roll Dice\""
						+ " to throw the dices.");
				display.waitForPlayerToClickRoll(pl);
				
				for (int i = 0; i < N_DICE; i++) 
					dice[i] = rgen.nextInt(1,6);
				display.displayDice(dice);
				    
				for(int k = 0; k < 2; k++)	{
					display.printMessage("Select the dices you want to throw again "
							+ "and press \"Roll again\".");
					display.waitForPlayerToSelectDice();
						
					for (int i = 0; i < N_DICE; i++) 
						if(display.isDieSelected(i)) 
							dice[i] = rgen.nextInt(1,6);
					display.displayDice(dice);
				}
				
				display.printMessage(playerNames[pl - 1] + ", choose a category.");
				int category = display.waitForPlayerToSelectCategory();
				int score = checkCategory(dice, category);
				updateScore(category, score, pl, scoreInEachCategory);
				
			}
		}
		computeFinalOutcome(scoreInEachCategory);	
	}
	
/**	Function checkCategory(int[] dice, int cat)
 * 	Input variables: int[] dice (= current values of the 5 dices), int cat (= category)
 * 	checkCategory computes the achieved score for category cat using the delivered dice configuration
 */
	private int checkCategory(int[] dice, int cat)	{
		
		int score = 0;
		switch(cat)	{
			case ONES: 
				for (int i = 0; i < N_DICE; i++) {
					if(dice[i] == 1)
						score += 1;
				}
				return score;
			case TWOS: 
				for (int i = 0; i < N_DICE; i++) {
					if(dice[i] == 2)
						score += 2;
				}
				return score;
				
			case THREES: 
				for (int i = 0; i < N_DICE; i++) {
					if(dice[i] == 3)
						score += 3;
				}
				return score;
				
			case FOURS: 
				for (int i = 0; i < N_DICE; i++) {
					if(dice[i] == 4)
						score += 4;
				}
				return score;
				
			case FIVES: 
				for (int i = 0; i < N_DICE; i++) {
					if(dice[i] == 5)
						score += 5;
				}
				return score;
				
			case SIXES: 
				for (int i = 0; i < N_DICE; i++) {
					if(dice[i] == 6)
						score += 6;
				}
				return score;
				
			case THREE_OF_A_KIND: 
				for (int i = 0; i < N_DICE - 2; i++) {
					int count = 1;
					for (int k = i+1; k < N_DICE; k++)	{						
						if(dice[k] == dice[i])
							count += 1;
							if(count == 3) {
								for (int j = 0; j < N_DICE; j++) {
									score += dice[j];
								}
								return score;
							}
					}
				}
				return 0;
				
			case FOUR_OF_A_KIND: 
				for (int i = 0; i < N_DICE - 3; i++) {
					int count = 1;
					for (int k = i+1; k < N_DICE; k++)	{						
						if(dice[k] == dice[i])
							count += 1;
							if(count == 4) {
								for (int j = 0; j < N_DICE; j++) {
									score += dice[j];
								}
								return score;
							}
					}
				}
				return 0;
			
			case FULL_HOUSE: 
				for (int i = 0; i < N_DICE - 2; i++) {
					int count = 1;
					for (int k = i+1; k < N_DICE; k++)	{						
						if(dice[k] == dice[i])
							count += 1;
							if(count == 3) {
								int[] otherTwoDices = new int[N_DICE - 3];
								int l = 0;
								for (int j = 0; j < N_DICE; j++) {
									if(dice[j] != dice[i])	{
										otherTwoDices[l] = dice[j];
										l += 1;
									}
								}
								
								if(otherTwoDices[0] == otherTwoDices[1])
									return 25;
							}
					}
				}
				return 0;
			case SMALL_STRAIGHT: 
				for (int i = 0; i < N_DICE; i++) {
					if(dice[i] == 2)	{
						for (int j = 0; j < N_DICE; j++) {
							if(dice[j] == 3)	{
								for (int k = 0; k < N_DICE; k++) {
									if(dice[k] == 4)	{
										for (int l = 0; l < N_DICE; l++) {
											if(dice[l] == 5)	return 30;
											else if(dice[l] == 1)	return 30;
											}
										}
									}
								}
							}
						}
					}
				
				for (int i = 0; i < N_DICE; i++) {
					if(dice[i] == 3)	{
						for (int j = 0; j < N_DICE; j++) {
							if(dice[j] == 4)	{
								for (int k = 0; k < N_DICE; k++) {
									if(dice[k] == 5)	{
										for (int l = 0; l < N_DICE; l++) {
											if(dice[l] == 6)	return 30;
											}
										}
									}
								}
							}
						}
					}
				return 0;
				
			case LARGE_STRAIGHT: 
				for (int i = 0; i < N_DICE; i++) {
					if(dice[i] == 2)	{
						for (int j = 0; j < N_DICE; j++) {
							if(dice[j] == 3)	{
								for (int k = 0; k < N_DICE; k++) {
									if(dice[k] == 4)	{
										for (int l = 0; l < N_DICE; l++) {
											if(dice[l] == 5)	{
												for (int m = 0; m < N_DICE; m++) {
													if(dice[m] == 6)
														return 40;
													else if(dice[m] == 1)
														return 40;
												}
											}
										}
									}
								}
							}
						}
					}
				}
				return 0;
				
			case YAHTZEE: 
				for (int j = 1; j < N_DICE; j++) {
					if(dice[j] != dice[0])	return 0;
					
				}
				return 50;
				
			case CHANCE: 
				for (int j = 0; j < N_DICE; j++) {
					score += dice[j];
				}
				return score;
				
			default:
				return -1;
		}
	}
	
/**	Function: updateScore(int category, int score, int pl, int[][] scoreInEachCategory)
 * 	Input variables: int category (= category chosen by player), int score (= score achieved in category),
 * 	int pl (= player number), int[][] scoreInEachCategory (= table holding the scores of all players)
 * 
 * 	updateScore assigns the achieved "score" of player "pl" in "category" to the corresponding field
 * 	in scoreInEachCategory and updates the fields "Upper Score", "Lower Score", "Upper Bonus" and "Total"
 * 	for player "pl". Moreover, the results are displayed on the gui. 
 */
	private void updateScore(int category, int score, int pl, int[][] scoreInEachCategory)	{
	/** Upper Score*/
		if(category <= SIXES)	{
			scoreInEachCategory[pl - 1][category-1] += score;
			scoreInEachCategory[pl - 1][UPPER_SCORE-1] += score;
			
			if (scoreInEachCategory[pl - 1][UPPER_SCORE-1] < 63)	
				scoreInEachCategory[pl - 1][TOTAL-1] += score;
			
			else if(bonusNotYetAssigned[pl - 1])	{
					
				scoreInEachCategory[pl - 1][TOTAL-1] += score + 35;
				scoreInEachCategory[pl - 1][UPPER_SCORE-1] += 35;
				scoreInEachCategory[pl - 1][UPPER_BONUS-1] = 35;
				
				display.printMessage("Super " + playerNames[pl - 1] + 
						"! You have just received a bonus of 35 points.");
				display.updateScorecard(UPPER_BONUS, pl, 
						scoreInEachCategory[pl - 1][UPPER_BONUS-1]);
				bonusNotYetAssigned[pl - 1] = false;
			}
			
			display.updateScorecard(UPPER_SCORE, pl, 
					scoreInEachCategory[pl - 1][UPPER_SCORE-1]);
			
		}
	/**	Lower Score*/
		else if (category >= THREE_OF_A_KIND && category <= CHANCE)	{
			scoreInEachCategory[pl - 1][category-1] += score;
			scoreInEachCategory[pl - 1][LOWER_SCORE-1] += score;
			scoreInEachCategory[pl - 1][TOTAL-1] += score;
			
			display.updateScorecard(LOWER_SCORE, pl, 
					scoreInEachCategory[pl - 1][LOWER_SCORE-1]);
		}
		
		display.updateScorecard(category, pl, scoreInEachCategory[pl - 1][category-1]);
		display.updateScorecard(TOTAL, pl, scoreInEachCategory[pl - 1][TOTAL-1]);
	}
	
/**	Function: computeFinalOutcome(int[][] scoreInEachCategory)
* 	Input variables: int[][] scoreInEachCategory (= table holding the scores of all players)
* 
* 	computeFinalOutcome determines the player with the maximum final score and prints a 
* 	congratulations-message
*/
	private void computeFinalOutcome(int[][] scoreInEachCategory)	{
		
		int winner = 0;
		int maxScore = scoreInEachCategory[winner][TOTAL - 1];
		for (int i = 0; i < nPlayers; i++)	{
			for (int j = i + 1; j < nPlayers; j++)	{
				if(scoreInEachCategory[j][TOTAL - 1] > scoreInEachCategory[i][TOTAL - 1])	{
					winner = j;
					maxScore = scoreInEachCategory[winner][TOTAL - 1];
					break;
				}
				else	{
					winner = i;
					maxScore = scoreInEachCategory[winner][TOTAL - 1];
				}
			}
		}
		display.printMessage("Congratulations, " + playerNames[winner] + 
				"! You won with a total score of " + maxScore + " points.");
	}
		
/* Private instance variables */
	
/** nPlayers is the actual number of players*/
	private int nPlayers;
	
/** playerNames store the names of all players*/	
	private String[] playerNames;
	
	private YahtzeeDisplay display;
	
	private RandomGenerator rgen = new RandomGenerator();
	
/** Each spot of the array "dice" corresponds to a "physical" dice in the game */	
	private int[] dice = new int[5];
	
/**	bonusNotYetAssigned serves as a flag for each player whether he/she is still eligible for the bonus*/
	private boolean[] bonusNotYetAssigned;;

}
