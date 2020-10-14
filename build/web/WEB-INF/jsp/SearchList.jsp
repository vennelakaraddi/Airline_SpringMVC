<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta name="description" content="" />
<meta name="keywords" content="" />
<title>Air Tickets Booking System</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<link href="${pageContext.request.contextPath}/resources/style.css" rel="stylesheet">
</head>
<body>
<div id="wrapper">
	<div id="header">
		<div id="logo">
			<h3><font size="" color="white">Air Tickets Booking System</font></h3>
		</div>
		<div id="slogan">
			
		</div>
	</div>
	<div id="menu">
	<ul>
			<li class="first current_page_item"><a href="BookFlight">Book Flight</a></li>
			<li><a href="Logout">Logout</a></li>
		</ul>
		
		<br class="clearfix" />
	</div>
	<div id="splash">
		<img class="pic" src="${pageContext.request.contextPath}/resources/images/investor.jpg" width="870" height="230" alt="" />
	</div>
	<br/>
	<table align="center" border="2" >
	<tr><th>To Location</th><th>From Location</th><th>Airline Name</th>
	<th>Flight Date</th><th>Confirm Booking</th>
	</tr>
    <c:forEach var="bfb" items="${list}"> 
    <tr>
    <td><font size="" color="black">${bfb.tolocation}</td>
    <td><font size="" color="black">${bfb.fromlocation}</td>
    <td><font size="" color="black">${bfb.airlinename}</td>
	<td><font size="" color="black">${bfb.flightdate}</td>
	<td><a href="BookingConfirm?t1=${bfb.tolocation}&t2=${bfb.fromlocation}&t3=${bfb.totaltickets}&t4=${bfb.flightdate}&t5=${bfb.airlinename}" target="_blank"><font size="" color="black">Click Here</font></a></td>
    </tr>
    </c:forEach>
    </table>
	<br/><br/><br/><br/><br/><br/><br/>
</body>
</html>