<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@ page import="com.mysql.*"%>
<%-- <%@ page session="false" %> --%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>All Events Page</title>
</head>
<body>
	<div style="text-align: center;">
		<img src="Images/OwnerPortal.jpg" height="20%" width="7%"
			style="float: left" /><br> <br> <br> <br> <a
			href="createEvent.jsp" style="float: right">Create An Event</a><br>
		<br> <a href="usercontroller?action=logout" style="float: right">Logout</a>
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
		<h2>List of All Events</h2>
		<table border="1" align="center">
			<th>Event Title</th>
			<th>Event Logo</th>
			<th>View</th>
			<c:forEach items="${myEventList}" var="myEventList">
				<tr>
					<td><c:out value="${myEventList.eventTitle}" /></td>
					<td><img
						src="${pageContext.request.contextPath}/photocontroller?eventId=${myEventList.eventId}"
						width="50" height="50"></td>
					<td><a
						href="eventcontroller?action=viewEvent&eventId=${myEventList.eventId}">View</a></td>
				</tr>
				<a
					href="eventcontroller?action=updateUser&eventId=${myEventList.eventId}">Setting</a>

			</c:forEach>
		</table>
	</div>
</body>
</html>