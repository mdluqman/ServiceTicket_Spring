<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>SERVICE-ENGINEER HOMEPAGE</title>
</head>
<%
String username = (String)session.getAttribute("username");
%>
<body bgcolor="#45B39D">
<CENTER>
<h1>WELCOME  <%=username %></h1>
you can perform the following<br/><br/><br/>
<a href="ViewandRespond">View & Respond to a ticket</a><br><br><br>
<a href="ReportperSeverity">PerformanceReportPerSeverity</a><br><br><br>
<a href="ReportperSE">PerformanceReportPerServiceEngineer</a><br><br><br>
<a href="openticketage">AgeOfOpenTicket</a><br><br><br><br/><br/><br/>
<form action="logout" method="POST">
<input type="submit" name="action" value="LOGOUT">
</form>
</CENTER>
</body>
</html>