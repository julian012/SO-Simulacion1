package models;

import java.util.ArrayList;

public class PartitionInfo {
	
	private String name;
    private ArrayList<Partition> partitionList;

    public PartitionInfo(String name, ArrayList<Partition> partitionList){
        setName(name);
        setPartitionList(partitionList);
    }

    public void setName (String name){
        this.name = name;
    }

    public void setPartitionList ( ArrayList<Partition> partitionList ){
        this.partitionList = partitionList;
    }

    public String getName(){
        return this.name;
    }

    public ArrayList<Partition> getPartitionList(){
        return this.partitionList;
    }
}
