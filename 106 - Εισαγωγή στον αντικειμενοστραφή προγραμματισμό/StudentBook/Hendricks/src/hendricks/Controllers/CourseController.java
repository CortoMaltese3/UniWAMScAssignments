package hendricks.Controllers;
import hendricks.Helpers.PrinterHelper;
import hendricks.Providers.CourseProvider;
import hendricks.Models.Course;
import hendricks.Models.Professor;
import hendricks.Providers.AllAroundProvider;
import hendricks.Providers.ProfessorProvider;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class CourseController {
    
    public static ArrayList<Course> GetCourses(){
        ArrayList<Course> courses = CourseProvider.GetCourses();
        return courses;
    }
    
    public static ArrayList<Course> GetCourse(String id){
        ArrayList<Course> courseToArrayList = new ArrayList<>();
        Course course = CourseProvider.GetCourse(id);
        courseToArrayList.add(course);
        return courseToArrayList;
    }
    
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
    
    public static void EditCourse(){
        Course newCourse = new Course();
        ArrayList<Course> newCourseToArrayList = new ArrayList<>();
        ArrayList<Course> courses = GetCourses(); 
        newCourseToArrayList.add(newCourse);
        
        ArrayList<Professor> allProfessors = ProfessorProvider.GetProfessors();
        ArrayList<Professor> freeProfessors = GetFreeProfessors(allProfessors);
        
        PrinterHelper.CourseGridView(courses);
        Scanner scanner = new Scanner(System.in);
        System.out.print("Course Id: ");
        String courseId = scanner.nextLine();
        while (!IsValidCourseId(courseId, courses)) {            
            PrinterHelper.CourseGridView(courses);
            System.out.print(PrinterHelper.ANSI_RED + "Enter a valid Course Id: " + PrinterHelper.ANSI_RESET);
            courseId = scanner.nextLine(); 
        }                
        ArrayList<Course> oldCourse = GetCourse(courseId);        
        newCourse.Id = courseId;
        PrinterHelper.CourseGridView(newCourseToArrayList);
        
        System.out.print("Edit Course title: ");
        String title = scanner.nextLine();
        while (!IsValidInput("title", title)) {  
            PrinterHelper.CourseGridView(newCourseToArrayList);
            System.out.print(PrinterHelper.ANSI_RED + "Enter a valid Course Id: " + PrinterHelper.ANSI_RESET);
            title = scanner.nextLine(); 
        }
        newCourse.Title = title;
        PrinterHelper.CourseGridView(newCourseToArrayList);
        
        System.out.print("Edit Course semester: ");
        String semester = scanner.nextLine();
        while (!IsValidInput("semester", semester)) {  
            PrinterHelper.CourseGridView(courses);
            System.out.print(PrinterHelper.ANSI_RED + "Enter a valid Course semester: " + PrinterHelper.ANSI_RESET);
            semester = scanner.nextLine(); 
        }
        newCourse.Semester = semester;
        PrinterHelper.CourseGridView(newCourseToArrayList);
        
        System.out.print("Edit Course profession: ");
        String profession = scanner.nextLine();
        while (!IsValidInput("profession", profession)) {  
            PrinterHelper.CourseGridView(courses);
            System.out.print(PrinterHelper.ANSI_RED + "Enter a valid profession. Choices are between Physics, Math and Engineering: " + PrinterHelper.ANSI_RESET);
            profession = scanner.nextLine(); 
        }
        newCourse.Profession = profession;
        
        System.out.print("Would you like to proceed to Course assignment? ");
        if (AllAroundProvider.IsYesOrNo()) {
            PrinterHelper.ProfessorGridView(freeProfessors);     
            if (freeProfessors.isEmpty()) {
                System.out.print(PrinterHelper.ANSI_RED + "\r\nThere are no free Professors to assign to this Course." + PrinterHelper.ANSI_RESET);   
            }
            else{                
                System.out.print("Assign Professor Id to this Course: ");
                String professorId = scanner.nextLine();
                while (!IsValidProfessorId(professorId)) {
                    PrinterHelper.ProfessorGridView(freeProfessors);
                    System.out.print(PrinterHelper.ANSI_RED + "\r\nEnter a valid Professor Id: " + PrinterHelper.ANSI_RESET);
                    professorId = scanner.nextLine(); 
                }
                Professor oldProfessor = ProfessorProvider.GetProfessor(professorId);
                Professor newProfessor = oldProfessor;
                newProfessor.Course = newCourse;
                ProfessorProvider.EditProfessor(oldProfessor, newProfessor);
            } 
        }
        CourseProvider.EditCourse(oldCourse.get(0), newCourse);
        PrinterHelper.CourseGridView(newCourseToArrayList);     
        System.out.print(PrinterHelper.ANSI_GREEN + "\r\nCourse was edited successfuly" + PrinterHelper.ANSI_RESET); 
     
    }
    
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
        System.out.print(PrinterHelper.ANSI_RED + "Are you sure you want to remove course " + oldCourseToArrayList.get(0).Title + " ?" + PrinterHelper.ANSI_RESET);
        if (AllAroundProvider.IsYesOrNo()) {
            ProfessorProvider.DeleteProfessor(courseId);
            System.out.print(PrinterHelper.ANSI_GREEN + "Professor " + oldCourseToArrayList.get(0).Title + " was removed successfully" + PrinterHelper.ANSI_RESET);
        } 
    }
    
    private static ArrayList<Professor> GetFreeProfessors(ArrayList<Professor> allProfessors){
        ArrayList<Professor> freeProfessors = new ArrayList<>();
        for (int i = 0; i < allProfessors.size(); i++) {
            if (allProfessors.get(i).Course.Professor.equals("*")) {
                freeProfessors.add(allProfessors.get(i));
            }
        }
        return freeProfessors;
    }
    
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
        //C0302
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
}
