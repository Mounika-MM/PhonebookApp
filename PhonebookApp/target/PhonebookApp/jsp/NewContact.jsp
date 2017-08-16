<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create Contact</title>
</head>
<script src="js/jquery.min.js"></script>
<script src="js/jquery.validate.min.js"></script>

<script>
	$(document).ready(function() {

		$("#CreateContact").submit(function(event) {
			callAjax();
			return false;
		});

		function callAjax() {
			
			var grpIds = "";
			if($("#groupId").val() != null && $("#groupId").val() != undefined)
			{
				grpIds = $("#groupId").val().toString();
			}
			
			var contact = {
				firstName : $.trim($("#firstName").val()),
				lastName : $.trim($("#lastName").val()),
				home : $.trim($("#home").val()),
				mobile : $.trim($("#mobile").val()),
				email : $.trim($("#email").val()),
				groupIds: grpIds
			}

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

	});
</script>
<body>
	<!-- <form action="/addContact" method="post"> -->
	<form id="CreateContact" name="CreateContact">
		<h2 align="center">Create New Contact</h2>
		<table align=center>
			<tr>
				<td>First name:</td>
				<td><input type="text" name="firstName" id="firstName"></td>
			</tr>
			<tr>
				<td>Last name:</td>
				<td><input type="text" name="lastName" id="lastName"></td>
			</tr>
			<tr>
				<td>Home:</td>
				<td><input type="text" name="home" id="home"></td>
			</tr>
			<tr>
				<td>Mobile:</td>
				<td><input type="text" name="mobile" id="mobile"></td>
			</tr>
			<tr>
				<td>Email:</td>
				<td><input type="text" name="email" id="email"></td>
			</tr>
			<tr>
				<td>Group:</td>
				<td>
					<select name="groupId" id="groupId" multiple>
						<c:if test="${not empty groupsList}">
						<c:forEach items="${groupsList}" var="group">
							 <option value=${group.id}>${group.name}</option>
					 	</c:forEach>
					 	</c:if>
					</select>
				</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td><input type="submit" value="Create"></td>
			</tr>
		</table>
		<div align=center id="statusMsg"></div>
	</form>
</body>
</html>