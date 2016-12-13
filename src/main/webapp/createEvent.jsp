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
		<img src="/Images/OwnerPortal.jpg" height="20%" width="5%"
			style="float: left" /><br> <br> <br>
		<h2>Create New event Here</h2>
		Event Title:-<input type="text" name="eventTitle"><br>
		Artist name :-<input type="text" name="artistName"><br>
		Start Date-Time:-<input type="datetime-local" name="startEvent">
		End Date-Time:-<input type="datetime-local" name="endEvent">
		Location:-<input type="text" name="location"><br>
		Description:-<input type="text" name="description"><br>
		Event Logo:-<input type="file" name="eventLogo"><br>
		Genre:-<input type="text" name="genre"><br>
		<form action="save" method="get">
			<input type="button" value="Save">
		</form>
		<form action="create" method="post">
			<input type="button" value="create">
		</form>
	</div>
</body>
</html>