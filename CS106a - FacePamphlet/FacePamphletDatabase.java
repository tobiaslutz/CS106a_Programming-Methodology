/*
 * File: FacePamphletDatabase.java
 * -------------------------------
 * This class keeps track of the profiles of all users in the
 * FacePamphlet application.  Note that profile names are case
 * sensitive, so that "ALICE" and "alice" are NOT the same name.
 */

import java.util.*;

public class FacePamphletDatabase implements FacePamphletConstants {

	/** 
	 * Constructor
	 * This method takes care of any initialization needed for 
	 * the database.
	 */
	public FacePamphletDatabase() {
	}
	
	
	/** 
	 * This method adds the given profile to the database.  If the 
	 * name associated with the profile is the same as an existing 
	 * name in the database, the existing profile is replaced by 
	 * the new profile passed in.
	 */
	public void addProfile(FacePamphletProfile profile) {
		
		if (database.containsKey(profile.getName()))
			database.replace(profile.getName(), profile);
		else
			database.put(profile.getName(), profile);
	}

	
	/** 
	 * This method returns the profile associated with the given name 
	 * in the database.  If there is no profile in the database with 
	 * the given name, the method returns null.
	 */
	public FacePamphletProfile getProfile(String name) {
		
		return database.get(name);
	}
	
	
	/** 
	 * This method removes the profile associated with the given name
	 * from the database.  It also updates the list of friends of all
	 * other profiles in the database to make sure that this name is
	 * removed from the list of friends of any other profile.
	 * 
	 * If there is no profile in the database with the given name, then
	 * the database is unchanged after calling this method.
	 */
	public void deleteProfile(String name) {
		
		database.remove(name);
		
		for (Map.Entry<String, FacePamphletProfile> entry : database.entrySet())	
			entry.getValue().removeFriend(name);
		    
	}

	
	/** 
	 * This method returns true if there is a profile in the database 
	 * that has the given name. It returns false otherwise.
	 */
	public boolean containsProfile(String name) {
		
		return database.containsKey(name);
		
	}
	
	public Map<String,FacePamphletProfile> getFacePamphlet()	{
		
		return database;
		
	}
	
	/* Private instance variable*/
	/** HashMap "database" stores all profiles of FacePamphlet; the name of a profile serves as key
	 while the profile itself is the corresponding value */
	private Map<String,FacePamphletProfile> database = new HashMap<String,FacePamphletProfile>();

}
