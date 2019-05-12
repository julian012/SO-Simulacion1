package models;

import utilities.Utilities;

public class Process {
	
	private int processId;
	private String processName;
	private int processTime;
	private boolean processBlock;
	
	public Process(int processId, String processName, int processTime, boolean processBlock) {
		setProcessId(processId);
		setProcessName(processName);
		setProcessTime(processTime);
		setProcessBlock(processBlock);
	}

	public int getProcessId() {
		return processId;
	}

	public void setProcessId(int processId) {
		this.processId = processId;
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
		return new Object[]{getProcessId(), getProcessName(), getProcessTime(), Utilities.booleanToString(isProcessBlock())};
	}

	@Override
	public String toString() {
		return "Id Proceso: " + processId + " , Nombre Proceso: " + processName + " , Tiempo Proceso: " + processTime
				+ " , Proceso Bloqueado: " + processBlock + "\n";
	}
}