package addressbook;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Manager {
    public static final String ANSI_RESET  = "\u001B[0m";
    public static final String ANSI_RED    = "\u001B[31m";
    public static final String ANSI_GREEN  = "\u001B[32m";
    
    
    public String UserInput(Type type){
        ArrayList<String> messageList = MessageList();
        ArrayList<String> messageWarningList = MessageWarningList();
        String userInput = null;        
        switch(type){
            case FIRSTNAME:
                userInput = ScanUserInput(messageList.get(0));
                while(!IsValidUserInput(userInput, Type.FIRSTNAME)){
                    PrintTitle();
                    userInput = ScanUserInput(messageList.get(0), messageWarningList.get(0));
                }
                break;
            case LASTNAME:
                userInput = ScanUserInput(messageList.get(1));
                while(!IsValidUserInput(userInput, Type.LASTNAME)){
                    PrintTitle();
                    userInput = ScanUserInput(messageList.get(1), messageWarningList.get(0));
                }
                break;
            case PRIMARYPHONE:
                userInput = ScanUserInput(messageList.get(2));
                while(!IsValidUserInput(userInput, Type.PRIMARYPHONE)){
                    PrintTitle();
                    userInput = ScanUserInput(messageList.get(2), messageWarningList.get(1));
                }
                break;            
            case SECONDARYPHONE:
                userInput = ScanUserInput(messageList.get(3));
                while(!IsValidUserInput(userInput, Type.SECONDARYPHONE)){
                    PrintTitle();
                    userInput = ScanUserInput(messageList.get(3), messageWarningList.get(1));
                }
                break;             
            case EMAIL:
                userInput = ScanUserInput(messageList.get(4));
                while(!IsValidUserInput(userInput, Type.EMAIL)){
                    PrintTitle();
                    userInput = ScanUserInput(messageList.get(4));
                }
                break; 
            case ADDRESS:
                userInput = ScanUserInput(messageList.get(5));
                while(!IsValidUserInput(userInput, Type.ADDRESS)){
                    PrintTitle();
                    userInput = ScanUserInput(messageList.get(5));
                }
                break;   
            case MAINMENU:
                userInput = ScanUserInput(messageList.get(6));
                while(!IsValidUserInput(userInput, Type.MAINMENU)){
                    PrintTitle();
                    userInput = ScanUserInput(messageList.get(6));
                }
                break;                
        }
        return Sanitize(type, userInput);
    }

    private String ScanUserInput(String inputMessage, String warningMessage){  
        System.out.print(ANSI_RED + warningMessage);
        System.out.print(ANSI_RESET + inputMessage);
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();
        return userInput;
    }
    
    private String ScanUserInput(String inputMessage){          
        System.out.print(inputMessage);
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();
        return userInput;
    }
    
    private String Sanitize(Type type, String userInput){
        if(type.equals(Type.FIRSTNAME) || type.equals(Type.LASTNAME)){
            userInput = userInput.replace(",", "");
        }
        else if(type.equals(Type.PRIMARYPHONE) || type.equals(Type.SECONDARYPHONE)){
            userInput = userInput.replace(",", "").replace("-", "").replace(" ", "".replace("-", ""));
        }
        else if(type.equals(Type.EMAIL) || type.equals(Type.ADDRESS)){
            if(IsNullOrEmpty(userInput)){
                userInput += "-";
            }
            userInput = userInput.replace(",", "");
        }
        return userInput;
    }
    
    private boolean IsValidUserInput(String userInput, Type type){        
        
        if(type.equals(type.FIRSTNAME) || type.equals(type.LASTNAME)){
            if (userInput.length() > 20 || IsNullOrEmpty(userInput))
                return false;
        }
        if(type.equals(type.PRIMARYPHONE) || type.equals(type.SECONDARYPHONE)){
            if (!userInput.matches("[0-9]+") || userInput.length() > 15 || IsNullOrEmpty(userInput))
                return false;
        }
        //TODO: Insert email validation
        if(type.equals(type.EMAIL) || type.equals(type.ADDRESS)){
            if(userInput.length() > 40)
                return true;          
        }
        if(type.equals(type.MAINMENU)){            
            List<String> mainMenuOptions = Arrays.asList("1","2","3","4","5","6","7");            
            if(!mainMenuOptions.contains(userInput))
                return false;          
        }        
        return true;
    }
    
    public static void PrintTitle(){
        ClearScreen();
        String logo = "  ___        __   __            ___            _   \n" +
                   " / __| ___  / _| / _| ___  ___ | _ ) ___  ___ | |__\n" +
                   "| (__ / _ \\|  _||  _|/ -_)/ -_)| _ \\/ _ \\/ _ \\| / /\n" +
                   " \\___|\\___/|_|  |_|  \\___|\\___||___/\\___/\\___/|_\\_\\";
        System.out.println(logo);
        System.out.println("-----------------Giorgos Kalomalos-----------------\n\n");
    }
    
    public void PrintMainMenuOptions(){
        PrintTitle();
        System.out.println("MAIN MENU\n");
        System.out.println("1. Show contacts");
        System.out.println("2. Add contact");
        System.out.println("3. Search by name");
        System.out.println("4. Search by phone number");
        System.out.println("5. Edit contact");
        System.out.println("6. Delete contact");
        System.out.println("7. Exit\n");   
    }
    
    public boolean IsNullOrEmpty(String userInput){
        return !(userInput!= null && !userInput.isEmpty());
    }
    
    public boolean Contains(String queryString, String addressBookEntry){
        return addressBookEntry.contains(queryString);               
    }
    
    public String GetCurrentWorkingFolderPath(){
        String currentDirectory = null;                              
        try{            
            currentDirectory = System.getProperty("user.dir");
            return currentDirectory;
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return currentDirectory;
    }
    
    public void InitializeAddressBook(){
        File file = new File(GetCurrentWorkingFolderPath()+"\\AddressBook.txt");
        if(!file.exists()){
            try {
                file.createNewFile();
                FileWriter writer = new FileWriter(file, true);
                BufferedWriter bufWriter = new BufferedWriter(writer);
                bufWriter.write("Giorgos,Kalomalos,2104547889,6987458774,g.kalomalos@uniwa.gr,Rodopoleos 150 Athina 11361");
                bufWriter.write("\r\nAfroditi,Aktypi,2104888774,6998774547,a.aktypi@uniwa.gr,Anemou 4 Peiraias 18774");
                bufWriter.write("\r\nMaria,Lagoudi,2791054785,6978558747,maria.lagoudi@gmail.com,Katsavraha 12 Palaiohori");
                bufWriter.write("\r\nIoanna,Stathoulopoulou,6945547845,-,ioanna@ioanna.gr,-");
                bufWriter.write("\r\nThanasis,Karatassos,2721024789,6987457885,thanasis.kara@uniwa.gr,Tektonon 12 Keratsini 18755");
                bufWriter.close();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
    
    public Contact CreateContact(){
        PrintTitle();
        String firstName = UserInput(Type.FIRSTNAME);
        String lastName = UserInput(Type.LASTNAME);
        String primaryPhone = UserInput(Type.PRIMARYPHONE);
        String secondaryPhone = UserInput(Type.SECONDARYPHONE);
        String email = UserInput(Type.EMAIL);
        String address = UserInput(Type.ADDRESS);
        Contact newContact = new Contact(firstName, lastName, primaryPhone, secondaryPhone, email, address);                
        return newContact;                        
    }

    public static void ClearScreen() {
        try{
            if (System.getProperty("os.name").contains("Windows"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else
                Runtime.getRuntime().exec("clear");
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }    
    
    public static void ResetScreen(){        
        System.out.print(ANSI_RESET + "\r\nPress any key to continue...");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();        
        ClearScreen();
    }   
    
    public static void GridView(List<Contact> contactList){
        PrintTitle();        
        String leftAlignFormat = "| %-20s | %-20s | %-15s | %-15s | %-40s | %-40s |%n";
        System.out.format("+----------------------+----------------------+-----------------+-----------------+------------------------------------------+------------------------------------------+%n");
        System.out.format("| First Name           | Last Name            | Primary Phone   | Secondary Phone | Email                                    | Address                                  |%n");
        System.out.format("+----------------------+----------------------+-----------------+-----------------+------------------------------------------+------------------------------------------+%n");
        for (int i = 0; i < contactList.size(); i++) {            
            System.out.format(leftAlignFormat, contactList.get(i).Name, contactList.get(i).Surname, contactList.get(i).PrimaryPhoneNumber, contactList.get(i).SecondaryPhoneNumber, 
                    GetSubString(contactList.get(i).Email), GetSubString(contactList.get(i).Address));
        }
        System.out.format("+----------------------+----------------------+-----------------+-----------------+------------------------------------------+------------------------------------------+%n");
    }
    
    private static String GetSubString(String string){
        if(string.length() > 40){
            return string.substring(0, 39);
        }
        else{
            return string;
        }            
    }
    
    public enum Type{
        FIRSTNAME,
        LASTNAME,
        PRIMARYPHONE,
        SECONDARYPHONE,
        EMAIL,
        ADDRESS,
        MAINMENU
    }
    
    
    private ArrayList<String> MessageList(){
        ArrayList<String> messageList = new ArrayList<>();
        messageList.add("Enter name: ");//0
        messageList.add("Enter last name: ");//1
        messageList.add("Enter phone number: ");//2
        messageList.add("Enter secondary phone number: ");//3
        messageList.add("Enter email: ");//4
        messageList.add("Enter address: ");//5
        messageList.add("Enter a valid menu option: ");//6
        return messageList;
    }
    
    private ArrayList<String> MessageWarningList(){
        ArrayList<String> messageWarningList = new ArrayList<>();
        messageWarningList.add("Requirements: Cannot be empty or longer than 20 characters.\r\n\n");//0        
        messageWarningList.add("Requirements: Cannot be empty, contain characters or longer than 15 characters.\r\n\n");//1        
        messageWarningList.add("Requirements: Not specified.");//2      
        messageWarningList.add("Search string returned more than one entries. Try a more specific name.\r\n\n");//3
        return messageWarningList;
    }
}