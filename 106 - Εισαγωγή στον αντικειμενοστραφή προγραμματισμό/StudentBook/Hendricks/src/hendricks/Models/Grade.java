
package hendricks.Models;

import hendricks.Providers.CourseProvider;
import hendricks.Providers.StudentProvider;

public class Grade {
    public Student Student;
    public Course Course;
    public int score;
    
    public Grade(){
        this.score = 0;
    }
    
    public Grade(String[] parts){
        this.Student = StudentProvider.GetStudent(parts[0]);
        this.Course = CourseProvider.GetCourse(parts[1]);
        this.score = Integer.parseInt(parts[2]);
    }
    
}
