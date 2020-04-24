/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package addressbook;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Giorgos Kalomalos
 */
public class Controller {
    
    //public ArrayList<Contact> Contacts = new ArrayList<>();

    
    public ArrayList<Contact> GetContacts(int searchOption, String queryString){
        
        ArrayList<Contact> contactList = GetAddressBook();
        ArrayList<Contact> returnList = new ArrayList<Contact>();
        
        if (searchOption == 1) {
            for (int i = 0; i <contactList.size(); i++) {
                String fullName = contactList.get(i).Name.concat(contactList.get(i).Surname);
                if (Contains(queryString, fullName)) {
                    returnList.add(new Contact(contactList.get(i).Name, contactList.get(i).Surname, contactList.get(i).PrimaryPhoneNumber, contactList.get(i).SecondaryPhoneNumber, 
                            contactList.get(i).Email, contactList.get(i).Address));                    
                }
            }
            return returnList; 
        }        
        if (searchOption == 2) {
            for (int i = 0; i <contactList.size(); i++) {
                String phoneNumbers = contactList.get(i).PrimaryPhoneNumber.concat(contactList.get(i).SecondaryPhoneNumber);
                if (!Contains(queryString, phoneNumbers)) {
                    returnList.add(new Contact(contactList.get(i).Name, contactList.get(i).Surname, contactList.get(i).PrimaryPhoneNumber, contactList.get(i).SecondaryPhoneNumber, 
                            contactList.get(i).Email, contactList.get(i).Address));   
                }
            }  
            return returnList; 
        }        
        return contactList;          
    }
    

    
    public ArrayList<Contact> AddContact(){
        ArrayList<Contact> addressBook = GetAddressBook();
        addressBook.add(new Contact("Giorgos", "Kalomalos", "2104547889", "6987458774", "g.kalomalos@uniwa.gr", "Rodopoleos 150, Athina 11361"));

        return addressBook;
    }
    
    public void EditContact(){
        
    }
    
    public ArrayList<Contact> DeleteContact(String queryString){
        
        ArrayList<Contact> contactList = GetAddressBook();
        ArrayList<Contact> returnList = new ArrayList<Contact>();
        
        for (int i = 0; i <contactList.size(); i++) {
            String fullName = contactList.get(i).Name.concat(contactList.get(i).Surname);
                if (!Contains(queryString, fullName)) {
                    returnList.add(new Contact(contactList.get(i).Name, contactList.get(i).Surname, contactList.get(i).PrimaryPhoneNumber, contactList.get(i).SecondaryPhoneNumber, 
                    contactList.get(i).Email, contactList.get(i).Address));                    
                }
        }   
        return returnList;
    }
    
    public ArrayList<Contact> GetAddressBook(){        
    ArrayList<Contact> addressBook = new ArrayList<Contact>();
    addressBook.add(new Contact("Giorgos", "Kalomalos", "2104547889", "6987458774", "g.kalomalos@uniwa.gr", "Rodopoleos 150, Athina 11361"));
    addressBook.add(new Contact("Afroditi", "Aktypi", "2104888774", "6998774547", "a.aktypi@uniwa.gr", "Anemou 4, Peiraias 18774"));
    addressBook.add(new Contact("Maria", "Lagoudi", "2791054785", "6978558747", "maria.lagoudi@gmail.com", "Katsavraha 12, Palaiohori"));
    addressBook.add(new Contact("Ioanna", "Stathoulopoulou", "6945547845", "-", "ioanna@ioanna.gr", "-"));
    addressBook.add(new Contact("Giorgos", "Karatassos", "2721024789", "6987457885", "a.aktypi@uniwa.gr", "Tektonon 12, Keratsini 18755"));

    return addressBook;
}
    
    public static String FullString(String s){
        while(s.length() < 25){
            s = s + " ";
        }        
        return s;                
    }
    
    private boolean Contains(String queryString, String addressBookEntry){
        return addressBookEntry.contains(queryString);               
    }
    

}

