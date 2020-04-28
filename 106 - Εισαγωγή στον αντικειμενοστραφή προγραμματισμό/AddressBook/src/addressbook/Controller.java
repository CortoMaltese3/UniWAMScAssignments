package addressbook;
import addressbook.Manager.Type;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

public class Controller {    
    private final Manager manager = new Manager();
    private final String currentWorkingDirectory = manager.GetCurrentWorkingFolderPath();

    public ArrayList<Contact> GetContacts(int searchOption){
        ArrayList<Contact> contactList = GetAddressBook();
        ArrayList<Contact> returnQueryList = new ArrayList<>();
        
        if (searchOption == 1) {
            manager.PrintTitle();            
            String queryString = manager.UserInput(Type.FIRSTNAME);
            for (int i = 0; i <contactList.size(); i++) {
                String fullName = contactList.get(i).Name.concat(contactList.get(i).Surname);
                if (manager.Contains(queryString, fullName)) {
                    returnQueryList.add(new Contact(contactList.get(i).Name, contactList.get(i).Surname, contactList.get(i).PrimaryPhoneNumber,
                            contactList.get(i).SecondaryPhoneNumber, contactList.get(i).Email, contactList.get(i).Address)); 
                }
            }
            return returnQueryList;
        }        
        else if (searchOption == 2) {
            manager.PrintTitle();            
            String queryString = manager.UserInput(Type.PRIMARYPHONE);
            for (int i = 0; i <contactList.size(); i++) {
                String phoneNumbers = contactList.get(i).PrimaryPhoneNumber.concat(contactList.get(i).SecondaryPhoneNumber);
                if (manager.Contains(queryString, phoneNumbers)) {
                    returnQueryList.add(new Contact(contactList.get(i).Name, contactList.get(i).Surname, contactList.get(i).PrimaryPhoneNumber,
                            contactList.get(i).SecondaryPhoneNumber, contactList.get(i).Email, contactList.get(i).Address));
                }
            }
            return returnQueryList;
        }      
        else if (searchOption == 3){
            manager.PrintTitle(); 
            String queryString = manager.UserInput(Type.FIRSTNAME);
            for (int i = 0; i <contactList.size(); i++) {
                String fullName = contactList.get(i).Name.concat(contactList.get(i).Surname);
                if (manager.Contains(queryString, fullName)) {
                    returnQueryList.add(new Contact(contactList.get(i).Name, contactList.get(i).Surname, contactList.get(i).PrimaryPhoneNumber,
                            contactList.get(i).SecondaryPhoneNumber, contactList.get(i).Email, contactList.get(i).Address));
                }
            }
            if (returnQueryList.size() > 1) {
                manager.PrintTitle();
                manager.GridView(returnQueryList);                
                System.out.println(Manager.ANSI_RED + "\nSearch string returned more than one entries. Try a more specific name. ");                
                manager.ResetScreen();
                returnQueryList.clear();                
                GetContacts(3); 
            }                                     
            return returnQueryList;                                               
        }
        return contactList;
    }

    public void AddContact(){        
        Contact newContact = manager.CreateContact();         
        File file = new File(currentWorkingDirectory+"\\AddressBook.txt");
        
        try {
            FileWriter writer = new FileWriter(file, true);
            writer.write("\r\n" + newContact.Name + "," + newContact.Surname + "," + newContact.PrimaryPhoneNumber + "," + newContact.SecondaryPhoneNumber 
                                + "," + newContact.Email + "," + newContact.Address);
            writer.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void EditContact(ArrayList<Contact> selectedContact){
        ArrayList<Contact> contactList = GetAddressBook();
        ArrayList<Contact> returnList = new ArrayList<>();
        
        if (selectedContact.isEmpty()) {
            manager.PrintTitle();
            System.out.println(Manager.ANSI_RED + "\nNo entry found. Try another name. ");
            manager.ResetScreen();
        }
        else{
            String queryString = selectedContact.get(0).Name.concat(selectedContact.get(0).Surname);
            for (int i = 0; i <contactList.size(); i++) {
            String addressBookEntry = contactList.get(i).Name.concat(contactList.get(i).Surname);
                if (!manager.Contains(queryString, addressBookEntry)) {
                    returnList.add(new Contact(contactList.get(i).Name, contactList.get(i).Surname, contactList.get(i).PrimaryPhoneNumber, 
                            contactList.get(i).SecondaryPhoneNumber, contactList.get(i).Email, contactList.get(i).Address));                    
                }
        }
        AddContact();
        }
    }
    
    public void DeleteContact(){
        ArrayList<Contact> contactList = GetAddressBook();
        ArrayList<Contact> returnList = new ArrayList<>();
        manager.PrintTitle();
        //System.out.println("Enter first or last name: ");        
        String queryString = manager.UserInput(Type.FIRSTNAME);
        
        for (int i = 0; i <contactList.size(); i++) {
            String addressBookEntry = contactList.get(i).Name.concat(contactList.get(i).Surname);
                if (!manager.Contains(queryString, addressBookEntry)) {
                    returnList.add(new Contact(contactList.get(i).Name, contactList.get(i).Surname, contactList.get(i).PrimaryPhoneNumber, 
                            contactList.get(i).SecondaryPhoneNumber, contactList.get(i).Email, contactList.get(i).Address));                    
                }
        }                                  
        File filePath = new File(currentWorkingDirectory+"\\AddressBook.txt");
        
        try {
            FileWriter writer = new FileWriter(filePath);            
            writer.write("");          
            for (int i = 0; i < returnList.size(); i++) {
                if (i != returnList.size()-1) 
                    writer.write(returnList.get(i).Name + "," + returnList.get(i).Surname + "," + returnList.get(i).PrimaryPhoneNumber + "," +
                                 returnList.get(i).SecondaryPhoneNumber + "," + returnList.get(i).Email + "," + returnList.get(i).Address + "\r\n");                
                else
                    writer.write(returnList.get(i).Name + "," + returnList.get(i).Surname + "," + returnList.get(i).PrimaryPhoneNumber + "," +
                                 returnList.get(i).SecondaryPhoneNumber + "," + returnList.get(i).Email + "," + returnList.get(i).Address);                
            }
            writer.close();            
        } catch (Exception ex){
            System.out.println(ex.getMessage()); 
        }         
    }
    
    public void Terminate(){
        manager.PrintTitle();
        System.out.println(Manager.ANSI_RED + "Terminating..." + Manager.ANSI_RESET);
        manager.ResetScreen();        
    }
    
    public ArrayList<Contact> GetAddressBook(){
        ArrayList<Contact> addressBook = new ArrayList<>();
        try {
            File file = new File(currentWorkingDirectory+"\\AddressBook.txt");
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            String[] parts;
            
            while((line = reader.readLine()) != null) {
                parts = line.split("\\,");
                addressBook.add(new Contact(parts[0], parts[1], parts[2], parts[3], parts[4], parts[5])); 
            }            
            reader.close();                         
        } 
        catch (Exception ex) {
            System.out.println(ex.getMessage());            
        }
        return addressBook; 
    }
    
    public static String FullString(String s){
        while(s.length() < 25){
            s = s + " ";
        }        
        return s;                
    }
}