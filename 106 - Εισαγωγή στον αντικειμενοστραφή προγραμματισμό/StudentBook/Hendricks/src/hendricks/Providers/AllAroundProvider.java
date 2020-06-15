package hendricks.Providers;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import hendricks.Helpers.PrinterHelper;
import java.io.PrintWriter;

public class AllAroundProvider {
     private static final String COURSES_FILE = "\\Courses.txt";
     private static final String STUDENT_FILE = "\\Students.txt";
     private static final String PROFESSOR_FILE = "\\Professors.txt";
     
    public static void ClearFile(String filePath){
        try {
            PrintWriter printWriter = new PrintWriter(GetCurrentWorkingFolderPath() + filePath);
            printWriter.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public static FileWriter CreateFileWriter(String filePath, boolean append){
        FileWriter writer = null;
        try {            
            writer = new FileWriter(new File(GetCurrentWorkingFolderPath() + filePath), append);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return writer;
    }
    
    public static String GetCurrentWorkingFolderPath(){
        String currentDirectory = null;                              
        try{            
            currentDirectory = System.getProperty("user.dir");
            return currentDirectory;
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return currentDirectory;
    }
    
    public static BufferedReader CreateBufferedReader(String filePath){
        BufferedReader fileReader = null;
        try {
            fileReader = new BufferedReader(new FileReader(GetCurrentWorkingFolderPath() + filePath));
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return fileReader;
    }

    public static void InitializeTextFiles(){
        File coursesFile = new File(GetCurrentWorkingFolderPath() + "\\Courses.txt");
        File studentFile = new File(GetCurrentWorkingFolderPath() + "\\Students.txt");
        File professorFile = new File(GetCurrentWorkingFolderPath() + "\\Professors.txt");
        if(!coursesFile.exists()){
            try {
                coursesFile.createNewFile();
                FileWriter writer = CreateFileWriter(COURSES_FILE, true);
                writer.write("C0101|Mechanics I|1|Apostolatos Th.|Physics");
                writer.write("\r\nC0102|Linear Algebra|1|Rassias|Math");
                writer.write("\r\nC0201|Introduction to Astrophysics|2|Dagklis Ioannis|Physics");
                writer.write("\r\nC0202|Quantum Mechanics I|2|Sfetsos K.|Physics");
                writer.write("\r\nC0301|ElectroDynamics I|3|Georgalas A.|Engineering");
                writer.write("\r\nC0302|Electronics|3|*|Engineering");
                writer.write("\r\n");
                writer.close();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
        if(!studentFile.exists()){
            try {
                studentFile.createNewFile();
                FileWriter writer = CreateFileWriter(STUDENT_FILE, true);
                writer.write("S0000|Giorgos Kalomalos|g.kalomalos@uniwa.gr|6973811028|2|C0201,C0202");
                writer.write("\r\nS0001|Afroditi Zevgoli|a.zevgoli@uniwa.gr|6987452147|1|C0101,C0102");
                writer.write("\r\nS0002|Maria Lagoudi|m.lagoudi@uniwa.gr|6945223669|1|C0101,C0102");
                writer.write("\r\nS0003|Giorgos Karatassos|g.karatassos@uniwa.gr|6985521102|3|C0301,C0302");
                writer.write("\r\nS0004|Giannis Apostolidis|a.apostolidis@uniwa.gr|6996999632|2|C0201,C0202");
                writer.write("\r\n");
                writer.close();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
        if(!professorFile.exists()){
            try {
                professorFile.createNewFile();
                FileWriter writer = CreateFileWriter(PROFESSOR_FILE, true);
                writer.write("P0000|Apostolatos Th.|a.apostolatos@uniwa.gr|2104300000|Physics|C0101");
                writer.write("\r\nP0001|Rassias|a.rassias@uniwa.gr|2103400001|Math|C0102");
                writer.write("\r\nP0002|Dagklis I.|i.dagklis@uniwa.gr|2103400002|Engineering|C0201");
                writer.write("\r\nP0003|Sfetsos K.|k.sfetsos@uniwa.gr|2103400003|Physics|C0202");
                writer.write("\r\nP0004|Georgalas A.|a.georgalas@uniwa.gr|2103400004|Physics|C0301");
                writer.write("\r\nP0005|Tompras T.|t.tompras@uniwa.gr|2103400005|Engineering|*");
                writer.write("\r\n");
                writer.close();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }   
    
    public String ScanUserInput(String inputMessage){          
        System.out.print(inputMessage);
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();
        return userInput;
    }
    
    public static String ScanMenuInput(String type){
        Scanner scanner = new Scanner(System.in);
        String userInput = null;
        switch (type){
            case "mainMenu":       
                System.out.print("Enter option: ");
                userInput = scanner.nextLine();
                while (!Arrays.stream(new String[]{"1", "2", "3", "0"}).anyMatch(userInput :: equals)) {                        
                    System.out.print(PrinterHelper.ANSI_RED + "Enter a valid main menu option: " + PrinterHelper.ANSI_RESET);
                    userInput = scanner.nextLine();                    
                }
                break;
            case "student" :
                System.out.print("Enter Student menu option: ");                
                userInput = scanner.nextLine();
                while (!Arrays.stream(new String[]{"1", "2", "3", "4", "0"}).anyMatch(userInput :: equals)) {                        
                    System.out.print(PrinterHelper.ANSI_RED + "Enter a valid Student menu option: " + PrinterHelper.ANSI_RESET);
                    userInput = scanner.nextLine();
                }                
                break;
            case "professor" :
                System.out.print("Enter Professor menu option: ");                
                userInput = scanner.nextLine();
                while (!Arrays.stream(new String[]{"1", "2", "3", "4", "0"}).anyMatch(userInput :: equals)) {                        
                    System.out.print(PrinterHelper.ANSI_RED + "Enter a valid Professor menu option: " + PrinterHelper.ANSI_RESET);
                    userInput = scanner.nextLine();
                }                
                break;   
            case "course" :
                System.out.print("Enter Course menu option: ");                
                userInput = scanner.nextLine();
                while (!Arrays.stream(new String[]{"1", "2", "3", "4", "0"}).anyMatch(userInput :: equals)) {                        
                    System.out.print(PrinterHelper.ANSI_RED + "Enter a valid Course menu option: " + PrinterHelper.ANSI_RESET);
                    userInput = scanner.nextLine();
                }                
                break;
            case "yesOrNo" :
                System.out.print("Type [Y]es to proceed or [N]o to abort: ");                
                userInput = scanner.nextLine();
                while (!Arrays.stream(new String[]{"y", "Y", "Yes", "YES", "n", "N", "No", "NO"}).anyMatch(userInput :: equals)) {                        
                    System.out.print(PrinterHelper.ANSI_RED + "Type [Y]es to proceed or [N]o to abort: " + PrinterHelper.ANSI_RESET);
                    userInput = scanner.nextLine();
                }    
                break;
        }
        return userInput;
    }       
    
    public static boolean IsYesOrNo(){
        String userInput = ScanMenuInput("yesOrNo");
        return !(userInput.equalsIgnoreCase("n") || userInput.equalsIgnoreCase("no"));
    }
    
    public static void TerminateHendricks(){
        PrinterHelper.PrintTitle();
        System.out.println(PrinterHelper.ANSI_RED + "Terminating..." + PrinterHelper.ANSI_RESET);
    }
}