package Business;

import java.util.List;

import Entities.Marks;
import Entities.StudentAndProfessorDetails;
import Service.ProfessorService;

public class ProfessorOperations {
	
	ProfessorService professorService = new ProfessorService(); 

	public List<StudentAndProfessorDetails> getProfessorClasses(String username) {
		return professorService.getProfessorClasses(username);
	}

	public List<String> getProfessorStudents(StudentAndProfessorDetails classDetails, String username) {
		
		return professorService.getProfessorStudents(classDetails,username);
	}

	public List<Marks> getMarks(StudentAndProfessorDetails details, String username) {
		return professorService.getMarks(details, username);
	}

	public static String updateMarks(Marks studentmarks) {
		return ProfessorService.updateMarks(studentmarks);
		
	}

	public List<StudentAndProfessorDetails> getProfessordetails(StudentAndProfessorDetails profdetails) {
		return ProfessorService.getProfessordetails(profdetails);
	}

	public List<Marks> getMarksforsubjects(Marks profstudMarks) {
		return ProfessorService.getMarksforsubjects(profstudMarks);		
	}

}
