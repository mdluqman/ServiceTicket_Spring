<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <%@page import="javax.ws.rs.client.*" %>
    <%@page import="javax.ws.rs.core.*" %>
    <%@page import="Beans.*" %>
    <%@page import="java.util.*" %>
    <%@page import="org.glassfish.jersey.client.ClientConfig" %>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>Raising Ticket</title>

</head>
<body >
<%
Integer ch=(Integer)request.getAttribute("ch");
List<deptInfo> dept=(List<deptInfo>)request.getAttribute("dept");
if(ch==null)
{
	ch=10;
}
else if(ch==8)
{
	out.println("kindly enter a valid REQUESTED-END-DATE");
}
	%>
<center><div style="background-color:#DC7633;width:800px; margin:0 auto;" >
<h1>Raise Ticket</h1> 
<form action="verify" name="RaiseTicket" method="post">
  Department*:    <select name="deptNo"> 
  <%
  for(deptInfo department: dept){
	  out.println("<option value="+department.getDeptNo()+">"+department.getDeptName()+"</option>");
	  
  }%>
</select> 
    <br/><br/>
 Type*:          <select name="type">
  <option value="incident">incident</option>
  <option value="general">general</option>
  <option value="service">service</option>
</select>     <br/><br/>  
 Problem Description:       
 <textarea rows="9" cols="45" name="subject">  
Explain/Mention your requirement  
</textarea>   <br/><br/>
 WorkStation number:    <input type="number" name="workStation"  >    <br/><br/> 
   
Priority*:      <select name="ticketPriority">
  <option value="3">High</option>
  <option value="2">Medium</option>
  <option value="1">Low</option>
</select>       <br/><br/>
 Enter EndDate:  <input type="date" name="requestedEndDAte"> 
 </br><br>
     
  

     <input type="submit" name="name" value="raiseticket"> 
</form></></div>
<br/><br/>
<form action="EndUser.jsp" method="POST">
<input type="submit" value="EndUser Homepage">
</form>
</center>
</body>
</html>