<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Setting Page</title>
</head>
<body>
	<div style="text-align: center;">
		<img src="Images/OwnerPortal.jpg" height="20%" width="7%"
			style="float: left" /><br> <br> <br>
		<a href="UserController?action=logout" style="float: right">Logout</a>
		<%
		    response.setHeader("Cache-Control", "no-cache");
		    response.setHeader("Cache-Control", "no-store");
		    response.setHeader("Pragma", "no-cache");
		    response.setDateHeader("Expires", 0);
		    if (session.getAttribute("email") == null)
		        response.sendRedirect("home.jsp");
		%>
		<h2>Edit Your Settings</h2>
		<form action="UserController?action=editUser" method="post">
			<c:forEach items="${userDetails}" var="userDetails">
				<input type="hidden" name="userId" value="${userDetails.userId}">
			First Name:-<br>
				<input type="text" name="fName" value="${userDetails.userFirstName}">
				<br> Last Name:-<br>
				<input type="text" name="lName" value="${userDetails.userLastName}">
				<br>
			Gender:-<input type="radio" name="sex" value="male" checked
					value="${userDetails.userGender}">Male <input type="radio"
					name="sex" value="female" value="${userDetails.userGender}">Female<br>
			Contact:-<br>
				<input type="tel" name="contact" value="${userDetails.userContact}"
					required min="10" max="10">
				<br>
			Email:-<br>
				<input type="email" name="email" value="${userDetails.userEmail}"
					required>
				<br>
				<br>
				<input type="submit" value="Save">
				<br>
				<br>
			</c:forEach>
		</form>
	</div>
</body>
</html>