package Servlets;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Business.AbsenteesOperations;
import Entities.Absentees;
import Entities.LoginCredentials;
import Entities.Subjects;

public class AbsenteesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		DateFormat df = new SimpleDateFormat("dd/MM/yy");
		Date dateobj = new Date();		
		String action = request.getParameter("action");
		AbsenteesOperations operations = new AbsenteesOperations();
		if(action.equals("markabsent")) {
			int len = Integer.parseInt(request.getParameter("length"));
			List<Absentees> absenteeList = new ArrayList<Absentees>();
			for(int i=0; i<len;i++)
			{
				String un = request.getParameter(String.valueOf(i));
				if(un!=null)
				{
					Absentees a = new Absentees();
					a.setDate(df.format(dateobj));
					LoginCredentials l = new LoginCredentials();
					Subjects s = new Subjects();
					s.setSubject_ID(Integer.parseInt((String)request.getParameter("subjectID")));
					l.setUsername(un);
					a.setUsername(l);
					a.setSubject_ID(s);
					System.out.println(a.getUsername().getUsername());
					absenteeList.add(a);
				}
				else
				{
					continue;
				}
			}
			String reply = operations.insert(absenteeList);
			System.out.println(reply);
			RequestDispatcher dispatcher = request.getRequestDispatcher("ProfessorServlet");
			request.setAttribute("getClass", "getClass");
			dispatcher.forward(request, response);
		}
		else if(action.equals("Search")) {
			String date = request.getParameter("date");
			List<Absentees> students = new ArrayList<Absentees>();
			try {
				students = operations.search(date);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			RequestDispatcher dispatcher = request.getRequestDispatcher("ViewAttendance.jsp");
			request.setAttribute("students", students);
			request.setAttribute("ch", "ProfessorView");
			dispatcher.forward(request, response);
		}
		else if(action.equals("SearchStudent")) {
			String date = request.getParameter("date");
			List<Absentees> students = new ArrayList<Absentees>();
			try {
				HttpSession session = request.getSession(false);
				String username = (String) session.getAttribute("username");
				students = operations.searchstud(date,username);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			RequestDispatcher dispatcher = request.getRequestDispatcher("ViewAttendance.jsp");
			request.setAttribute("students", students);
			request.setAttribute("ch", "StudentView");
			dispatcher.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
