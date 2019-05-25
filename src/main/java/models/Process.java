package models;

import utilities.Utilities;

public class Process {
	
	private int processPriority;
	private int newProcessPriority;
	private String processName;
	private int processTime;
	private boolean isExcecute;
	private boolean processBlock;
	private boolean processDestroy;
	private boolean processLayoff;
	private String connectProcess;
	
	public Process(int processPriority, int newProcessPriority, String processName, int processTime, boolean isExcecute,
			boolean processBlock, boolean processDestroy, boolean processLayoff, String connectProcess) {
		super();
		this.processPriority = processPriority;
		this.newProcessPriority = newProcessPriority;
		this.processName = processName;
		this.processTime = processTime;
		this.isExcecute = isExcecute;
		this.processBlock = processBlock;
		this.processDestroy = processDestroy;
		this.processLayoff = processLayoff;
		this.connectProcess = connectProcess;
	}
	
	public boolean isExcecute() {
		return isExcecute;
	}

	public void setExcecute(boolean isExcecute) {
		this.isExcecute = isExcecute;
	}



	public int getNewProcessPriority() {
		return newProcessPriority;
	}

	public void setNewProcessPriority(int newProcessPriority) {
		this.newProcessPriority = newProcessPriority;
	}

	public boolean isProcessDestroy() {
		return processDestroy;
	}

	public void setProcessDestroy(boolean processDestroy) {
		this.processDestroy = processDestroy;
	}

	public boolean isProcessLayoff() {
		return processLayoff;
	}

	public void setProcessLayoff(boolean processLayoff) {
		this.processLayoff = processLayoff;
	}

	public String getConnectProcess() {
		return connectProcess;
	}

	public void setConnectProcess(String connectProcess) {
		this.connectProcess = connectProcess;
	}

	public Process(Process process) {
		setProcessPriority(process.getProcessPriority());
		setNewProcessPriority(process.getNewProcessPriority());
		setProcessName(process.getProcessName());
		setProcessTime(process.getProcessTime());
		setProcessBlock(process.isProcessBlock());
		setProcessDestroy(process.isProcessDestroy());
		setProcessLayoff(process.isProcessLayoff());
		setConnectProcess(process.getConnectProcess());
		setExcecute(process.isExcecute());
	}
	
	public Process() {};

	public int getProcessPriority() {
		return processPriority;
	}

	public void setProcessPriority(int processId) {
		this.processPriority = processId;
	}

	public String getProcessName() {
		return processName;
	}

	public void setProcessName(String processName) {
		this.processName = processName;
	}

	public int getProcessTime() {
		return processTime;
	}

	public void setProcessTime(int processTime) {
		this.processTime = processTime;
	}

	public boolean isProcessBlock() {
		return processBlock;
	}

	public void setProcessBlock(boolean processBlock) {
		this.processBlock = processBlock;
	}

		
	public Object[] toObject(){
		return new Object[]{getProcessPriority(),Utilities.newPriority(getProcessPriority(), getNewProcessPriority()), 
				getProcessName(), getProcessTime(), Utilities.booleanToString(isExcecute()),
				Utilities.booleanToString(isProcessBlock()), Utilities.booleanToString(isProcessDestroy()),
				Utilities.booleanToString(isProcessLayoff()), getConnectProcess()};
	}

	@Override
	public String toString() {
		return "Id Proceso: " + getProcessPriority() + " , Nombre Proceso: " + processName + " , Tiempo Proceso: " + processTime
				+ " , Proceso Bloqueado: " + processBlock + "\n";
	}
}