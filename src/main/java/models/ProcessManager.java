package models;

import java.util.ArrayList;
import java.util.Comparator;

import utilities.Utilities;

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
	private ArrayList<Process> destroyProcess; //Cola de procesos destruidos
	private ArrayList<Process> layokffProcess; //Cola de supender
	private ArrayList<Process> resumeProcess; //Cola de renaudar
	private ArrayList<Process> connectProcess; //Cola de procesos que se comunican
	private ArrayList<ProcessInfo> info;
	
	public ProcessManager() {
		processList = new ArrayList<Process>();
		destroyProcess = new ArrayList<Process>();
		readyList = new ArrayList<Process>();
		executionList = new ArrayList<Process>();
		blockList = new ArrayList<Process>();
		wakeList = new ArrayList<Process>();
		packoffList = new ArrayList<Process>();
		expireList = new ArrayList<Process>();
		blockedList = new ArrayList<Process>();
		exitList = new ArrayList<Process>();
		connectProcess = new ArrayList<Process>();
		layokffProcess = new ArrayList<Process>();
		resumeProcess = new ArrayList<Process>();
		test();
	}
	
	public void test() {
		//Prioridad//Nueva Prioridad//Nombre Proceso//Tiempo proceso
		//Bloqueado//Destruido//Suspender
		//Conectado
		processList.add(new Process(3, 3, "P10", 8, false, true, false, "No"));//P10
		processList.add(new Process(2, 2, "P20", 9, true, false, true, "No"));//P20
		processList.add(new Process(6, 6, "P30", 7, false, false, false, "No"));//P30
		processList.add(new Process(4, 4, "P40", 5, false, true, false, "No"));//P40
		processList.add(new Process(7, 7, "P50", 11, false, false, false, "YES"));//P50
		processList.add(new Process(5, 5, "P60", 13, false, false, false, "No"));//P60
		processList.add(new Process(8, 1, "P70", 18, false, false, false, "No"));//P70
		processList.add(new Process(9, 9, "P80", 14, false, false, false, "No"));//P80
		processList.add(new Process(10, 10, "P90", 13, true, true, true, "No"));//P90
	}

	public ArrayList<ProcessInfo> setList(){
		info = new ArrayList<ProcessInfo>();
		info.add(new ProcessInfo("Cola de Procesos",processList));
		info.add(new ProcessInfo("Cola de Listos", readyList));
		info.add(new ProcessInfo("Cola de Ejecucion", executionList));
		info.add(new ProcessInfo("Cola de Bloqueo", blockList));
		info.add(new ProcessInfo("Cola destruidos", destroyProcess));
		info.add(new ProcessInfo("Cola de Despertar", wakeList));
		info.add(new ProcessInfo("Cola de Despacho", packoffList));
		info.add(new ProcessInfo("Cola de Expiracion de Tiempo", expireList));
		info.add(new ProcessInfo("Cola de Bloqueado", blockedList));
		info.add(new ProcessInfo("Cola de Suspendido", layokffProcess));
		info.add(new ProcessInfo("Cola de Reanudados", resumeProcess));
		info.add(new ProcessInfo("Cola de Salida", exitList));
		info.add(new ProcessInfo("Cola comunicados", connectProcess));
		return info;
	}

	public Process addProcess(int processPriority, int newProcessPriority, String processName, int processTime,
			boolean processBlock, boolean processDestroy, boolean processLayoff, String connectProcess){
		Process process = new Process(processPriority, newProcessPriority, processName, processTime, 
				processBlock, processDestroy, processLayoff, connectProcess);
				
		return process;
	}
	
	public void generateReport() {
		//for (Process process : processList) {
			excecuteProcess();
		//}
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
	
	/*public void excecuteProcess(Process process) {
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
	}*/
	
	public void destroyProcess() {
		for(int i = 0; i < processList.size(); i++) {
			if(processList.get(i).isProcessDestroy()) {
				destroyProcess.add(new Process(processList.get(i)));
				processList.remove(i);
				i = 0;
			}
		}
	}
	
	public void communicateProcess() {
		for(int i = 0; i < processList.size(); i++) {
			if(processList.get(i).getConnectProcess() != "No") {
				connectProcess.add(new Process(processList.get(i)));
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	public void excecuteProcess() {
		processList.sort( new Comparator<Process>() {
			public int compare(Process p1, Process p2) {
				return p1.getNewProcessPriority() - p2.getNewProcessPriority();
			}
		});
		communicateProcess();
		destroyProcess();
		ArrayList<Process> arrayAux = new ArrayList<>();
		arrayAux = (ArrayList<Process>) processList.clone();
		for (int i = 0; i < arrayAux.size(); i++) {
			Process process = new Process(arrayAux.get(i));
			packoffList.add(new Process(process)); //Lista de despacho
			readyList.add( new Process(process));
			process.setProcessTime(Utilities.quitNegativeNumbers(process.getProcessTime() - TIME_PROCESS));
			executionList.add(new Process(process));
			if(process.getProcessTime() > 0) {
				if(process.isProcessBlock()) {
					blockedList.add(new Process(process));
					blockList.add(new Process(process));
					wakeList.add(new Process(process));
				}else {
					expireList.add(new Process(process));
				}
			}
			if(process.getProcessTime() == 0) {
				exitList.add(new Process(process));
			}else {
				if(process.isProcessLayoff()) {
					
					layokffProcess.add(new Process(process));
					resumeProcess.add(new Process(process));
				}
				arrayAux.add(process);
			}
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
	
	public void deleteProcess(String nameProcess) {
		for(int i=0; i< processList.size(); i++) {
			if(processList.get(i).getProcessName() == nameProcess) {
				processList.remove(i);
				return;
			}			
		}
	}
}
