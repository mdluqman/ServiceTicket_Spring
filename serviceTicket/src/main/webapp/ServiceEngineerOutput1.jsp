<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="Beans.EndUserBean"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>STATISTICS</title>
</head>

<%
	Integer ch = (Integer) request.getAttribute("ch");
	if (ch == 4) {
		List<String> severity = (List<String>) request.getAttribute("severity");
		out.println(
				"<center><table border=1><th>Priority</th><th>AverageTimeTakenToCompleteTicket<br/>(int terms of days)</th>");
		if(severity.get(2).equals(null))
		{
			
		}
		else{
		out.println("<tr><th> High Priority TICKET </th><td> " + severity.get(2) + " </td></tr>");
		}
		if(severity.get(1).equals(null))
		{
			
		}
		else{
		out.println("<tr><th> Medium Priority TICKET </th><td> " + severity.get(1) + " </td></tr>");
		}
		if(severity.get(0).equals(null))
		{
			
		}
		else{
		out.println("<tr><th> LOW Priority TICKET </th><td> " + severity.get(0) + " </td></tr>");
		}
		out.println("</table>");
	}
	else if (ch == 5) {
		List<String> serviceengineer = (List<String>) request.getAttribute("service");
		out.println("<center>");
%><table border=1>
	<tr>
		<th>Service-Engineer</th>
		<th>AverageTimeTakenToCompleteTicket (in terms of days)</th>
	</tr>
	<%
		for (int i = 0; i < serviceengineer.size(); i+=2){
			if(serviceengineer.get(i).equals("USER-DELETED"))
			{
				continue;
			}
	%>
	<tr>
		<td><%=serviceengineer.get(i) %></td>
		<td><%=serviceengineer.get(i+1) %></td>
	</tr>
	<%
		}%>
</table>
<% 
		}
	if(ch==6){
		List<String> serviceengineer = (List<String>) request.getAttribute("tickets");
	out.println("<center>");
	 %><table border=1>
	<tr>
		<th>Ticket-Id</th>
		<th>Age Of Open Tickets</th>
	</tr>
	<%
		for (int i = 0; i < serviceengineer.size(); i+=2){
	%>
	<tr>
		<td><%=serviceengineer.get(i) %></td>
		<td><%=serviceengineer.get(i+1) %>
	</tr>
	<%
		}%>
		</table>
		<%
		}
	
else{			switch (ch) {
			case 1:
				out.println(
						"<center><h3>Congratulations!!!<br/>Your request of changing status for given ticketId is fulfilled</h3></center>");
				break;
			case 2:
				out.println(
						"<center><h3>Congratulations!!!<br/>Your request of changing priority for given ticketId is fulfilled</h3></center>");
				break;
			case 3:
				out.println(
						"<center><h3>Sorry!! but you are trying to deal with a dealt or an Incorrigible ticketid</h3></center>");
				break;
			case 11:
				out.println(
						"<center><h3>There are no Tickets raised of any Priority for you!</h3></center>");
				break;
			case 12:
				out.println(
						"<center><h3>Sorry you cannot change priority of a ticket you are currently working on!! rather try changing the status to achieve your requirement </h3></center>");
				break;	
			}
		}
%>
<body>
	<center>
		<br /> <br /> <br /> <br />
		<form action="ServiceEngineer.jsp">
			<input type="submit" name="action" value="HomePage">
		</form>

	</center>
</body>
</html>