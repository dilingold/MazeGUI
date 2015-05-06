package View;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Controller.MapPanelListener;
import Model.MazeEngine;
import Model.MazeRef;

public class MapPanel extends JPanel {
	
	/*instance variables for MazeEngine, ApplicationFrame, MapPanelListener
	 * and JLabel array objects */
	private MazeEngine maze;
	private ApplicationFrame frame;
	private MapPanelListener mapListener;
	private JLabel[][] labelGrid;
	
	/*instance variables for the x and y coordinates of the selected grid */
	private int clickedX;
	private int clickedY;
	
	/*instance variable for a new Grid object called source that will keep track of 
	 * selected grid */
	private Grid source;
	
	
	public MapPanel(ApplicationFrame frame) {
		
		/*instantiates a new MazeEngine, an ApplicationFrame and a
		 * MapPanelListener */
		maze = new MazeEngine(new MazeRef(8,13));
		this.frame = frame;
		mapListener = new MapPanelListener(this);
		
		/*instantiates selected grid */
		source = new Grid(0,0);
		
		/*sets up the mapPanel as a grid layout with 8 rows, 13 columns
		 * and a gap of 1 between grids */
		this.setLayout(new GridLayout(8,13,1,1));
		
		/*instantiates an array of JLabels, 8 x 13 */
		labelGrid = new JLabel[8][13];
		
		/*sets up JPanels from the Grid class for each grid in the grid
		 * layout (so 8 x 13) as Grid(i,j), and adds a JLabel[i][j] to each
		 * respectively */
		for (int i=0; i<8; i++) {
			for (int j=0; j<13; j++) {
				Grid grid = new Grid(j,i);
				labelGrid[i][j] = new JLabel();
				grid.add(labelGrid[i][j]);
				
				/*adds each grid panel to each grid in the grid layout */
				this.add(grid);
				
				/*sets each grid to white */
				grid.setBackground(Color.white);				
				
				/*adds mouse listeners to each grid, so that when it is
				 * selected, the program will know what to do */ 
				grid.addMouseListener(mapListener);
			}
		}
	}
	
	
	/*activated via mapPanelListener when user selects a grid from the map panel. It accepts
	 * parameters x and y, the coordinates of the selected grid */
	public void highlightGrid(int x,int y) {
		
		/*gets the Grid object of the selected grid and saves it in Grid source */
		source = mapListener.getSource();
		
		/*saves the selected grid's x and y coordinates in clickedX and clickedY */
		clickedX = x;
		clickedY = y;		
		
		/*sets the selected grid to the color gray */
		source.setBackground(Color.gray);			
	}
	
	
	/*activatd via mapPanelListener when user selects a grid from the map panel. It
	 * acceptes parameters of Grid, x and y of the last selected grid, and makes
	 * appropriate changes to it */
	public void resetPreviousGrid(Grid prevGrid,int x,int y) {
		
		/*checks if the last selected grid is an existing room */
		if (maze.roomExists(new MazeRef(x,y))) {
			
			/*checks if the currently selected grid is the same as the last
			 * selected grid (i.e. the user selected the same grid twice in a row) */
			if (prevGrid.equals(source)) {
				
				/*if the last selected grid is also the currently selected grid, set
				 * the color to gray */
				prevGrid.setBackground(Color.gray);
			}
			
			/*otherwise (if the there is an existing room a the last selected grid, and 
			 * it is not the currently selected grid), set the background to red */
			else {
				prevGrid.setBackground(Color.red);
			}
			
			/*checks if a start room is set */
			if (maze.isStartRoomSet()) {
			
				/*checks if the previously selected grid is the start room */
				if (maze.getStartPoint().equals(new MazeRef(x,y))) {
				
					/*if the previously selected grid is the start room, and is the
					 * currently selected grid (i.e. the user selected the same grid
					 * twice in a row), set the background to gray */
					if (prevGrid.equals(source)) {
						prevGrid.setBackground(Color.gray);
					}
					
					/*otherwise (if the previously selected grid is the start room, but not
					 * the currently selected grid), mark an 'S' on the grid */
					else {
						labelGrid[y][x].setText("S");
					}
				}
			}
			
			/*checks if a finish room is set */
			if (maze.isFinishRoomSet()) {
			
				/*checks if the previously selected grid is the finish room */
				if (maze.getFinishPoint().equals(new MazeRef(x,y))) {
					
					/*if the previously selected grid is the finish room, and is the
					 * currently selected grid (i.e. the user selected the same grid
					 * twice in a row), set the background to gray */
					if (prevGrid.equals(source)) {
						prevGrid.setBackground(Color.gray);
					}
					
					/*otherwise (if the previously selected grid is the finish room, but not
					 * the currently selected grid), mark an 'F' on the grid */
					else {
						labelGrid[y][x].setText("F");
					}
				}
			}
		}
		
		/*if the previous room is not an existing room */
		else {
			
			/*if the previously selected grid is the currently selected grid (i.e. the user
			 * selected the same grid twice in a row), set the background to gray */
			if (prevGrid.equals(source)) {
				prevGrid.setBackground(Color.gray);
			}
			
			/*otherwise, set the previously selected grid back to white */
			else {
				prevGrid.setBackground(Color.white);
			}
		}
	}
	
	
	/*activated via MapPanelListener when user selects a grid from the map panel.
	 * Checks the status of a room and then enables / disables menu items
	 * and button panel options appropriately. It accepts parameters x and y
	 * of the currently selected grid */
	public void checkRoomStatus(int x,int y) {
		
		/*sets a boolean 'exists' to true if a room exists at the currently selected
		 * grid, and false if a room does not exist */
		boolean exists = maze.roomExists(new MazeRef(x,y));
		
		/*perform the following if a room does exist... */
		if (exists) {
			
			/*...disable the menu item and button option 'Add Room' */
			frame.getMazeMenuBar().getAddRoom().setEnabled(false);
			frame.getButtonPanel().getAddRoom().setEnabled(false);
			
			/*...checks if a start room is yet set */
			if (maze.isStartRoomSet()) {
				
				/*if a start room is set, disable the 'Set Start Room' menu item */
				frame.getMazeMenuBar().getStartRoom().setEnabled(false);
				
				/*if the currently selected grid is the start room, also disable the 
				 * 'Set Finish Room' menu item, so that a user can not try to set the same room
				 * as start and finish room */
				if (maze.getStartPoint().equals(new MazeRef(x,y))) {
					frame.getMazeMenuBar().getFinishRoom().setEnabled(false);
				}
				
				/*otherwise (if a start room is set, but is not the currently selected grid), enable the 
				 * 'Set Finish Room' menu item */
				else {
					frame.getMazeMenuBar().getFinishRoom().setEnabled(true);
				}
			}
			
			/*...if the start room is not set, enable to 'Set Start Room' menu item */
			else {
				frame.getMazeMenuBar().getStartRoom().setEnabled(true);
			
				/*checks if the finish room is set */
				if (maze.isFinishRoomSet()) {
					
					/*if the finish room is set, and is the currently selected grid, disable
					 * 'Set Start Room' menu item so that a user can not try to set the same room
				 * as start and finish room */
					if (maze.getFinishPoint().equals(new MazeRef(x,y))) {
						frame.getMazeMenuBar().getStartRoom().setEnabled(false);
					}
				}
			}
			
			/*...if the finish room is set, disable 'Set Finish Room' from menu item */
			if (maze.isFinishRoomSet()) {
				frame.getMazeMenuBar().getFinishRoom().setEnabled(false);
			}
			
			/*otherwise (if the finish room is not set) */
			else {
				
				/*checks if the start room is set */
				if (maze.isStartRoomSet()) {
					
					/*if the start room is set and is the currently selected grid, disable
					 * 'Set Finish Room' from menu item */
					if (maze.getStartPoint().equals(new MazeRef(x,y))) {
						frame.getMazeMenuBar().getFinishRoom().setEnabled(false);
					}
				}
				
				/*if neither finish room nor start room are set, enable 'Set Finish Room' */
				else {
					frame.getMazeMenuBar().getFinishRoom().setEnabled(true);
					}
			}
		}
		
		/*if the currently selected grid is not an existing room, disable all buttons and 
		 * menu items except 'Add Room' */
		else {
			frame.getMazeMenuBar().getAddRoom().setEnabled(true);
			frame.getButtonPanel().getAddRoom().setEnabled(true);
			frame.getMazeMenuBar().getStartRoom().setEnabled(false);
			frame.getMazeMenuBar().getFinishRoom().setEnabled(false);
			frame.getMazeMenuBar().getAddItem().setEnabled(false);
			frame.getButtonPanel().getAddItem().setEnabled(false);
			frame.getMazeMenuBar().getRemoveItem().setEnabled(false);
			frame.getButtonPanel().getRemoveItem().setEnabled(false);
			frame.getMazeMenuBar().getAddExit().setEnabled(false);
			frame.getButtonPanel().getAddExit().setEnabled(false);
			frame.getMazeMenuBar().getRemoveExit().setEnabled(false);
			frame.getButtonPanel().getRemoveExit().setEnabled(false);
		}		
	}
	
	
	/*activated via MapPanelListener when user selects a grid from the map panel.
	 * Checks the status of a room and item and then enables / disables menu items
	 * and button panel options appropriately. It accepts parameters x and y
	 * of the currently selected grid */
	public void checkRoomItem(int x,int y) {
		
		/*boolean 'itemExists' is true if the currently selected grid contains an item, 
		 * and false if it does not */
		boolean itemExists = maze.checkItemCount(new MazeRef(x,y));
		
		/*checks if a room exists at currently selected grid */
		if (maze.roomExists(new MazeRef(x,y))) {
			
			/*if a room does exist at currently selected grid and an item is already added
			 * to that room, disable the 'Add Item' menu item and button option, and enable
			 * the 'Remove Item' menu item and button option */
			if (itemExists) {
				frame.getMazeMenuBar().getAddItem().setEnabled(false);
				frame.getButtonPanel().getAddItem().setEnabled(false);
				frame.getMazeMenuBar().getRemoveItem().setEnabled(true);
				frame.getButtonPanel().getRemoveItem().setEnabled(true);	
			}
		
			/*if a room does exist and currently selected grid and no item is currently in the room,
			 * enable 'Add Item' menu item and button option, and enable the 'Remove Item' menu item
			 * and button option */
			else {
				frame.getMazeMenuBar().getAddItem().setEnabled(true);
				frame.getButtonPanel().getAddItem().setEnabled(true);
				frame.getMazeMenuBar().getRemoveItem().setEnabled(false);
				frame.getButtonPanel().getRemoveItem().setEnabled(false);	
			}						
		}		
	}
	
	
	/*activated via MapPanelListener when user selects a grid from the map panel.
	 * Checks the status of a room and exit points and then enables / disables menu items
	 * and button panel options appropriately. It accepts parameters x and y
	 * of the currently selected grid */
	public void checkRoomExit(int x,int y) {
		
		/*boolean 'exitExists' is true if the currently selected grid contains an exit, 
		 * and false if it does not */
		boolean exitExists = maze.checkExitExists(new MazeRef(x,y));
		
		/*checks if a room exists at currently selected grid */
		if (maze.roomExists(new MazeRef(x,y))) {
			
			/*if a room does exist at currently selected grid enable the 'Add Exit' 
			 * menu item and button option */
			frame.getMazeMenuBar().getAddExit().setEnabled(true);
			frame.getButtonPanel().getAddExit().setEnabled(true);
			
			/*if a room does exist at currently selected grid and an exit is already added
			 * to that room, enable the 'Remove Exit' menu item and button option */
			if (exitExists) {
				frame.getMazeMenuBar().getRemoveExit().setEnabled(true);
				frame.getButtonPanel().getRemoveExit().setEnabled(true);		
			}
		
			/*if a room does exist at currently selected grid and no exit is yet added
			 * to that room, disable the 'Remove Exit' menu item and button option */
			else {
				frame.getMazeMenuBar().getRemoveExit().setEnabled(false);
				frame.getButtonPanel().getRemoveExit().setEnabled(false);	
			}						
		}		
	}
	
	
	/*activated via MapPanelListener when user selects a grid from the map panel. It 
	 * accepts parameters x and y of the selected grid, and then displays information about
	 * that grid via the InformationPanel */
	public void changeInformationDisplay(int x,int y) {
		frame.getInfoPanel().changeDisplay(x,y);		
	}
	
	
	/*returns MazeEngine object of current maze */
	public MazeEngine getMaze() {
		return maze;
	}
	
	
	/*returns JLabel object of the currently selected grid */
	public JLabel getClickedLabel() {		
		return labelGrid[clickedX][clickedY];		
	}	
	
	
	/*returns Grid object of the currently selected grid */
	public Grid getSource() {
		return source;
	}
	
	
	/*returns int of currently selected x coordinate */
	public int getClickedX() {
		return clickedX;
	}
	
	
	/*returns int of currently selected y coordinate */
	public int getClickedY() {
		return clickedY;
	}
}	