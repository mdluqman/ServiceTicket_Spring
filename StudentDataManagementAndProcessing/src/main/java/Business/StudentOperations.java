package Business;

import java.util.List;

import Entities.Absentees;
import Entities.Marks;
import Entities.Subjects;
import Service.StudentService;

public class StudentOperations {

	StudentService studentService = new StudentService();
	
	public List<Marks> getMarks(String username) {
		return studentService.getMarks(username);
	}

	public List<Absentees> getAttendance(String username) {
		// TODO Auto-generated method stub
		return studentService.getAttendance(username);
	}

}
