package models;

public class Partition {
	
	private String partitionName;
	private int size;
	private int time;
	
	public Partition(String partitionName, int size) {
		this.partitionName = partitionName;
		this.size = size;
		time = 0;
	}

	public String getPartitionName() {
		return partitionName;
	}

	public void setPartitionName(String partitionName) {
		this.partitionName = partitionName;
	}
	
	public void lessSize(int processSize) {
		this.size -= processSize;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
	
	public Object[] toObject(){
		return new Object[]{getPartitionName(), getSize()};
	}

	
	public void addProcess(int time) {
		this.time += time;
	}
	
	public int getTime() {
		return time;
	}

	@Override
	public String toString() {
		return "Partition [partitionName=" + partitionName + ", size=" + size + ", time=" + time + "]";
	}
	
	
	
}
