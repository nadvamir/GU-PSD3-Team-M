<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Team M</title>
	<meta charset="UTF-8"/>
	<meta http-equiv="X-UA-Compatible" content="IE=edge" /> 
	<link rel="stylesheet" type="text/css" href="../resources/style.css">
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
</head>
<body>
	<div id="page">
		<header><h1>Student Information System</h1></header>
		<div id="timetable-links">
			<a href="http://localhost:8080/sprint2/timetable-day/">Daily timetable</a>
			<br />
			<a href="http://localhost:8080/sprint2/timetable-week/">Weekly timetale</a>
		</div>
		<h1>You are enrolled in the following courses:</h1>
		<p>Test Course</p>
		<p>Another Course</p>
		<div id="all-timetable"></div>
		<div id="dummy"></div>
		<footer>© Team M, 2013</footer>
	</div>
</body>
</html>