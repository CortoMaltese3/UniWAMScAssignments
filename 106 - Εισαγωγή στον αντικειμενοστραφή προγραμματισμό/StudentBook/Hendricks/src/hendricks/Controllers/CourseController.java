package hendricks.Controllers;
import hendricks.Providers.CourseProvider;
import hendricks.Models.Course;
import java.util.List;
import hendricks.Helpers.PrinterHelper;

public class CourseController {
    
    public static List<Course> GetCourses(){
        List<Course> courses = CourseProvider.GetCourses();
        PrinterHelper.CourseGridView(courses);
        return courses;
    }
    
    public static Course GetCourse(String id){
        Course course = CourseProvider.GetCourse(id);
        return course;
    }
    
    public static void CreateCourse(Course course){
        
        
        CourseProvider.AddCourse(course);
    }
    
    public static void EditCourse(String id){
        //Course course = GetCourse(id);
        //CourseProvider.EditCourse(id);
        
    }
    
    public static void DeleteCourse(String id){
        CourseProvider.DeleteCourse(id);
    }
    
}
