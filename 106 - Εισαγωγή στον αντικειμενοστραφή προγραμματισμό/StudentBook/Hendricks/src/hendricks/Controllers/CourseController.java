package hendricks.Controllers;
import hendricks.Helpers.*;
import hendricks.Providers.*;
import hendricks.Models.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**Represents the Business layer of the application's architecture for the Course Business Model**/
public class CourseController {
    /**Returns a list of all the courses available
     * @return**/
    public static ArrayList<Course> GetCourses(){
        ArrayList<Course> courses = CourseProvider.GetCourses();
        return courses;
    }
    
    /**Returns the specified by the id Course, converted to ArrayList
     * @param id
     * @return**/
    public static ArrayList<Course> GetCourse(String id){
        ArrayList<Course> courseToArrayList = new ArrayList<>();
        Course course = CourseProvider.GetCourse(id);
        courseToArrayList.add(course);
        return courseToArrayList;
    }
    
    /**Creates a new Course**/
    public static void CreateCourse(){
        Course newCourse = new Course();
        
        ArrayList<Course> newCourseToArrayList = new ArrayList();
        newCourseToArrayList.add(newCourse);
        PrinterHelper.CourseGridView(newCourseToArrayList);
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter Course title: ");
        String title = scanner.nextLine();
        while (!IsValidInput("title", title)) {  
            PrinterHelper.CourseGridView(newCourseToArrayList);
            System.out.print(PrinterHelper.ANSI_RED + "Enter a Course title: " + PrinterHelper.ANSI_RESET);
            title = scanner.nextLine(); 
        }
        newCourse.Title = title;
        PrinterHelper.CourseGridView(newCourseToArrayList);
        
        System.out.print("Enter Course semester: ");
        String semester = scanner.nextLine();
        while (!IsValidInput("semester", semester)) {  
            PrinterHelper.CourseGridView(newCourseToArrayList);
            System.out.print(PrinterHelper.ANSI_RED + "Enter a valid Course semester. Choices are between 1, 2 and 3: " + PrinterHelper.ANSI_RESET);
            semester = scanner.nextLine(); 
        }
        newCourse.Semester = semester;
        PrinterHelper.CourseGridView(newCourseToArrayList);
        
        String newCourseId = GetNextCourseId(semester);
        newCourse.Id = newCourseId;    
        PrinterHelper.CourseGridView(newCourseToArrayList);
        
        System.out.print("Enter Course profession: ");
        String profession = scanner.nextLine();
        while (!IsValidInput("profession", profession)) {  
            PrinterHelper.CourseGridView(newCourseToArrayList);
            System.out.print(PrinterHelper.ANSI_RED + "Enter a valid profession. Choices are between Physics, Math and Engineering: " + PrinterHelper.ANSI_RESET);
            profession = scanner.nextLine(); 
        }
        newCourse.Profession = profession;
        newCourse.Professor = "*";
        PrinterHelper.CourseGridView(newCourseToArrayList);
        
        CourseProvider.AddCourse(newCourse, true);
        System.out.print(PrinterHelper.ANSI_GREEN + "New Course was added successfuly" + PrinterHelper.ANSI_RESET);         
    }
    
