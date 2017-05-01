<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
<head>
<title>Java Web Programming: Index</title>
<meta name="description" content="This is a servlet example that demonstrates 
	how to use IO to output the contents of an Excel spreadsheet to a web page.">
<%@ include file="includes/styles.jsp" %>
</head>
<body>
<div class="container">
	<div class="hero-unit">
		<h1>Welcome to Java Web Programming</h1>
	</div>
	<%@ include file="includes/navigation.jsp" %>
	<div class="container">
<c:if test="${user != null && not empty user.firstName}">
		<p>Hello ${user.firstName}!</p>
</c:if>
		<p>Thank you for making the commitment to be in class everyday as we cover 
		the competencies you will need to demonstrate in order to be a Java Web Programmer 
		in our industry.</p>
	</div>
	<%@ include file="includes/footer.jsp" %>
</div>
<%@ include file="includes/scripts.jsp" %>
</body>
</html>