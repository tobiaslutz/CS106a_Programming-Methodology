/*
 * File: NameSurfer.java
 * ---------------------
 * When it is finished, this program will implements the viewer for
 * the baby-name database described in the assignment handout.
 */

import acm.program.*;
import acm.util.RandomGenerator;

import java.awt.event.*;
import java.awt.Color;
import java.awt.TextField;
import javax.swing.*;

public class NameSurfer extends Program implements NameSurferConstants {

	public static void main(String[] args) {
        new NameSurfer().start(args);
}
	
	/**
	 * Method: init()
	 * This method has the responsibility for reading in the data base
	 * and initializing the interactors at the bottom of the window.
	 */
	public void init() {
		setSize(APPLICATION_WIDTH, APPLICATION_HEIGHT);
		label = new JLabel("Name");
		name = new TextField(20);
		add(label,SOUTH);
		add(name,SOUTH);
		add(new JButton("Graph"), SOUTH);
		add(new JButton("Clear"), SOUTH);
		
		graph = new NameSurferGraph();
		add(graph);
		
		database = new NameSurferDataBase(NAMES_DATA_FILE);
		
		addActionListeners();
		name.addActionListener(this);
		
	}

/**
 * Method: actionPerformed(e)
 * Detects when content is inserted into the text field and buttons are pressed.
 * Appropriate actions like printing or erasing are initiated
 */
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == name || e.getActionCommand().equals("Graph"))	{
			if(database.findEntry(name.getText()) != null)	{
				// generate random color
				Color color = new Color(rgen.nextInt(0, 255),rgen.nextInt(0, 255),rgen.nextInt(0, 255));
				graph.addEntry(database.findEntry(name.getText()),color);
			}
			else
				println( name.getText() + " has never made it into the TOP 1000 in the 20th century. ");
		}
		else if (e.getActionCommand().equals("Clear"))	{
			graph.clear();
		}
	}
	
	/* Private instance variables */
	
	private JLabel label;
	
	private TextField name;
	
	/** stores all relevant data from an external file about names and ranks 
	in the Top 1000 since 1900*/
	private NameSurferDataBase database;
	
	/** Generates the GUI, interacts with the user and displays the data*/
	private NameSurferGraph graph;
	
	private RandomGenerator rgen = RandomGenerator.getInstance();
}
