package hendricks.Helpers;

import hendricks.Models.*;
import hendricks.Providers.GradeProvider;
import hendricks.Providers.StudentProvider;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;

public class PrinterHelper {
    public static final String ANSI_RESET  = "\u001B[0m";
    public static final String ANSI_RED    = "\033[0;91m";
    public static final String ANSI_GREEN  = "\u001B[32m";
    
    public static void PrintTitle(){
        ClearScreen();
        String logo =   "░░   ░░ ░░░░░░░ ░░░    ░░ ░░░░░░  ░░░░░░  ░░  ░░░░░░ ░░   ░░ ░░░░░░░ \n" +
                        "▒▒   ▒▒ ▒▒      ▒▒▒▒   ▒▒ ▒▒   ▒▒ ▒▒   ▒▒ ▒▒ ▒▒      ▒▒  ▒▒  ▒▒      \n" +
                        "▒▒▒▒▒▒▒ ▒▒▒▒▒   ▒▒ ▒▒  ▒▒ ▒▒   ▒▒ ▒▒▒▒▒▒  ▒▒ ▒▒      ▒▒▒▒▒   ▒▒▒▒▒▒▒ \n" +
                        "▓▓   ▓▓ ▓▓      ▓▓  ▓▓ ▓▓ ▓▓   ▓▓ ▓▓   ▓▓ ▓▓ ▓▓      ▓▓  ▓▓       ▓▓ \n" +
                        "██   ██ ███████ ██   ████ ██████  ██   ██ ██  ██████ ██   ██ ███████ ";
        System.out.println(logo);
        System.out.println("-------------------------Giorgos Kalomalos--------------------------\n\n");
    }
    
    public static void PrintMainMenuOptions(){
        PrintTitle();
        System.out.println("MAIN MENU\n");
        System.out.println("1. Students menu");
        System.out.println("2. Professors menu");
        System.out.println("3. Courses menu");
        System.out.println("4. Grades menu");
        System.out.println("0. Terminate Hendricks\n");   
    }
    
    public static void PrintCourseMenuOptions(){
        PrintTitle();
        System.out.println("COURSES MENU\n");
        System.out.println("1. Show Courses");
        System.out.println("2. Add Course");
        System.out.println("3. Edit Course/Assign Professor");
        System.out.println("4. Remove Course");
        System.out.println("0. Back\n");         
    }
    
    public static void PrintStudentMenuOptions(){
        PrintTitle();
        System.out.println("COURSES MENU\n");
        System.out.println("1. Show Students");
        System.out.println("2. Add Student");
        System.out.println("3. Edit Student");
        System.out.println("4. Remove Student");
        System.out.println("0. Back\n");         
    }
 
    public static void PrintProfessorMenuOptions(){
        PrintTitle();
        System.out.println("COURSES MENU\n");
        System.out.println("1. Show Professors");
        System.out.println("2. Add Professor");
        System.out.println("3. Edit Professor/Assign Course");
        System.out.println("4. Remove Professor");
        System.out.println("0. Back\n");         
    }
    
    public static void PrintGradeMenuOptions(){
        PrintTitle();
        System.out.println("GRADES MENU\n");
        System.out.println("1. Show Student Statistics");
        System.out.println("2. Show Course Statistics");
        System.out.println("3. Alter Student Grade");
        System.out.println("0. Back\n");         
    }
    
    public static void ResetScreen(){        
        System.out.print(ANSI_RESET + "\r\nPress any key to continue...");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();        
        ClearScreen();
    }   
       
    public static void CourseGridView(ArrayList<Course> courses){
        PrintTitle();        
        String leftAlignFormat = "| %-5s | %-30s | %-10s | %-30s | %-20s |%n";
        System.out.format("+-------+--------------------------------+------------+--------------------------------+----------------------+%n");
        System.out.format("| Id    | Title                          | Semester   | Professor                      | Profession           |%n");
        System.out.format("+-------+--------------------------------+------------+--------------------------------+----------------------+%n");
        for (int i = 0; i < courses.size(); i++) {
            System.out.format(leftAlignFormat, courses.get(i).Id, courses.get(i).Title, courses.get(i).Semester, courses.get(i).Professor, courses.get(i).Profession);
        }
        System.out.format("+-------+--------------------------------+------------+--------------------------------+----------------------+%n");
        System.out.println();
    }
    
