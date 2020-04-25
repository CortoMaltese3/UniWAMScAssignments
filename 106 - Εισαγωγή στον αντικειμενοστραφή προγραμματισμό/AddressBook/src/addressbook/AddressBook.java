package addressbook;

public class AddressBook {
    private static final Controller controller = new Controller();
    private static final Manager manager = new Manager();
    
    public static void main(String[] args) { 
        manager.InitializeAddressBook();
        while(mainMenu()){           
        }                       
    }

    private static boolean mainMenu() {
        manager.PrintMainMenuOptions();
        String userMenuOption = manager.ScanUserMenuOption(); 
        manager.ClearScreen();
                
        if (userMenuOption.equals("1")) {                      
            manager.GridView(controller.GetContacts(0));  
            manager.ResetScreen();            
        }
        
        if (userMenuOption.equals("2")) {            
            controller.AddContact();
            manager.ResetScreen();            
        }
        
        if (userMenuOption.equals("3")) {                        
            manager.GridView(controller.GetContacts(1));
            manager.ResetScreen();            
        }
        
        if (userMenuOption.equals("4")) {             
            manager.GridView(controller.GetContacts(2));
            manager.ResetScreen();            
        }
        
        if (userMenuOption.equals("5")) {
            controller.EditContact();            
        }
        
        if (userMenuOption.equals("6")) {
            controller.DeleteContact();
            manager.ResetScreen();           
        }
        
        if (userMenuOption.equals("7")) {            
            System.out.println("Terminating...");         
            return false;
        }
        manager.PrintMainMenuOptions();
        return true;        
    }      
}