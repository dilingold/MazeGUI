package View;

import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import Controller.MenuBarListener;
import Model.Item;
import Model.MazeRef;

public class MazeMenuBar extends JMenuBar {
	
	/*instance variable for the application frame */
	private ApplicationFrame frame;
	
	/*instance variables for the Menu Bar */
	private JMenu maze;
	private JMenu room;
	private JMenu item;
	private JMenu exitPoint;
	
	/*instance variables for the Menu Bar Items */
	private JMenuItem exit;
	private JMenuItem startRoom;
	private JMenuItem finishRoom;
	private JMenuItem addRoom;
	private JMenuItem addItem;
	private JMenuItem removeItem;
	private JMenuItem addExit;
	private JMenuItem removeExit;
	
	/*instance variable for Menu Bar Listener */
	private MenuBarListener menuListener;
	
	
	/*constructor for Menu Bar object, takes parameter application frame object  */
	public MazeMenuBar(final ApplicationFrame frame) {
		
		/*instantiate the instance variables */
		this.frame = frame;
		menuListener = new MenuBarListener(this);
		
		maze = new JMenu("Maze");
		room = new JMenu("Room");
		item = new JMenu("Item");
		exitPoint = new JMenu("Exit Point");
		
		/*adds a key event to menu bar and menu bar items, so user can have access
		 * via keyboard */
		maze.setMnemonic(KeyEvent.VK_M);
		room.setMnemonic(KeyEvent.VK_R);
		item.setMnemonic(KeyEvent.VK_I);
		exitPoint.setMnemonic(KeyEvent.VK_E);
		
		exit = new JMenuItem("Exit Maze",KeyEvent.VK_X);
		startRoom = new JMenuItem("Set Start Room",KeyEvent.VK_S);
		finishRoom = new JMenuItem("Set Finish Room",KeyEvent.VK_F);
		addRoom = new JMenuItem("Add Room",KeyEvent.VK_A);
		addItem = new JMenuItem("Add Item",KeyEvent.VK_A);
		removeItem = new JMenuItem("Remove Item",KeyEvent.VK_R);
		addExit = new JMenuItem("Add Exit",KeyEvent.VK_E);
		removeExit = new JMenuItem("Remove Exit",KeyEvent.VK_R);
		
		/*adds menu bar items to the appropriate menu header */
		maze.add(startRoom);
		maze.add(finishRoom);
		maze.add(exit);
		room.add(addRoom);
		item.add(addItem);
		item.add(removeItem);
		exitPoint.add(addExit);
		exitPoint.add(removeExit);
		
		/*adds menu headers to menu bar */
		this.add(maze);
		this.add(room);
		this.add(item);
		this.add(exitPoint);
		
		/*initially set all menu bar items (except exit) to disabled */ 
		startRoom.setEnabled(false);
		finishRoom.setEnabled(false);
		addRoom.setEnabled(false);
		addItem.setEnabled(false);
		removeItem.setEnabled(false);
		addExit.setEnabled(false);
		removeExit.setEnabled(false);
		
		/*add action listeners to all menu bar items, so that when it is
		 * selected, the program will know what to do */
		exit.addActionListener(menuListener);
		startRoom.addActionListener(menuListener);
		finishRoom.addActionListener(menuListener);
		addRoom.addActionListener(menuListener);
		addItem.addActionListener(menuListener);
		removeItem.addActionListener(menuListener);
		addExit.addActionListener(menuListener);
		removeExit.addActionListener(menuListener);
	}
	
	
	/*activated via menuListener, when user selects "Exit Maze" from 
	 * menu bar, application closes */
	public void exitMaze() {
		System.exit(0);
	}
	
	
	/*activated via menuListener when user selects "Set Start Room" from 
	 * menu bar */
	public void setStartRoom() {
		
		/*gets the selected coordinate from the MapPanel */
		int clickedX = frame.getMapPanel().getClickedX();
		int clickedY = frame.getMapPanel().getClickedY();
		
		/*sets the selected coordinate as the start room of the maze */
		frame.getMapPanel().getMaze().setStartPoint(new MazeRef(clickedX,clickedY));		
	}
	
	
	/*activated via menuListener when user selects "Set Finish Room" from 
	 * menu bar */
	public void setFinishRoom() {
		
		/*gets the selected coordinate from the MapPanel */
		int clickedX = frame.getMapPanel().getClickedX();
		int clickedY = frame.getMapPanel().getClickedY();
		
		/*sets the selected coordinate as the start room of the maze */
		frame.getMapPanel().getMaze().setFinishPoint(new MazeRef(clickedX,clickedY));
	}
	
	
	/*activated via menuListener when user selects "Add Room" from menu bar */
	public void addRoom() {
		
		/*gets the selected coordinates from the MapPanel */
		int clickedX = frame.getMapPanel().getClickedX();
		int clickedY = frame.getMapPanel().getClickedY();
		
		/*a message dialog appears and asks the user to enter a room name, and then
		 * stores that name in a string 'name' */
		String name = JOptionPane.showInputDialog(frame,"Enter Room Name");
		
		/* if input is null, or user selects cancel, do nothing, otherwise create a new room 
		 * at the selected coordinates, giving it the input 'name' */
		if (name != null && name.length() > 0) {
			
			frame.getMapPanel().getMaze().createRoom(new MazeRef(clickedX,clickedY),name);	
		
		}
	}
	
	
	/*activated via menuListener when user selects "Add Item" from menu bar */
	public void addItem() {
		
		/*gets the selected coordinates from the MapPanel */
		int clickedX = frame.getMapPanel().getClickedX();
		int clickedY = frame.getMapPanel().getClickedY();
		
		/*creates a number spinner called 'healthSpinner' which will be used in a message dialog */
		SpinnerNumberModel healthModel = new SpinnerNumberModel(0,-10,10,1);
		JSpinner healthSpinner = new JSpinner(healthModel);
		
		/*creates an array of objects to add to a message dialog, including text, the text field 'nameInput'
		 * and the number spinner 'healthSpinner' */
		Object[] message = {"Enter Item Name and Health Value",healthSpinner};
		
		/*a message dialog appears with the array of objects created above, asking for user to input
		 * a name and health value of new item, and saving the input in 'name' and 'health' respectively */
		String name = JOptionPane.showInputDialog(frame,message);
		int health = (int)healthSpinner.getValue();
		
		/* if input is null do nothing, otherwise adds a new item into selected coordinates, 
		 * assigning it the input name and health value */
		if (name != null && name.length() > 0) {											
			frame.getMapPanel().getMaze().addItem(new MazeRef(clickedX,clickedY), name, health);			
		}	
	}
	
	
	/*activated via menuListener when user selects "Remove Item" from menu bar */
	public void removeItem() {
		
		/*gets the selected coordinates from the MapPanel */
		int clickedX = frame.getMapPanel().getClickedX();
		int clickedY = frame.getMapPanel().getClickedY();
		
		/*gets the name of the item from the selected room */
		Item[] item = frame.getMapPanel().getMaze().getRoom(new MazeRef(clickedX,clickedY)).getItemList();
		String itemName = item[0].getName();
		
		/*removes the item from the selected room */
		frame.getMapPanel().getMaze().removeItem(new MazeRef(clickedX,clickedY), itemName);
	}
	
	
	/*activated via menuListener when user selects "Add Exit" from menu bar */
	public void addExit() {
		
		/*gets the selected coordinates from the MapPanel */
		int clickedX = frame.getMapPanel().getClickedX();
		int clickedY = frame.getMapPanel().getClickedY();
		
		/*creates a number spinner called 'destinationXSpinner' which will be used in a message dialog */
		SpinnerNumberModel destinationXModel = new SpinnerNumberModel(0,0,12,1);
		JSpinner destinationXSpinner = new JSpinner(destinationXModel);
		
		/*creates a number spinner called 'destinationYSpinner' which will be used in a message dialog */
		SpinnerNumberModel destinationYModel = new SpinnerNumberModel(0,0,7,1);
		JSpinner destinationYSpinner = new JSpinner(destinationYModel);
		
		/*creates an array of objects to add to a message dialog, including text, the text field 'nameInput'
		 * and the number spinners 'destinationXSpinner' and 'destinationYSpinner' */
		Object[] message = {"Desination Room X Coordinate",destinationXSpinner,
				"Destination Room Y Coordinate",destinationYSpinner,"Enter Exit Point Name"};
		
		/*a message dialog appears with the array of objects created above, asking for user to input
		 * a name and coordinate of exit room, and saving the input in 'name', 'destinationX' and 'destinationY'
		 * respectively */
		String name = JOptionPane.showInputDialog(frame,message);
		int destinationX = (int)destinationXSpinner.getValue();
		int destinationY = (int)destinationYSpinner.getValue();
		
		/* if name input is null do nothing */
		if (name != null && name.length() > 0) {
			
			/*checks if a room exists at the user input destination coordinates. */
			if (frame.getMapPanel().getMaze().roomExists(new MazeRef(destinationX,destinationY))) {
			
				/*checks if the destination coordinates is actually the selected room. If it is,
				 * a message appears that the destination coordinates entered are not valid */
				if (clickedX == destinationX && clickedY == destinationY) {
					JOptionPane.showMessageDialog(frame,"The destination coordinates you have entered is not valid");
				}
			
				/*otherwise, if the destination coordinates is another room that does exist, an exit point is
				 * added to that selected room, with the input destination coordinates */
				else {
					frame.getMapPanel().getMaze().addExitPoint(new MazeRef(clickedX,clickedY), name, new MazeRef(destinationX,destinationY));
				}
			}
		
			/*if the destination coordinates entered is not a valid room, a message appears telling the user that it is 
			 * not valid. */
			else {
				JOptionPane.showMessageDialog(frame,"The destination coordinates you have entered is not a valid room");
			}
		}
	}
	
	
	/*activated via menuListener when user selects "Remove Exit" from menu bar */
	public void removeExit() {
		
		/*gets the selected coordinates from the MapPanel */
		int clickedX = frame.getMapPanel().getClickedX();
		int clickedY = frame.getMapPanel().getClickedY();
		
		/*a message dialog appears and asks the user to enter an exit point name, and then
		 * stores that name in a string 'name' */
		String name = JOptionPane.showInputDialog(frame,"Enter the name of the Exit Room you would like to remove.");
		
		/*removes user input exit point from selected room */
		frame.getMapPanel().getMaze().removeExitPoint(new MazeRef(clickedX,clickedY), name);
	}
	
	
	/*returns menu item start room */
	public JMenuItem getExitMaze() {
		return exit;
	}
	
	
	/*returns menu item start room */
	public JMenuItem getStartRoom() {
		return startRoom;
	}
	
	
	/*returns menu item finish room */
	public JMenuItem getFinishRoom() {
		return finishRoom;
	}
	
	
	/*returns menu item add room */
	public JMenuItem getAddRoom() {
		return addRoom;
	}
	
	
	/*returns menu item add item */
	public JMenuItem getAddItem() {
		return addItem;
	}
	
	
	/*returns menu item remove item */
	public JMenuItem getRemoveItem() {
		return removeItem;
	}
	
	
	/*returns menu item add exit */
	public JMenuItem getAddExit() {
		return addExit;
	}
	
	
	/*returns menu item remove exit */
	public JMenuItem getRemoveExit() {
		return removeExit;
	}
	
}