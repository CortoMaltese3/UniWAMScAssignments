package hendricks.Controllers;

import hendricks.Helpers.PrinterHelper;
import hendricks.Models.*;
import hendricks.Providers.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**Represents the Business layer of the application's architecture for the Student Business Model**/
public class StudentController{
    /**Returns a list of all the students available**/
    public static ArrayList<Student> GetStudents(){
        ArrayList<Student> students = StudentProvider.GetStudents();
        return students;
    }
    
    /**Returns the specified by the id Student, converted to ArrayList**/
    public static ArrayList<Student> GetStudent(String id){
        ArrayList<Student> studentToArrayList = new ArrayList<>();
        Student student = StudentProvider.GetStudent(id);
        studentToArrayList.add(student);
        return studentToArrayList;
    }
    
    /**Creates a new Student**/
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
        System.out.print(PrinterHelper.ANSI_GREEN + "Student " + newStudent.Name + " was added successfully." + PrinterHelper.ANSI_RESET);
    }
    
    /**Edits a specified Student**/
    public static void EditStudent(){        
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
        ArrayList<Student> studentToBeEdited = GetStudent(studentId);
        
        PrinterHelper.StudentGridView(studentToBeEdited);
        System.out.print(PrinterHelper.ANSI_GREEN + "Leave blank to remain unaffected." + PrinterHelper.ANSI_RESET + "\r\n" + "Edit name: ");
        String name = scanner.nextLine();
        if (!name.isEmpty()) {
            while (!IsValidInput("name", name)) {
                PrinterHelper.StudentGridView(studentToBeEdited);
                System.out.print(PrinterHelper.ANSI_GREEN + "Leave blank to remain unaffected." + PrinterHelper.ANSI_RESET + "\r\n" + PrinterHelper.ANSI_RED + "Enter a valid name: " + PrinterHelper.ANSI_RESET);
                name = scanner.nextLine(); 
            }
            studentToBeEdited.get(0).Name = name;
        }        
        PrinterHelper.StudentGridView(studentToBeEdited);
        
        System.out.print(PrinterHelper.ANSI_GREEN + "Leave blank to remain unaffected." + PrinterHelper.ANSI_RESET + "\r\n" + "Edit email: ");
        String email = scanner.nextLine();
        if (!email.isEmpty()) {
            while (!IsValidInput("email", email)) {  
                PrinterHelper.StudentGridView(studentToBeEdited);
                System.out.print(PrinterHelper.ANSI_GREEN + "Leave blank to remain unaffected." + PrinterHelper.ANSI_RESET + "\r\n" + PrinterHelper.ANSI_RED + "Enter a valid email: " + PrinterHelper.ANSI_RESET);
                email = scanner.nextLine(); 
           }
            studentToBeEdited.get(0).Email = email;
        }
        PrinterHelper.StudentGridView(studentToBeEdited);
        
        System.out.print(PrinterHelper.ANSI_GREEN + "Leave blank to remain unaffected." + PrinterHelper.ANSI_RESET + "\r\n" + "Edit phone number: ");
        String phoneNumber = scanner.nextLine();
        if (!phoneNumber.isEmpty()) {
            while (!IsValidInput("phoneNumber", phoneNumber)) {  
                PrinterHelper.StudentGridView(studentToBeEdited);
                System.out.print(PrinterHelper.ANSI_GREEN + "Leave blank to remain unaffected." + PrinterHelper.ANSI_RESET + "\r\n" + PrinterHelper.ANSI_RED + "Enter a valid phone number: " + PrinterHelper.ANSI_RESET);
                phoneNumber = scanner.nextLine(); 
            }
            studentToBeEdited.get(0).PhoneNumber = phoneNumber;
        }
        PrinterHelper.StudentGridView(studentToBeEdited);
        
        System.out.print(PrinterHelper.ANSI_GREEN + "Leave blank to remain unaffected." + PrinterHelper.ANSI_RESET + "\r\n" + "Edit semester: ");
        String semester = scanner.nextLine();
        if (!semester.isEmpty()) {
            while (!IsValidInput("semester", semester)) {  
                PrinterHelper.StudentGridView(studentToBeEdited);
                System.out.print(PrinterHelper.ANSI_GREEN + "Leave blank to remain unaffected." + PrinterHelper.ANSI_RESET + "\r\n" + PrinterHelper.ANSI_RED + "Enter a valid semester: " + PrinterHelper.ANSI_RESET);
                semester = scanner.nextLine(); 
            }
            studentToBeEdited.get(0).Semester = semester;
            studentToBeEdited.get(0).Courses = GetSemesterCourses(semester);
        }
        StudentProvider.EditStudent(studentToBeEdited.get(0));
        PrinterHelper.StudentGridView(studentToBeEdited);
        System.out.print(PrinterHelper.ANSI_GREEN + "Student " + studentToBeEdited.get(0).Name + " was edited successfully." + PrinterHelper.ANSI_RESET);
    }
    
    /**Deletes specified Course**/
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
        System.out.print(PrinterHelper.ANSI_RED + "\r\nAre you sure you want to remove student " + oldStudentToArrayList.get(0).Name + " ?" + PrinterHelper.ANSI_RESET);
        if (AllAroundProvider.IsYesOrNo()) {
            StudentProvider.DeleteStudent(studentId);
            System.out.print(PrinterHelper.ANSI_GREEN + "Student " + oldStudentToArrayList.get(0).Name + " was removed successfully" + PrinterHelper.ANSI_RESET);
        } 
    }
    
    /**Gets the next iterated Student id. Example if the last Professor id is S0004, the Id S0005 will be returned**/
    private static String GetNextStudentId(){         
        StringBuilder stringBuilder = new StringBuilder();
        ArrayList<Student> students = StudentProvider.GetStudents();
        String lastStudentId = students.get(students.size() - 1).Id.substring(1);
        
        try {
               int newStudentId = Integer.parseInt(lastStudentId) + 1;               
               stringBuilder.append(newStudentId);
               while (stringBuilder.length() < 4
                       ) {                
                   stringBuilder.insert(0, "0");
            }
               stringBuilder.insert(0, 'S');
        } catch (NumberFormatException exception) {
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
    
    /**User input validations**/
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
                if (userInput.length() > 30)
                    return false;       
                break;
                
            case "email":
                if (userInput.length() > 30)
                    return false;                
                break;
            case "phoneNumber":
                if (userInput.length() > 15)
                    return false;     
                break;
            case "semester":
                if (!Arrays.stream(new String[]{"1", "2", "3", "4"}).anyMatch(userInput :: equals))
                    return false; 
                break;
        }
        return true;
    }
}