package hendricks.Providers;
import hendricks.Models.Course;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class CourseProvider extends AllAroundProvider{
    private static final String COURSES_FILE = "\\Courses.txt";
    
    public static void WriteToCoursesFile(ArrayList<Course> courses){
        for (int i = 0; i < courses.size(); i++) {
            try {
            FileWriter writer = new FileWriter(COURSES_FILE, true);
            writer.write("\r\n" + courses.get(i).Id + "|" + courses.get(i).Title + "|" + courses.get(i).Semester + "|" + courses.get(i).Professor + "|" + courses.get(i).Profession);
            writer.close();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
    
    public static ArrayList<Course> GetCourses(String coursesIdsDelimited){
        ArrayList<Course> courses = new ArrayList<>();
        try {
            String[] coursesIdsToStringArray = coursesIdsDelimited.split(",");
            List<String> courseIdsToList = Arrays.asList(coursesIdsToStringArray); 
            ArrayList<String> courseIdsToArrayList = new ArrayList<>(courseIdsToList);
        
            for (int i = 0; i < courseIdsToArrayList.size(); i++) {
                courses.add(GetCourse(courseIdsToArrayList.get(i)));
            }
            return courses;
        } catch (Exception e) {
            return courses;
        }
    }
    
    public static ArrayList <Course> GetCourses(){
        ArrayList <Course> courses = new ArrayList<>();
        try {                  
            //BufferedReader reader = new BufferedReader(new FileReader(COURSES_FILE));
            BufferedReader reader = CreateBufferedReader(COURSES_FILE);
            String line;
            String[] parts;
            
            while((line = reader.readLine()) != null) {
                parts = line.split("\\|");
//                courses.add(new Course(parts[0], parts[1], parts[2], parts[3], parts[4])); 
                courses.add(new Course(parts)); 
            }            
            reader.close(); 
        } catch (IOException ex) {
            System.out.println(ex.getMessage());   
        }
        courses.sort(Comparator.comparing((course) -> course.Id));        
        return courses;
    }
    
    public static Course GetCourse(String id){
        Course course = new Course();
        ArrayList<Course> courses = GetCourses();
        for (int i = 0; i <courses.size(); i++) {
                if (courses.get(i).Id == null ? id == null : courses.get(i).Id.equals(id)) {
                course = courses.get(i);
            } 
        }
        return course;
    }
    
    public static void AddCourse(Course newCourse){        
        try {
            FileWriter writer = CreateFileWriter(COURSES_FILE, true);
            writer.write("\r\n" + newCourse.Id + "|" + newCourse.Title + "|" + newCourse.Semester + "|" + newCourse.Professor + "|" + newCourse.Profession);
            writer.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public static void EditCourse(Course oldCourse, Course newCourse){
        ArrayList<Course> courses = GetCourses();
        courses.set(courses.indexOf(oldCourse), newCourse);   
        courses.sort(Comparator.comparing((course) -> course.Id));
        AllAroundProvider.ClearFile(COURSES_FILE);
        for (int i = 0; i < courses.size(); i++) {
            AddCourse(courses.get(i));
        }
    }
    
    public static void DeleteCourse(String id){
        ArrayList<Course> courses = GetCourses();
        courses.removeIf(c -> (c.Id == null ? id == null : c.Id.equals(id)));
        courses.sort(Comparator.comparing((course) -> course.Id));    
        AllAroundProvider.ClearFile(COURSES_FILE);
        for (int i = 0; i < courses.size(); i++) {
            AddCourse(courses.get(i));
        }
    }
}