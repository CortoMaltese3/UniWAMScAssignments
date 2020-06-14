package hendricks.Models;

import java.util.ArrayList;

public class Student extends Entity{
    public String Semester;
    public ArrayList<Course> Courses;
    
    public Student(){
        
    }

    public Student(String[] parts, ArrayList<Course> courses) {
        this.Id = parts[0];
        this.Name = parts[1];
        this.Email = parts[2];
        this.PhoneNumber = parts[3];
        this.Semester = parts[4];
        this.Courses = courses;   
    }   
}
