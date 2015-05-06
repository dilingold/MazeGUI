package View;

import javax.swing.JPanel;

public class Grid extends JPanel {

	/*instance variables for xIndex and yIndex of 
	 * Grid x and y values */
	private int xIndex;
	private int yIndex;
	
	
	/*constructor for Grid object, accepts parameters ints X and Y */
	public Grid(int xIndex,int yIndex) {
		
		/*instantiates xIndex and yIndex */
		this.xIndex = xIndex;
		this.yIndex = yIndex;		
	}
	
	
	/* returns yIndex of Grid object */
	public int getGridXIndex() {
		return yIndex;
	}
	
	
	/* returns xIndex of Grid object */
	public int getGridYIndex() {
		return xIndex;
	}	
}
