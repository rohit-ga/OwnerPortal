<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Owner Portal Home Page</title>
</head>
<body>
	<div style="text-align: center;">
		<img src="Images/OwnerPortal.jpg" height="20%" width="7%"
			style="float: left" /><br> <br> <br>
		<%
		    response.setHeader("Cache-Control", "no-cache");
		    response.setHeader("Cache-Control", "no-store");
		    response.setHeader("Pragma", "no-cache");
		    response.setDateHeader("Expires", 0);
		%>
		<%
		    if (request.getAttribute("message") == null) {
		    } else {
		        out.print(request.getAttribute("message"));
		    }
		%>
		<h2>Owner Portal</h2>
		<div style="text-align: center;">
			<form action="usercontroller?action=login" method="post">
				Email :- <input type="email" name="email" required><br>
				<br> Password :- <input type="password" name="password"
					required><br> <br> <input type="submit"
					value="Login"><br> <br> <a
					href="registration.jsp">New User? Register Here</a>
			</form>
		</div>
	</div>
</body>
</html>