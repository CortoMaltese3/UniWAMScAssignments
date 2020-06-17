package hendricks.Models;

public class Course {

    public String Id;
    public String Title;
    public String Semester;    
    public String Professor;
    public String Profession;
    
    public Course(){
        
    }
    
    public Course(String[] parts){
        this.Id = parts[0];
        this.Title = parts[1];
        this.Semester = parts[2];
        this.Professor = parts[3];
        this.Profession = parts[4];
    }
}