    /**Edits a specified Course**/
    public static void EditCourse(){
        ArrayList<Course> courses = GetCourses();
            
        PrinterHelper.CourseGridView(courses);
        Scanner scanner = new Scanner(System.in);
        System.out.print("Course Id: ");
        String courseId = scanner.nextLine();
        while (!IsValidCourseId(courseId, courses)) {            
            PrinterHelper.CourseGridView(courses);
            System.out.print(PrinterHelper.ANSI_RED + "Enter a valid Course Id: " + PrinterHelper.ANSI_RESET);
            courseId = scanner.nextLine(); 
        }                
        ArrayList<Course> courseToBeEdited = GetCourse(courseId);        
        PrinterHelper.CourseGridView(courseToBeEdited);
        
        System.out.print(PrinterHelper.ANSI_GREEN + "Leave blank to remain unaffected." + PrinterHelper.ANSI_RESET + "\r\n" + "Edit Course title: ");
        String title = scanner.nextLine();
        if (!title.isEmpty()) {
            while (!IsValidInput("title", title)) {  
                PrinterHelper.CourseGridView(courseToBeEdited);
                System.out.print(PrinterHelper.ANSI_GREEN + "Leave blank to remain unaffected." + PrinterHelper.ANSI_RESET + "\r\n" + PrinterHelper.ANSI_RED + "Enter a valid Course title: " + PrinterHelper.ANSI_RESET);
                title = scanner.nextLine(); 
            }
            courseToBeEdited.get(0).Title = title;
        }
        PrinterHelper.CourseGridView(courseToBeEdited);
        
        System.out.print(PrinterHelper.ANSI_GREEN + "Leave blank to remain unaffected." + PrinterHelper.ANSI_RESET + "\r\n" + "Edit Course semester: ");
        String semester = scanner.nextLine();
        if (!semester.isEmpty()) {
            while (!IsValidInput("semester", semester)) {  
                PrinterHelper.CourseGridView(courses);
                System.out.print(PrinterHelper.ANSI_GREEN + "Leave blank to remain unaffected." + PrinterHelper.ANSI_RESET + "\r\n" + PrinterHelper.ANSI_RED + "Enter a valid Course semester: " + PrinterHelper.ANSI_RESET);
                semester = scanner.nextLine(); 
            } 
            courseToBeEdited.get(0).Semester = semester;
        }
        PrinterHelper.CourseGridView(courseToBeEdited);
        
        System.out.print(PrinterHelper.ANSI_GREEN + "Leave blank to remain unaffected." + PrinterHelper.ANSI_RESET + "\r\n" + "Edit Course profession: ");
        String profession = scanner.nextLine();
        if (!profession.isEmpty()) {
            while (!IsValidInput("profession", profession)) {  
                PrinterHelper.CourseGridView(courses);
                System.out.print(PrinterHelper.ANSI_GREEN + "Leave blank to remain unaffected." + PrinterHelper.ANSI_RESET + "\r\n" + PrinterHelper.ANSI_RED + "Enter a valid profession. Choices are between Physics, Math and Engineering: " + PrinterHelper.ANSI_RESET);
                profession = scanner.nextLine(); 
            }
            courseToBeEdited.get(0).Profession = profession;
        }

        /**If selected Course will be assigned to a free Professor of the specified Profession**/
        System.out.print("Would you like to proceed to Course assignment? ");
        if (AllAroundProvider.IsYesOrNo()) {
            ArrayList<Professor> freeProfessors = GetFreeProfessors(courseToBeEdited.get(0));
            PrinterHelper.ProfessorGridView(freeProfessors);     
            if (freeProfessors.isEmpty()) {
                System.out.print(PrinterHelper.ANSI_RED + "\r\nThere are no free Professors with profession: [" + courseToBeEdited.get(0) + "] to assign to this Course." + PrinterHelper.ANSI_RESET);   
            }
            else{                
                System.out.print("Assign Professor Id to this Course: ");
                String professorId = scanner.nextLine();
                while (!IsValidProfessorId(professorId)) {
                    PrinterHelper.ProfessorGridView(freeProfessors);
                    System.out.print(PrinterHelper.ANSI_RED + "\r\nEnter a valid Professor Id: " + PrinterHelper.ANSI_RESET);
                    professorId = scanner.nextLine(); 
                }
                Professor professorToBeAssigned = ProfessorProvider.GetProfessor(professorId);
                professorToBeAssigned.Course = courseToBeEdited.get(0);
                ProfessorProvider.EditProfessor(professorToBeAssigned);
            } 
        }
        PrinterHelper.ResetScreen();
        CourseProvider.EditCourse(courseToBeEdited.get(0));
        PrinterHelper.CourseGridView(courseToBeEdited);     
        System.out.print(PrinterHelper.ANSI_GREEN + "\r\nCourse " + courseToBeEdited.get(0) + " was edited successfuly" + PrinterHelper.ANSI_RESET); 
    }
    
