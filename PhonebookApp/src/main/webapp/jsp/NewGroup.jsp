<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create Group</title>
</head>
<script src="js/jquery.min.js"></script>
<script src="js/jquery.validate.min.js"></script>

<script>
	$(document).ready(function() {

		$("#CreateGroup").submit(function(event) {
			callAjax();
			return false;
		});

		function callAjax() {

			var contact = {
				name : $.trim($("#name").val()),
				type : $.trim($("#type").val()),
				location : $.trim($("#location").val())
			}

			$.ajax({
				type : "POST",
				contentType : "application/json",
				url : "creategroup.do",
				data : JSON.stringify(contact),
				dataType : 'json',
				timeout : 100000,
				success : function(data) {
					if (data.id != undefined
							&& data.id != null
							&& data.id > 0) {
						$("#statusMsg").html("Status: New Group Created...Id is " + data.id +". Click <a href='groups'>HERE</a> to see all groups");
					}
					else
					{
						$("#statusMsg").html("Status: Error in creating group. Please check logs.");
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
	<!-- <form action="/addGroup" method="post"> -->
	<form id="CreateGroup" name="CreateGroup">
		<h2 align="center">Create New Group</h2>
		<table align=center>
			<tr>
				<td>Group name:</td>
				<td><input type="text" name="name" id="name"></td>
			</tr>
			<tr>
				<td>Type:</td>
				<td><input type="text" name="type" id="type"></td>
			</tr>
			<tr>
				<td>Location:</td>
				<td><input type="text" name="location" id="location"></td>
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