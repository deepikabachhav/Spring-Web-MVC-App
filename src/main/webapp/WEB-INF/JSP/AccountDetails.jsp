<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<table>
		<tr> 
			<th> <a href="sortByAccountNumber">Account Number</a> </th>
			<th> <a href="sortByName">Holder Name</a> </th>
			<th> <a href="sortByAccountBalance"> Account Balance</a>  </th>
			<th> <a href="sortBySalary">Salary</a> </th>
			<th> Over Draft Limit </th>
			<th> Type </th>
		</tr>
		<jstl:if test="${account !=null}">
				<tr>
				<td>${account.bankAccount.accountNumber}</td>
				<td>${account.bankAccount.accountHolderName }</td>
				<td>${account.bankAccount.accountBalance}</td>
				<td>${account.salary==true?"Yes":"No"}</td>
				<td>${account.odLimit}</td>
				<td>${account.bankAccount.type}</td>
			</tr>
		</jstl:if>
		<jstl:if test="${accounts!=null}">
			<jstl:forEach var="account" items="${accounts}">
				<tr>
					<td>${account.bankAccount.accountNumber}</td>
					<td>${account.bankAccount.accountHolderName }</td>
					<td>${account.bankAccount.accountBalance}</td>
					<td>${account.salary==true?"Yes":"No"}</td>
					<td>${account.odLimit}</td>
					<td>${account.bankAccount.type}</td>
				</tr>
			</jstl:forEach>
		</jstl:if>
	</table>
</body>
</html>