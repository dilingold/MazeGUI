package View;

import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import Model.ExitPoint;
import Model.Item;
import Model.MazeRef;

public class InformationPanel extends JPanel {
	
	/*instance variable for Application Frame object */
	private ApplicationFrame frame;
	
	/*instance variables for JLabels to input selected grid
	 * information */
	private JLabel selectedCoord;
	private JLabel roomName;
	private JLabel item;
	private JLabel exits;
	
	private JLabel coordInput;
	private JLabel roomInput;
	private JLabel itemInput;
	private JLabel exitInput;
	
	
	/*constructor for InformationPanel object, accepts parameter
	 * of ApplicationFrame */
	public InformationPanel(ApplicationFrame frame) {
		
		/*instantiate ApplicationFrame object 'frame' */
		this.frame = frame;
		
		/*instantiates JLabels to display selected grid information */
		selectedCoord = new JLabel("Selected Coord: ");
		roomName = new JLabel("Room Name: ");
		item = new JLabel("Item: ");
		exits = new JLabel("Exit(s): ");
		
		coordInput = new JLabel();
		roomInput = new JLabel();
		itemInput = new JLabel();
		exitInput = new JLabel();
		
		/*set font style and size to JLabel text */
		selectedCoord.setFont(new Font("Sans Serif",Font.BOLD,16));
		roomName.setFont(new Font("Sans Serif",Font.BOLD,16));
		item.setFont(new Font("Sans Serif",Font.BOLD,16));
		exits.setFont(new Font("Sans Serif",Font.BOLD,16));
		
		coordInput.setFont(new Font("Sans Serif",Font.PLAIN,16));
		roomInput.setFont(new Font("Sans Serif",Font.PLAIN,16));
		itemInput.setFont(new Font("Sans Serif",Font.PLAIN,16));
		exitInput.setFont(new Font("Sans Serif",Font.PLAIN,16));
		
		/*set layout to the InformationPanel as grid layout containing
		 * 2 rows and 4 columns */
		this.setLayout(new GridLayout(2,4));
		
		/*add JLabels to InformationPanel layout */
		this.add(selectedCoord);
		this.add(coordInput);
		
		this.add(item);
		this.add(itemInput);
		
		this.add(roomName);
		this.add(roomInput);
		
		this.add(exits);
		this.add(exitInput);
	}
	
	
	/*changes the display of the InformationPanel to display information on the
	 * selected grid. Accepts parameters X and Y of selected grid. */
	public void changeDisplay(int x,int y) {
		
		/*coordInput JLabel will display the X and Y coordinates in the format x,y */
		coordInput.setText(x+","+y);
		
		/*boolean 'roomExists' is true if a room exists in currently selected grid, and false 
		 * if one does not */
		boolean roomExists = frame.getMapPanel().getMaze().roomExists(new MazeRef(x,y));

		/*if a room does exist, display the name of the room in the JLabel roomInput, and then check
		 * if an item or exit exists in that room, and assign the boolean to 'itemExists' and 'exitExists'
		 * respectively */
		if (roomExists) {			
			roomInput.setText(frame.getMapPanel().getMaze().getRoom(new MazeRef(x,y)).getName());			

			boolean itemExists = frame.getMapPanel().getMaze().checkItemCount(new MazeRef(x,y));			
			boolean exitExists = frame.getMapPanel().getMaze().checkExitExists(new MazeRef(x,y));
			
			/*if an item exists in the existing room of the selected grid, get the name and health of the 
			 * item and display it in the JLabel itemInput in the format "item (health)" */
			if (itemExists) {				
				Item[] item = frame.getMapPanel().getMaze().getRoom(new MazeRef(x,y)).getItemList();				
				String itemName = item[0].getName();				
				int itemHealth = frame.getMapPanel().getMaze().getRoom(new MazeRef(x,y)).getItem(itemName).getHealth();
				
				itemInput.setText(itemName+" ("+itemHealth+")");				
			}
			
			/*if no item exists in the existing room of the selected grid, display an empty string */
			else {				
				itemInput.setText(" ");				
			}
			
			/*if exit(s) exists in the existing room of the selected grid, get the name of the 
			 * exit(s) and display it in the JLabel exitInput in the format "exit1, exit2, ..." */
			if (exitExists) {				
				ExitPoint[] exit = frame.getMapPanel().getMaze().getRoom(new MazeRef(x,y)).getExitList();				
				String exitString = exit[0].getName();
				
				for (int i=1; i<exit.length; i++) {					
					exitString = exitString + ", " + exit[i].getName();					
				}				
				exitInput.setText(exitString);				
			}
			
			/*if no exit exists in the existing room of the selected grid, display an empty string */
			else {				
				exitInput.setText(" ");				
			}
		}
		
		/*if no room exists in the selected grid, display empty strings for roomInput, itemInput and exitInput
		 * JLabels */
		else {			
			roomInput.setText(" ");
			itemInput.setText(" ");
			exitInput.setText(" ");			
		}				
	}
}
