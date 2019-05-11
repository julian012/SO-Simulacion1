package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import models.ProcessManager;
import view.JFMainWindow;

public class Controller implements ActionListener {
	
//	private JFMainWindow mainWindow;
	private ProcessManager manager;
	
	public Controller() {
		manager = new ProcessManager();
		manager.test();
//		mainWindow = new JFMainWindow(this);
//		mainWindow.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
