<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
<head>
<title>Java Web Programming: Class List</title>
<meta name="description" content="This is a JSP example that demonstrates how to output 
	every Person in our Excel spreadsheet to a web page.">
<%@ include file="includes/styles.jsp" %>
</head>
<body>
<div class="container">
	<div class="hero-unit">
		<h1>Class List</h1>
	</div>
	<%@ include file="includes/navigation.jsp" %>
	<div class="container">
		<c:choose>
			<c:when test="${empty people}">
				<p>Sorry, the list of people is empty.</p>
			</c:when>
			<c:otherwise>
				<c:forEach var="person" items="${people}">
					<div class="span4">
						<h2>${person.firstName}</h2>
						<p>${person.firstName} ${person.lastName} is ${person.age} years old. 
						${person.firstName}'s favorite color is ${person.favoriteColor}.</p>
					</div>
				</c:forEach>
			</c:otherwise>
		</c:choose>
	</div>
	<%@ include file="includes/footer.jsp" %>
</div>
<%@ include file="includes/scripts.jsp" %>
</body>
</html>