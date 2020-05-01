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
    
//    public Contact GetContact(){
//        ArrayList<Contact> contactList = GetAddressBook();
//        ArrayList<Contact> matchingContactList = new ArrayList<>();
//        Contact rValue = null;
//        manager.PrintTitle(); 
//        String queryString = manager.UserInput(Type.FIRSTNAME);
//        for (int i = 0; i <contactList.size(); i++) {
//            String fullName = contactList.get(i).Name.concat(contactList.get(i).Surname);
//            if (manager.Contains(queryString, fullName)) {
//                matchingContactList.add(new Contact(contactList.get(i).Name, contactList.get(i).Surname, contactList.get(i).PrimaryPhoneNumber,
//                        contactList.get(i).SecondaryPhoneNumber, contactList.get(i).Email, contactList.get(i).Address));
//            }
//        }
//        if (matchingContactList.size() > 1) {
//            manager.PrintTitle();
//            manager.GridView(matchingContactList);                
//            System.out.println(Manager.ANSI_RED + "\nSearch string returned more than one entries. Try a more specific name. ");                
//            manager.ResetScreen();
//            matchingContactList.clear();                
//            GetContact(); 
//        }
//        else if(matchingContactList.size() > 1){               
//            System.out.println(Manager.ANSI_RED + "\nSearch string [" + queryString + "] returned more than one entries. Try a more specific name. ");            
//        }
//        else{
//            rValue.Name = matchingContactList.get(0).Name;
//            rValue.Surname = matchingContactList.get(0).Surname;
//            rValue.PrimaryPhoneNumber = matchingContactList.get(0).PrimaryPhoneNumber;
//            rValue.SecondaryPhoneNumber = matchingContactList.get(0).SecondaryPhoneNumber;
//            rValue.Email = matchingContactList.get(0).Email;
//            rValue.Address = matchingContactList.get(0).Address;
//        }
//        return rValue;
//    }

    public void AddContact(){        
        Contact newContact = manager.CreateContact();         
//        File file = new File(currentWorkingDirectory+"\\AddressBook.txt");
        AppendContact(newContact);
//        try {
//            FileWriter writer = new FileWriter(file, true);
//            writer.write("\r\n" + newContact.Name + "," + newContact.Surname + "," + newContact.PrimaryPhoneNumber + "," + newContact.SecondaryPhoneNumber 
//                                + "," + newContact.Email + "," + newContact.Address);
//            writer.close();
//        } catch (Exception ex) {
//            System.out.println(ex.getMessage());
//        }
    }
    
    public void AppendContact(Contact newContact){                       
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
    
    
    public void EditContact(ArrayList<Contact> oldContact){
        ArrayList<Contact> editedContactList = new ArrayList();
        manager.PrintTitle();    
        manager.GridView(oldContact); 
        System.out.println();
        
        String newFirstName = manager.UserInput(Type.FIRSTNAME);
        String newLastName = manager.UserInput(Type.LASTNAME);
        String newPrimaryPhoneNumber = manager.UserInput(Type.PRIMARYPHONE);
        String newSecondaryPhoneNumber = manager.UserInput(Type.SECONDARYPHONE);
        String newEmail = manager.UserInput(Type.EMAIL);
        String newAddress = manager.UserInput(Type.ADDRESS);
        
        Contact newContact = new Contact(newFirstName, newLastName, newPrimaryPhoneNumber, newSecondaryPhoneNumber, newEmail, newAddress);        
        AppendContact(newContact);
        editedContactList.add(newContact);
        manager.GridView(editedContactList); 
    }
    
    public void EditContact(){
        ArrayList<Contact> contactList = GetAddressBook();
        ArrayList<Contact> returnList = new ArrayList<>();
        ArrayList<Contact> matchingContactList = new ArrayList<>();
        manager.PrintTitle();    
        manager.GridView(contactList); 
        System.out.println();
        String queryString = manager.UserInput(Type.FIRSTNAME);
        
        for (int i = 0; i <contactList.size(); i++) {
            String addressBookEntry = contactList.get(i).Name.concat(contactList.get(i).Surname);
                if (!manager.Contains(queryString, addressBookEntry)) {
                    returnList.add(new Contact(contactList.get(i).Name, contactList.get(i).Surname, contactList.get(i).PrimaryPhoneNumber, 
                            contactList.get(i).SecondaryPhoneNumber, contactList.get(i).Email, contactList.get(i).Address));                    
                }
                else{
                    matchingContactList.add(new Contact(contactList.get(i).Name, contactList.get(i).Surname, contactList.get(i).PrimaryPhoneNumber, 
                            contactList.get(i).SecondaryPhoneNumber, contactList.get(i).Email, contactList.get(i).Address));
                }
        }
        manager.GridView(matchingContactList); 
        
        if (matchingContactList.isEmpty()) {
            System.out.println(Manager.ANSI_RED + "\nNo entry found matching the search criteria [" + queryString + "].");            
        }
        
        else if(matchingContactList.size() > 1){               
            System.out.println(Manager.ANSI_RED + "\nSearch string [" + queryString + "] returned more than one entries. Try a more specific name. ");            
        }
        else{
            System.out.println(Manager.ANSI_GREEN + "\nSearch string [" + queryString + "] returned this entry. Would you like to proceed? \r\n" + Manager.ANSI_RESET);    
            if (manager.IsYesOrNo()) {
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
            
            EditContact(matchingContactList);    
            System.out.println(Manager.ANSI_GREEN + "\nContact " + matchingContactList.get(0).Name + " " + matchingContactList.get(0).Surname + " was edited successfully!" + Manager.ANSI_RESET);              
            }          
        } 
    }
    

    
    public void DeleteContact(){
        ArrayList<Contact> contactList = GetAddressBook();
        ArrayList<Contact> returnList = new ArrayList<>();
        ArrayList<Contact> matchingContactList = new ArrayList<>();
        manager.PrintTitle();    
        manager.GridView(contactList); 
        System.out.println();
        String queryString = manager.UserInput(Type.FIRSTNAME);
        
        for (int i = 0; i <contactList.size(); i++) {
            String addressBookEntry = contactList.get(i).Name.concat(contactList.get(i).Surname);
                if (!manager.Contains(queryString, addressBookEntry)) {
                    returnList.add(new Contact(contactList.get(i).Name, contactList.get(i).Surname, contactList.get(i).PrimaryPhoneNumber, 
                            contactList.get(i).SecondaryPhoneNumber, contactList.get(i).Email, contactList.get(i).Address));                    
                }
                else{
                    matchingContactList.add(new Contact(contactList.get(i).Name, contactList.get(i).Surname, contactList.get(i).PrimaryPhoneNumber, 
                            contactList.get(i).SecondaryPhoneNumber, contactList.get(i).Email, contactList.get(i).Address));
                }
        }
        manager.GridView(matchingContactList); 
        if (matchingContactList.isEmpty()) {
            System.out.println(Manager.ANSI_RED + "\nNo entry found matching the search criteria [" + queryString + "].");            
        }
        
        else if(matchingContactList.size() > 1){               
            System.out.println(Manager.ANSI_RED + "\nSearch string [" + queryString + "] returned more than one entries. Try a more specific name. ");            
        }
        
        else{
            System.out.println(Manager.ANSI_RED + "\nSearch string [" + queryString + "] returned this entry. Would you like to proceed? \r\n" + Manager.ANSI_RESET);    
            if (manager.IsYesOrNo()) {
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
            System.out.println(Manager.ANSI_GREEN + "\nContact " + matchingContactList.get(0).Name + " " + matchingContactList.get(0).Surname + " was removed successfully!" + Manager.ANSI_RESET);              
            }          
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