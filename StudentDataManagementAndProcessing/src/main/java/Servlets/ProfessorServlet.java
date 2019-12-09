package Servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Business.ProfessorOperations;
import Entities.Branch;
import Entities.LoginCredentials;
import Entities.Marks;
import Entities.StudentAndProfessorDetails;
import Entities.Subjects;

public class ProfessorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ProfessorOperations professorOperations = new ProfessorOperations();
		HttpSession session = request.getSession(false);
		String username = (String) session.getAttribute("username");
		if (request.getAttribute("getClass") != null && request.getAttribute("getClass").equals("getClass")) {
			List<StudentAndProfessorDetails> classList = professorOperations.getProfessorClasses(username);
			RequestDispatcher dispatcher = request.getRequestDispatcher("ProfessorHomePage.jsp");
			request.setAttribute("classList", classList);
			dispatcher.forward(request, response);
		} else if (request.getParameter("operation") != null
				&& request.getParameter("operation").equals("Mark Attendance")) {
			String[] values = request.getParameter("select").split(",");
			System.out.println(values);
			StudentAndProfessorDetails details = new StudentAndProfessorDetails();
			Branch b = new Branch();
			Subjects s = new Subjects();
			s.setSubject_ID(Integer.parseInt(values[3]));
			b.setBranch_ID(Integer.parseInt(values[2]));
			details.setBranch_ID(b);
			details.setSemester(Integer.parseInt(values[1]));
			details.setSection(values[0]);
			details.setSubject_ID(s);
			List<String> students = professorOperations.getProfessorStudents(details, username);
			RequestDispatcher dispatcher = request.getRequestDispatcher("ProfessorOutput.jsp");
			request.setAttribute("AttendanceList", students);
			request.setAttribute("ch", "1");
			request.setAttribute("subjectID", details.getSubject_ID().getSubject_ID());
			dispatcher.forward(request, response);

		} else if (request.getParameter("operation") != null
				&& request.getParameter("operation").equals("Edit midmarks")) {
			String[] values = request.getParameter("select").split(",");
			StudentAndProfessorDetails details = new StudentAndProfessorDetails();
			Branch b = new Branch();
			Subjects s = new Subjects();
			s.setSubject_ID(Integer.parseInt(values[3]));
			b.setBranch_ID(Integer.parseInt(values[2]));
			details.setBranch_ID(b);
			details.setSemester(Integer.parseInt(values[1]));
			details.setSection(values[0]);
			details.setSubject_ID(s);
			List<Marks> studentMarks = professorOperations.getMarks(details, username);
			RequestDispatcher dispatcher = request.getRequestDispatcher("ProfessorOutput.jsp");
			request.setAttribute("MarksList", studentMarks);
			request.setAttribute("ch", "2");
			request.setAttribute("subjectID", details.getSubject_ID().getSubject_ID());
			dispatcher.forward(request, response);

		}
		else if (request.getParameter("operation") != null && request.getParameter("operation").equals("EnterMarks")) {
			Marks studentmarks = new Marks();
			int size = Integer.parseInt(request.getParameter("length"));
			LoginCredentials student = new LoginCredentials();
			Subjects subjectid = new Subjects();
			int len = Integer.parseInt(request.getParameter("subjectID"));
			subjectid.setSubject_ID(len);
			String[] studentname = request.getParameterValues("username");
			String[] m1marks = request.getParameterValues("mid1");
			String[] m2marks = request.getParameterValues("mid2");
			String[] labInternals = request.getParameterValues("labInternals");
			String[] labExternals = request.getParameterValues("labExternals");
			String[] semGrades = request.getParameterValues("semGrades");
			for (int i = 0; i < size; i++) {
				student.setUsername(studentname[i]);
				studentmarks.setUsername(student);
				studentmarks.setMid1(Integer.parseInt(m1marks[i]));
				studentmarks.setMid2(Integer.parseInt(m2marks[i]));
				studentmarks.setLabInternals(Integer.parseInt(labInternals[i]));
				studentmarks.setLabInternals(Integer.parseInt(labExternals[i]));
				studentmarks.setSemGrades(semGrades[i]);
				studentmarks.setSubject_ID(subjectid);
				String message = ProfessorOperations.updateMarks(studentmarks);
			}
		}
		else if (request.getParameter("operation") != null
				&& request.getParameter("operation").equals("View stats")) {
			StudentAndProfessorDetails profdetails = new StudentAndProfessorDetails();
			double avg = 0;
			LoginCredentials user = new LoginCredentials();
			HttpSession professoreSession = request.getSession(false);
			user.setUsername((String) professoreSession.getAttribute("username"));
			profdetails.setUsername(user);
			List<StudentAndProfessorDetails> profdetailsList = professorOperations.getProfessordetails(profdetails);
			List<Marks> statistics = new ArrayList<>();
			for(int i=0;i<profdetailsList.size();i++)
			{
				avg=0;
				Marks profstudMarks = new Marks();
				Branch b = new Branch();
				b.setBranch_ID(profdetailsList.get(i).getBranch_ID().getBranch_ID());
				b.setBranchName(profdetailsList.get(i).getBranch_ID().getBranchName());
				Subjects s = new Subjects();
				s.setSubject_ID(profdetailsList.get(i).getSubject_ID().getSubject_ID());
				s.setSubjectName(profdetailsList.get(i).getSubject_ID().getSubjectName());
				profstudMarks.setSubject_ID(s);
				profstudMarks.setBranch(b);
				profstudMarks.setSemester(profdetailsList.get(i).getSemester());
				profstudMarks.setSection(profdetailsList.get(i).getSection());
				List<Marks> marksList= professorOperations.getMarksforsubjects(profstudMarks);
				for(int j=0;j<marksList.size();j++)
				{
					if(marksList.get(j).getMid1()>marksList.get(j).getMid2())
					{
						avg+= (marksList.get(j).getMid1()*0.7 + marksList.get(j).getMid2()*0.3);
					}
					else
					{
						avg+= (marksList.get(j).getMid2()*0.7 + marksList.get(j).getMid1()*0.3);
					}
				}
				avg = (avg/marksList.size())*(100/20);
				String average = String.valueOf(avg);
				while(average.length() < 5) {
					average += '0';
				}
				Marks stats = new Marks();
				stats.setBranch(b);
				stats.setSubject_ID(s);
				stats.setSection(profdetailsList.get(i).getSection());
				stats.setSemester(profdetailsList.get(i).getSemester());
				stats.setSemGrades(average.subSequence(0, 5)+"%");
				statistics.add(stats);
			}
			RequestDispatcher dispatcher = request.getRequestDispatcher("ProfessorOutput.jsp");
			request.setAttribute("statistics", statistics);
			request.setAttribute("ch", "3");
			request.setAttribute("subjectID", 404);
			dispatcher.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
