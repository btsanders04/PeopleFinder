<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<title>SearchPeople</title>
</head>
<body>
	<div class="container">
		<h2>Search Customers and Companies</h2>
		<form role="form" action="PeopleFinder" method="POST">
			<div class="form-group" >
				<label for="name">Name:</label> <input type="name" name="name"
					class="form-control" id="name" placeholder="Enter name">
			</div>
			<button type="submit" class="btn btn-default" name="submit" >Submit</button>
		</form>
	</div>
${details}
${companies}
</body>
</html>