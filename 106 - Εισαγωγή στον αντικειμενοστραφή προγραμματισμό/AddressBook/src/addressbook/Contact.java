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
    
//    public Contact(){
//        ArrayList<Contact> ContactList = new ArrayList<Contact>();
////        ContactList.add(Id = "0", Name = "Giorgos", Surname = "Kalomalos", primaryPhoneNumber = "2108745745", secondaryPhoneNumber = "6978745874", Email = "giorgos.kalomalos@uniwa.gr", Address = "Rodopoleos 160, Athina 11361");
////        ContactList.add(this.Id = "0", this.Name = "Giorgos", this.Surname = "Kalomalos");
//        ContactList.add(new Contact(this.Id = "0"));
//    }
    
    public String Id;
    public String Name;
    public String Surname;
    public String primaryPhoneNumber;
    public String secondaryPhoneNumber;
    public String Email;
    public String Address;
        
    public ArrayList<Contact> ContactList;
}

