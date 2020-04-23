/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package addressbook;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author giorg
 */
public class Controller {
    
    //public ArrayList<Contact> Contacts = new ArrayList<>();

    
    public List<Contact> GetContact(String searchName){
        
        ArrayList<Contact> contactList = new ArrayList<Contact>();
        contactList.add(new Contact("0", "Giorgos", "Kalomalos", "2104547889", "6987458774", "g.kalomalos@uniwa.gr", "Rodopoleos 150, Athina 11361"));
        contactList.add(new Contact("1", "Afroditi", "Aktypi", "2104888774", "6998774547", "a.aktypi@uniwa.gr", "Anemou 4, Peiraias 18774"));
        
//        List<Contact> result = new ArrayList<Contact>();
//        Contact contact = new Contact();
//        result = contact.ContactList;
//        
        for (int i = 0; i < contactList.size(); i++) {
            System.out.println(FullString(contactList.get(i).Id) + FullString(contactList.get(i).Name) + FullString(contactList.get(i).Surname) + FullString(contactList.get(i).PrimaryPhoneNumber) + FullString(contactList.get(i).SecondaryPhoneNumber) + FullString(contactList.get(i).Email) + FullString(contactList.get(i).Address));            
        }
        
        

                
        return contactList;
        
    }
    
    public void AddContact(){
           
    }
    
    public void EditContact(){
        
    }
    
    public void DeleteContact(){
        
    }
    
    public static String FullString(String s){
        while(s.length() < 25){
            s = s + " ";
        }
        
        return s;
        
        
    }

}

