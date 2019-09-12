<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>EndUserHomepage</title>
</head>
<%
String username = (String)session.getAttribute("username");
%>
<body bgcolor="#45B39D"><center>
<h1>WELCOME <%=username %></h1>
you can perform the following	
<a href="viewtickets" ><h3>View Tickets</h3></a>
<a href="raiseticket" ><h3>raise Tickets</h3></a>
<br/><br/><br/>
<form action="logout" method="POST">
<input type="submit" name="action" value="LOGOUT">
</center></body>
</html>