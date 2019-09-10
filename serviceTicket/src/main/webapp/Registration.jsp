<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
      <%@page import="javax.ws.rs.client.*" %>
    <%@page import="javax.ws.rs.core.*" %>
    <%@page import="beans.*" %>
    <%@page import="java.util.*" %>
    <%@page import="org.glassfish.jersey.client.ClientConfig" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>REGISTRATION</title>

<style type="text/css">

input {
  width: 100%;
  padding: 12px;
  border: 1px solid #ccc;
  border-radius: 4px;
  box-sizing: border-box;
  margin-top: 6px;
  margin-bottom: 16px;
}

input[type=submit] {
  background-color: #4CAF50;
  color: white;
}

.container {
  background-color: #f1f1f1;
  padding: 20px;
}

#message {
  display:none;
  background: #f1f1f1;
  color: #000;
  position: relative;
  padding: 20px;
  margin-top: 10px;
}

#message p {
  padding: 10px 35px;
  font-size: 18px;
}

/ Add a green text color and a checkmark when the requirements are right /
.valid {
  color: green;
}

.valid:before {
  position: relative;
  left: -35px;
  content: "✔";
}

.invalid {
  color: red;
}

.invalid:before {
  position: relative;
  left: -35px;
  content: "✖";
}

</style>
			 
  <% 
  Integer ch=(Integer)request.getAttribute("ch");
  List<deptInfo> dept=(List<deptInfo>)request.getAttribute("dept");
  %>
<script type="text/javascript">
function checkPassword() { 
    password1 = document.forms["form"]["password"].value; 
    password2 =  document.forms["form"]["confirmpassword"].value; 

    // If password not entered 
    if (password1 == '') {
        alert ("Please enter Password"); 
        return false;
        }
          
    // If confirm password not entered 
    else if (password2 == '') 
        {
        alert ("Please enter confirm password"); 
        return false;
        }          
    // If Not same return False.     
    else if (password1 != password2) { 
        alert ("\nPassword did not match: Please try again...") 
        return false; 
    } 

    // If same return True. 
    else{ 
        return true; 
    } 
} 
</script>
</head>
<body>
<div class="container">
	<h1 align="center">Registration Page</h1>
	<br>
	<form action="register" name="form" method="Post" id="formID" onsubmit="return checkPassword()">
			Enter User name:&nbsp;&nbsp;&nbsp;<input type="text" name="username"
				id="username">
			Enter Password:&nbsp;&nbsp;&nbsp;<input type="password"
				name="passwords" id="password" pattern="(?=.*\d)(?=.*[a-z])(?=.*[^\w\s])(?=.*[A-Z]).{8,50}" title="Must contain at least one number, one uppercase, one lowercase letter and one special character with least 8 or more characters" onchange="validatePassword();">
			
		    
<div id="message">
  <h3>Password must contain the following:</h3>
  <p id="letter" class="invalid">A <b>lowercase</b> letter</p>
  <p id="capital" class="invalid">A <b>capital (uppercase)</b> letter</p>
  <p id="number" class="invalid">A <b>number</b></p>
  <p id="specialChar" class="invalid">A <b>special</b> Character</p>
  <p id="length" class="invalid">Minimum <b>8 characters</b></p>
</div>
		
		
			Confirm Password:&nbsp;&nbsp;&nbsp;<input type="password"
				name="confirmpassword" id="confirmpassword">
				
					Select the type of Entry:&nbsp;&nbsp;&nbsp;
					<select name="userTypeId" id="rt"  onchange = "ShowHideDiv()">
			<option value=1>Admin</option>
			<option value=2>ServiceEngineer</option>
			<option value=3>EndUser</option>
			</select> 
			<br/>
			<br/>
			<br/>
			<div id="dvPassport" style="display: none">
			provide a UID for ServiceEngineer<input type="text" name="ServiceEngineerId" placeholder="eg : seid_____"> 
			<br/>
     Department*:    <select name="deptNo"> 
  <%
  for(deptInfo department: dept){
	  out.println("<option value="+department.getDeptNo()+">"+department.getDeptName()+"</option>");
	  
  }%>
</select>
  
</div>
			&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="submit" name="value"
				value="Register!" >
				
	</form>
</div>
<br/><br/><br/>
</form>
	<form action="Admin.jsp" method="POST">
	<input type="submit" value="HomePage">
	</form>
<script type="text/javascript">
    function ShowHideDiv() {
        var ddlPassport = document.getElementById("rt");
        var dvPassport = document.getElementById("dvPassport");
         ddlPassport.value == 2 ? dvPassport.style.display ="block" : dvPassport.style.display ="none";
    }
</script>


<script>
var myInput = document.getElementById("password");
var letter = document.getElementById("letter");
var capital = document.getElementById("capital");
var number = document.getElementById("number");
var length = document.getElementById("length");

// When the user clicks on the password field, show the message box
myInput.onfocus = function() {
  document.getElementById("message").style.display = "block";
}

// When the user clicks outside of the password field, hide the message box
myInput.onblur = function() {
  document.getElementById("message").style.display = "none";
}

// When the user starts to type something inside the password field
myInput.onkeyup = function() {
  // Validate lowercase letters
  var lowerCaseLetters = /[a-z]/g;
  if(myInput.value.match(lowerCaseLetters)) {  
    letter.classList.remove("invalid");
    letter.classList.add("valid");
  } else {
    letter.classList.remove("valid");
    letter.classList.add("invalid");
  }
  
  // Validate capital letters
  var upperCaseLetters = /[A-Z]/g;
  if(myInput.value.match(upperCaseLetters)) {  
    capital.classList.remove("invalid");
    capital.classList.add("valid");
  } else {
    capital.classList.remove("valid");
    capital.classList.add("invalid");
  }

  //Validate special characters
  var specialcharacters = /[^\w\s]/g;
  if(myInput.value.match(specialcharacters)) {  
	    specialChar.classList.remove("invalid");
	    specialChar.classList.add("valid");
	  } else {
		  specialChar.classList.remove("valid");
		  specialChar.classList.add("invalid");
	  }

  // Validate numbers
  var numbers = /[0-9]/g;
  if(myInput.value.match(numbers)) {  
    number.classList.remove("invalid");
    number.classList.add("valid");
  } else {
    number.classList.remove("valid");
    number.classList.add("invalid");
  }
  
  // Validate length
  if(myInput.value.length >= 8) {
    length.classList.remove("invalid");
    length.classList.add("valid");
  } else {
    length.classList.remove("valid");
    length.classList.add("invalid");
  }

  //Confirm Password
}

</script>

</body>
</html>