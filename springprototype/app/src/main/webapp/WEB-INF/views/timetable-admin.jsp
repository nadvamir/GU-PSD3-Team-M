<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Team M</title>
	<meta charset="UTF-8"/>
	<meta http-equiv="X-UA-Compatible" content="IE=edge" /> 
	<link rel="stylesheet" type="text/css" href="style.css">
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
	<script src="calendar.js"></script>
	<!--[if lt IE 9]><script src="html5shiv.js"></script><![endif]-->
	<script>


	days = ['Mo', 'Tu', 'We', 'Th', 'Fr'];
	times = ['8:00', '9:00', '10:00', '11:00', '12:00', '13:00', '14:00', '15:00', '16:00', '17:00', '18:00'];
	durations = [1, 2, 3, 4];

	cal = new Calendar({
		cols: days,
		rows: times,
		deletable: true
	});

	sessions = [
		['Tu', '9:00', 'Test Course', 'Boyd Orr 420', 2, 'Dr. Watson'],
		['Tu', '12:00', 'Another Course', 'Boyd Orr 716', 1, 'Dr. Hyde'],
		['Th', '12:00', 'Another Course', 'Boyd Orr 716', 1, 'Dr. Hyde'],
	];

	cal.addSessions( sessions )

$(document).ready(function() {
	$('#day-timetable').append(cal.render());


	for (x in days) {
		$("#days").append("<option value=\"" + days[x] + "\">" + days[x] +"</option>");
	}

	for (x in times) {
		$("#times").append("<option value=\"" + times[x] + "\">" + times[x] +"</option>");
	}	

	for (x in durations) {
		$("#durations").append("<option value=\"" + durations[x] + "\">" + durations[x] +"</option>");
	}


	$("#session-add").click(function(event) {
		day = $("select[name=day]").val();
		time = $("select[name=time]").val();
		name = $("input[name=name]").val();
		loc = $("input[name=location]").val();
		duration = $("select[name=duration]").val();
		lecturer = $("input[name=lecturer]").val();

		session = [day, time, name, loc, duration, lecturer];

		sessions.push(session);
		
		cal.addSessions(sessions);

		$('#day-timetable').empty();
		$('#day-timetable').append(cal.render());
	})

});
	</script>
</head>
<body>
	<div id="page">
		<header><h1>Student Information System</h1></header>

		
		<div id="session-adder">
				Day: <select id="days" name="day">
				</select>
				<br>

				Time: <select id="times" name="time">
				</select>
				<br>

				Name: <input type="text" name="name"><br>

				Location: <input type="text" name="location"><br>

				Duration: <select id="durations" name="duration">
				</select>
				<br>
			  
				Lecturer: <input type="text" name="lecturer"><br>

			  <button id="session-add">Add session</button>
		</div>

		<h1>Week 10: 18/11/2013</h1>
		<div id="day-timetable"></div>
		<div id="dummy"></div>
		<footer>© Team M, 2013</footer>
	</div>
</body>
</html>
