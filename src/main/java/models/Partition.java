package models;

public class Partition {
	
	private String partitionName;
	private int size;
	
	public Partition(String partitionName, int size) {
		this.partitionName = partitionName;
		this.size = size;
	}

	public String getPartitionName() {
		return partitionName;
	}

	public void setPartitionName(String partitionName) {
		this.partitionName = partitionName;
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
	
	
}
