package addressbook;
import java.util.ArrayList;

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
       
}