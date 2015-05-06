package View;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import Controller.ButtonPanelListener;
import Model.Item;
import Model.MazeRef;

public class ButtonPanel extends JPanel {
	
	/*instance variable for the application frame */
	private ApplicationFrame frame;
	
	/*instance variables for JButtons on the button panel */
	private JButton addRoom;
	private JButton addItem;
	private JButton removeItem;
	private JButton addExit;
	private JButton removeExit;
	
	/*instance variable for Button Panel Listener */
	private ButtonPanelListener buttonListener;
	
	
	/*constructor for ButtonPanel object, takes parameter application frame object  */
	public ButtonPanel(ApplicationFrame frame) {
		
		/*instantiate the instance variables */
		this.frame = frame;
		
		addRoom = new JButton("Add Room");
		addItem = new JButton("Add Item");
		removeItem = new JButton("Remove Item");
		addExit = new JButton("Add Exit");
		removeExit = new JButton("Remove Exit");
		
		buttonListener = new ButtonPanelListener(this);
		
		/*sets preferred size of the buttons */
		addRoom.setPreferredSize(new Dimension(150,30));
		addItem.setPreferredSize(new Dimension(150,30));
		removeItem.setPreferredSize(new Dimension(150,30));
		addExit.setPreferredSize(new Dimension(150,30));
		removeExit.setPreferredSize(new Dimension(150,30));
		
		/*sets button panel to flow layout */
		this.setLayout(new FlowLayout());
		
		/*adds buttons to the button panel */
		this.add(addRoom);
		this.add(addItem);
		this.add(removeItem);
		this.add(addExit);
		this.add(removeExit);
		
		/*initially disables all buttons */
		addRoom.setEnabled(false);
		addItem.setEnabled(false);
		removeItem.setEnabled(false);
		addExit.setEnabled(false);
		removeExit.setEnabled(false);
		
		/*add action listeners to all buttons, so that when it is
		 * selected, the program will know what to do */
		addRoom.addActionListener(buttonListener);
		addItem.addActionListener(buttonListener);
		removeItem.addActionListener(buttonListener);
		addExit.addActionListener(buttonListener);
		removeExit.addActionListener(buttonListener);				
	}

	
	/*activated via buttonListener when user selects "Add Room" button */
	public void addRoom() {
		
		/*gets the selected coordinates from the MapPanel */
		int clickedX = frame.getMapPanel().getClickedX();
		int clickedY = frame.getMapPanel().getClickedY();
		
		/*a message bar appears and asks the user to enter a room name, and then
		 * stores that name in a string 'name' */
		String name = JOptionPane.showInputDialog(frame,"Enter Room Name");
		
		/* if input is null, or user selects cancel, do nothing, otherwise create a new room 
		 * at the selected coordinates, giving it the input 'name' */
		if (name != null && name.length() > 0) {
			
			frame.getMapPanel().getMaze().createRoom(new MazeRef(clickedX,clickedY),name);			
		}					
	}

	
	/*activated via buttonListener when user selects "Add Item" button */
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
	
	/*activated via buttonListener when user selects "Remove Item" button */
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
	
	
	/*activated via buttonListener when user selects "Add Exit" button */
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
	
	
	/*activated via buttonListener when user selects "Remove Exit" button */
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

	
	/*returns JButton addRoom */
	public JButton getAddRoom() {
		return addRoom;
	}
	
	
	/*returns JButton addItem */
	public JButton getAddItem() {
		return addItem;
	}
	
	
	/*returns JButton removeItem */
	public JButton getRemoveItem() {
		return removeItem;
	}
	
	
	/*returns JButton addExit */
	public JButton getAddExit() {
		return addExit;
	}
	
	
	/*returns JButton removeExit */
	public JButton getRemoveExit() {
		return removeExit;
	}
}

