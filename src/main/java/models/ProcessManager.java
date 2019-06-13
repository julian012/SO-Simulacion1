package models;

import java.util.ArrayList;
import java.util.Comparator;

import structure.CircularList;
import structure.Node;
import utilities.Utilities;

public class ProcessManager {

	public static final int TIME_PROCESS = 5;
	public static final int TIME_BLOCK = 2;

	private ArrayList<Process> processList;
	private ArrayList<Partition> partitionList; //Lista de particiones;
	private ArrayList<Process> fireList; //Lista de no procesados
	private ArrayList<ProcessInfo> info;
	private ArrayList<PartitionInfo> infoPartitions;

	public ProcessManager() {
		processList = new ArrayList<Process>();
		partitionList = new ArrayList<Partition>();
		fireList = new ArrayList<Process>();
		infoPartitions = new ArrayList<PartitionInfo>();
		//test();
	}

	public void test() {
		partitionList.add(new Partition("PAR-1", 30));
		partitionList.add(new Partition("PAR-2", 40));
		partitionList.add(new Partition("PAR-3", 60));
		partitionList.add(new Partition("PAR-4", 20));
		partitionList.add(new Partition("PAR-5", 10));
		partitionList.add(new Partition("PAR-6", 50));
		processList.add(new Process("P5", 10, 5));
		processList.add(new Process("P45", 5, 45));
		processList.add(new Process("P65", 6, 65));
		processList.add(new Process("P28", 12, 28));
		processList.add(new Process("P46", 15, 46));
		processList.add(new Process("P18", 20, 18));
		processList.add(new Process("P58", 18, 58));
		processList.add(new Process("P37", 19, 37));
		processList.add(new Process("P56", 17, 56));
		processList.add(new Process("P25", 22, 25));
		processList.add(new Process("P12", 9, 12));

	}

	public ArrayList<ProcessInfo> setList(){
		info = new ArrayList<ProcessInfo>();
		info.add(new ProcessInfo("Cola de Procesos",processList));
		info.add(new ProcessInfo("Procesos eliminados por no exisitir tamaño de particion igual o mayor a su tamaño", fireList));
		info.add(new ProcessInfo("Orden de los procesos por tiempo", orderProcessByTime()));
		info.add(new ProcessInfo("Orden de los procesos por Tamaño", orderProcessBySize()));
		info.add(new ProcessInfo("Orden de terminacion de los procesos", exitListByTime(orderProcessByTime())));
		getOrderWithPartition(orderProcessByTime());
		return info;
	}

	public ArrayList<PartitionInfo> setPartitionList(){
		infoPartitions = new ArrayList<PartitionInfo>();
		infoPartitions.add(new PartitionInfo("Lista de particiones", partitionList));
		infoPartitions.add(new PartitionInfo("Orden de Salida por particiones", getPartitionOrder()));
		return infoPartitions;
	}

	public Process addProcess(String processName, int processTime, int processSize){
		Process process = new Process(processName, processTime, processSize);
		processList.add(process);	
		return process;
	}

	public Partition addPartition(String name, int size) {
		Partition partition = new Partition(name, size);
		partitionList.add(partition);
		return partition;
	}

	public void generateReport() {
		excecuteProcess();
		addProcessToFireList();
	}

	public void addProcessToFireList() {
		for (Process process : processList) {
			System.out.println(process);
			if(process.getPartitionName() == null) {
				fireList.add(process);
			}
		}
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
		list = (ArrayList<Partition>)partitionList.clone();
		list.sort( new Comparator<Partition>() {
			public int compare(Partition p1, Partition p2) {
				return p1.getTime() - p2.getTime();
			}
		});
		return list;
	}

	public Partition searchPartitionByName(String name) {
		for(int i = 0; i < partitionList.size(); i++) {
			if(partitionList.get(i).getPartitionName().equals(name)) {
				return partitionList.get(i);
			}
		}
		return null;
	}


