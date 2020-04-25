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
    public String UserStringInput(){    
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();
        while(IsNullOrEmpty(userInput)){
            System.out.println("The field is required.");
            userInput = scanner.nextLine();
        }
        userInput = userInput.replace(",", "");          
        return userInput;     
    }
    
    public String ScanUserMenuOption(){                 
        
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter menu option: ");
        String userInput = scanner.nextLine();
        while(!IsValidMenuOption(userInput)){
            PrintMainMenuOptions();
            System.out.println("Enter a valid menu option: ");
            userInput = scanner.nextLine();            
        }
        return userInput;
    }
    
    public void PrintMainMenuOptions(){
        ClearScreen();
        System.out.println("MAIN MENU");
        System.out.println("1. Show contacts");
        System.out.println("2. Add contact");
        System.out.println("3. Search by name");
        System.out.println("4. Search by phone number");
        System.out.println("5. Edit contact");
        System.out.println("6. Delete contact");
        System.out.println("7. Exit\n");   
    }
    
    public boolean IsValidMenuOption(String userOption){        
        ArrayList<String> validMenuOptions = new ArrayList<>();
        List<String> options = Arrays.asList("1","2","3","4","5","6","7");
        validMenuOptions.addAll(options);        
        return validMenuOptions.contains(userOption);
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
    
    public String QueryContact(){
        
        String name = UserStringInput();                
        return name;
    }
    
    public Contact CreateContact(){        
        System.out.println("Enter first name: ");        
        String firstName = UserStringInput();
        System.out.println("Enter last name: ");
        String lastName = UserStringInput();
        System.out.println("Enter primary phone number: ");
        String primaryPhone = UserStringInput();
        System.out.println("Enter secondary phone number: ");
        String secondaryPhone = UserStringInput();
        System.out.println("Enter email: ");
        String email = UserStringInput();
        System.out.println("Enter address: ");
        String address = UserStringInput();
        
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
        System.out.println("Press any key to continue...");
        Scanner userInput = new Scanner(System.in);
        userInput.nextLine();        
        ClearScreen();
    }
    
    public static void GridView(List<Contact> contactList){
        String leftAlignFormat = "| %-20s | %-20s | %-15s | %-15s | %-30s | %-30s |%n";
        System.out.format("+----------------------+----------------------+-----------------+-----------------+--------------------------------+--------------------------------+%n");
        System.out.format("| First Name           | Last Name            | Primary Phone   | Secondary Phone | Email                          | Address                        |%n");
        System.out.format("+----------------------+----------------------+-----------------+-----------------+--------------------------------+--------------------------------+%n");
        for (int i = 0; i < contactList.size(); i++) {            
            System.out.format(leftAlignFormat, contactList.get(i).Name, contactList.get(i).Surname, contactList.get(i).PrimaryPhoneNumber, contactList.get(i).SecondaryPhoneNumber, 
                    contactList.get(i).Email, contactList.get(i).Address);
        }
        System.out.format("+----------------------+----------------------+-----------------+-----------------+--------------------------------+--------------------------------+%n");
    }
}
