<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Food Item</title>
</head>
<body>
<table>
	<tr><td>Add Food Item</td></tr>
	<c:if test="${not empty foodItemForm.error}"><tr><td style="color:red">${foodItemForm.error}</td></tr></c:if>
	<tr><td>
		<form:form method="post" action="/mcvpract/dlmvc/updateFoodItem">
		<table>
			<tr><td>Name</td><td>${foodItemForm.name}<input name="name" type="hidden" value="${foodItemForm.name}"/></td><td style="color:red"><c:if test="${foodItemForm.nameNotSupplied}">Not Supplied</c:if></td></tr>
			<tr><td>Units</td><td><input name="units" type="text" value="${foodItemForm.units}"  /></td><td style="color:red"><c:if test="${foodItemForm.unitsNotSupplied}">Not Supplied</c:if></td><tr>
			<tr><td>Carbs</td><td><input name="carbs" type="number" step="0.01" value="${foodItemForm.carbs}" /></td><td style="color:red"><c:if test="${foodItemForm.carbsNotSupplied}">Not Supplied</c:if></td><tr>
			<tr><td>Sugars</td><td><input name="sugars" type="number" step="0.01" value="${foodItemForm.sugars}"  /></td><td style="color:red"><c:if test="${foodItemForm.sugarsNotSupplied}">Not Supplied</c:if></td><tr>
			<tr><td>Fats</td><td><input name="fats" type="number" step="0.01" value="${foodItemForm.fats}" /></td><td style="color:red"><c:if test="${foodItemForm.fatsNotSupplied}">Not Supplied</c:if></td><tr>
			<tr><td>Saturates</td><td><input name="saturates" type="number" step="0.01" value="${foodItemForm.saturates}" /></td><td style="color:red"><c:if test="${foodItemForm.saturatesNotSupplied}">Not Supplied</c:if></td><tr>
			<tr><td>Protein</td><td><input name="protein" type="number" step="0.01" value="${foodItemForm.protein}" /></td><td style="color:red"><c:if test="${foodItemForm.proteinNotSupplied}">Not Supplied</c:if></td><tr>
			<tr><td>Fibre</td><td><input name="fibre" type="number" step="0.01" value="${foodItemForm.fibre}" /></td><td style="color:red"><c:if test="${foodItemForm.fibreNotSupplied}">Not Supplied</c:if></td><tr>
			<tr><td>Sodium</td><td><input name="sodium" type="number" step="0.01" value="${foodItemForm.sodium}" /></td><td style="color:red"><c:if test="${foodItemForm.sodiumNotSupplied}">Not Supplied</c:if></td><tr>
			<tr><td>Energy</td><td><input name="energy" type="number" step="0.01" value="${foodItemForm.energy}" /></td><td style="color:red"><c:if test="${foodItemForm.energyNotSupplied}">Not Supplied</c:if></td><tr>
			<tr><td></td><td align="right"><input type="submit"	value="Update"/></td></tr>
		</table>
		</form:form >
	</td></tr>
</table>
</body>
</html>