    public static void StudentGridView(ArrayList<Student> students){
        PrintTitle();        
        String leftAlignFormat = "| %-5s | %-30s | %-30s | %-15s | %-10s | %-50s |%n";
        System.out.format("+-------+--------------------------------+--------------------------------+-----------------+------------+----------------------------------------------------+%n");
        System.out.format("| Id    | Name                           | Email                          | Phone Number    | Semester   | Courses                                            |%n");
        System.out.format("+-------+--------------------------------+--------------------------------+-----------------+------------+----------------------------------------------------+%n");
        for (int i = 0; i < students.size(); i++) {          
            String studentCourses = StudentProvider.CoursesAssignedToStudent(students.get(i));
            System.out.format(leftAlignFormat, students.get(i).Id, students.get(i).Name, students.get(i).Email, students.get(i).PhoneNumber, students.get(i).Semester, studentCourses);
        }
        System.out.format("+-------+--------------------------------+--------------------------------+-----------------+------------+----------------------------------------------------+%n");
        System.out.println();
    }
    
    public static void ProfessorGridView(ArrayList<Professor> professors){
        PrintTitle();        
        String leftAlignFormat = "| %-5s | %-30s | %-30s | %-15s | %-20s | %-30s |%n";
        System.out.format("+-------+--------------------------------+--------------------------------+-----------------+----------------------+--------------------------------+%n");
        System.out.format("| Id    | Name                           | Email                          | Phone Number    | Profession           | Course                         |%n");
        System.out.format("+-------+--------------------------------+--------------------------------+-----------------+----------------------+--------------------------------+%n");
        for (int i = 0; i < professors.size(); i++) {  
            String professorCourse;
            if (professors.get(i).Course == null) {
                professorCourse = "*";
            }
            else{
                professorCourse = professors.get(i).Course.Id;
            }
            System.out.format(leftAlignFormat, professors.get(i).Id, professors.get(i).Name, professors.get(i).Email, professors.get(i).PhoneNumber, professors.get(i).Profession, professorCourse);
        }
        System.out.format("+-------+--------------------------------+--------------------------------+-----------------+----------------------+--------------------------------+%n");
        System.out.println();   
    }    
    
    public static void GradeGridView(ArrayList<Grade> grades, Student student){
        PrintTitle();        
        String leftAlignFormat = "| %-10s | %-38s | %-9s |%n";
        String leftAlignTitleFormat = "| %-10s | %-50s |%n";
        String leftAlignAverageFormat = "| %-10s | %44s       |%n";
        System.out.format("+------------+----------------------------------------------------+%n");
        System.out.format(leftAlignTitleFormat, student.Id, student.Name);
        System.out.format("+------------+----------------------------------------+-----------+%n");
        System.out.format("| Course Id  | Title                                  | Grade     |%n");
        System.out.format("+------------+----------------------------------------+-----------+%n");
        for (int i = 0; i < GetDistinctCourseGrades(grades); i++) {
            if (grades.get(i).score == 0) {
                System.out.format(leftAlignFormat, grades.get(i).Course.Id, grades.get(i).Course.Title, "*");
            }
            else{
                System.out.format(leftAlignFormat, grades.get(i).Course.Id, grades.get(i).Course.Title, grades.get(i).score);
            }
        }
        System.out.format("+-----------------------------------------------------+-----------+%n");
        System.out.format(leftAlignAverageFormat, "Average", GradeProvider.CalculateStudentAverageGrade(student.Id));
        System.out.format("+-----------------------------------------------------------------+%n");
        System.out.println();   
    }    
    
    public static void GradeAverageGridView(ArrayList<Grade> grades){
        PrintTitle();
        String leftAlignFormat = "| %-10s | %-38s | %-9s |%n";
        System.out.format("+------------+----------------------------------------+-----------+%n");
        System.out.format("| Course Id  | Title                                  | Average   |%n");
        System.out.format("+------------+----------------------------------------+-----------+%n");
        
        for (int i = 0; i < GetDistinctCourseGrades(grades); i++) {

            System.out.format(leftAlignFormat, grades.get(i).Course.Id, grades.get(i).Course.Title, GradeProvider.CalculateCourseAverageGrade(grades.get(i).Course.Id));
            
        }
        System.out.format("+-----------------------------------------------------+-----------+%n");
        System.out.println();   
    }
        
    public static void ClearScreen() {
        try{
            if (System.getProperty("os.name").contains("Windows"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else
                Runtime.getRuntime().exec("clear");
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public boolean IsNullOrEmpty(String userInput){
        return !(userInput!= null && !userInput.isEmpty());
    }       

    private static String SubString(String fullString, int maxLength){
        if (fullString.length() > maxLength) {
            return fullString.substring(0, maxLength);
        }
        return fullString;
    }
    
    private static int GetDistinctCourseGrades(ArrayList<Grade> grades){
        ArrayList<String> distinctCourseGrades = new ArrayList<>();
        for (int i = 0; i < grades.size(); i++) {
            if (!distinctCourseGrades.contains(grades.get(i).Course.Id)) {
                distinctCourseGrades.add(grades.get(i).Course.Id);
            }
        }
        return distinctCourseGrades.size();
    }
}
