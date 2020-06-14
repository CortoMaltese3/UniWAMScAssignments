package hendricks.Models;

import java.util.ArrayList;

public class Professor extends Entity{
    
    public String Profession;    
    public ArrayList<Course> Courses;
    
    public Professor(){
        
    }
    
    public Professor(String[] parts) {
        this.Id = parts[0];
        this.Name = parts[1];
        this.Email = parts[2];
        this.PhoneNumber = parts[3];
        this.Profession = parts[4];
    } 
    
    
    
    public Professor(String[] parts, ArrayList<Course> courses) {
        this.Id = parts[0];
        this.Name = parts[1];
        this.Email = parts[2];
        this.PhoneNumber = parts[3];
        this.Profession = parts[4];
        this.Courses = courses;
    }   
}
