<!doctype html>
<html>
<head>
<title>Java Web Programming: Success</title>
<meta name="description" content="This is a JSP example that demonstrates a Success response page for successful actions perform in our web application.">
<%@ include file="includes/styles.jsp" %>
</head>
<body>
<div class="container">
	<div class="hero-unit">
		<h1>Success</h1>
	</div>
	<%@ include file="includes/navigation.jsp" %>
	<div class="container">
		<p>${message}</p>
	</div>
	<hr>
	<%@ include file="includes/footer.jsp" %>
</div>
<%@ include file="includes/scripts.jsp" %>
</body>
</html>