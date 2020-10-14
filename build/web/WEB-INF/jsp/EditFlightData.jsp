<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta name="description" content="" />
<meta name="keywords" content="" />
<title>Air Tickets Booking System</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<link href="${pageContext.request.contextPath}/resources/style.css" rel="stylesheet"><style>  
.error{color:red}  
</style>  
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
			<li class="first current_page_item"><a href="AddFlight">Add Flight Details</a></li>
			<li><a href="EditFlight">Edit Flight Details</a></li>
			<li><a href="DeleteFlight">Delete Flight Details</a></li>
			<li><a href="Logout">Logout</a></li>
		</ul>			<br class="clearfix" />
				</div>
			
	<div id="splash">
		<img class="pic" src="${pageContext.request.contextPath}/resources/images/investor.jpg" width="870" height="230" alt="" />
	</div>	
			<center>
<form:form method="post" action="EditFlightAction" modelAttribute="EditFlight">
<br/>
   <h2><b>Edit Flight Details Screen</b></h2><br/>

   <c:if test="${not empty msg}">
   <center><font face=verdana color=red>${msg}</center></font>
</c:if>
   
							
						<table align="center" width="40" >
			<tr><td><b>Flight&nbsp;ID</b></td>
			 <td><form:input path="id" value="${id}"/></td>
			 <td><form:errors path="id" cssClass="error"/></td>
			 </tr>


			 <tr><td><b>To</b></td>
			 <td><form:input path="tolocation" value="${to}"/></td>
			 <td><form:errors path="tolocation" cssClass="error"/></td>
			 </tr>
         
		  <tr><td><b>From</b></td>
		  <td><form:input path="fromlocation" value="${from}"/></td>
		  <td><form:errors path="fromlocation" cssClass="error"/></td>
		  </tr>

		  <tr><td><b>Number&nbsp;Of&nbsp;Seats</b></td>
		  <td><form:input path="totalseats" value="${seats}"/></td>
		  <td><form:errors path="totalseats" cssClass="error"/></td>
		  </tr>

		  <tr><td><b>Airline&nbsp;Name</b></td>
		  <td><form:input path="airlinename" value="${airline}"/></td>
		  <td><form:errors path="airlinename" cssClass="error"/></td>
		  </tr>

		  <tr><td><b>Price</b></td>
		  <td><form:input path="price" value="${price}"/></td>
		  <td><form:errors path="price" cssClass="error"/></td>
		  </tr>
         
			<tr><td></td><td><input type="submit" value="Edit Flight">
			</td>
			</table>
			</form:form>
				</div>	
					
				</div>
				
	</body>
</html>