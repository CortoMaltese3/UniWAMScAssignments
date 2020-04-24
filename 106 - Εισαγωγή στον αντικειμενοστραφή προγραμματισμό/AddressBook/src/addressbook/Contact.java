/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package addressbook;

import java.util.ArrayList;

/**
 *
 * @author Giorgos Kalomalos
 */
public class Contact {
    
    public Contact(String name, String surname, String primaryPhoneNumber, String secondaryPhoneNumber, String email, String address){
        Name = name;
        Surname = surname;
        PrimaryPhoneNumber = primaryPhoneNumber;
        SecondaryPhoneNumber = secondaryPhoneNumber;
        Email = email;
        Address = address;
        
        
        
    }
    
    public String Name;
    public String Surname;
    public String PrimaryPhoneNumber;
    public String SecondaryPhoneNumber;
    public String Email;
    public String Address;
        
    public ArrayList<Contact> ContactList;

    Contact() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

