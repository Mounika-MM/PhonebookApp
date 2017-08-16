<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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
				type : "GET",
				contentType : "application/json",
				url : "contacts.do",
				data : null,
				dataType : 'json',
				timeout : 100000,
				success : function(data) {
					if (data) {
			            var len = data.length;
			            var txt = "";
		                if(len > 0){
		                    for(var i=0;i<len;i++){
		                        if(data[i].id){
		                            txt += "<tr><td>"+data[i].id+"</td><td>"+data[i].firstName+"</td><td>"+data[i].lastName+"</td><td>"+data[i].home+"</td><td>"+data[i].mobile+"</td><td>"+data[i].email+"</td><td>"+data[i].groupNames+"</td></tr>";
		                        }
		                    }
		                    $("#contactsTable").append(txt);
		                }			                
					}
					else
					{
						$("#statusMsg").html("Status: Error in getting contacts. Please check logs.");
					}
					return false;
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
	<table align="center" id="contactsTable" border=2>
		<tr>
			<th>ContactID</th>
			<th>FirstName</th>
			<th>LastName</th>
			<th>Home</th>
			<th>Mobile</th>
			<th>Email</th>
			<th>Groups</th>
		</tr>
	</table>
	<table align="center">
		<tr>
			<td><a href="createcontact">Create New Contact</a></td>
			<td><a href="deletecontact">Delete Contact</a></td>
		</tr>
	</table>
	<div align=center id="statusMsg"></div>
</body>
</html>