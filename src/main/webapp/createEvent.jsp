<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
		<h2>Create An Event Here</h2>
		Event Title:-<input type="text" name="eventTitle" required><br><br>
		Artist Name:-<input type="text" name="artistName" required><br><br>
		Event Start Date:-<input type="date" name="startDate" required>
		Event End Date:-<input type="date" name="endDate" required><br><br>
		Event Start Time:-<input type="time" name="startTime" required>
		Event End Time:-<input type="time" name="endTime" required><br><br>
		Location:-<input type="text" name="location" required>
		Event Description:-<input type="text" name="description"><br><br>
		Event Image:-<input type="file" name="eventLogo">
		Genre:-<input type="text" name="genre"><br><br>
		<form action="allEvents.jsp" method="get">
			<input type="button" value="Save">
		</form><br>
		<form action="createEvent.jsp" method="post">
			<input type="button" value="Create">
		</form>
	</div>
</body>
</html>