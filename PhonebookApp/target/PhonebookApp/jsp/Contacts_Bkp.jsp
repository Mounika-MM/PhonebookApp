<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Contacts List Page</title>
</head>
<script src="js/jquery.min.js"></script>
<script src="js/jquery.validate.min.js"></script>
<script>
	$(document).ready(function() {
		
		function pageOnload()
		{
			
			$.ajax({
				type : "POST",
				contentType : "application/json",
				url : "createcontact.do",
				data : JSON.stringify(contact),
				dataType : 'json',
				timeout : 100000,
				success : function(data) {
					if (data.id != undefined
							&& data.id != null
							&& data.id > 0) {
						$("#statusMsg").html("Status: New Contact Created...Id is " + data.id +". Click <a href='contacts'>HERE</a> to see all contacts");
					}
					else
					{
						$("#statusMsg").html("Status: Error in creating contact. Please check logs.");
					}
				},
				error : function(e) {
				},
				done : function(e) {
				}
			});
			
		}
		
		
		pageOnload();
	});
</script>
<body>
	<h2 align="center">Contacts List</h2>
	<table align="center" border=2>
		<tr>
			<th>ContactID</th>
			<th>FirstName</th>
			<th>LastName</th>
			<th>Home</th>
			<th>Mobile</th>
			<th>Email</th>
			<th>Groups</th>
		</tr>
		<c:forEach items="${contactsList}" var="contact">
			<tr>
				<td>${contact.id}</td>
				<td>${contact.firstName}</td>
				<td>${contact.lastName}</td>
				<td>${contact.home}</td>
				<td>${contact.mobile}</td>
				<td>${contact.email}</td>
				<td>${contact.groupNames}</td>
			</tr>
		</c:forEach>
	</table>
	<table align="center">
		<tr>
			<td><a href="createcontact">Create New Contact</a></td>
			<td><a href="deletecontact">Delete Contact</a></td>
		</tr>
	</table>
</body>
</html>