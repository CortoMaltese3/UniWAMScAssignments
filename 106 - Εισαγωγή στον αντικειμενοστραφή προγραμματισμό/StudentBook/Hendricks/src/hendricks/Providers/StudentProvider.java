package hendricks.Providers;
import hendricks.Models.Student;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

public class StudentProvider extends AllAroundProvider{
        
    private static final String STUDENT_FILE = "\\Students.txt";
    
    public static ArrayList<Student> GetStudents(){
        ArrayList <Student> students = new ArrayList<>();
        try {          
            BufferedReader reader = CreateBufferedReader(STUDENT_FILE);
            String line;
            String[] parts;
            
            while((line = reader.readLine()) != null) {
                parts = line.split("\\|");
                students.add(new Student(parts, CourseProvider.GetCourses(parts[5]))); 
            }            
            reader.close(); 
        } catch (IOException ex) {
            System.out.println(ex.getMessage());   
        }
        students.sort(Comparator.comparing((student) -> student.Id));        
        return students;
    }
    
    public static Student GetStudent(String id){
        Student student = new Student();
        ArrayList<Student> students = GetStudents();
        for (int i = 0; i <students.size(); i++) {
                if (students.get(i).Id == null ? id == null : students.get(i).Id.equals(id)) {
                student = students.get(i);
            } 
        }
        return student;
    }
    
    public static void AddStudent(Student newStudent){        
        try {
            FileWriter writer = CreateFileWriter(STUDENT_FILE, true);
            writer.write(newStudent.Id + "|" + newStudent.Name + "|" + newStudent.Email + "|" + newStudent.PhoneNumber + "|" + newStudent.Semester + "|" + CoursesAssignedToStudent(newStudent) +"\r\n");
            writer.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public static String CoursesAssignedToStudent(Student student){    
        String coursesToString = "";
        StringBuilder stringBuilder = new StringBuilder();
        try {
            for (int i = 0; i < student.Courses.size(); i++) {
                stringBuilder.append(student.Courses.get(i).Id).append(",");
            }
            coursesToString = stringBuilder.toString();
            if (coursesToString.length() > 0 ) {
                coursesToString = coursesToString.substring(0, coursesToString.length() - 1);
            }
        return coursesToString;
        } catch (Exception e) {
            return coursesToString;
        }
    } 
    
    public static void EditStudent(Student oldStudent, Student newStudent){
        ArrayList<Student> students = GetStudents();
        students.removeIf(x -> (x.Id == null ? oldStudent.Id == null : x.Id.equals(oldStudent.Id)));
        students.add(newStudent); 
        students.sort(Comparator.comparing((student) -> student.Id));
        AllAroundProvider.ClearFile(STUDENT_FILE);
        for (int i = 0; i < students.size(); i++) {
            AddStudent(students.get(i));
        }
    }
    
    public static void EditStudent(Student editedStudent){
        ArrayList<Student> students = GetStudents();
        students.removeIf(x -> (x.Id == null ? editedStudent.Id == null : x.Id.equals(editedStudent.Id)));
        students.add(editedStudent); 
        students.sort(Comparator.comparing((student) -> student.Id));
        AllAroundProvider.ClearFile(STUDENT_FILE);
        for (int i = 0; i < students.size(); i++) {
            AddStudent(students.get(i));
        }
    }
    
    
        public static void DeleteStudent(String id){
            ArrayList<Student> students = GetStudents();
            students.removeIf(student -> (student.Id == null ? id == null : student.Id.equals(id)));
            students.sort(Comparator.comparing((student) -> student.Id));    
            AllAroundProvider.ClearFile(STUDENT_FILE);
            for (int i = 0; i < students.size(); i++) {
                AddStudent(students.get(i));
            }
    }
}