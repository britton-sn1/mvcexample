<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script type="text/javascript" >
function enableOtherButtons() {
	document.getElementById('Delete').disabled = false;
	document.getElementById('Update').disabled = false;
}

function disableOtherButtons() {
	document.getElementById('Delete').disabled = true;
	document.getElementById('Update').disabled = true;
}

function deleteFoodItem() {
	var selectedItem = $('input[name="fi"]:checked').val();
	var loc = '/mcvpract/dlmvc/deleteFoodItem'+'?selectedItem='+selectedItem;
	window.location=loc;
}

function updateFoodItem() {
	var selectedItem = $('input[name="fi"]:checked').val();
	var loc = '/mcvpract/dlmvc/showUpdateFoodItemForm'+'?selectedItem='+selectedItem;
	window.location=loc;
}

function addFoodItem() {
	var loc = '/mcvpract/dlmvc/showAddFoodItemForm';
	window.location=loc;
}

</script>
<html>
<head>
</head>
<body onload="disableOtherButtons()">

	<table>
	<tr><td>
	<h1>Food Items</h1>
 	</td></tr>
 	<tr><td>
 	<table border="2">
 	<tr>
 	<th></th>
 	<th>Name</th><th>Units</th><th>Carbs</th>
 	<th>Sugars</th><th>Fats</th><th>Saturates</th>
 	<th>Protein</th><th>Fibre</th><th>Sodium</th>
 	<th>Energy</th>
 	</tr>
	<c:forEach items="${foodItems}" var="foodItem">
	<tr>
		<td border="none"><input type="radio" name="fi" value="${foodItem.name}" onchange="enableOtherButtons();"></td><td>${foodItem.name}</td><td>${foodItem.units}</td><td>${foodItem.carbs}</td>
		<td>${foodItem.sugars}</td><td>${foodItem.fats}</td><td>${foodItem.saturates}</td>
		<td>${foodItem.protein}</td><td>${foodItem.fibre}</td><td>${foodItem.sodium}</td>
		<td>${foodItem.energy}</td>
	</tr>
	</c:forEach>
 	</table>
 	</td>
 	</tr>
 	<tr>
 	<td align="left" ><button id="Delete" onclick="deleteFoodItem();">Delete</button></td>
 	<td align="right" ><button id="Update" onclick="updateFoodItem();">Update</button></td>
 	<td align="right" ><button id="Add" onclick="addFoodItem();">Add</button></td>
 	</tr>
 	</table>
 
</body>
</html>