<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form action="withdrawAmount">
		Enter Account Number: <input type="number" name="txtAccountNumber" /><br /><br />
		Enter Amount: <input type="text" name="amount" /> <br /> <br />
		<input type="submit" value="submit">
	</form><br />
		<div>
		<jsp:include page="HomeLink.html"></jsp:include>
		</div>
</body>
</html>