package hendricks.Controllers;

import hendricks.Helpers.PrinterHelper;
import hendricks.Models.Course;
import hendricks.Models.Professor;
import hendricks.Providers.AllAroundProvider;
import hendricks.Providers.CourseProvider;
import hendricks.Providers.ProfessorProvider;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ProfessorController {
    public static ArrayList<Professor> GetProfessors(){
        ArrayList<Professor> professors = ProfessorProvider.GetProfessors();
        return professors;
    }
    
    public static ArrayList<Professor> GetProfessor(String id){
        ArrayList<Professor> professorToArrayList = new ArrayList<>();
        Professor professor = ProfessorProvider.GetProfessor(id);
        professorToArrayList.add(professor);
        return professorToArrayList;
    }
    
    public static void CreateProfessor(Professor professor){                
        String newProfessorId = GetNextProfessorId();
        Professor newProfessor = new Professor();
        newProfessor.Id = newProfessorId;
        ArrayList<Professor> newProfessorToArrayList = new ArrayList();
        ArrayList<Course> allCourses = CourseProvider.GetCourses();
        ArrayList<Course> freeCourses = GetFreeCourses(allCourses);
        newProfessorToArrayList.add(newProfessor);
        PrinterHelper.ProfessorGridView(newProfessorToArrayList);
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        while (!IsValidInput("name", name)) {  
            PrinterHelper.ProfessorGridView(newProfessorToArrayList);
            System.out.print(PrinterHelper.ANSI_RED + "/r/nEnter a valid name: " + PrinterHelper.ANSI_RESET);
            name = scanner.nextLine(); 
        }
        newProfessor.Name = name;
        PrinterHelper.ProfessorGridView(newProfessorToArrayList);
        
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        while (!IsValidInput("email", email)) {  
            PrinterHelper.ProfessorGridView(newProfessorToArrayList);
            System.out.print(PrinterHelper.ANSI_RED + "/r/nEnter a valid email: " + PrinterHelper.ANSI_RESET);
            email = scanner.nextLine(); 
        }
        newProfessor.Email = email;
        PrinterHelper.ProfessorGridView(newProfessorToArrayList);
        
        System.out.print("Enter phone number: ");
        String phoneNumber = scanner.nextLine();
        while (!IsValidInput("phoneNumber", phoneNumber)) {  
            PrinterHelper.ProfessorGridView(newProfessorToArrayList);
            System.out.print(PrinterHelper.ANSI_RED + "/r/nEnter a valid phone number: " + PrinterHelper.ANSI_RESET);
            phoneNumber = scanner.nextLine(); 
        }
        newProfessor.PhoneNumber = phoneNumber;
        PrinterHelper.ProfessorGridView(newProfessorToArrayList);
        
        System.out.print("Enter professions: ");
        String profession = scanner.nextLine();
        while (!IsValidInput("profession", profession)) {  
            PrinterHelper.ProfessorGridView(newProfessorToArrayList);
            System.out.print(PrinterHelper.ANSI_RED + "\r\nEnter a valid profession. Choices are between Physics, Math and Engineering: " + PrinterHelper.ANSI_RESET);
            profession = scanner.nextLine(); 
        }
        newProfessor.Profession = profession;
        
        PrinterHelper.CourseGridView(freeCourses);        
        if (freeCourses.isEmpty()) {
            System.out.print(PrinterHelper.ANSI_RED + "\r\nThere are no free Courses to assign to Professor." + PrinterHelper.ANSI_RESET);            
        }
        else{
            System.out.print("Assign Course Id to Professor: ");
            String courseId = scanner.nextLine();
            while (!IsValidCourseId(courseId)) {  
                PrinterHelper.CourseGridView(freeCourses);
                System.out.print(PrinterHelper.ANSI_RED + "\r\nEnter a valid Course Id: " + PrinterHelper.ANSI_RESET);
                courseId = scanner.nextLine(); 
            }
            newProfessor.Courses.add(CourseProvider.GetCourse(courseId));
            PrinterHelper.ProfessorGridView(newProfessorToArrayList);         
        }
        ProfessorProvider.AddProfessor(newProfessor);
        System.out.print(PrinterHelper.ANSI_GREEN + "\r\nNew Professor was added successfuly" + PrinterHelper.ANSI_RESET); 
    }
    
    public static void EditProfessor(){
        Professor newProfessor = new Professor();
        ArrayList<Professor> newProfessorToArrayList = new ArrayList<>();
        ArrayList<Course> allCourses = CourseProvider.GetCourses();
        ArrayList<Course> freeCourses = GetFreeCourses(allCourses);
        ArrayList<Professor> professors = GetProfessors(); 
        newProfessorToArrayList.add(newProfessor);
        
        PrinterHelper.ProfessorGridView(professors);
        Scanner scanner = new Scanner(System.in);
        System.out.print("Professor Id: ");
        String professorId = scanner.nextLine();
        while (!IsValidProfessorId(professorId, professors)) {            
            PrinterHelper.ProfessorGridView(professors);
            System.out.print(PrinterHelper.ANSI_RED + "Enter a valid Professor Id: " + PrinterHelper.ANSI_RESET);
            professorId = scanner.nextLine(); 
        }                
        ArrayList<Professor> oldProfessor = GetProfessor(professorId);        
        newProfessor.Id = professorId;
        PrinterHelper.ProfessorGridView(newProfessorToArrayList);
        
        System.out.print("Edit name: ");
        String name = scanner.nextLine();
        while (!IsValidInput("name", name)) {  
            PrinterHelper.ProfessorGridView(newProfessorToArrayList);
            System.out.print(PrinterHelper.ANSI_RED + "Enter a valid name: " + PrinterHelper.ANSI_RESET);
            name = scanner.nextLine(); 
        }
        newProfessor.Name = name;
        PrinterHelper.ProfessorGridView(newProfessorToArrayList);
        
        System.out.print("Edit email: ");
        String email = scanner.nextLine();
        while (!IsValidInput("email", email)) {  
            PrinterHelper.ProfessorGridView(professors);
            System.out.print(PrinterHelper.ANSI_RED + "Enter a valid email: " + PrinterHelper.ANSI_RESET);
            email = scanner.nextLine(); 
        }
        newProfessor.Email = email;
        PrinterHelper.ProfessorGridView(newProfessorToArrayList);
        
        System.out.print("Edit phone number: ");
        String phoneNumber = scanner.nextLine();
        while (!IsValidInput("phoneNumber", phoneNumber)) {  
            PrinterHelper.ProfessorGridView(professors);
            System.out.print(PrinterHelper.ANSI_RED + "Enter a valid phone number: " + PrinterHelper.ANSI_RESET);
            phoneNumber = scanner.nextLine(); 
        }
        newProfessor.PhoneNumber = phoneNumber;
        PrinterHelper.ProfessorGridView(newProfessorToArrayList);
        
        System.out.print("Edit profession: ");
        String profession = scanner.nextLine();
        while (!IsValidInput("profession", profession)) {  
            PrinterHelper.ProfessorGridView(professors);
            System.out.print(PrinterHelper.ANSI_RED + "Enter a valid profession. Choices are between Physics, Math and Engineering: " + PrinterHelper.ANSI_RESET);
            profession = scanner.nextLine(); 
        }
        newProfessor.Profession = profession;
        PrinterHelper.ProfessorGridView(newProfessorToArrayList);

        if (freeCourses.isEmpty()) {
            System.out.print(PrinterHelper.ANSI_RED + "\r\nThere are no free Courses to assign to Professor." + PrinterHelper.ANSI_RESET);            
        }
        else{
            System.out.print("Assign Course Id to Professor: ");
            String courseId = scanner.nextLine();
            while (!IsValidCourseId(courseId)) {  
                PrinterHelper.CourseGridView(freeCourses);
                System.out.print(PrinterHelper.ANSI_RED + "\r\nEnter a valid Course Id: " + PrinterHelper.ANSI_RESET);
                courseId = scanner.nextLine(); 
            }
            newProfessor.Courses.add(CourseProvider.GetCourse(courseId));
            PrinterHelper.ProfessorGridView(newProfessorToArrayList);         
        }
        ProfessorProvider.EditProfessor(oldProfessor.get(0), newProfessor);
        System.out.print(PrinterHelper.ANSI_GREEN + "\r\nNew Professor was edited successfuly" + PrinterHelper.ANSI_RESET); 
        
    }
    
    public static void DeleteProfessor(String id){
        ArrayList<Professor> professors = GetProfessors(); 
        
        PrinterHelper.ProfessorGridView(professors);
        Scanner scanner = new Scanner(System.in);
        System.out.print("Professor Id: ");
        String professorId = scanner.nextLine();
        while (!IsValidProfessorId(professorId, professors)) {            
            PrinterHelper.ProfessorGridView(professors);
            System.out.print(PrinterHelper.ANSI_RED + "Enter a valid Professor Id: " + PrinterHelper.ANSI_RESET);
            professorId = scanner.nextLine(); 
        }                
        ArrayList<Professor> oldProfessorToArrayList = GetProfessor(professorId);   
        PrinterHelper.ProfessorGridView(oldProfessorToArrayList);
        System.out.print(PrinterHelper.ANSI_RED + "Are you sure you want to remove professor " + oldProfessorToArrayList.get(0).Name + " ?" + PrinterHelper.ANSI_RESET);
        if (AllAroundProvider.IsYesOrNo()) {
            ProfessorProvider.DeleteProfessor(professorId);
            System.out.print(PrinterHelper.ANSI_GREEN + "Professor " + oldProfessorToArrayList.get(0).Name + " was removed successfully" + PrinterHelper.ANSI_RESET);
        } 
    }
    
    private static ArrayList<Course> GetFreeCourses(ArrayList<Course> allCourses){
        ArrayList<Course> freeCourses = new ArrayList<>();
        for (int i = 0; i < allCourses.size(); i++) {
            if (allCourses.get(i).Professor.equals("")) {
                freeCourses.add(allCourses.get(i));
            }
        }
        return freeCourses;
    }
    
    private static String GetNextProfessorId(){         
        StringBuilder stringBuilder = new StringBuilder();
        List<Professor> professors = ProfessorProvider.GetProfessors();
        String lastProfessorId = professors.get(professors.size() - 1).Id.substring(1);
        
        try {
               int newProfessorId = Integer.parseInt(lastProfessorId) + 1;               
               stringBuilder.append(newProfessorId);
               while (stringBuilder.length() < 4
                       ) {                
                   stringBuilder.insert(0, "0");
            }
               stringBuilder.insert(0, 'P');
        } catch (NumberFormatException exception) {
            System.out.print(exception.getMessage());
        }
        return stringBuilder.toString();
    }
    
    private static boolean IsValidProfessorId(String id, ArrayList<Professor> professors){
        for (int i = 0; i < professors.size(); i++) {
            if(professors.get(i).Id.equals(id)){
                return true;
            }
        }
        return false;
    }
    
    private static boolean IsValidCourseId(String userInput){
        ArrayList<Course> allCourses = CourseProvider.GetCourses();
        for (int i = 0; i < allCourses.size(); i++) {
            if (allCourses.get(i).Id.equals(userInput))  {
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
            case "profession":
                if (!Arrays.stream(new String[]{"Physics", "Math", "Engineering"}).anyMatch(userInput :: equals))
                    return false; 
                break;
        }
        return true;
    }
    
    private static ArrayList<Course> GetProfessionCourses(String professor){
        ArrayList<Course> professorCourses = new ArrayList<>();
        ArrayList<Course> allCourses = CourseProvider.GetCourses();
        for (int course = 0; course < allCourses.size(); course++) { 
            if (allCourses.get(course).Professor.equals(professor)) {
                professorCourses.add(allCourses.get(course));
            }
        }
        return professorCourses;        
    }
}
