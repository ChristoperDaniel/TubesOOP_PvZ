package main;
import java.util.Scanner;

public class ScannerJava {
    private static Scanner scanner;
    
        // Method to read a string
    public static Scanner getScanner() {
        if (scanner == null){
            scanner = new Scanner(System.in);
        }
        return scanner;
    }
}
