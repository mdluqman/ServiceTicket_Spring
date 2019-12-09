package Servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Business.StudentOperations;
import Entities.Absentees;
import Entities.Marks;
import Entities.Subjects;

public class StudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public StudentServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		StudentOperations studentOperations = new StudentOperations();
		HttpSession session = request.getSession(false);
		String username = (String) session.getAttribute("username");
		System.out.println(username);
		System.out.println(request.getParameter("operation"));
		if(request.getParameter("operation") != null && request.getParameter("operation").equals("View Marks")) {
			System.out.println("hi");
			List<Marks> marks = studentOperations.getMarks(username);
			RequestDispatcher dispatcher = request.getRequestDispatcher("StudentMarks.jsp");
			request.setAttribute("marks", marks);
			System.out.println(marks.toString());
			dispatcher.forward(request, response);
		}
		if(request.getParameter("operation") != null && request.getParameter("operation").equals("View Attendance")) {
			System.out.println("hi");
			List<Absentees> absentdates = studentOperations.getAttendance(username);
			RequestDispatcher dispatcher = request.getRequestDispatcher("ViewAttendance.jsp");
			request.setAttribute("students", absentdates);
			request.setAttribute("ch", "StudentView");
			System.out.println(absentdates.toString());
			dispatcher.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
