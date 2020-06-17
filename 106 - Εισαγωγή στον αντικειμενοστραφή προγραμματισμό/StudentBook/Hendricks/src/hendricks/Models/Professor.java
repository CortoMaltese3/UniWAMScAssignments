package hendricks.Models;

public class Professor extends Entity{
    
    public String Profession;    
    public Course Course;

    public Professor(){
        
    }
    
    public Professor(String[] parts) {
        this.Id = parts[0];
        this.Name = parts[1];
        this.Email = parts[2];
        this.PhoneNumber = parts[3];
        this.Profession = parts[4];
    } 
    
    public Professor(String[] parts, Course course) {
        this.Id = parts[0];
        this.Name = parts[1];
        this.Email = parts[2];
        this.PhoneNumber = parts[3];
        this.Profession = parts[4];
        this.Course = course;
    }   
}
