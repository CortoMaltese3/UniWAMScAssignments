package hendricks.Controllers;

import hendricks.Helpers.PrinterHelper;
import hendricks.Models.*;
import hendricks.Providers.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**Represents the Business layer of the application's architecture for the Grade Business Model**/
public class GradeController {
    /**Returns a list of the grades from all the students available**/
    public static ArrayList<Grade> GetStudentGrades(){
        ArrayList<Student> students = StudentProvider.GetStudents();
        
        PrinterHelper.StudentGridView(students);
        Scanner scanner = new Scanner(System.in);
        System.out.print("Student Id: ");
        String studentId = scanner.nextLine();
        while (!IsValidStudentId(studentId, students)) {            
            PrinterHelper.StudentGridView(students);
            System.out.print(PrinterHelper.ANSI_RED + "Enter a valid Student Id: " + PrinterHelper.ANSI_RESET);
            studentId = scanner.nextLine(); 
        }                
        Student student = StudentProvider.GetStudent(studentId);
        ArrayList<Grade> allGrades = GradeProvider.GetGrades(studentId);
        PrinterHelper.GradeGridView(allGrades, student);
        return allGrades;
    }
    
    /**Returns the specified by the Student Grade, converted to ArrayList**/
    public static ArrayList<Grade> GetStudentGrades(Student student){
        ArrayList<Grade> allGrades = GradeProvider.GetGrades(student.Id);
        return allGrades;
    }
    
    /**Creates a new Student**/
    public static void GetCourseStatistics(){
        ArrayList<Grade> grades = GradeProvider.GetGrades();
        PrinterHelper.GradeAverageGridView(grades);
    }
    
    /**Alters the grade of a Course for a Student**/
    public static void AlterStudentGradeToCourse(){
        ArrayList<Student> students = StudentProvider.GetStudents();
        
        PrinterHelper.StudentGridView(students);
        Scanner scanner = new Scanner(System.in);
        System.out.print("Student Id: ");
        String studentId = scanner.nextLine();
        while (!IsValidStudentId(studentId, students)) {            
            PrinterHelper.StudentGridView(students);
            System.out.print(PrinterHelper.ANSI_RED + "Enter a valid Student Id: " + PrinterHelper.ANSI_RESET);
            studentId = scanner.nextLine(); 
        }                
        Student studentToBeGraded = StudentProvider.GetStudent(studentId);
        
        ArrayList<Grade> grades = GetStudentGrades(studentToBeGraded);
        ArrayList<Course> courses = CourseProvider.GetCourses();
        PrinterHelper.GradeGridView(grades, studentToBeGraded);
        
        System.out.print("Course Id: ");
        String courseId = scanner.nextLine();
        while (!IsValidCourseId(courseId, courses)) {            
            PrinterHelper.GradeGridView(grades, studentToBeGraded);
            System.out.print(PrinterHelper.ANSI_RED + "Enter a valid Course Id: " + PrinterHelper.ANSI_RESET);
            courseId = scanner.nextLine(); 
        }
        
        /**If left unassigned, there will be no change at the Student grade.**/
        System.out.print(PrinterHelper.ANSI_GREEN + "Leave blank to remain unaffected." + PrinterHelper.ANSI_RESET + "\r\n" + "Edit Grade: ");
        String grade = scanner.nextLine();
        if (!grade.isEmpty()) {
            while (!IsValidInput("grade", grade)) {  
                PrinterHelper.GradeGridView(grades, studentToBeGraded);
                System.out.print(PrinterHelper.ANSI_GREEN + "Leave blank to remain unaffected." + PrinterHelper.ANSI_RESET + "\r\n" + PrinterHelper.ANSI_RED + "Enter a valid profession. Choices are between Physics, Math and Engineering: " + PrinterHelper.ANSI_RESET);
                grade = scanner.nextLine(); 
            }
            grades.stream()
                .filter(s -> (s.Student.Id == null ? studentToBeGraded.Id == null : s.Student.Id.equals(studentToBeGraded.Id)))
                .findAny()
                .orElseGet(null)
                .score = Integer.parseInt(grade);
        }
        PrinterHelper.GradeGridView(grades, studentToBeGraded);
        GradeProvider.AlterStudentGrade(studentId, courseId, grade);
       
        System.out.print(PrinterHelper.ANSI_GREEN + "\r\nGrade for Course " + CourseProvider.GetCourse(courseId).Title + " was altered successfuly to " + grade + PrinterHelper.ANSI_RESET); 
    }
    
    /**User input validations**/
    private static boolean IsValidStudentId(String id, ArrayList<Student> students){
        for (int i = 0; i < students.size(); i++) {
            if(students.get(i).Id.equals(id)){
                return true;
            }
        }
        return false;
    }
    
    private static boolean IsValidCourseId(String id, ArrayList<Course> courses){
        for (int i = 0; i < courses.size(); i++) {
            if(courses.get(i).Id.equals(id)){
                return true;
            }
        }
        return false;
    }
    
    private static boolean IsValidInput(String type, String userInput){
        switch(type){
            case "grade":
                if (!Arrays.stream(new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"}).anyMatch(userInput :: equals))
                    return false; 
                break;
        }
        return true;
    }
}
