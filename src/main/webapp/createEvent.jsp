<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Create New Event Page</title>
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
		    if (session.getAttribute("email") == null)
		        response.sendRedirect("home.jsp");
		%>
		<%
		    if (request.getAttribute("message") == null) {
		    } else {
		        out.print(request.getAttribute("message"));
		    }
		%>
		<form action="eventcontroller?action=addEvent" method="post" enctype="multipart/form-data">
		<h2>Create An Event Here</h2>
		Event Title:-<input type="text" name="eventTitle" required><br><br>
		Artist Name:-<input type="text" name="artistName" required><br><br>
		Event Start Date:-<input type="date" name="startDate" required>
		Event End Date:-<input type="date" name="endDate" required><br><br>
		Event Start Time:-<input type="time" name="startTime" required>
		Event End Time:-<input type="time" name="endTime" required><br><br>
		Location:-<input type="text" name="location" required>
		Event Description:-<input type="text" name="description" ><br><br>
		Event Image:-<input type="file" name="eventImage">
		Genre:-<input type="text" name="genre"><br><br>
			<input type="submit" value="Create">
		</form>
	</div>
</body>
</html>