    /**Deletes specified Course**/
    public static void DeleteCourse(){
        ArrayList<Course> courses = GetCourses(); 
        
        PrinterHelper.CourseGridView(courses);
        Scanner scanner = new Scanner(System.in);
        System.out.print("Course Id: ");
        String courseId = scanner.nextLine();
        while (!IsValidCourseId(courseId, courses)) {            
            PrinterHelper.CourseGridView(courses);
            System.out.print(PrinterHelper.ANSI_RED + "Enter a valid Course Id: " + PrinterHelper.ANSI_RESET);
            courseId = scanner.nextLine(); 
        }                
        ArrayList<Course> oldCourseToArrayList = GetCourse(courseId);   
        PrinterHelper.CourseGridView(oldCourseToArrayList);
        System.out.print(PrinterHelper.ANSI_RED + "\r\nAre you sure you want to remove course " + oldCourseToArrayList.get(0).Title + " ?" + PrinterHelper.ANSI_RESET);
        if (AllAroundProvider.IsYesOrNo()) {
            ProfessorProvider.DeleteProfessor(courseId);
            System.out.print(PrinterHelper.ANSI_GREEN + "Professor " + oldCourseToArrayList.get(0).Title + " was removed successfully" + PrinterHelper.ANSI_RESET);
        } 
    }
    
    /**Gets professors of the specified Course Profession that do not have any Course assignment**/
    private static ArrayList<Professor> GetFreeProfessors(Course course){
        ArrayList<Professor> allProfessors = ProfessorProvider.GetProfessors();
        ArrayList<Professor> freeProfessors = new ArrayList<>();
        for (int i = 0; i < allProfessors.size(); i++) {
            if (allProfessors.get(i).Course.Professor.equals("*") && allProfessors.get(i).Profession.equals(course.Profession)) {
                freeProfessors.add(allProfessors.get(i));
            }
        }
        return freeProfessors;
    }
    
    /**Gets the next iterated Course id. Example if the last of Semester 3 is S0302 the Id S0303 will be returned**/
    private static String GetNextCourseId(String semester){         
        StringBuilder stringBuilder = new StringBuilder();
        ArrayList<Course> allCourses = CourseProvider.GetCourses();
        ArrayList<Course> semesterCourses = new ArrayList<>();
        for (int i = 0; i < allCourses.size(); i++) {
            if (allCourses.get(i).Semester.equals(semester)) {
                semesterCourses.add(allCourses.get(i));
            }
        }
        String lastCourseId = semesterCourses.get(semesterCourses.size() - 1).Id.substring(1);
        try {
               int newCourseId = Integer.parseInt(lastCourseId) + 1;               
               stringBuilder.append(newCourseId);
               while (stringBuilder.length() < 4
                       ) {                
                   stringBuilder.insert(0, "0");
            }
               stringBuilder.insert(0, 'C');
        } catch (NumberFormatException exception) {
            System.out.print(exception.getMessage());
        }
        return stringBuilder.toString();
    }
    
    /**User input validations**/
    private static boolean IsValidInput(String type, String userInput){
        switch(type){
            case "title":
                if (userInput.length() > 30 || userInput.isEmpty())
                    return false;       
                break;
                
            case "semester":
                if (!Arrays.stream(new String[]{"1", "2", "3"}).anyMatch(userInput :: equals))
                    return false;                
                break;
            case "profession":
                if (!Arrays.stream(new String[]{"Physics", "Math", "Engineering"}).anyMatch(userInput :: equals))
                    return false; 
                break;
        }
        return true;
    }
    
    private static boolean IsValidCourseId(String id, ArrayList<Course> courses){
        for (int i = 0; i < courses.size(); i++) {
            if(courses.get(i).Id.equals(id)){
                return true;
            }
        }
        return false;
    }
    
    private static boolean IsValidProfessorId(String userInput){
        ArrayList<Professor> allProfessors = ProfessorProvider.GetProfessors();
        for (int i = 0; i < allProfessors.size(); i++) {
            if (allProfessors.get(i).Id.equals(userInput))  {
                return true;
            }
        }
        return false;
    }
}