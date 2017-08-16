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

		$("#DeleteContact").submit(function(event) {
			callAjax();
			return false;
		});

		function callAjax() {

			var contact = {
				id : $.trim($("#contactId").val())
			}

			$.ajax({
				type : "POST",
				contentType : "application/json",
				url : "deletecontact.do",
				data : JSON.stringify(contact),
				dataType : 'json',
				timeout : 100000,
				success : function(data) {
					alert(data.status);
					if (data.status != undefined
							&& data.status != null
							&& data.status ==  true) {
						$("#statusMsg").html("Status: Contact Deleted. Click <a href='contacts'>HERE</a> to see all contacts");
					}
					else
					{
						$("#statusMsg").html("Status: Error in deleting contact. Please check logs.");
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
	<form id="DeleteContact" name="DeleteContact">
		<h2 align="center">Delete Contact</h2>
		<table align=center>
			<tr>
				<td>Contact ID:</td>
				<td><input type="text" name="contactId" id="contactId"></td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td><input type="submit" value="Delete"></td>
			</tr>
		</table>
		<div align=center id="statusMsg"></div>
	</form>
</body>
</html>