<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<form action="search">
		<h3>Search Account By</h3>
		<li><a href="searchByAccountNumber">Search By Account Number</a></li>
         <li><a href="searchByName">Search By Name</a></li>
	</form><br /><br />
	<div>
		<jsp:include page="HomeLink.html"></jsp:include>
	</div>
</body>
</html>