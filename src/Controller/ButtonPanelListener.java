package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import View.ButtonPanel;

public class ButtonPanelListener implements ActionListener {
	
	/*instance variable for object ButtonPanel */
	private ButtonPanel button;

	
	/*constructor for ButtonPanelListener object, takes parameter ButtonPanel object*/
	public ButtonPanelListener(ButtonPanel button) {
		
		/*instantiates ButtonPanel object button */
		this.button = button;
	}

	
	/*set up to activate from ButtonPanel when a button is selected */
	public void actionPerformed(ActionEvent e) {
		
		/*retrieves the button that was selected, and saves it in
		 * JButton source */
		JButton source = (JButton)e.getSource();
		
		/*checks if the button selected is addRoom. If it is,
		 * it proceeds to addRoom() method in ButtonPanel */
		if (source == button.getAddRoom()) {			
			button.addRoom();			
		}
		
		/*checks if the button selected is addItem. If it is,
		 * it proceeds to addItem() method in ButtonPanel */
		if (source == button.getAddItem()) {			
			button.addItem();			
		}

		/*checks if the button selected is removeItem. If it is,
		 * it proceeds to removeItem() method in ButtonPanel */
		if (source == button.getRemoveItem()) {			
			button.removeItem();			
		}
		
		/*checks if the button selected is addExit. If it is,
		 * it proceeds to addExit() method in ButtonPanel */
		if (source == button.getAddExit()) {			
			button.addExit();			
		}
		
		/*checks if the button selected is removeExit. If it is,
		 * it proceeds to removeExit() method in ButtonPanel */
		if (source == button.getRemoveExit()) {			
			button.removeExit();			
		}		
	}	
}