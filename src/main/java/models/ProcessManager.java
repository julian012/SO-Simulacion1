package models;

import java.util.ArrayList;
import java.util.Collections;
import utilities.Utilities;

public class ProcessManager {
	
	public static final int TIME_PROCESS = 5;
	public static final int TIME_BLOCK = 2;
	
	private ArrayList<Process> processList;
	private ArrayList<Process> readyList; //Cola de Listos
	private ArrayList<Process> executionList; //Cola de Ejecución
	private ArrayList<Process> packoffList; //Cola de Despacho
	private ArrayList<Process> exitList; //Cola de Salida
	private ArrayList<Partition> partitionList; //Lista de particiones;
	private ArrayList<Process> fireList; //Lista de procesos que no se ejecutaron
	private ArrayList<Process> expiredList; //Lista de expirados
	private ArrayList<ProcessInfo> info;
	private ArrayList<PartitionInfo> infoPartitions;
	
	public ProcessManager() {
		processList = new ArrayList<Process>();
		readyList = new ArrayList<Process>();
		executionList = new ArrayList<Process>();
		packoffList = new ArrayList<Process>();
		exitList = new ArrayList<Process>();
		partitionList = new ArrayList<Partition>();
		expiredList = new ArrayList<Process>();
		fireList = new ArrayList<Process>();
		infoPartitions = new ArrayList<PartitionInfo>();
		//test();
	}
	
	public void test() {
		partitionList.add(new Partition("Uno", 10));
		partitionList.add(new Partition("Dos", 20));
		partitionList.add(new Partition("Tres", 15));
		processList.add(new Process("P1", 10, 5, "Uno"));
		processList.add(new Process("P2", 10, 7, "Dos"));
		processList.add(new Process("P3", 15, 8, "Dos"));
		processList.add(new Process("P4", 8, 20, "Tres"));
		processList.add(new Process("P5", 12, 30, "Uno"));	

	}

	public ArrayList<ProcessInfo> setList(){
		info = new ArrayList<ProcessInfo>();
		info.add(new ProcessInfo("Cola de Procesos",processList));
		info.add(new ProcessInfo("Cola de Listos", readyList));
		info.add(new ProcessInfo("Cola de Despacho", packoffList));
		info.add(new ProcessInfo("Cola de Ejecucion", executionList));
		info.add(new ProcessInfo("Cola de Salida", exitList));
		info.add(new ProcessInfo("Procesos no ejecutados", fireList));
		return info;
	}
	
	public ArrayList<PartitionInfo> setPartitionList(){
		infoPartitions = new ArrayList<PartitionInfo>();
		infoPartitions.add(new PartitionInfo("Lista de particiones", partitionList));
		infoPartitions.add(new PartitionInfo("Orden de Salida por particiones", getPartitionOrder()));
		return infoPartitions;
	}

	public Process addProcess(String processName, int processTime, int processSize, String partitionName){
		Process process = new Process(processName, processTime, processSize, partitionName);
		processList.add(process);	
		return process;
	}
	
	public Partition addPartition(String name, int size) {
		Partition partition = new Partition(name, size);
		partitionList.add(partition);
		return partition;
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
	}

	public ArrayList<Process> getProcessList() {
		return processList;
	}
	
	public ArrayList<Partition> getPartititonList(){
		return partitionList;
	}

	public void setProcessList(ArrayList<Process> processList) {
		this.processList = processList;
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<Partition> getPartitionOrder(){
		ArrayList<Partition> list = new ArrayList<Partition>();
		ArrayList<Process> arrayAux = new ArrayList<>();
		arrayAux = (ArrayList<Process>) exitList.clone();
		while(list.size() != partitionList.size()) {
			System.out.println(list.size() + "  " + partitionList.size());
			if(arrayAux.size() == 0) {
				break;
			}else {
				String partitionName = arrayAux.get(arrayAux.size() - 1).getPartitionName();
				list.add(searchPartitionByName(partitionName));
				removePartitionProcess(arrayAux, partitionName);
			}
		}
		Collections.reverse(list);
		return list;
	}
	
	public void removePartitionProcess(ArrayList<Process> aux,String partitionName) {
		for (int i = 0; i < aux.size(); i++) {
			if(aux.get(i).getPartitionName().equals(partitionName)) {
				aux.remove(i);
				i = 0;
			}
		}
	}
	
	public Partition searchPartitionByName(String name) {
		for(int i = 0; i < partitionList.size(); i++) {
			if(partitionList.get(i).getPartitionName().equals(name)) {
				return partitionList.get(i);
			}
		}
		return null;
	}
	
	
	@SuppressWarnings("unchecked")
	public void excecuteProcess() {
		ArrayList<Process> arrayAux = new ArrayList<>();
		arrayAux = (ArrayList<Process>) processList.clone();
		//Verificar si los procesos se pueden ejecutar
		for(int i = 0; i < arrayAux.size(); i++) {
			if(!Utilities.evaluateProcessSize(arrayAux.get(i), partitionList)) {
				fireList.add(arrayAux.remove(i));
				i = 0;
			}
		}
		
		for (int i = 0; i < arrayAux.size(); i++) {
			Process procesoAux = new Process(arrayAux.get(i));
            readyList.add(new Process(procesoAux));
            packoffList.add(new Process(procesoAux));
            procesoAux.setProcessTime(Utilities.quitNegativeNumbers(procesoAux.getProcessTime() - TIME_PROCESS ));
            executionList.add(new Process(procesoAux));
            if (procesoAux.getProcessTime() <= 0) {
                exitList.add(new Process(procesoAux));
            }
            if (procesoAux.getProcessTime() > 0) {
            	expiredList.add(new Process(procesoAux));
            	arrayAux.add(new Process(procesoAux));
            }

        }

	}

	public void cleanProcessList(){
		readyList.clear(); //Cola de Listos
		executionList.clear(); //Cola de Ejecución
		packoffList.clear(); //Cola de Despacho
		exitList.clear(); //Cola de Salida
		fireList.clear(); //Lista de procesos que no se ejecutaron
		expiredList.clear(); //Lista de expirados
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
