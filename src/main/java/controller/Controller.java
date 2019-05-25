package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import com.itextpdf.text.DocumentException;
import models.Process;
import models.ProcessManager;
import persistence.CreatePDF;
import utilities.Utilities;
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
		test();
	}
	
	public void test() {
		loadProcessInTable();
	}

	public void createReport(){
		if(manager.getProcessList().size() != 0) {
			manager.generateReport();
			createPDF = new CreatePDF();
			try {
				createPDF.createReport(manager.setList());
				manager.cleanProcessList();
				continueReport();
				
			} catch (DocumentException e) {
				e.printStackTrace();
				manager.cleanProcessList();
			} catch (IOException e) {
				e.printStackTrace();
				manager.cleanProcessList();
			}
		}else {
			showMessage("No tiene procesos en lista", "Error");
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
		int priority = mainWindow.getPriority(); //Prioridad
		int newPriority = mainWindow.getNewPriority(); //Nueva prioridad
		String name = mainWindow.getNameProcess();
		int time = mainWindow.getTimeProcess();
		boolean blocked = mainWindow.isBlockedProcess();
		boolean isDestroy = mainWindow.isDestroy();//Destruido
		boolean isLayoff = mainWindow.isLayoff();//Suspendido
		String connectProcess = mainWindow.isConnect();	//Conectado
		boolean isExecute = mainWindow.isExcecute();
		if(time <= 0){
			showMessage("El tiempo del proceso debe ser mayor a 0","Error");
		}
		if(name.length() == 0){
			showMessage("Debe ingresar un nombre en el proceso","Error");
		}
		if(time > 0 && name.length() > 0){
			if(Utilities.exist(name, manager.getProcessList())) {
				Process process = manager.addProcess(priority, newPriority,name,time,isExecute,blocked, isDestroy, isLayoff, connectProcess);
				mainWindow.addProcessInTable(process);
			}else {
				showMessage("Proceso con ese nombre ya existe en la lista", "Error");
			}
			
		}

	}

	public void showMessage(String message, String error){
		JOptionPane.showMessageDialog(mainWindow,message, error, JOptionPane.ERROR_MESSAGE,null);
	}
	
	/*public void deleteProcess(int id) {
		if(JOptionPane.showConfirmDialog(mainWindow, "¿Segundo que desea borrar el proceso con Id: " + id +"?",
				"Pregunta", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
			manager.deleteProcess(id);
			mainWindow.clearTable();
			loadProcessInTable();
		}
	}*/
	
	public void loadProcessInTable() {
		ArrayList<Process> list = manager.getProcessList();
		for (Process process : list) {
			mainWindow.addProcessInTable(process);
		}
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
			case CLOSE:
				mainWindow.close();
				break;
			case MINIMIZE:
				mainWindow.minimize();
				break;
			}
		
		
	}
}
