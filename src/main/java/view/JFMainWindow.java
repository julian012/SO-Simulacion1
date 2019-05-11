package view;

import javax.swing.*;

import controller.Controller;

import java.awt.*;

public class JFMainWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanelNorth jPanelNorth;
	private JPanelWest jPanelWest;
	private JPanelEast jPanelEast;
	private JPanelSouth jPanelSouth;

	public JFMainWindow(Controller controller) {
		setTitle(Constraints.TITLE);
		setSize(850, 475);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		initComponents(controller);
	}

	public void initComponents(Controller controller){

	}
	
	
}