	public void excecuteProcess() {
		ArrayList<Process> arrayAux = new ArrayList<>();
		CircularList<Partition> list = new CircularList<Partition>();
		addPartitionsToCircularList(partitionList, list);
		arrayAux = processList;
		Node<Partition> flag = list.getFirst();
		Node<Partition> actualPartition = flag;
		for( int i =0; i < arrayAux.size(); i++) {
			Process process = arrayAux.get(i);
			while(process.getPartitionName() == null) {
				if(actualPartition.getInfo().getSize() >= process.getProcessSize()) {
					actualPartition.getInfo().addProcess(process.getProcessTime());
					process.setPartitionName(actualPartition.getInfo().getPartitionName());
					actualPartition = actualPartition.getNext();
					flag = actualPartition;
					break;
				}else {
					actualPartition = actualPartition.getNext();
				}
				if(flag.getInfo().getPartitionName() == actualPartition.getInfo().getPartitionName()) {
					actualPartition = actualPartition.getNext();
					break;
				}

			}
		}
		for(int i = 0; i < arrayAux.size(); i++) {
			Process process = arrayAux.get(i);
			System.out.println(process.getProcessName() + "con el tiempo: " + process.getProcessTime() +" esta en la partición " + process.getPartitionName());
		}
	}

	public void addPartitionsToCircularList(ArrayList<Partition> partitionAux, CircularList<Partition> list) {
		for (Partition partition : partitionAux) {
			list.addNode(new Node<Partition>(partition));
		}
	}

	public void cleanProcessList(){
		fireList.clear(); //Lista de procesos que no se ejecutaron
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

	/**
	 * Orden de salida por particiones
	 * 
	 */

	@SuppressWarnings("unchecked")
	public ArrayList<Process> orderProcessByTime(){
		ArrayList<Process> arrayAux = new ArrayList<>();
		arrayAux = (ArrayList<Process>) processList.clone();
		arrayAux.sort( new Comparator<Process>() {
			public int compare(Process p1, Process p2) {
				return p1.getProcessTime() - p2.getProcessTime();
			}
		});
		return arrayAux;
	}

	@SuppressWarnings("unchecked")
	public ArrayList<Process> orderProcessBySize(){
		ArrayList<Process> arrayAux = new ArrayList<>();
		arrayAux = (ArrayList<Process>) processList.clone();
		arrayAux.sort( new Comparator<Process>() {
			public int compare(Process p1, Process p2) {
				return p1.getProcessSize()- p2.getProcessSize();
			}
		});
		return arrayAux;
	}

	//Ver los procesos por particion
	public void getOrderWithPartition(ArrayList<Process> list) {
		/*for(int i = 0; i < list.size(); i++) {
			if(!Utilities.evaluateProcessSize(list.get(i), partitionList)) {
				list.remove(i);
				i = -1;
			}
		}*/
		for(int  i = 0; i < partitionList.size(); i++) {
			ArrayList<Process> arrayAux = new ArrayList<>();
			for (int j = 0; j < list.size(); j++) {
				if(partitionList.get(i).getPartitionName().equals(list.get(j).getPartitionName())) {
					arrayAux.add(new Process(list.get(j)));
				}
			}
			info.add(new ProcessInfo("Procesos en la particion con el nombre: " + partitionList.get(i).getPartitionName()
					, arrayAux));
		}
	}

	//Terminación de los procesos por tiempo
	//Entra lista de procesos terminados
	@SuppressWarnings("unchecked")
	public ArrayList<Process> exitListByTime(ArrayList<Process> list) {
		/*for(int i = 0; i < list.size(); i++) {
			if(!Utilities.evaluateProcessSize(list.get(i), partitionList)) {
				list.remove(i);
				i = -1;
			}
		}*/
		ArrayList<Process> arrayAux = new ArrayList<>();
		arrayAux = (ArrayList<Process>) list.clone();
		for(int i = 0; i < arrayAux.size(); i++) {
			if(!Utilities.evaluateProcessSize(arrayAux.get(i), partitionList)) {
				arrayAux.remove(i);
				i = 0;
			}
		}
		arrayAux.sort( new Comparator<Process>() {
			public int compare(Process p1, Process p2) {
				return p1.getProcessTime() - p2.getProcessTime();
			}
		});
		return arrayAux;		
	}

	//Terminacion de los procesos por tiempo

}
