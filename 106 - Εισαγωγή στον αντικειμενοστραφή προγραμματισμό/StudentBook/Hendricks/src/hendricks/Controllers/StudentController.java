
package hendricks.Controllers;

import hendricks.Helpers.PrinterHelper;
import hendricks.Models.Course;
import hendricks.Models.Student;
import hendricks.Providers.AllAroundProvider;
import hendricks.Providers.CourseProvider;
import hendricks.Providers.StudentProvider;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class StudentController{
    public static ArrayList<Student> GetStudents(){
        ArrayList<Student> students = StudentProvider.GetStudents();
        return students;
    }
    
    public static ArrayList<Student> GetStudent(String id){
        ArrayList<Student> studentToArrayList = new ArrayList<>();
        Student student = StudentProvider.GetStudent(id);
        studentToArrayList.add(student);
        return studentToArrayList;
    }
    
    public static void CreateStudent(){
        String newStudentId = GetNextStudentId();
        Student newStudent = new Student();
        newStudent.Id = newStudentId;
        ArrayList<Student> newStudentToArrayList = new ArrayList();
        newStudentToArrayList.add(newStudent);
        PrinterHelper.StudentGridView(newStudentToArrayList);
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        while (!IsValidInput("name", name)) {  
            PrinterHelper.StudentGridView(newStudentToArrayList);
            System.out.print(PrinterHelper.ANSI_RED + "Enter a valid name: " + PrinterHelper.ANSI_RESET);
            name = scanner.nextLine(); 
        }
        newStudent.Name = name;
        PrinterHelper.StudentGridView(newStudentToArrayList);
        
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        while (!IsValidInput("email", email)) {  
            PrinterHelper.StudentGridView(newStudentToArrayList);
            System.out.print(PrinterHelper.ANSI_RED + "Enter a valid email: " + PrinterHelper.ANSI_RESET);
            email = scanner.nextLine(); 
        }
        newStudent.Email = email;
        PrinterHelper.StudentGridView(newStudentToArrayList);
        
        System.out.print("Enter phone number: ");
        String phoneNumber = scanner.nextLine();
        while (!IsValidInput("phoneNumber", phoneNumber)) {  
            PrinterHelper.StudentGridView(newStudentToArrayList);
            System.out.print(PrinterHelper.ANSI_RED + "Enter a valid phone number: " + PrinterHelper.ANSI_RESET);
            phoneNumber = scanner.nextLine(); 
        }
        newStudent.PhoneNumber = phoneNumber;
        PrinterHelper.StudentGridView(newStudentToArrayList);
        
        System.out.print("Enter semester: ");
        String semester = scanner.nextLine();
        while (!IsValidInput("semester", semester)) {  
            PrinterHelper.StudentGridView(newStudentToArrayList);
            System.out.print(PrinterHelper.ANSI_RED + "Enter a valid semester: " + PrinterHelper.ANSI_RESET);
            semester = scanner.nextLine(); 
        }
        newStudent.Semester = semester;
        PrinterHelper.StudentGridView(newStudentToArrayList);
        
        newStudent.Courses = GetSemesterCourses(semester);
        PrinterHelper.StudentGridView(newStudentToArrayList);
        
        StudentProvider.AddStudent(newStudent);
    }
    
    public static void EditStudent(){        
        Student newStudent = new Student();
        ArrayList<Student> newStudentList = new ArrayList<>();
        ArrayList<Student> students = GetStudents(); 
        newStudentList.add(newStudent);
        
        PrinterHelper.StudentGridView(students);
        Scanner scanner = new Scanner(System.in);
        System.out.print("Student Id: ");
        String studentId = scanner.nextLine();
        while (!IsValidStudentId(studentId, students)) {            
            PrinterHelper.StudentGridView(students);
            System.out.print(PrinterHelper.ANSI_RED + "Enter a valid Student Id: " + PrinterHelper.ANSI_RESET);
            studentId = scanner.nextLine(); 
        }                
        ArrayList<Student> oldStudent = GetStudent(studentId);
        
        newStudent.Id = studentId;
        
        PrinterHelper.StudentGridView(newStudentList);
        System.out.print("Edit name: ");
        String name = scanner.nextLine();
        while (!IsValidInput("name", name)) {  
            PrinterHelper.StudentGridView(newStudentList);
            System.out.print(PrinterHelper.ANSI_RED + "Enter a valid name: " + PrinterHelper.ANSI_RESET);
            name = scanner.nextLine(); 
        }
        newStudent.Name = name;
        PrinterHelper.StudentGridView(newStudentList);
        
        System.out.print("Edit email: ");
        String email = scanner.nextLine();
        while (!IsValidInput("email", email)) {  
            PrinterHelper.StudentGridView(students);
            System.out.print(PrinterHelper.ANSI_RED + "Enter a valid email: " + PrinterHelper.ANSI_RESET);
            email = scanner.nextLine(); 
        }
        newStudent.Email = email;
        PrinterHelper.StudentGridView(newStudentList);
        
        System.out.print("Edit phone number: ");
        String phoneNumber = scanner.nextLine();
        while (!IsValidInput("phoneNumber", phoneNumber)) {  
            PrinterHelper.StudentGridView(students);
            System.out.print(PrinterHelper.ANSI_RED + "Enter a valid phone number: " + PrinterHelper.ANSI_RESET);
            phoneNumber = scanner.nextLine(); 
        }
        newStudent.PhoneNumber = phoneNumber;
        PrinterHelper.StudentGridView(newStudentList);
        
        System.out.print("Edit semester: ");
        String semester = scanner.nextLine();
        while (!IsValidInput("semester", semester)) {  
            PrinterHelper.StudentGridView(students);
            System.out.print(PrinterHelper.ANSI_RED + "Enter a valid semester: " + PrinterHelper.ANSI_RESET);
            semester = scanner.nextLine(); 
        }
        newStudent.Semester = semester;
        
        // If the semester is changed, the student automatically enrolls to all the new semester courses, else it remains unaffected
        if (newStudent.Semester == null ? oldStudent.get(0).Semester != null : !newStudent.Semester.equals(oldStudent.get(0).Semester)) {
            newStudent.Courses = GetSemesterCourses(semester);
        }
        else{
            newStudent.Courses = oldStudent.get(0).Courses;
        }
        
        PrinterHelper.StudentGridView(newStudentList);
        
        StudentProvider.EditStudent(oldStudent.get(0), newStudent);
    }
    
    public static void DeleteStudent(){
        ArrayList<Student> students = GetStudents(); 
        
        PrinterHelper.StudentGridView(students);
        Scanner scanner = new Scanner(System.in);
        System.out.print("Student Id: ");
        String studentId = scanner.nextLine();
        while (!IsValidStudentId(studentId, students)) {            
            PrinterHelper.StudentGridView(students);
            System.out.print(PrinterHelper.ANSI_RED + "Enter a valid Student Id: " + PrinterHelper.ANSI_RESET);
            studentId = scanner.nextLine(); 
        }                
        ArrayList<Student> oldStudentToArrayList = GetStudent(studentId);   
        PrinterHelper.StudentGridView(oldStudentToArrayList);
        System.out.print(PrinterHelper.ANSI_RED + "Are you sure you want to remove student " + oldStudentToArrayList.get(0).Name + " ?" + PrinterHelper.ANSI_RESET);
        if (AllAroundProvider.IsYesOrNo()) {
            StudentProvider.DeleteStudent(studentId);
            System.out.print(PrinterHelper.ANSI_GREEN + "Student " + oldStudentToArrayList.get(0).Name + " was removed successfully" + PrinterHelper.ANSI_RESET);
        } 
    }
    
    private static boolean IsValidStudentId(String id, ArrayList<Student> students){
        for (int i = 0; i < students.size(); i++) {
            if(students.get(i).Id.equals(id)){
                return true;
            }
        }
        return false;
    }
    
    private static boolean IsValidInput(String type, String userInput){
        switch(type){
            case "name":
                if (userInput.length() > 30 || userInput.isEmpty())
                    return false;       
                break;
                
            case "email":
                if (userInput.length() > 30 || userInput.isEmpty())
                    return false;                
                break;
            case "phoneNumber":
                if (userInput.length() > 15 || userInput.isEmpty())
                    return false;     
                break;
            case "semester":
                if (!Arrays.stream(new String[]{"1", "2", "3", "4"}).anyMatch(userInput :: equals))
                    return false; 
                break;
        }
        return true;
    }
    
    private static String GetNextStudentId(){         
        StringBuilder stringBuilder = new StringBuilder();
        List<Student> students = StudentProvider.GetStudents();
        String lastStudentId = students.get(students.size() - 1).Id.substring(1);
        
        try {
               int newStudentId = Integer.parseInt(lastStudentId) + 1;               
               stringBuilder.append(newStudentId);
               while (stringBuilder.length() < 4
                       ) {                
                   stringBuilder.insert(0, "0");
            }
               stringBuilder.insert(0, 'S');
        } catch (Exception exception) {
            System.out.print(exception.getMessage());
        }
        return stringBuilder.toString();
    }
    
    private static ArrayList<Course> GetSemesterCourses(String semester){
        ArrayList<Course> semesterCourses = new ArrayList<>();
        ArrayList<Course> allCourses = CourseProvider.GetCourses();
        for (int course = 0; course < allCourses.size(); course++) { 
            String courseId = allCourses.get(course).Id;
            if (courseId.contains("C0" + semester)) {
                semesterCourses.add(allCourses.get(course));
            }
        }
        return semesterCourses;        
    }
}
