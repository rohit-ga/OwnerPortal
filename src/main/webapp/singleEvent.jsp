<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Single Event Page</title>
</head>
<body>
	<div style="text-align: center;">
		<img src="Images/OwnerPortal.jpg" height="20%" width="7%"
			style="float: left" /><br> <br> <br>
		<h2>Details of Single Events</h2>
		<table border="1" align="center">
			<th>Event Title</th>
			<th>Artist Name</th>
			<th>Event Start Date</th>
			<th>Event End Date</th>
			<th>Event Start Time</th>
			<th>Event End Time</th>
			<th>Event Location</th>
			<th>Event Description</th>
			<th>Event Logo</th>
			<th>Genre</th>
			<th>Edit</th>
<!-- 			<th>Setting</th> -->
			<c:forEach items="${anEventDetail}" var="anEventDetail">
				<tr>
					<td><c:out value="${anEventDetail.eventTitle}" /></td>
					<td><c:out value="${anEventDetail.artistName}" /></td>
					<td><c:out value="${anEventDetail.startDate}" /></td>
					<td><c:out value="${anEventDetail.endDate}" /></td>
					<td><c:out value="${anEventDetail.startTime}" /></td>
					<td><c:out value="${anEventDetail.endTime}" /></td>
					<td><c:out value="${anEventDetail.location}" /></td>
					<td><c:out value="${anEventDetail.description}" /></td>
					<td><img src="${anEventDetail.eventImage}" /></td>
					<td><c:out value="${anEventDetail.genre}" /></td>
					<td><a href="EventController?action=update&eventId=${anEventDetail.eventId}">Edit</a></td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>