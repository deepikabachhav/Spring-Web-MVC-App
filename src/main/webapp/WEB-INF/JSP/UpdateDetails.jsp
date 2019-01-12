<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form action="updateAccountDB">
 
	<table>
		<tr> 
			<th> Account Number </th>
			<th> Holder Name</th>
			<th>Account Balance </th>
			<th> Salary </th>
			<th> Over Draft Limit </th>
			<th> Type </th>
		</tr>
		<jstl:if test="${account !=null}">
				<tr>
				<td><input name="txtAccountNumber" value=${account.bankAccount.accountNumber} readonly="readonly"></td>
				<td><input name="accountHolderName" value=${account.bankAccount.accountHolderName}></td>
				<td><input value=${account.bankAccount.accountBalance} readonly></td>
				<jstl:if test="${account.salary==true}">
					<td><select name="salary">
							<option value="Yes">Yes</option>
							<option value="No">No</option></select></td>
					<td>${"N/A"}</td>
				<td>${"Savings"}</td>
				</jstl:if>
				<jstl:if test="${account.salary==false}">
					<td><select name="salary">
							<option value="No">No</option>
							<option value="Yes">Yes</option></select></td>
					<td>${"N/A"}</td>
					<td>${"Savings"}</td>
				</jstl:if>
			</tr>
		</jstl:if>
	</table>
	<input type = "submit" value="Submit"> 	&nbsp &nbsp&nbsp
	<input type = "reset" value="reset"> <br>
	</form>
	<div>
		<jsp:include page="HomeLink.html"></jsp:include>
	</div>
</body>
</html>