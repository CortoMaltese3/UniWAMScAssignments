package hendricks.Providers;
import hendricks.Models.Professor;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

public class ProfessorProvider extends AllAroundProvider{
    
    private static final String PROFESSOR_FILE = "\\Professors.txt";
    
    public static void WriteToProfessorsFile(ArrayList<Professor> professors){
        for (int i = 0; i < professors.size(); i++) {
            String coursesAssignedToProfessor = "";
            for (int j = 0; j < professors.get(i).Courses.size(); j++) {
                coursesAssignedToProfessor = professors.get(i).Courses.get(j).Id + ",";
            }
            try {
            FileWriter writer = CreateFileWriter(PROFESSOR_FILE, true);
            writer.write("\r\n" + professors.get(i).Id + "|" + professors.get(i).Name + "|" + professors.get(i).Email + "|" + professors.get(i).PhoneNumber + "|" + professors.get(i).Profession + "|" + coursesAssignedToProfessor);
            writer.close();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }  
    
        public static String CoursesAssignedToProfessor(Professor professor){    
        String coursesToString = "";
        StringBuilder stringBuilder = new StringBuilder();
        try {
            for (int i = 0; i < professor.Courses.size(); i++) {
                stringBuilder.append(professor.Courses.get(i).Id).append(",");
            }
            coursesToString = stringBuilder.toString();
            if (coursesToString.length() > 0 ) {
                coursesToString = coursesToString.substring(0, coursesToString.length() - 1);
            }
            return coursesToString;
        } catch (Exception e) {
            return coursesToString;
        }
    } 
    
    public static ArrayList<Professor> GetProfessors(){
        ArrayList <Professor> professors = new ArrayList<>();
        try {          
            BufferedReader reader = CreateBufferedReader(PROFESSOR_FILE);
            String line;
            String[] parts;
            
            while((line = reader.readLine()) != null) {
                parts = line.split("\\|");
                if (parts[5].equals("*")) {
                    professors.add(new Professor(parts)); 
                }
                else{
                    professors.add(new Professor(parts, CourseProvider.GetCourses(parts[5]))); 
                }
            }            
            reader.close(); 
        } catch (IOException ex) {
            System.out.println(ex.getMessage());   
        }
        professors.sort(Comparator.comparing((professor) -> professor.Id));        
        return professors;
    }
    
    public static Professor GetProfessor(String id){
        Professor professor = new Professor();
        ArrayList<Professor> professors = GetProfessors();
        for (int i = 0; i <professors.size(); i++) {
                if (professors.get(i).Id == null ? id == null : professors.get(i).Id.equals(id)) {
                professor = professors.get(i);
            } 
        }
        return professor;
    }
    
    public static void AddProfessor(Professor newProfessor){        
        try {
            FileWriter writer = CreateFileWriter(PROFESSOR_FILE, true);
            writer.write("\r\n" + newProfessor.Id + "|" + newProfessor.Name + "|" + newProfessor.Email + "|" + newProfessor.Email + "|" + newProfessor.Profession);
            writer.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public static void EditProfessor(Professor oldProfessor, Professor newProfessor){
        ArrayList<Professor> professors = GetProfessors();
        professors.set(professors.indexOf(oldProfessor), newProfessor);   
        professors.sort(Comparator.comparing((professor) -> professor.Id));
        AllAroundProvider.ClearFile(PROFESSOR_FILE);
        for (int i = 0; i < professors.size(); i++) {
            AddProfessor(professors.get(i));
        }
    }
    
    public static void DeleteProfessor(String id){
        ArrayList<Professor> professors = GetProfessors();
        professors.removeIf(professor -> (professor.Id == null ? id == null : professor.Id.equals(id)));
        professors.sort(Comparator.comparing((professor) -> professor.Id));    
        AllAroundProvider.ClearFile(PROFESSOR_FILE);
        for (int i = 0; i < professors.size(); i++) {
            AddProfessor(professors.get(i));
        }
    }
}