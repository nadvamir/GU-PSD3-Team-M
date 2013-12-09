<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Team M</title>
	<meta charset="UTF-8"/>
	<meta http-equiv="X-UA-Compatible" content="IE=edge" /> 
	<link rel="stylesheet" type="text/css" href="../resources/style.css">
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
	<script src="../resources/calendar.js"></script>
	<!--[if lt IE 9]><script src="html5shiv.js"></script><![endif]-->
	<script>
		cal = new Calendar({
		  cols: ['Today'],
		  rows: ['8:00', '9:00', '10:00', '11:00', '12:00', '13:00', '14:00', '15:00', '16:00', '17:00', '18:00']
		});
		
		sessions = [
			['Today', '9:00', 'Test Course', 'Boyd Orr 420', 2, 'Dr. Watson'],
			['Today', '12:00', 'Another Course', 'Boyd Orr 716', 1, 'Dr. Hyde'],
		];
		cal.addSessions( sessions )
		
		$(document).ready(function() {
		  return $('#day-timetable').append(cal.render());
		});
	</script>
</head>
<body>
	<div id="page">
		<header>
			<a href="http://localhost:8080/sprint2/">Logout</a>
			<br />
			<h1>Student Information System</h1>
		</header>
		<div id="timetable-links">
			<a href="http://localhost:8080/sprint2/timetable-week/">Weekly timetable</a>
			<br />
			<a href="http://localhost:8080/sprint2/timetable/">All courses</a>
		</div>
		<h1>Today you have</h1>
		<div id="day-timetable"></div>
		<div id="dummy"></div>
		<footer>© Team M, 2013</footer>
	</div>
</body>
</html>