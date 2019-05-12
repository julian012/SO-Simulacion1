package utilities;

public class Utilities {

    public static String booleanToString(boolean result){
        return (result)? "Si" : "No";
    }

    public static int quitNegativeNumbers(int number){
        return (number < 0) ? 0 : number;
    }
}
