/*
 * File: NameSurferEntry.java
 * --------------------------
 * This class represents a single entry in the database.  Each
 * NameSurferEntry contains a name and a list giving the popularity
 * of that name for each decade stretching back to 1900.
 */

import acm.util.*;
import java.util.*;

public class NameSurferEntry implements NameSurferConstants {

/**
 * Constructor: NameSurferEntry(String line)
 * Input variable: String line (= a line from the data file; each line begins with the name 
 * followed by 11 integers, whereas each integer indicates the rank of that name in the corresponding decade.
 * Example: Sam 58 69 99 131 168 236 278 380 467 408 466)
 * The constructor NameSurferEntry creates a new NameSurferEntry from a data line.
 */
	public NameSurferEntry(String line) {
		
	/** line is parsed and the content is stored in the variables "name" and "ranks" */
		int nameEndIndex = line.indexOf(" ");
		name = line.substring(0, nameEndIndex);
		
		int nextDigitStartIndex = nameEndIndex + 1;
		int nextDigitEndIndex;
		
		for (int i = 0; i < 11; i++)	{
			if (i < 10)
				nextDigitEndIndex = line.indexOf(" ", nextDigitStartIndex);
			else
				nextDigitEndIndex = line.length();
			
			ranks[i] = Integer.parseInt(line.substring(nextDigitStartIndex, nextDigitEndIndex));
			nextDigitStartIndex = nextDigitEndIndex + 1;
		}
	}

/**
 * Method: getName()
 * Returns the name associated with the NameSurferEntry.
 */
	public String getName() {
		return name;
	}

/**
 * Method: getRank(int decade)
 * Input variable: int decade (can take values from 1 to 11)
 * getRank returns the rank of name associated with a particular decade. 
 */
	public int getRank(int decade) {
		if (name.equals(null))
			return 0;
		else
			return ranks[decade - 1];
	}

/**
 * Method: toString()
 * Returns a string representation of the NameSurferEntry
 */
	public String toString() {
		
		return name + " " + Arrays.toString(ranks);
	}
	
/* Private instance variables */
	
/**	Stores the name of the NameSurferEntry */
	private String name = null;
	
/** Stores the ranks of the name in each decade from 1900 to 2000 */
	private int[] ranks = new int[11];
}

