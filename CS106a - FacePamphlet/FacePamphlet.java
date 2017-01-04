/* 
 * File: FacePamphlet.java
 * -----------------------
 * When it is finished, this program will implement a basic social network
 * management system.
 *
 * Remark: The possible extension "Save and load social networks" is implemented
 */

import acm.program.*;
import acm.graphics.*;
import acm.util.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;


import javax.swing.*;

public class FacePamphlet extends Program 
					implements FacePamphletConstants {

	/**
	 * This method has the responsibility for initializing the 
	 * interactors in the application, and taking care of any other 
	 * initialization that needs to be performed.
	 */
	public void init() {
		setSize(APPLICATION_WIDTH, APPLICATION_HEIGHT);
		
		/**
		 * Add interactors to the westside of the canvas
		 */
		//Add "Change Status" Textfield and Button
		statusField = new JTextField(TEXT_FIELD_SIZE);
		add(statusField,WEST);
		add(new JButton("Change Status"), WEST);
		
		//Separator
		add(new JLabel(EMPTY_LABEL_TEXT), WEST);
		
		
		//Add "Change Picture" Textfield and Button
		pictureField = new JTextField(TEXT_FIELD_SIZE);
		add(pictureField,WEST);
		add(new JButton("Change Picture"), WEST);
		
		//Separator
		add(new JLabel(EMPTY_LABEL_TEXT), WEST);
		
		//Add "Add Friend" Textfield and Button
		friendField = new JTextField(TEXT_FIELD_SIZE);
		add(friendField,WEST);
		add(new JButton("Add Friend"), WEST);
		canvas = new FacePamphletCanvas();
		
		/**
		 * Add interactors to the northside of the canvas
		 */
		add(new JLabel("Name"), NORTH);
		nameField = new JTextField(TEXT_FIELD_SIZE);
		add(nameField,NORTH);
		add(new JButton("Add"), NORTH);
		add(new JButton("Delete"), NORTH);
		add(new JButton("Lookup"), NORTH);
		
		canvas = new FacePamphletCanvas();
		add(canvas);
		
		loadFacePamphlet();
		
		addActionListeners();
		statusField.addActionListener(this);
		pictureField.addActionListener(this);
		friendField.addActionListener(this);
		nameField.addActionListener(this);
		
		/** Extension of the assignment: FacePamphlet is stored after shuting down the application */
		Runtime.getRuntime().addShutdownHook(new Thread() {

	        public void run() {
	            
	        	storeFacePamphlet();
	        }

	    });
		
    }
    
  
    /**
     * This class is responsible for detecting when the buttons are
     * clicked or interactors are used, so you will have to add code
     * to respond to these actions.
     */
    public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Change Status") || e.getSource() == statusField)	{
			
			String status = statusField.getText();
			if(!status.equals(""))	{
				
				if(currentProfile != null)	{
					
					database.getProfile(currentProfile.getName()).setStatus(status);
					currentProfile = database.getProfile(currentProfile.getName());
				
					updateCanvas("Status updated to " + status);
				}
				else	{
	
					updateCanvas("Please select a profile to change status");
				}
			}	
		}
		else if (e.getActionCommand().equals("Change Picture") || e.getSource() == pictureField)	{
			
			String filename = pictureField.getText();
			if(!filename.equals("")) 	{
				
				if(currentProfile != null)	{
					GImage image = null;
					try	{
						image = new GImage(filename);
						database.getProfile(currentProfile.getName()).setImage(image,filename);
						currentProfile = database.getProfile(currentProfile.getName());
						updateCanvas("Picture updated");
					} catch (ErrorException ex)	{

						updateCanvas("Unable to open image file: " + filename);
					}
				}
				else	{
					
					updateCanvas("Please select a profile to change the picture");
				}
			}
			else {
				if(currentProfile.getImage() != null)	{
					
					database.getProfile(currentProfile.getName()).setImage(null,"");
					currentProfile = database.getProfile(currentProfile.getName());
					updateCanvas("Picture updated");
					
				}
			}
				
		}
		else if (e.getActionCommand().equals("Add Friend") || e.getSource() == friendField)	{
			
			String newFriend = friendField.getText();
			
				if(!newFriend.equals("")) 	{
				
					if(currentProfile != null)	{
						
						if(database.containsProfile(newFriend))	{
							
							Iterator<String> friendsIt = currentProfile.getFriends();
							boolean friendIsNew = true;
							while (friendsIt.hasNext())	{
								
								if(friendsIt.next().equals(newFriend))	{
									
									updateCanvas(currentProfile.getName() + " already has " + newFriend
											+ " as a friend");
									friendIsNew = false;
									break;
								}
							}
							
							if(friendIsNew)	{
								
								database.getProfile(currentProfile.getName()).addFriend(newFriend);
								currentProfile = database.getProfile(currentProfile.getName());
								
								database.getProfile(newFriend).addFriend(currentProfile.getName());
								
								updateCanvas(newFriend + " added as a friend");
							}
						}
						else	{
	
							updateCanvas(newFriend + " does not exist");
						}
					}
					else	{
					
						updateCanvas("Please select a profile to add friend");
					}
			}
		}
		else if (e.getSource() == nameField || e.getActionCommand().equals("Add"))	{
			
			String name = nameField.getText();
			if(!name.equals(""))	{
				
				if (database.containsProfile(name))	{
					
					currentProfile = database.getProfile(name);
					
					updateCanvas("A profile with the name " + name + 
							" already exists");
					
				}
				else {
					
					currentProfile = new FacePamphletProfile(name);
					database.addProfile(currentProfile);
					
					updateCanvas("New profile created");
				}	
			}
		}
		else if (e.getActionCommand().equals("Delete"))	{
			
			String name = nameField.getText();
			if(!name.equals(""))	{
				
				if (database.containsProfile(name))	{
					
					database.deleteProfile(name);
					currentProfile = null;
					updateCanvas("Profile of " + name + " deleted");
					
				}
				else	{
					
					currentProfile = null;
					updateCanvas("A profile with the name " + name + 
							" does not exist");
				}
					
			}
		}
		else if (e.getActionCommand().equals("Lookup"))	{
			String name = nameField.getText();
			if(!name.equals(""))	{
				
				if (database.containsProfile(name))	{
					
					currentProfile = database.getProfile(name);
					updateCanvas("Displaying " + name);
					
				}
				else	{
					
					currentProfile = null;
					updateCanvas("A profile with the name " + name + 
							" does not exist");
				}
					
			}
		}
	}
    
    private void updateCanvas(String str)	{
    	
    	canvas.displayProfile(currentProfile);
		canvas.showMessage(str);
    	
    }
    
    /**
     * storeFacePamphlet() stores the FacePhamplet database in the file FacePamphlet.txt
     */
    private void storeFacePamphlet()	{

    	try {
    		BufferedWriter bf = new BufferedWriter(new FileWriter("FacePamphlet.txt"));
    		for(String key : database.getFacePamphlet().keySet())	{
    			
    			bf.write(database.getFacePamphlet().get(key).toString());
    			bf.newLine();
    			
    		}
    		bf.close();
		} catch(IOException ex) {
			throw new ErrorException(ex);
		}
    }
    
    /**
     * loadFacePamphlet() reloads the FacePhamplet database from the file FacePamphlet.txt
     */
    private void loadFacePamphlet()	{
    	
    	try {
			BufferedReader rd = new BufferedReader(new FileReader("FacePamphlet.txt"));
			while (true) {
				String line = rd.readLine();
				if (line == null) break;
				FacePamphletProfile existingProfile = loadProfile(line); 
				if (existingProfile != null)
					database.addProfile(existingProfile);
			}
			rd.close();
		} catch(IOException ex) {
			// Nothing is done. FacePamphlet is built from scratch.
		}
    }
    
    /**
     * loadProfile(String line) reconstructs a FacePamphletProfile database from line, whereas 
     * line corresponds to a line from FacePamphlet.txt with the structure: 
     * "name; status; imagePath; friends,". Note that friends is a comma separated list of the 
     * names of all of the friends in this profile. 
     */
    private FacePamphletProfile loadProfile(String line) {
		int nameEnd = line.indexOf(";");
		if (line.substring(0, nameEnd).trim().length() > 0)	{
			// Load name
			FacePamphletProfile profile = new FacePamphletProfile(line.substring(0, nameEnd));
			
			// Load status
			int statusEnd = line.indexOf(";" , nameEnd + 1);
			if (line.substring(nameEnd + 1, statusEnd).trim().length() > 0)	
				profile.setStatus(line.substring(nameEnd + 1, statusEnd));
			
			// Load image
			int imageEnd = line.indexOf(";", statusEnd + 1);
			GImage image = null;
			profile.setImage(image, "");
			try	{
				String path = line.substring(statusEnd + 1, imageEnd);
				image = new GImage(path);
				double height = image.getHeight();
				if (height > 0)
					profile.setImage(image, path);
			} catch (ErrorException ex)	{
				throw new ErrorException(ex);
			}
			
			// Load friendlist
			String friendList = line.substring(imageEnd + 1);
			int friendEnd = friendList.indexOf(",");
			int friendStart = 0;
			while(friendEnd > 0)	{
				profile.addFriend(friendList.substring(friendStart,friendEnd));
				friendStart = friendEnd + 1;
				friendEnd = friendList.indexOf(",", friendStart);
			}			
			
			return profile;
		}
		else
			return null;
	}
    
    
    
    // Instance Variables
    private FacePamphletCanvas canvas; 
    private JTextField statusField;
    private JTextField pictureField;
    private JTextField friendField;
    private JTextField nameField;
    
    /** database stores all profiles of FacePamphlet*/
    private FacePamphletDatabase database = new FacePamphletDatabase();
    private FacePamphletProfile currentProfile = null;

}
