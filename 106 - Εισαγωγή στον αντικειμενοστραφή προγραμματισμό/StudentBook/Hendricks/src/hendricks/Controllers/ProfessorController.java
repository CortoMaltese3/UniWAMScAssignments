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
    
    public static void CreateProfessor(){                
        String newProfessorId = GetNextProfessorId();
        Professor newProfessor = new Professor();
        newProfessor.Id = newProfessorId;        
        ArrayList<Professor> newProfessorToArrayList = new ArrayList();
        newProfessorToArrayList.add(newProfessor);
        PrinterHelper.ProfessorGridView(newProfessorToArrayList);
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        while (!IsValidInput("name", name)) {  
            PrinterHelper.ProfessorGridView(newProfessorToArrayList);
            System.out.print(PrinterHelper.ANSI_RED + "Enter a valid name: " + PrinterHelper.ANSI_RESET);
            name = scanner.nextLine(); 
        }
        newProfessor.Name = name;
        PrinterHelper.ProfessorGridView(newProfessorToArrayList);

        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        while (!IsValidInput("email", email)) {  
            PrinterHelper.ProfessorGridView(newProfessorToArrayList);
            System.out.print(PrinterHelper.ANSI_RED + "Enter a valid email: " + PrinterHelper.ANSI_RESET);
            email = scanner.nextLine(); 
        }
        newProfessor.Email = email;
        PrinterHelper.ProfessorGridView(newProfessorToArrayList);

        System.out.print("Enter phone number: ");
        String phoneNumber = scanner.nextLine();
        while (!IsValidInput("phoneNumber", phoneNumber)) {  
            PrinterHelper.ProfessorGridView(newProfessorToArrayList);
            System.out.print(PrinterHelper.ANSI_RED + "nter a valid phone number: " + PrinterHelper.ANSI_RESET);
            phoneNumber = scanner.nextLine(); 
        }
        newProfessor.PhoneNumber = phoneNumber;
        PrinterHelper.ProfessorGridView(newProfessorToArrayList);

        System.out.print("Enter professions: ");
        String profession = scanner.nextLine();
        while (!IsValidInput("profession", profession)) {  
            PrinterHelper.ProfessorGridView(newProfessorToArrayList);
            System.out.print(PrinterHelper.ANSI_RED + "Enter a valid profession. Choices are between Physics, Math and Engineering: " + PrinterHelper.ANSI_RESET);
            profession = scanner.nextLine(); 
        }
        newProfessor.Profession = profession;

        ProfessorProvider.AddProfessor(newProfessor);
        System.out.print(PrinterHelper.ANSI_GREEN + "New Professor was added successfuly" + PrinterHelper.ANSI_RESET); 
    }
    
    public static void EditProfessor(){
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
        ArrayList<Professor> professorToBeEdited = GetProfessor(professorId);        
        PrinterHelper.ProfessorGridView(professorToBeEdited);
        
        System.out.print(PrinterHelper.ANSI_GREEN + "Leave blank to remain unaffected." + PrinterHelper.ANSI_RESET + "\r\n" + "Edit name: ");
        String name = scanner.nextLine();
        if (!name.isEmpty()) {
            while (!IsValidInput("name", name)) {  
                PrinterHelper.ProfessorGridView(professorToBeEdited);
                System.out.print(PrinterHelper.ANSI_GREEN + "Leave blank to remain unaffected." + PrinterHelper.ANSI_RESET + "\r\n" + PrinterHelper.ANSI_RED + "Enter a valid name: " + PrinterHelper.ANSI_RESET);
                name = scanner.nextLine(); 
            }
            professorToBeEdited.get(0).Name = name;
        }
        PrinterHelper.ProfessorGridView(professorToBeEdited);
        
        System.out.print(PrinterHelper.ANSI_GREEN + "Leave blank to remain unaffected." + PrinterHelper.ANSI_RESET + "\r\n" + "Edit email: ");
        String email = scanner.nextLine();
        if (!email.isEmpty()) {
            while (!IsValidInput("email", email)) {  
                PrinterHelper.ProfessorGridView(professors);
                System.out.print(PrinterHelper.ANSI_GREEN + "Leave blank to remain unaffected." + PrinterHelper.ANSI_RESET + "\r\n" + PrinterHelper.ANSI_RED + "Enter a valid email: " + PrinterHelper.ANSI_RESET);
                email = scanner.nextLine(); 
            }
            professorToBeEdited.get(0).Email = email;
        } 
        PrinterHelper.ProfessorGridView(professorToBeEdited);
        
        System.out.print(PrinterHelper.ANSI_GREEN + "Leave blank to remain unaffected." + PrinterHelper.ANSI_RESET + "\r\n" + "Edit phone number: ");
        String phoneNumber = scanner.nextLine();
        if (!phoneNumber.isEmpty()) {
            while (!IsValidInput("phoneNumber", phoneNumber)) {  
                PrinterHelper.ProfessorGridView(professors);
                System.out.print(PrinterHelper.ANSI_GREEN + "Leave blank to remain unaffected." + PrinterHelper.ANSI_RESET + "\r\n" + PrinterHelper.ANSI_RED + "Enter a valid phone number: " + PrinterHelper.ANSI_RESET);
                phoneNumber = scanner.nextLine(); 
            }   
            professorToBeEdited.get(0).PhoneNumber = phoneNumber;
        }
        PrinterHelper.ProfessorGridView(professorToBeEdited);
        
        System.out.print(PrinterHelper.ANSI_GREEN + "Leave blank to remain unaffected." + PrinterHelper.ANSI_RESET + "\r\n" + "Edit profession: ");
        String profession = scanner.nextLine();
        if (!profession.isEmpty()) {
            while (!IsValidInput("profession", profession)) {  
                PrinterHelper.ProfessorGridView(professors);
                System.out.print(PrinterHelper.ANSI_GREEN + "Leave blank to remain unaffected." + PrinterHelper.ANSI_RESET + "\r\n" + PrinterHelper.ANSI_RED + "Enter a valid profession. Choices are between Physics, Math and Engineering: " + PrinterHelper.ANSI_RESET);
                profession = scanner.nextLine(); 
            }
            professorToBeEdited.get(0).Profession = profession;
        }
        PrinterHelper.ProfessorGridView(professorToBeEdited);
        
        System.out.println("Would you like to proceed to courses assignment?");
        if (AllAroundProvider.IsYesOrNo()) {
            ArrayList<Course> freeCourses = GetFreeCourses(professorToBeEdited.get(0));
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
                Course courseToBeAssigned = CourseProvider.GetCourse(courseId);
                courseToBeAssigned.Professor = professorToBeEdited.get(0).Name;
                professorToBeEdited.get(0).Course = courseToBeAssigned;
                CourseProvider.EditCourse(courseToBeAssigned);
            }
            ProfessorProvider.EditProfessor(professorToBeEdited.get(0));
        }
        PrinterHelper.ResetScreen();
        PrinterHelper.ProfessorGridView(professorToBeEdited);     
        System.out.print(PrinterHelper.ANSI_GREEN + "\r\nProfessor " + professorToBeEdited.get(0).Name + " was edited successfuly" + PrinterHelper.ANSI_RESET);
    }
    
    public static void DeleteProfessor(){
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
        System.out.print(PrinterHelper.ANSI_RED + "\r\nAre you sure you want to remove professor " + oldProfessorToArrayList.get(0).Name + " ?" + PrinterHelper.ANSI_RESET);
        if (AllAroundProvider.IsYesOrNo()) {
            ProfessorProvider.DeleteProfessor(professorId);
            System.out.print(PrinterHelper.ANSI_GREEN + "\r\nProfessor " + oldProfessorToArrayList.get(0).Name + " was removed successfully" + PrinterHelper.ANSI_RESET);
        } 
    }
    
    private static ArrayList<Course> GetFreeCourses(Professor professor){
        ArrayList<Course> allCourses = CourseProvider.GetCourses();
        ArrayList<Course> freeCourses = new ArrayList<>();
        for (int i = 0; i < allCourses.size(); i++) {
            if (allCourses.get(i).Professor.equals("*") && allCourses.get(i).Profession.equals(professor.Profession)) {
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
