<!doctype html>
<html>
<head>
<title>Java Web Programming: Register User</title>
<meta name="description" content="This is a JSP example that demonstrates how to use a 
form to register a new user for the application.">
<%@ include file="includes/styles.jsp" %>
</head>
<body>
<div class="container">
	<div class="hero-unit">
		<h1>Register User</h1>
	</div>
	<%@ include file="includes/navigation.jsp" %>
	<div class="container">
		<form action="RegisterUser" method="post">
		
			<p>Please register to access our website.</p>
		
			<input type="hidden" name="action" value="registerUser">
			
			<label for="firstName"><strong>First Name:</strong></label>
			<input name="firstName"><br>
		
			<label for="lastName"><strong>Last Name:</strong></label>
			<input name="lastName"><br>
		
			<label for="email"><strong>Email:</strong></label>
			<input name="email"><br>
		
			<input type="submit" value="Register!">
		</form>
	</div>
	<%@ include file="includes/footer.jsp" %>
</div>
<%@ include file="includes/scripts.jsp" %>
</body>
</html>