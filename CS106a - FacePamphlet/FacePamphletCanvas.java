/*
 * File: FacePamphletCanvas.java
 * -----------------------------
 * This class represents the canvas on which the profiles in the social
 * network are displayed.  NOTE: This class does NOT need to update the
 * display when the window is resized.
 */


import acm.graphics.*;
import java.awt.*;
import java.util.*;

public class FacePamphletCanvas extends GCanvas 
					implements FacePamphletConstants {
	
	/** 
	 * Constructor
	 * This method takes care of any initialization needed for 
	 * the display
	 */
	public FacePamphletCanvas() {
		
	}

	
	/** 
	 * This method displays a message string near the bottom of the 
	 * canvas.  Every time this method is called, the previously 
	 * displayed message (if any) is replaced by the new message text 
	 * passed in.
	 */
	public void showMessage(String msg) {
		
		int width = this.getWidth();
		int height = this.getHeight();
		
		GLabel messageLabel = new GLabel(msg);
		messageLabel.setFont(MESSAGE_FONT);
		double stringWidth = messageLabel.getWidth();
		add(messageLabel,(width - stringWidth)/2,height - BOTTOM_MESSAGE_MARGIN);
	}
	
	
	/** 
	 * This method displays the given profile on the canvas.  The 
	 * canvas is first cleared of all existing items (including 
	 * messages displayed near the bottom of the screen) and then the 
	 * given profile is displayed.  The profile display includes the 
	 * name of the user from the profile, the corresponding image 
	 * (or an indication that an image does not exist), the status of
	 * the user, and a list of the user's friends in the social network.
	 */
	public void displayProfile(FacePamphletProfile profile) {
		
		this.removeAll();
		
		if (profile != null)	{
			// Add name
			double nameHeight = createLabel(profile.getName(), PROFILE_NAME_FONT, LEFT_MARGIN, TOP_MARGIN, 1, Color.BLUE);
			
			// Add image
			if (profile.getImage() == null)	{
				
				add(new GRect(LEFT_MARGIN, IMAGE_MARGIN + TOP_MARGIN + nameHeight, IMAGE_WIDTH, IMAGE_HEIGHT));
				GLabel noImage = new GLabel("No Image");
				noImage.setFont(PROFILE_IMAGE_FONT);
				double noImageWidth = noImage.getWidth();
				double noImageHeight = noImage.getHeight();
				add(noImage,LEFT_MARGIN + (IMAGE_WIDTH - noImageWidth)/2, TOP_MARGIN + IMAGE_MARGIN + nameHeight
						+ (IMAGE_HEIGHT + noImageHeight)/2);
			}
			else	{
				
				GImage image = profile.getImage();
				double width = image.getWidth();
				double height = image.getHeight();
				image.scale(IMAGE_WIDTH/width, IMAGE_HEIGHT/height);
				image.setLocation(LEFT_MARGIN, IMAGE_MARGIN + TOP_MARGIN + nameHeight);
				add(image);
			}
			
			// Add status
			if(profile.getStatus().equals(""))	
				createLabel("No current status", PROFILE_STATUS_FONT, LEFT_MARGIN, 
						TOP_MARGIN + nameHeight + IMAGE_MARGIN + IMAGE_HEIGHT + STATUS_MARGIN, 1, Color.BLACK);
			else
				createLabel(profile.getName() + " is " + profile.getStatus(), 
						PROFILE_STATUS_FONT, LEFT_MARGIN, 
						TOP_MARGIN + nameHeight + IMAGE_MARGIN + IMAGE_HEIGHT + STATUS_MARGIN, 1, Color.BLACK);
			
			// Add friends
			createLabel("Friends:", PROFILE_FRIEND_LABEL_FONT, this.getWidth()/2, 
					IMAGE_MARGIN + TOP_MARGIN + nameHeight, 0, Color.BLACK);
			Iterator<String> friendsIt = profile.getFriends();
			int c = 1;
			while (friendsIt.hasNext())	{
				
				createLabel(friendsIt.next(), PROFILE_FRIEND_FONT, this.getWidth()/2, 
						IMAGE_MARGIN + TOP_MARGIN + nameHeight, c, Color.BLACK);
				c++;
				
			}
		}
	}
	
	/** 
	 * This method creates a label with "text" that is printed in "font". The coordinates x and y 
	 * correspond to the upper left corner of the label. The parameter c is used to specify the vertical
	 * offset of the label in terms of the height of the label. 
	 * The function returns the height of the label.
	 */
	private double createLabel(String text, String font, double x, double y, int c, Color color)	{
		
		GLabel label = new GLabel(text);
		label.setFont(font);
		label.setColor(color);
		// Height of the label
		double h = label.getHeight();
		
		/** Baseline origin of the label is at (x, y + (c * h)); when enumerating the friend list, c can
		take integer values greater than 1 thereby specifying the vertical offset between the friend labels*/
		add(label, x, y + (c * h));
		
		return h;
	}
}
