<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world!  
</h1>

<P>  The time on the server is ${serverTime}. </P>
<form action="login" method="get">
  Username: <input type="text" name="username"><br>
  Password: <input type="text" name="password"><br>
  <input type="submit" value="Submit">
</form> 
</body>
</html>
