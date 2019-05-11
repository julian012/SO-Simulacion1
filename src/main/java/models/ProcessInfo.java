package models;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ProcessInfo {

    private String name;
    private ArrayList<Process> processList;

    public ProcessInfo(String name, ArrayList<Process> processList){
        setName(name);
        setProcessList(processList);
    }

    public void setName (String name){
        this.name = name;
    }

    public void setProcessList ( ArrayList<Process> processList ){
        this.processList = processList;
    }

    public String getName(){
        return this.name;
    }

    public ArrayList<Process> getProcessList(){
        return this.processList;
    }
}
