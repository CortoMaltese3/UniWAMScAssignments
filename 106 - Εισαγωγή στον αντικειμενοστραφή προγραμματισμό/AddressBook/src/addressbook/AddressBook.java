package addressbook;
import addressbook.Manager.Type;
import java.util.ArrayList;

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
        String userMenuOption = manager.UserInput(Type.MAINMENU);
                
        if (userMenuOption.equals("1")) {
            manager.GridView(controller.GetContacts(0));
        }
        
        if (userMenuOption.equals("2")) {
            controller.AddContact();
        }
        
        if (userMenuOption.equals("3")) {
            manager.GridView(controller.GetContacts(1));
        }
        
        if (userMenuOption.equals("4")) {
            manager.GridView(controller.GetContacts(2));
        }
        
        if (userMenuOption.equals("5")) {
            ArrayList<Contact> selectedContact = controller.GetContacts(3);
            manager.GridView(selectedContact);
            controller.EditContact(selectedContact);
        }
        
        if (userMenuOption.equals("6")) {
            controller.DeleteContact();
        }
        
        if (userMenuOption.equals("7")) {            
            controller.Terminate();
            return false;
        }
        //manager.ResetScreen();
        return true;
    }
}