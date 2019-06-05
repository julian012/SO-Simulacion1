package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import com.itextpdf.text.DocumentException;

import models.Partition;
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
		mainWindow.validateComboBoxPartition();
		mainWindow.setVisible(true);
		//test();
	}
	
	public void test() {
		loadProcessInTable();
	}

	public void createReport(){
		if(manager.getProcessList().size() != 0) {
			manager.generateReport();
			createPDF = new CreatePDF();
			try {
				createPDF.createReport(manager.setList(), manager.setPartitionList());
				
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
				mainWindow.clearTableProcess();
				mainWindow.clearTablePartition();
			}
		}

	}

	public void addProcess(){
		String name = mainWindow.getNameProcess();
		int time = mainWindow.getTimeProcess();
		int size = mainWindow.getSizeProcess();
		String partition = mainWindow.getSelectedPartition();
		
		if(time <= 0){
			showMessage("El tiempo del proceso debe ser mayor a 0","Error");
		}
		if(name.length() == 0){
			showMessage("Debe ingresar un nombre en el proceso","Error");
		}
		if(size <= 0) {
			showMessage("El tamaño del proceso debe ser mayor a 0","Error");
		}
		if(time > 0 && name.length() > 0 && size > 0){
			if(Utilities.exist(name, manager.getProcessList())) {
				Process process = manager.addProcess(name,time,size, partition);
				mainWindow.addProcessInTable(process);
			}else {
				showMessage("Proceso con ese nombre ya existe en la lista", "Error");
			}
			
		}

	}
	
	public void addPartition() {
		String name = mainWindow.getPartitionName();
		int size = mainWindow.getPartitionSize();
		
		if(size <= 0){
			showMessage("El Tamaño de la partición debe ser mayor a 0","Error");
		}
		if(name.length() == 0){
			showMessage("Debe ingresar un nombre en la partición","Error");
		}
		if(size > 0 && name.length() > 0){
			if(Utilities.existPartition(name, manager.getPartititonList())) {
				Partition partition = manager.addPartition(name, size);
				mainWindow.addPartitionInTable(partition);
			}else {
				showMessage("Partición con ese nombre ya existe en la lista", "Error");
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
		ArrayList<Partition> listp = manager.getPartititonList();
		for (Process process : list) {
			mainWindow.addProcessInTable(process);
		}
		for(Partition partition : listp) {
			mainWindow.addPartitionInTable(partition);
		}
	}



	@Override
	public void actionPerformed(ActionEvent e) {
			switch (Events.valueOf(e.getActionCommand())){
			case ADD_PROCESS:
				addProcess();
				mainWindow.cleanProcessForm();
				break;
			case CLEAR:
				//mainWindow.cleanForm();
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
			case ADD_PARTITION:
				addPartition();
				mainWindow.cleanPartitionForm();
				break;
			}
		
		
	}
}
