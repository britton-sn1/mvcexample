<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Diet Logger login</title>
</head>
<body>
<h1>Diet Logger Application - Login</h1>
<form method="post" action="/mcvpract/dlmvc/autheticate/login" >
<table>
<c:if test="${not empty loginForm.error}"><tr><td style="color:red">${loginForm.error}</td></tr></c:if>
<tr><td>User Name</td><td><input type="text" name="user" value="admin"/></td></tr>
<tr><td>Password</td><td><input type="password" name="password" value="Td?vHwRQbW"/></td></tr>
<tr><td></td><td><input type="submit" value="OK" /></td></tr>
<tr><td><a href="/mcvpract/dlmvc/autheticate/forgotpassword">Forgot Password</a></td></tr>
</table>
</form>
</body>
</html>