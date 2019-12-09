<%@page import="Entities.Absentees"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	@SuppressWarnings("unchecked")
	List<Absentees> students = (List<Absentees>) request.getAttribute("students");
	String ch = (String) request.getAttribute("ch");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Attendance</title>
</head>
<body>
<%
		if (ch == null ) {
	%>
<form action="AbsenteesServlet">
		Date:<input type="date" name="date"> <input type="submit"
			name="action" value="Search">
	</form>
	<%
	}
		if (ch == "ProfessorView" ) {
	%>
	<form action="AbsenteesServlet">
		Date:<input type="date" name="date"> <input type="submit"
			name="action" value="Search">
	</form>
	<table>
		<tr>
			<th>StudentName</th>
			<th>SubjectName</th>
		</tr>
		<%
			for (int i = 0; i < students.size(); i++) {
		%>
		<tr>
			<td><%= students.get(i).getUsername().getUsername()%></td>
			<td><%= students.get(i).getSubject_ID().getSubjectName() %></td>
		</tr>		
			<%
				}
			%>
		
	</table>
	<%
		}
	else if (ch == "StudentView" ) {
	%>
	<table>
		<tr>
			<th>StudentName</th>
			<th>SubjectName</th>
		</tr>
		<%
			for (int i = 0; i < students.size(); i++) {
		%>
		<tr>
			<td><%= students.get(i).getDate()%></td>
			<td><%= students.get(i).getSubject_ID().getSubjectName() %></td>
		</tr>		
			<%
				}
			%>
		
	</table>
	<%
		}
	%>
</body>
</html>