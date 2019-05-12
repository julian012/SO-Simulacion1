package models;

import java.util.ArrayList;

public class ProcessManager {
	
	public static final int TIME_PROCESS = 5;
	public static final int TIME_BLOCK = 2;
	
	private ArrayList<Process> processList;
	private ArrayList<Process> readyList; //Cola de Listos
	private ArrayList<Process> executionList; //Cola de Ejecuci�n
	private ArrayList<Process> blockList; //Cola de Bloqueo
	private ArrayList<Process> wakeList; //Cola de Despertar
	private ArrayList<Process> packoffList; //Cola de Despacho
	private ArrayList<Process> expireList; //Cola de Expiraci�n de Tiempo
	private ArrayList<Process> blockedList; //Cola de Bloqueado
	private ArrayList<Process> exitList; //Cola de Salida
	private ArrayList<ProcessInfo> info;
	
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

	public ArrayList<ProcessInfo> setList(){
		info = new ArrayList<ProcessInfo>();
		info.add(new ProcessInfo("Cola de Procesos",processList));
		info.add(new ProcessInfo("Cola de Listos", readyList));
		info.add(new ProcessInfo("Cola de Ejecucion", executionList));
		info.add(new ProcessInfo("Cola de Bloqueo", blockList));
		info.add(new ProcessInfo("Cola de Despertar", wakeList));
		info.add(new ProcessInfo("Cola de Despacho", packoffList));
		info.add(new ProcessInfo("Cola de Expiracion de Tiempo", expireList));
		info.add(new ProcessInfo("Cola de Bloqueado", blockedList));
		info.add(new ProcessInfo("Cola de Salida", exitList));
		return info;
	}

	public Process addProcess(String name, int time, boolean blocked){
		Process process;
		if(processList.size() == 0) {
			process = new Process(processList.size(), name, time, blocked);
		}else {
			process = new Process(processList.get(processList.size() - 1).getProcessId() + 1, name, time, blocked);
		}
		processList.add(process);
		return process;
	}
	
	public void generateReport() {
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
		System.out.println("Cola de Salida \n\n");
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

	public void cleanProcessList(){
		readyList.clear();
		executionList.clear(); //Cola de Ejecuci�n
		blockList.clear(); //Cola de Bloqueo
		wakeList.clear(); //Cola de Despertar
		packoffList.clear(); //Cola de Despacho
		expireList.clear(); //Cola de Expiraci�n de Tiempo
		blockedList.clear(); //Cola de Bloqueado
		exitList.clear(); //Cola de Salida
	}

	public void cleanlist(){
		processList.clear();
	}
	
	public void deleteProcess(int id) {
		for(int i=0; i< processList.size(); i++) {
			if(processList.get(i).getProcessId() == id) {
				processList.remove(i);
				return;
			}			
		}
	}
}
