<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>All Events Page</title>
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
		<h2>List of All Events</h2>

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
			<c:forEach items="${myEventList}" var="myEventList">
				<tr>
					<td><c:out value="${myEventList.eventTitle}" /></td>
					<td><c:out value="${myEventList.artistName}" /></td>
					<td><c:out value="${myEventList.startDate}" /></td>
					<td><c:out value="${myEventList.endDate}" /></td>
					<td><c:out value="${myEventList.startTime}" /></td>
					<td><c:out value="${myEventList.endTime}" /></td>
					<td><c:out value="${myEventList.location}" /></td>
					<td><c:out value="${myEventList.description}" /></td>
					<td><img src="${pageContext.servletContext.contextPath }/EventController?eventImage=${myEventList.eventImage}" /></td>
					<td><c:out value="${myEventList.genre}" /></td>
				</tr>
			</c:forEach>
		</table>

	</div>
</body>
</html>