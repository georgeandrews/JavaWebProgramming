<!doctype html>
<html>
<head>
<title>Error Page</title>
</head>
<body>
	<h1>Error!</h1>
	<p>Houston, we've had a problem.</p>
	<p>To continue, click the Back button.</p>
	
	<h2>Error Details</h2>
	<p>Type: ${pageContext.exception["class""]}</p>
	<p>Message: ${pageContext.exception.message}</p>
</body>
</html>