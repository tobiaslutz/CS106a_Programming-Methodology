/*
 * File: NameSurferGraph.java
 * ---------------------------
 * This class represents the canvas on which the graph of
 * names is drawn. This class is responsible for updating
 * (redrawing) the graphs whenever the list of entries changes or the window is resized.
 */

import acm.graphics.*;
import acm.util.RandomGenerator;

import java.awt.event.*;
import java.util.*;
import java.awt.*;

public class NameSurferGraph extends GCanvas
	implements NameSurferConstants, ComponentListener {

	/**
	* Constructor: NameSurferGraph()
	* Creates a new NameSurferGraph object that displays the data.
	*/
	public NameSurferGraph() {
		addComponentListener(this);
		drawGrid();
		
	}
	
	/**
	* Method: clear()
	* Clears the list of name surfer entries stored inside this class.
	*/
	public void clear() {
		entries.clear();
		colors.clear();
		removeAll();
		drawGrid();
	}
	
	/**
	* Method: addEntry(NameSurferEntry entry, Color color)
	* Adds a new NameSurferEntry to the list of entries on the display.
	* Note that this method does not actually draw the graph, but
	* simply stores the entry; the graph is drawn by calling update.
	*/
	public void addEntry(NameSurferEntry entry, Color color) {
		entries.add(entry);
		colors.add(color);
		update();
	}
	
	
	
	/**
	* Method: upate()
	* Updates the display image by deleting all the graphical objects
	* from the canvas and then reassembling the display according to
	* the list of entries. Your application must call update after
	* calling either clear or addEntry; update is also called whenever
	* the size of the canvas changes.
	*/
	public void update() {
		removeAll();
		drawGrid();	
		drawNames();
	}
	
	/**
	* Method: drawGrid()
	* Draws all horizontal and vertical lines on the GUI. Moreover, prints the name of the decades
	* in an equally spaced manner at the bottom of the GUI.
	*/
	private void drawGrid()	{
		
		// width of a single decade
		xOffset = getWidth() / NDECADES;
		for (int i = 0; i < NDECADES; i++)	{
			xCoord = i * xOffset + 1;
			decade = 1900 + i * 10;
			
			// vertical lines
			add(new GLine(xCoord, 0, xCoord, getHeight()));
			add(new GLabel("" + decade, xCoord + 3, getHeight() - (GRAPH_MARGIN_SIZE / 3)));
		}
		
		// horizontal lines
		add(new GLine(1, GRAPH_MARGIN_SIZE, getWidth(), GRAPH_MARGIN_SIZE));
		add(new GLine(1, getHeight() - GRAPH_MARGIN_SIZE, getWidth(), 
				getHeight() - GRAPH_MARGIN_SIZE));
		
	}
	
	/**
	* Method: drawNames()
	* Prints a graph for each name provided by the user. 
	*/
	private void drawNames()	{
		
		// vertical spacing between two consecutive ranks
		yOffset = ((double)getHeight() - 2 * (double)GRAPH_MARGIN_SIZE) / (double)MAX_RANK 
				- 5 / MAX_RANK; //last term compensates for the term "+5" in line 116

		// prints a graph for each entry in "entries"
		for (int i = 0; i < entries.size(); i++)	{
			for (int j = 0; j < NDECADES; j++)	{
				xCoord = j * xOffset + 3;
				// Name is not in Top 1000 for the decade corresponding to j
				if(entries.get(i).getRank(j+1) == 0)	{
					yCoord = getHeight() - GRAPH_MARGIN_SIZE - 3;
					GLabel name = new GLabel(entries.get(i).getName() + "*", xCoord, yCoord);
					name.setColor(colors.get(i));
					add(name);
				}
				// Name is in Top 1000 for the decade corresponding to j
				else {
					yCoord = yOffset * entries.get(i).getRank(j+1) + GRAPH_MARGIN_SIZE + 5;
					GLabel name = new GLabel(entries.get(i).getName() + " " + entries.get(i).getRank(j+1), 
							xCoord, yCoord);
					name.setColor(colors.get(i));
					add(name);
				}
				if (j > 0) 	{
					// Connects the baseline origins of the previous and current label
					GLine line = new GLine(xCoordPrev, yCoordPrev, xCoord, yCoord);
					line.setColor(colors.get(i));
					add(line);
				}
				yCoordPrev = yCoord;
				xCoordPrev = xCoord;			
			}
		}
	}
	
	/* Implementation of the ComponentListener interface */
	public void componentHidden(ComponentEvent e) { }
	public void componentMoved(ComponentEvent e) { }
	public void componentResized(ComponentEvent e) { update(); }
	public void componentShown(ComponentEvent e) { }
	
	// Private instance variables
	
	/** Horizontal space on the GUI provided for a single decade */
	private int xOffset;
	
	/** Vertical spacing between two consecutive ranks */
	private double yOffset;
	
	/** x-coordinate of the baseline origin of a label holding name and rank of a certain decade*/
	private int xCoord;
	
	/**	x-coordinate of the baseline origin of the previously printed label. The assigned value of 
	1 corresponds to the x-coordinate of the very first label to be printed */
	private int xCoordPrev = 1;
	
	/** y-coordinate of the baseline origin of a label holding name and rank of a certain decade */
	private double yCoord;
	
	/**	y-coordinate of the baseline origin of the previously printed label. The assigned value of 
	1 corresponds to the y-coordinate of the very first label to be printed */
	private double yCoordPrev = 1;
	
	/** Stores the current decade to be printed on the time axis*/
	private int decade;
	
	/** The ArrayList "entries" stores all NameSurferEntry(s) that correspond to names provided by the user;
	"entries" serves as basis for printing the graphs */
	private ArrayList<NameSurferEntry> entries = new ArrayList<NameSurferEntry>();
	
	/** The ArrayList "entries" stores the color in which the corresponding entry in "entries" 
	will be printed */
	private ArrayList<Color> colors = new ArrayList<Color>();
}
