<%@page import="Entities.StudentAndProfessorDetails"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%

@SuppressWarnings("unchecked")
List<StudentAndProfessorDetails> classList = (List<StudentAndProfessorDetails>)request.getAttribute("classList");

%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Professor HomePage</title>
</head>
<body>
<h1 style="text-align: center;">Welcome Professor</h1>
<br><br>

<form action="ProfessorServlet">
<select name="select">

<% for(int i=0; i<classList.size(); i++) {%>
<option value="<%=classList.get(i).getSection()%>,<%=classList.get(i).getSemester()%>,<%=classList.get(i).getBranch_ID().getBranch_ID()%>,<%=classList.get(i).getSubject_ID().getSubject_ID() %>">
Branch:<%=classList.get(i).getBranch_ID().getBranchName() %> Semester: <%=classList.get(i).getSemester() %> Section: <%= classList.get(i).getSection() %> Subject:<%= classList.get(i).getSubject_ID().getSubjectName()%>
</option>
<% } %>
</select>
<br>
<input type="submit" name="operation" value="Mark Attendance"><br>
<input type="submit" name="operation"  value="Edit midmarks"><br>
</form>
<hr/>
<form action="ProfessorServlet">
<input type="submit" name="operation" value="View stats"><br>
</form>
<hr/>
<form action="ViewAttendance.jsp">
<input type="submit" name="operation" value="View Attendance"><br>
</form>

</body>
</html>