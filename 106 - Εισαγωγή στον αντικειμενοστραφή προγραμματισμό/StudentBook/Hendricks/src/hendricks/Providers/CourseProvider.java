package hendricks.Providers;
import hendricks.Models.*;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class CourseProvider extends AllAroundProvider{
    private static final String COURSES_FILE = "\\Courses.txt";
    private static final String GRADES_FILE = "\\Grades.txt";
    
    public static ArrayList<Course> GetCourses(String coursesIdsDelimited){
        ArrayList<Course> courses = new ArrayList<>();
        try {
            String[] coursesIdsToStringArray = coursesIdsDelimited.split(",");
            List<String> courseIdsToList = Arrays.asList(coursesIdsToStringArray); 
            ArrayList<String> courseIdsToArrayList = new ArrayList<>(courseIdsToList);
        
            for (int i = 0; i < courseIdsToArrayList.size(); i++) {
                courses.add(GetCourse(courseIdsToArrayList.get(i)));
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return courses;
    }
    
    public static ArrayList<Course> GetCourses(){
        ArrayList <Course> courses = new ArrayList<>();
        try {                  
            BufferedReader reader = CreateBufferedReader(COURSES_FILE);
            String line;
            String[] parts;
            
            while((line = reader.readLine()) != null) {
                parts = line.split("\\|");
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
    
    public static void AddCourse(Course newCourse, boolean isNewCourse){        
        try {
            FileWriter courseFileWriter = CreateFileWriter(COURSES_FILE, true);
            FileWriter gradeFileWriter = CreateFileWriter(GRADES_FILE, true);
            ArrayList<Student> students =  StudentProvider.GetStudents();
            if (isNewCourse) {
                courseFileWriter.write(newCourse.Id + "|" + newCourse.Title + "|" + newCourse.Semester + "|" + "*" + "|" + newCourse.Profession + "\r\n");
                for (int i = 0; i < students.size(); i++) {
                    gradeFileWriter.write(students.get(i).Id + "|" + newCourse.Id + "|" + "0" + "\r\n");
                }
            }
            else{
                courseFileWriter.write(newCourse.Id + "|" + newCourse.Title + "|" + newCourse.Semester + "|" + newCourse.Professor + "|" + newCourse.Profession + "\r\n");
            }
            courseFileWriter.close();
            gradeFileWriter.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public static void EditCourse(Course editedCourse){
        ArrayList<Course> courses = GetCourses();
        courses.removeIf(x -> (x.Id == null ? editedCourse.Id == null : x.Id.equals(editedCourse.Id)));
        courses.add(editedCourse);  
        courses.sort(Comparator.comparing((course) -> course.Id));
        AllAroundProvider.ClearFile(COURSES_FILE);
        for (int i = 0; i < courses.size(); i++) {
            AddCourse(courses.get(i), false);
        }
    }
    
    public static void DeleteCourse(String id){
        ArrayList<Course> courses = GetCourses();
        courses.removeIf(c -> (c.Id == null ? id == null : c.Id.equals(id)));
        courses.sort(Comparator.comparing((course) -> course.Id));    
        AllAroundProvider.ClearFile(COURSES_FILE);
        for (int i = 0; i < courses.size(); i++) {
            AddCourse(courses.get(i), false);
        }
    }
}