<!doctype html>
<html>
<head>
<title>Java Web Programming: Add New Person</title>
<meta name="description" content="This is a JSP example that demonstrates how to use a 
form to add a new Person to the database.">
<%@ include file="includes/styles.jsp" %>
</head>
<body>
<div class="container">
	<div class="hero-unit">
		<h1>Add Person</h1>
	</div>
	<%@ include file="includes/navigation.jsp" %>
	<div class="container">
		<form action="AddPerson" method="post">
			<label for="firstName"><strong>First Name:</strong></label>
			<input name="firstName"><br>
		
			<label for="lastName"><strong>Last Name:</strong></label>
			<input name="lastName"><br>
		
			<label for="age"><strong>Age:</strong></label>
			<input name="age"><br>
		
			<label for="favoriteColor"><strong>Favorite Color:</strong></label>
			<input name="favoriteColor"><br>
		
			<input type="submit" value="Add Person">
		</form>
	</div>
	<hr>
	<%@ include file="includes/footer.jsp" %>
</div>
<%@ include file="includes/scripts.jsp" %>
</body>
</html>