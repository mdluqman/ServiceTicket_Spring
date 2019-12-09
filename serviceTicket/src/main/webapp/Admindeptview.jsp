<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	 <%@page import="javax.ws.rs.client.*" %>
    <%@page import="javax.ws.rs.core.*" %>
    <%@page import="beans.*" %>
    <%@page import="java.util.*" %>
    <%@page import="org.glassfish.jersey.client.ClientConfig" %>
<!DOCTYPE html>
<%
List<deptInfo> dept=(List<deptInfo>)request.getAttribute("dept");
%>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body style="text-align: center;">
	<form action="createdepartment.jsp" method = "POST">
	 <table><tr><th>Department.No</th><th>DepartmentName</th></tr>
  <%
  for(deptInfo department: dept){
		out.println("<tr><td>"+department.getDeptNo()+
			"</td><td>"+department.getDeptName()+"</td></tr>");			
  }%>
    </table>
  <button>Add a department</button>
	</form>

</body>
</html>