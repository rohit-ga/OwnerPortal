<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Registration Page</title>
</head>
<body>
	<div style="text-align: center;">
		<img src="Images/OwnerPortal.jpg" height="20%" width="7%"
			style="float: left" /><br> <br> <br> <br>
		<%
		    if (request.getAttribute("message") == null) {
		    } else {
		        out.print(request.getAttribute("message"));
		    }
		%>
		<%
		    if (request.getAttribute("errors") == null) {
		    } else {
		        out.print(request.getAttribute("errors"));
		    }
		%>
		<h2>Register Your Details Here</h2>
		<form action="UserController?action=register" method="post">
			First Name:-<br> <input type="text" name="fName"
				pattern="[A-Za-z]{1,32}"><br> Last Name:-<br> <input
				type="text" name="lName" pattern="[A-Za-z]{1,32}"><br>
			Gender:-<input type="radio" name="sex" value="male" checked>Male
			<input type="radio" name="sex" value="female">Female<br>
			Contact:-<br> <input type="tel" name="contact"
				pattern="[789][0-9]{9}" maxlength="10" required><br>
			Email:-<br> <input type="email" name="email" required><br>
			Password:-<br> <input type="password" name="password"
				pattern=".{6,}" required><br> <br> <input
				type="submit" value="Register"><br> <br>
		</form>
	</div>
</body>
</html>