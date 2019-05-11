package models;

import java.util.ArrayList;

public class ProcessManager {
	
	public static final int TIME_PROCESS = 5;
	public static final int TIME_BLOCK = 2;
	
	private ArrayList<Process> processList;
	private ArrayList<Process> readyList; //Cola de Listos
	private ArrayList<Process> executionList; //Cola de Ejecución
	private ArrayList<Process> blockList; //Cola de Bloqueo
	private ArrayList<Process> wakeList; //Cola de Despertar
	private ArrayList<Process> packoffList; //Cola de Despacho
	private ArrayList<Process> expireList; //Cola de Expiración de Tiempo
	private ArrayList<Process> blockedList; //Cola de Bloqueado
	private ArrayList<Process> exitList; //Cola de Salida
	
	public ProcessManager() {
		processList = new ArrayList<Process>();
		readyList = new ArrayList<Process>();
		executionList = new ArrayList<Process>();
		blockList = new ArrayList<Process>();
		wakeList = new ArrayList<Process>();
		packoffList = new ArrayList<Process>();
		expireList = new ArrayList<Process>();
		blockedList = new ArrayList<Process>();
		exitList = new ArrayList<Process>();
	}
	
	public void test() {
		processList.add(new Process(1, "Proceso 1", 10, true));
		processList.add(new Process(2, "Proceso 2", 8, false));
		processList.add(new Process(3, "Proceso 3", 9, true));
		for (Process process : processList) {
			excecuteProcess(process);
		}
		System.out.println("Cola de Listos \n\n");
		System.out.println(readyList);
		System.out.println("Cola de Ejecución \n\n");
		System.out.println(executionList);
		System.out.println("Cola de Bloqueo \n\n");
		System.out.println(blockList);
		System.out.println("Cola de Despertar \n\n");
		System.out.println(wakeList);
		System.out.println("Cola de Despacho \n\n");
		System.out.println(packoffList);
		System.out.println("Cola de Expiración de Tiempo \n\n");
		System.out.println(expireList);
		System.out.println("Cola de Bloqueado \n\n");
		System.out.println(blockedList);
		System.out.println("Cola de Salida"
				+ " \n\n");
		System.out.println(exitList);
	}

	public ArrayList<Process> getProcessList() {
		return processList;
	}

	public void setProcessList(ArrayList<Process> processList) {
		this.processList = processList;
	}
	
	public void excecuteProcess(Process process) {
		readyList.add(process);
		packoffList.add(process);
		Process processEx = new Process(process.getProcessId(), process.getProcessName(), 
				process.getProcessTime() - TIME_PROCESS, process.isProcessBlock());
		executionList.add(processEx);
		if(processEx.getProcessTime() <= 0) {
			exitList.add(processEx);
		}else {
			if(processEx.isProcessBlock()) {
				blockedList.add(processEx);
				blockList.add(processEx);
				wakeList.add(processEx);
			}else {
				expireList.add(processEx);
			}
			excecuteProcess(processEx);
		}
	}
}
