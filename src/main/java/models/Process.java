package models;

public class Process {
	
	private String processName;
	private int processTime;
	private int processSize;
	private String partitionName;
	
	public Process(String processName, int processTime, int processSize, String partitionName) {
		this.processName = processName;
		this.processTime = processTime;
		this.processSize = processSize;
		this.partitionName = partitionName;
	}
	
	public Process(String processName, int processTime, int processSize) {
		this.processName = processName;
		this.processTime = processTime;
		this.processSize = processSize;
	}

	public Process(Process process) {
		setProcessName(process.getProcessName());
		setProcessTime(process.getProcessTime());
		setPartitionName(process.getPartitionName());
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

	
		
	public int getProcessSize() {
		return processSize;
	}

	public void setProcessSize(int processSize) {
		this.processSize = processSize;
	}

	public String getPartitionName() {
		return partitionName;
	}

	public void setPartitionName(String partitionName) {
		this.partitionName = partitionName;
	}

	public Object[] toObject(){
		return new Object[]{getProcessName(), getProcessTime(), getProcessSize(), getPartitionName()};
	}
}