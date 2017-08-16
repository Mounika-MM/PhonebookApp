<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Groups List Page</title>
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
				url : "groups.do",
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
		                            txt += "<tr><td>"+data[i].id+"</td><td>"+data[i].name+"</td><td>"+data[i].type+"</td><td>"+data[i].location+"</td></tr>";
		                        }
		                    }
		                    $("#groupsTable").append(txt);
		                }			                
					}
					else
					{
						$("#statusMsg").html("Status: Error in getting groups. Please check logs.");
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
	<h2 align="center">Groups List</h2>
	<table align="center" id="groupsTable" border=2>
		<tr>
			<th>GroupID</th>
			<th>Name</th>
			<th>Type</th>
			<th>Location</th>
		</tr>
	</table>
	<table align="center">
		<tr>
			<td><a href="creategroup">Create New Group</a></td>
			<td><a href="deletegroup">Delete Group</a></td>
		</tr>
	</table>
	<div align=center id="statusMsg"></div>
</body>
</html>