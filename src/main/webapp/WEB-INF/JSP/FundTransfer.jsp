<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form action="fundtransferAmount">
		Enter sender's Account Number: <input type="text" name="senderAccountNumber" maxlength="14" /> <br /> <br />
		Enter receiver's Account Number: <input type="text" name="receiverAccountNumber" maxlength="14" /> <br /> <br />
		Enter Amount: <input type="number" name="amount" /> <br /> <br />
		<input type="submit" value="submit">
	</form><br />
	<div>
		<jsp:include page="HomeLink.html"></jsp:include>
	</div>
</body>
</html>