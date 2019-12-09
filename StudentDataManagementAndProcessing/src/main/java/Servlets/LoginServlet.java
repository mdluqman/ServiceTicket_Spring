package Servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Business.LoginOperations;
import Entities.LoginCredentials;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LoginCredentials user=new LoginCredentials();
		user.setUsername(request.getParameter("username"));
		user.setPassword(request.getParameter("password"));
		
		LoginOperations loginOperations = new LoginOperations();
		
		if(loginOperations.checkUserExists(user))
		{
			if(loginOperations.validatePassword(user))
			{
				user.setUsertype(loginOperations.getUsertype(user));
				if(user.getUsertype().equals("Student"))
				{
					RequestDispatcher dispatcher = request.getRequestDispatcher("StudentHomePage.jsp");
					HttpSession httpSession = request.getSession();
					httpSession.setAttribute("username", user.getUsername());
					dispatcher.forward(request, response);
				}
				else if(user.getUsertype().equals("Professor"))
				{
					RequestDispatcher dispatcher = request.getRequestDispatcher("ProfessorServlet");
					HttpSession httpSession = request.getSession();
					httpSession.setAttribute("username", user.getUsername());
					request.setAttribute("getClass", "getClass");
					dispatcher.forward(request, response);
				}
			}
			else
			{
				RequestDispatcher dispatcher = request.getRequestDispatcher("Login.jsp");
				request.setAttribute("message", "Wrong password!!!");
				dispatcher.forward(request, response);
			}
		}
		else
		{
			RequestDispatcher dispatcher = request.getRequestDispatcher("Login.jsp");
			request.setAttribute("message", "user does not exist");
			dispatcher.forward(request, response);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
