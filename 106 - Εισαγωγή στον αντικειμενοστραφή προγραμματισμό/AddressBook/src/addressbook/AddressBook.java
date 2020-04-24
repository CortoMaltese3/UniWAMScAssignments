/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package addressbook;

import java.util.List;
import java.util.Scanner;

public class AddressBook {

    public static void main(String[] args) {
        //printer();       
        
        while(true){
           mainMenu(); 
        }                       
    }

    private static void mainMenu() {
        
        Controller controller = new Controller();
        Scanner userInput = new Scanner(System.in);
        
        System.out.println("MAIN MENU");
        System.out.println("1. Show contacts");
        System.out.println("2. Add contact");
        System.out.println("3. Search by name");
        System.out.println("4. Search by phone number");
        System.out.println("5. Edit contact");
        System.out.println("6. Delete contact");
        System.out.println("7. Exit");
        
        System.out.println("\nOption: ");
        int choice = userInput.nextInt();
        userInput.nextLine();
        
        switch(choice){
        case 1:
            clearScreen();            
            GridView(controller.GetContacts(0, ""));  
            resetScreen();
            break;
         
        case 2:
            clearScreen();  
            GridView(controller.AddContact());
            resetScreen();
            break;
            
        case 3:
            clearScreen();            
            GridView(controller.GetContacts(1, "Giorgos"));
            resetScreen();
            break;
         
        case 4:
            clearScreen();            
            GridView(controller.GetContacts(2, "6945547845"));
            resetScreen();
            break;
        
        case 5:
            controller.EditContact();
            break;
         
        case 6:
            GridView(controller.DeleteContact("Giorgos"));
            resetScreen();
            break; 
            
       case 7:
            System.out.println("Terminating...");
            
            break;
         
        default:
            
            break;
            
        }       
        
    }
    
    public static void clearScreen() {  
        try{
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public static void resetScreen(){
        System.out.println("Press any key to continue...");
        Scanner userInput = new Scanner(System.in);
        userInput.nextLine();        
        clearScreen();
    }
    
        public static void GridView(List<Contact> contactList){
        String leftAlignFormat = "| %-15s | %-15s | %-15s | %-15s | %-30s | %-30s |%n";

        System.out.format("+-----------------+-----------------+-----------------+-----------------+--------------------------------+--------------------------------+%n");
        System.out.format("| First Name      | Last Name       | Primary Phone   | Secondary Phone | Email                          | Address                        |%n");
        System.out.format("+-----------------+-----------------+-----------------+-----------------+--------------------------------+--------------------------------+%n");
        for (int i = 0; i < contactList.size(); i++) {            
            System.out.format(leftAlignFormat, contactList.get(i).Name, contactList.get(i).Surname, contactList.get(i).PrimaryPhoneNumber, contactList.get(i).SecondaryPhoneNumber, 
                    contactList.get(i).Email, contactList.get(i).Address);
        }
        System.out.format("+-----------------+-----------------+-----------------+-----------------+--------------------------------+--------------------------------+%n");
    }
}
