package view;

import models.Partition;
import models.Process;

import java.awt.BorderLayout;
import javax.swing.JPanel;

import controller.Controller;

public class JPanelEast extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTableCustom proccessTable;
	private JTableCustom partitionTable;
	
    public JPanelEast(){
        setLayout(new BorderLayout());
        proccessTable = new JTableCustom(Constraints.TABLE_HEADER);
        partitionTable = new JTableCustom(Constraints.TABLE_PARTITION_HEADER);
        add(partitionTable, BorderLayout.SOUTH);
        add(proccessTable, BorderLayout.NORTH);
        setVisible(true);
    }

    public void addProcess(Process process, Controller controller){
    	Object[] obj = process.toObject();
    	Object[] set = new Object[]{obj[0],obj[1],obj[2], obj[3]};
        proccessTable.addInfo(set);
    }
    
    public void addPartition(Partition partition, Controller controller) {
    	System.out.println("LLego" + partition.getPartitionName());
    	Object[] obj = partition.toObject();
    	Object[] set = new Object[]{obj[0],obj[1]};
    	partitionTable.addInfo(set);
    }

    public void cleanTableProcess(){
        proccessTable.cleanTable();
    }
    
    public void cleanTablePartition() {
    	partitionTable.cleanTable();
    }
    
}
