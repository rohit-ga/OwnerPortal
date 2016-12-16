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
		    if (request.getAttribute("message") == null) {
		    } else {
		        out.print(request.getAttribute("message"));
		    }
		%>
		<h2>Create An Event Here</h2>
		<form action="EventController?action=addEvent" method="post"  enctype="multipart/form-data">
		<c:forEach items="${anEventDetail}" var="anEventDetail">
		<input type="hidden" value="${anEventDetail.eventId}">
		Event Title:-<input type="text" name="eventTitle" value="${anEventDetail.eventTitle}" required><br><br>
		Artist Name:-<input type="text" name="artistName" value="${anEventDetail.artistName}" required><br><br>
		Event Start Date:-<input type="date" name="startDate" value="${anEventDetail.startDate}" required>
		Event End Date:-<input type="date" name="endDate" value="${anEventDetail.endDate}" required><br><br>
		Event Start Time:-<input type="time" name="startTime" value="${anEventDetail.startTime}" required>
		Event End Time:-<input type="time" name="endTime" value="${anEventDetail.endTime}" required><br><br>
		Location:-<input type="text" name="location" value="${anEventDetail.location}" required>
		Event Description:-<input type="text" name="description" value="${anEventDetail.description}"><br><br>
		Event Image:-<input type="file" name="eventImage" value="${anEventDetail.eventImage}">
		Genre:-<input type="text" name="genre" value="${anEventDetail.genre}"><br><br>
			<input type="submit" value="Create">
			</c:forEach>
		</form>
		<form action="EventController?action=editEvent" method="post"  enctype="multipart/form-data"">
		<c:forEach items="${anEventDetail}" var="anEventDetail">
		<input type="hidden" value="${anEventDetail.eventId}">
		<input type="submit" value="Update">
		</c:forEach>
		</form>
	</div>
</body>
</html>