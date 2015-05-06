package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

import View.MazeMenuBar;

public class MenuBarListener implements ActionListener {
	
	/*instance variable for object MazeMenuBar */
	private MazeMenuBar menu;
	
	
	/*constructor for MenuBarListener object, takes parameter MazeMenuBar object*/
	public MenuBarListener(MazeMenuBar menu) {
		
		/*instantiates MazeMenuBar object menu */
		this.menu = menu;
	}

	
	/*set up to activate from MazeMenuBar when a menu item is selected */
	@Override
	public void actionPerformed(ActionEvent e) {

		/*retrieves the menu item that was selected, and saves it in
		 * JMenuItem source */
		JMenuItem source = (JMenuItem)e.getSource();
		
		/*checks if the menu item selected is exit maze. If it is,
		 * it proceeds to exitMaze() method in MazeMenuBar */
		if (source == menu.getExitMaze()) {
			menu.exitMaze();
		}
		
		/*checks if the menu item selected is set start room. If it is,
		 * it proceeds to setStartRoom() method in MazeMenuBar */
		if (source==menu.getStartRoom()) {
			menu.setStartRoom();
		}
		
		/*checks if the menu item selected is set finish room. If it is,
		 * it proceeds to setFinishRoom() method in MazeMenuBar */
		if (source == menu.getFinishRoom()) {
			menu.setFinishRoom();
		}
		
		/*checks if the menu item selected is add room. If it is,
		 * it proceeds to addRoom() method in MazeMenuBar */
		if (source == menu.getAddRoom()) {
			menu.addRoom();
		}
		
		/*checks if the menu item selected is add item. If it is,
		 * it proceeds to addItem() method in MazeMenuBar */
		if (source == menu.getAddItem()) {
			menu.addItem();
		}

		/*checks if the menu item selected is remove item. If it is,
		 * it proceeds to removeItem() method in MazeMenuBar */
		if (source == menu.getRemoveItem()) {	
			menu.removeItem();
		}
		
		/*checks if the menu item selected is add exit. If it is,
		 * it proceeds to addExit() method in MazeMenuBar */
		if (source == menu.getAddExit()) {
			menu.addExit();
		}
		
		/*checks if the menu item selected is remove exit. If it is,
		 * it proceeds to removeExit() method in MazeMenuBar */
		if (source == menu.getRemoveExit()) {
			menu.removeExit();
		}
	}
}
