package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import com.itextpdf.text.DocumentException;
import models.Process;
import models.ProcessManager;
import persistence.CreatePDF;
import view.JFMainWindow;

import javax.swing.*;

public class Controller implements ActionListener {
	
	private JFMainWindow mainWindow;
	private ProcessManager manager;
	private CreatePDF createPDF;
	
	public Controller() {
		manager = new ProcessManager();
		//manager.test();
		mainWindow = new JFMainWindow(this);
		mainWindow.setVisible(true);
		//test();
	}

	public void createReport(){
		manager.generateReport();
		createPDF = new CreatePDF();
		try {
			createPDF.createReport(manager.setList());
			manager.cleanProcessList();
			continueReport();

		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void continueReport(){
		if (JOptionPane.showConfirmDialog(mainWindow, "¿Desea agregar más procesos?",
				"Pregunta", JOptionPane.YES_NO_OPTION) == JOptionPane.NO_OPTION){
			if(JOptionPane.showConfirmDialog(mainWindow, "¿Desea limpiar la lista de procesos?",
					"Pregunta", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
				manager.cleanlist();
				mainWindow.clearTable();
			}
		}

	}

	public void addProcess(){
		String name = mainWindow.getNameProcess();
		int time = mainWindow.getTimeProcess();
		boolean blocked = mainWindow.isBlockedProcess();
		if(time <= 0){
			showMessage("El tiempo del proceso debe ser mayor a 0","Error");
		}
		if(name.length() == 0){
			showMessage("Debe ingresar un nombre en el proceso","Error");
		}
		if(time > 0 && name.length() > 0){
			Process process = manager.addProcess(name,time,blocked);
			mainWindow.addProcessInTable(process);
		}

	}

	public void showMessage(String message, String error){
		JOptionPane.showMessageDialog(mainWindow,message, error, JOptionPane.ERROR_MESSAGE,null);
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		switch (Events.valueOf(e.getActionCommand())){
			case ADD:
				addProcess();
				mainWindow.cleanForm();
				break;
			case CLEAR:
				mainWindow.cleanForm();
				break;
			case START:
				createReport();
				break;
		}
		
	}
}
