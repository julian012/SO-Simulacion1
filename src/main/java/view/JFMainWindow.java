package view;

import javax.swing.JFrame;

import controller.Controller;

public class JFMainWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	
	public JFMainWindow(Controller controller) {
		setTitle(Constraints.TITLE);
		setSize(850, 475);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	
}
