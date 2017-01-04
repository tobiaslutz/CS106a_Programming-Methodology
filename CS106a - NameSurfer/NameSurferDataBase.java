import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import acm.util.ErrorException;

/*
 * File: NameSurferDataBase.java
 * -----------------------------
 * This class keeps track of the complete database of names.
 * The constructor reads in the database from a file, and
 * the only public method makes it possible to look up a
 * name and get back the corresponding NameSurferEntry.
 * Names are matched independent of case, so that "Eric"
 * and "ERIC" are the same names.
 */

public class NameSurferDataBase implements NameSurferConstants {
	
/* Constructor: NameSurferDataBase(filename) */
/**
 * Constructor: NameSurferDataBase(String filename)
 * Input variable: String filename (= name of the external database file)
 * Creates a new NameSurferDataBase and initializes it using the data in the specified file.  
 * The constructor throws an error exception if the requested file does not exist 
 * or if an error occurs as the file is being read.
 */
	public NameSurferDataBase(String filename) {
		
		try{		
			rd = new BufferedReader(new FileReader(filename));			
		}catch (IOException ex)	{			
			throw new ErrorException(ex);			
		}
		
		try	{
			while (true)	{
					String line = rd.readLine();
					if(line == null) break;
					entry = new NameSurferEntry(line);
					database.put(entry.getName(),entry);
			}
			rd.close();
		}catch (IOException ex)	{
			throw new ErrorException(ex);
		}
	}
	
/**
 * Method: findEntry(String name)
 * Input variable: String name (= key used for the search in database)
 * Returns the NameSurferEntry associated with name. If name does not appear in the database,
 * the method returns null.
 */
	public NameSurferEntry findEntry(String name) {
		
			return database.get(name);
			
	}
	
	/* Private instance variables */
	private BufferedReader rd;
/**	The HashMap "database" is used to store the data contained in the external file "filename"*/
	private Map<String,NameSurferEntry> database = new HashMap<String,NameSurferEntry>();
	
/**	NameSuferEntry "entry" holds the data of a single record of the external database */
	private NameSurferEntry entry;
}

