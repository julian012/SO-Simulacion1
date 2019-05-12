package utilities;

import java.util.ArrayList;

import models.Process;

public class Utilities {

    public static String booleanToString(boolean result){
        return (result)? "Si" : "No";
    }

    public static int quitNegativeNumbers(int number){
        return (number < 0) ? 0 : number;
    }
    
    public static boolean exist(String name, ArrayList<Process> list) {
    	for (Process process : list) {
			if (process.getProcessName().equals(name)) {
				return false;
			}
		}
    	return true;
    }
}
