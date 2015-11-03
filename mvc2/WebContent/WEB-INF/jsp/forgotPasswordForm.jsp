<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Forgot Password</title>
</head>
<body>
<form action="/mcvpract/dlmvc/autheticate/sendnewpassword" method="POST">
<table>
<c:if test="${not empty error}"><tr><td style="color:red">${error}</td></tr></c:if>
<tr><td>User Name:</td><td><input type="text" name="username"/></td></tr>
<tr><td>E-mail Address:</td><td><input type="text" name="emailaddr"/></td></tr>
<tr><td></td><td><input type="submit" value="Send New Password" /></td></tr>
</table>
</form>
</body>
</html>