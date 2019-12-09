<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ADMIN HOMEPAGE</title>
</head>
<%
String username=(String)session.getAttribute("username");
%>

<body>
${message}

<center>
<h1>WELCOME <%=username %></h1>
<div style="background-color:	#8FBC8F ;margin: 200px 50px 75px	 50px">
  <a href="register" >To Register a Admin / EndUser / ServiceEngineer </a><br/><br/><br/>
<a href="viewlist" >View/Delete- Clients(ServiceEngineer/Users)</a><br/><br/><br/>
<a href="getdepts" >View/Create a Department</a><br/><br/><br/>

</div>
<form action="logout" method="POST">
	<input type="submit" value="Logout">
	</form>
</body>
</html>