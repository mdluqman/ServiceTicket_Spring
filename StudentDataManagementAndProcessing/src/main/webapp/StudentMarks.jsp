<%@page import="Entities.Marks"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<style>
table,tr,td,th{
border: orange solid 2px;
 border-collapse: collapse;
text-align: center;
margin: 25px 320px;
}
body{
text-align: center;
background-color: khaki;
}
</style>
<body>
<h2>Student Marks</h2>
	<%
List<Marks> marks = (List<Marks>) request.getAttribute("marks");
	String ch = (String) request.getAttribute("ch");
	 %>
	 <table>
	 <tr>
	 <th>Student_Name</th>
	 <th>Semester</th>
	 <th>Subject_Name</th>
	 <th>Mid-I Marks</th>
	 <th>Mid-II Marks</th>
	 <th>Lab_Internals</th>
	 <th>Lab_Externals</th>
	 <th>Subject_Grade</th>
	 </tr>
	 <%
	 for(int i=0;i<marks.size();i++)
	 {
	 %>
	 <tr>
	 <td><%=marks.get(i).getUsername().getUsername() %></td>
	 <td><%=marks.get(i).getSemester()%></td>
	 <td><%=marks.get(i).getSubject_ID().getSubjectName()%></td>
	 <td><%=marks.get(i).getMid1()%></td>
	 <td><%=marks.get(i).getMid2()%></td>
	 <td><%=marks.get(i).getLabInternals()%></td>
	 <td><%=marks.get(i).getLabExternals() %></td>
	 <td><%=marks.get(i).getSemGrades() %></td>
	 </tr>
	 <%
	 }
	 %>
	 </table>
	 <form action ="StudentHomePage.jsp">
	 <input type="submit" value="Back2Homepage">
	 </form>
</body>
</html>