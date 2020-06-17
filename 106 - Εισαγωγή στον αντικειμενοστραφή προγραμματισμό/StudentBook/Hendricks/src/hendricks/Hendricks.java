package hendricks;

import hendricks.Controllers.CourseController;
import hendricks.Controllers.GradeController;
import hendricks.Controllers.ProfessorController;
import hendricks.Controllers.StudentController;
import hendricks.Providers.AllAroundProvider;
import hendricks.Helpers.PrinterHelper;

public class Hendricks {

    public static void main(String[] args) {
        AllAroundProvider.InitializeTextFiles();
        while (MainMenu()) {             
        }        
    }
    
    private static boolean MainMenu(){        
        PrinterHelper.PrintMainMenuOptions();
        String userMainMenuOption = AllAroundProvider.ScanMenuInput("mainMenu");      

        switch(userMainMenuOption){
            case "1":
                PrinterHelper.PrintStudentMenuOptions();
                String userStudentMenuOption = AllAroundProvider.ScanMenuInput("student");
                
                switch(userStudentMenuOption){
                    case "1":
                        PrinterHelper.StudentGridView(StudentController.GetStudents());                     
                        break;    
                    case "2":
                        StudentController.CreateStudent();                        
                        break;
                    case "3":
                        StudentController.EditStudent();                        
                        break;
                    case "4":
                        StudentController.DeleteStudent();                        
                        break;  
                    case "0":
                        
                        break;
                }
                PrinterHelper.ResetScreen();
                break;
                
            case "2":
                PrinterHelper.PrintProfessorMenuOptions();
                String userProfessorMenuOption = AllAroundProvider.ScanMenuInput("professor");
                
                switch(userProfessorMenuOption){
                    case "1":
                        PrinterHelper.ProfessorGridView(ProfessorController.GetProfessors());                         
                        break;
                    case "2":
                        ProfessorController.CreateProfessor();                        
                        break;
                    case "3":
                        ProfessorController.EditProfessor();                        
                        break;
                    case "4":
                        ProfessorController.DeleteProfessor();                        
                        break;  
                    case "0":
                        
                        break;
                }
                PrinterHelper.ResetScreen();
                break;
                
            case "3":
                PrinterHelper.PrintCourseMenuOptions();
                String userCourseMenuOption = AllAroundProvider.ScanMenuInput("course");
                
                switch(userCourseMenuOption){
                    case "1":
                        PrinterHelper.CourseGridView(CourseController.GetCourses());                         
                        break;
                    case "2":
                        CourseController.CreateCourse();                        
                        break;
                    case "3":
                        CourseController.EditCourse();                        
                        break;
                    case "4":
                        CourseController.DeleteCourse();
                        break;
                    case "0":
                        
                        break;
                }
                PrinterHelper.ResetScreen();
                break;
            case "4":
                PrinterHelper.PrintGradeMenuOptions();
                String userGradeMenuOption = AllAroundProvider.ScanMenuInput("grade");
                
                switch(userGradeMenuOption){
                    case "1":
                        GradeController.GetStudentGrades();                         
                        break;
                    case "2":
                        GradeController.GetCourseStatistics();
                        break;
                    case "3":
                        GradeController.AlterStudentGradeToCourse();
                        break;
                    case "0":
                        
                        break;
                }
                PrinterHelper.ResetScreen();
                break;
            case "0":
                AllAroundProvider.TerminateHendricks();
                return false;
        }
        return true;
    }
}