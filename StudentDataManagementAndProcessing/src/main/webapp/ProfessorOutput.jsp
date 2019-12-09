<%@page import="Entities.Marks"%>
<%@page import="Entities.StudentAndProfessorDetails"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	@SuppressWarnings("unchecked")
	List<String> classList = (List<String>) request.getAttribute("AttendanceList");
	@SuppressWarnings("unchecked")
	List<Marks> MarksList = (List<Marks>) request.getAttribute("MarksList");
	@SuppressWarnings("unchecked")
	List<Marks> statistics = (List<Marks>) request.getAttribute("statistics");
	String ch = (String) request.getAttribute("ch");
	int subjectID = (int) request.getAttribute("subjectID");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ProfessorView</title>
</head>
<body>
	<%
		if (ch.equals("1")) {
	%>
	<form action="AbsenteesServlet">
		<table style="border: 1px solid black; border-collapse: collapse;">
			<tr style="border: 1px solid black; border-collapse: collapse;">
				<th style="border: 1px solid black; border-collapse: collapse;">StudentName</th>
				<th style="text-align: center" colspan="2">Attendance</th>
			</tr>
			<tr style="border: 1px solid black; border-collapse: collapse;">
				<%
					for (int i = 0; i < classList.size(); i++) {
				%>
			
			<tr style="border: 1px solid black; border-collapse: collapse;">
				<td style="border: 1px solid black; border-collapse: collapse;"><%=classList.get(i)%></td>
				<td style="border: 1px solid black; border-collapse: collapse;">Absent:<input
					type="checkbox" name="<%=i%>" value=<%=classList.get(i)%>></td>
			</tr>
			<%
				}
			%>
		</table>
		<input type="hidden" name="subjectID" value=<%=subjectID%>> <input
			type="hidden" name="length" value=<%=classList.size()%>> <input
			type="submit" name="action" value="markabsent">
	</form>
	<%
		} else if (ch.equals("2")) {
	%>
	<form action="ProfessorServlet">
		<table>
			<tr>
				<th>StudentName</th>
				<th>Mid-I</th>
				<th>Mid-II</th>
				<th>Lab_Internal</th>
				<th>Lab_External</th>
				<th>Subject_Grade</th>
			</tr>
			<%
				for (int i = 0; i < MarksList.size(); i++) {
			%>
			<tr>
				<td><input type="text" name="username"
					value=<%=MarksList.get(i).getUsername().getUsername()%>></td>
				<td><input type="text" name="mid1"
					value=<%=MarksList.get(i).getMid1()%>></td>
				<td><input type="text" name="mid2"
					value=<%=MarksList.get(i).getMid2()%>></td>
				<td><input type="text" name="labInternals"
					value=<%=MarksList.get(i).getLabInternals()%>></td>
				<td><input type="text" name="labExternals"
					value=<%=MarksList.get(i).getLabExternals()%>></td>
				<td><input type="text" name="semGrades"
					value=<%=MarksList.get(i).getSemGrades()%>></td>
			</tr>
			<%
				}
			%>
		</table>
		<input type="hidden" name="length" value=<%=MarksList.size()%>>
		<input type="hidden" name="subjectID" value=<%=subjectID%>> <input
			type="submit" name="operation" value="EnterMarks">
	</form>
	<%
		}else if (ch.equals("3")) {
			%>
	<table style="text-align: center; border:2px solid green; margin : 20px auto">
		<tr>
			<th>BranchName</th>
			<th>Semester</th>
			<th>SubjectName</th>
			<th>Section</th>
			<th>PerformanceRate</th>
		</tr>
		<%
						for (int i = 0; i < statistics.size(); i++) {
					%>
		<tr>
			<td><%=statistics.get(i).getBranch().getBranchName() %></td>
			<td><%=statistics.get(i).getSemester() %></td>
			<td><%=statistics.get(i).getSubject_ID().getSubjectName() %></td>
			<td><%=statistics.get(i).getSection()%></td>
			<td><%=statistics.get(i).getSemGrades() %></td>
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