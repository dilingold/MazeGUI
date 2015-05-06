package View;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;

public class ApplicationFrame extends JFrame {

	/*instance variables for MapPanel, InformationPanel,
	 * MazeMenuBar and ButtonPanel objects */
	private MapPanel mapPanel;
	private InformationPanel infoPanel;
	private MazeMenuBar menuBar;
	private ButtonPanel buttonPanel;
	
	
	/*constructor for ApplicationFrame object */
	public ApplicationFrame() {
		
	/*instantiates new MapPanel, InformationPanel, MazeMenuBar
	 * and ButtonPanel objects */
	mapPanel = new MapPanel(this);
	infoPanel = new InformationPanel(this);
	menuBar = new MazeMenuBar(this);	
	buttonPanel = new ButtonPanel(this);
	
	/*sets the dimensions for each panel in the application frame */
	menuBar.setPreferredSize(new Dimension(600,25));
	buttonPanel.setPreferredSize(new Dimension(160,300));
	infoPanel.setPreferredSize(new Dimension(600,70));
		
	/*sets the application frame to border layout, with a gap of 20
	 * between panels */
	this.setLayout(new BorderLayout(20,20));
	
	/*lays out the panels in the application frame */
	this.add(menuBar,BorderLayout.NORTH);
	this.add(buttonPanel,BorderLayout.WEST);
	this.add(infoPanel,BorderLayout.SOUTH);
	this.add(mapPanel,BorderLayout.CENTER);
	
	/*sets the size, location, visibility and exit operations of the
	 * application frame */
	this.setSize(600,500);
	this.setLocation(50,80);
	this.setVisible(true);
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
	}
	
	
	/*returns the mapPanel */
	public MapPanel getMapPanel() {
		return mapPanel;
	}
	
	
	/*returns the information panel */
	public InformationPanel getInfoPanel() {
		return infoPanel;
	}
	
	
	/*returns the button panel */
	public ButtonPanel getButtonPanel() {
		return buttonPanel;
	}
	
	
	/*returns the menu bar */
	public MazeMenuBar getMazeMenuBar() {
		return menuBar;
	}		
}
