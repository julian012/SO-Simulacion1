package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import com.itextpdf.text.DocumentException;
import models.ProcessManager;
import persistence.CreatePDF;
import view.JFMainWindow;

public class Controller implements ActionListener {
	
//	private JFMainWindow mainWindow;
	private ProcessManager manager;
	private CreatePDF createPDF;
	
	public Controller() {
		manager = new ProcessManager();
		manager.test();
//		mainWindow = new JFMainWindow(this);
//		mainWindow.setVisible(true);
		test();
	}

	public void test(){
		createPDF = new CreatePDF();
		try {
			createPDF.createReport(manager.setList());
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
