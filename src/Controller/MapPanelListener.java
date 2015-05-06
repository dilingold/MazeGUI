package Controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import View.Grid;
import View.MapPanel;


public class MapPanelListener implements MouseListener {
	
	/*instance variables of MapPanel object of current map panel
	 * and Grid object of selected grid panel */
	private MapPanel mapPanel;
	private Grid source;
	
	
	/*constructor for MapPanelListener object, takes parameter of
	 * MapPanel object */
	public MapPanelListener(MapPanel mapPanel) {
		this.mapPanel = mapPanel;				
	}

	
	/*set up to activate from MapPanel when a grid item is selected */
	public void mouseClicked(MouseEvent e) {

		/*gets the previously selected X and Y coordinates from mapPanel
		 * and saves it as 'previousX' and 'previousY' respectively */
		int previousX = mapPanel.getClickedX();
		int previousY = mapPanel.getClickedY();
		
		/*gets the Grid panel from mapPanel of the previously selected grid */
		Grid prevGrid = mapPanel.getSource();
		
		/*saves the Grid panel of the currently selected grid and saves
		 * it as 'source' */
		source = (Grid)e.getSource();
		
		/*gets the X and Y coordinates of the currently selected Grid panel */
		int x = source.getGridXIndex();
		int y = source.getGridYIndex();		
		
		/*highlights the currently selected grid via mapPanel */
		mapPanel.highlightGrid(x,y);
		
		/*resets the previously selected grid via mapPanel, based on whether
		 * it is a room, start room or finish room */
		mapPanel.resetPreviousGrid(prevGrid,previousX,previousY);
		
		/*checks via mapPanel whether a room exists at the currently selected grid, and
		 * enables/disables buttons and menu items accordingly */
		mapPanel.checkRoomStatus(x,y);
		
		/*checks via mapPanel whether an item exists in a currently selected grid, and
		 * enables/disables buttons and menu items accordingly */
		mapPanel.checkRoomItem(x,y);

		/*checks via mapPanel whether exits exist in a currently selected grid, and 
		 * enables/disables buttons and menu items accordingly */
		mapPanel.checkRoomExit(x,y);
		
		/*sends currently selected grid to InformationPanel via mapPanel so that
		 * InformationPanel can update its display accordingly */
		mapPanel.changeInformationDisplay(x,y);
	}
	
	/*returns Grid object or currently selected grid */
	public Grid getSource() {
		
		return source;
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}




}
