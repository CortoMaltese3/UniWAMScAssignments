/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package addressbook;

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
            try{
                Runtime.getRuntime().exec("cls");
            }
            catch(Exception e){
                System.out.println(e.getMessage());
            }
            
            controller.GetContact("");
            userInput.nextLine();
            break;
         
        case 2:
            controller.AddContact();
            break;
            
        case 3:
            controller.GetContact("");
            break;
         
        case 4:
            controller.GetContact("");
            break;
        
        case 5:
            controller.EditContact();
            break;
         
        case 6:
            controller.DeleteContact();
            break; 
            
       case 7:
            
            break;
         
        default:
            
            break;
            
        }       
        
    }
    
    public static void clearScreen() {  
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
   }
}
