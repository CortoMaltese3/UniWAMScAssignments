package hendricks.Providers;

import hendricks.Models.*;
import static hendricks.Providers.AllAroundProvider.CreateFileWriter;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

public class GradeProvider extends AllAroundProvider{
    private static final String GRADES_FILE = "\\Grades.txt";
    
    public static ArrayList<Grade> GetGrades(){
        ArrayList<Grade> grades = new ArrayList<>();
        try {                  
            BufferedReader reader = CreateBufferedReader(GRADES_FILE);
            String line;
            String[] parts;
            
            while((line = reader.readLine()) != null) {
                parts = line.split("\\|");
                grades.add(new Grade(parts));
            }            
            reader.close(); 
        } catch (IOException ex) {
            System.out.println(ex.getMessage());   
        }
        grades.sort(Comparator.comparing((grade) -> grade.Student.Id));      
        return grades;
    }
    
    public static ArrayList<Grade> GetGrades(String studentId){
        ArrayList<Grade> grades = new ArrayList<>();
        try {                  
            BufferedReader reader = CreateBufferedReader(GRADES_FILE);
            String line;
            String[] parts;
            
            while((line = reader.readLine()) != null) {
                parts = line.split("\\|");
                if (parts[0].equals(studentId)) {
                    grades.add(new Grade(parts));
                }
            }            
            reader.close(); 
        } catch (IOException ex) {
            System.out.println(ex.getMessage());   
        }
        grades.sort(Comparator.comparing((grade) -> grade.Student.Id));      
        return grades;
    }
    
    public static void AlterStudentGrade(String studentId, String courseId, String grade){
        FileWriter gradeFileWriter = CreateFileWriter(GRADES_FILE, true);
        ArrayList<Grade> allGrades = GetGrades();
        for (int i = 0; i < allGrades.size(); i++) {
            if (allGrades.get(i).Course.Id.equals(courseId) && allGrades.get(i).Student.Id.equals(studentId)) {
                allGrades.get(i).score = Integer.parseInt(grade);
            }
        }
        AllAroundProvider.ClearFile(GRADES_FILE);
        try {
            for (int i = 0; i < allGrades.size(); i++) {
                gradeFileWriter.write(allGrades.get(i).Student.Id + "|" + allGrades.get(i).Course.Id + "|" + allGrades.get(i).score + "\r\n");
            }
            gradeFileWriter.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public static double CalculateStudentAverageGrade(String studentId){
        ArrayList<Grade> studentGrades = GetGrades(studentId);
        studentGrades.removeIf(grade -> grade.score == 0);
        double sum = 0;
        for (int i = 0; i < studentGrades.size(); i++) {
            sum += studentGrades.get(i).score;
        }
        return sum / studentGrades.size();
    }
    
    public static double CalculateCourseAverageGrade(String courseId){
        ArrayList<Grade> courseGrades = GetGrades();
        courseGrades.removeIf(course -> (course.Course.Id == null ? courseId != null : !course.Course.Id.equals(courseId)));
        courseGrades.removeIf(grade -> grade.score == 0);
        double sum = 0;
        for (int i = 0; i < courseGrades.size(); i++) {
            sum += (double)courseGrades.get(i).score;
        }
        return Math.round((sum / courseGrades.size()) * 10.0) / 10.0;
    }
}
