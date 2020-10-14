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
			<li class="first current_page_item"><a href="index">Homepage</a></li>
			<li><a href="AdminLogin">Admin</a></li>
			<li><a href="CustomerLogin">Customer</a></li>
			<li><a href="Register">Register Here</a></li>
		</ul>			<br class="clearfix" />
				</div>
			
	<div id="splash">
		<img class="pic" src="${pageContext.request.contextPath}/resources/images/investor.jpg" width="870" height="230" alt="" />
	</div>	
			<center>
<form:form method="post" action="AdminLoginAction" modelAttribute="AdminLogin">
<br/>
   <h2><b>Admin Login Screen</b></h2>

   <c:if test="${not empty error}">
   <center><font face=verdana color=red>Error: ${error}</center></font>
</c:if>
   
							
						<table align="center" width="40" >
			 <tr><td><b>Username</b></td>
			 <td><form:input path="username"/></td>
			 <td><form:errors path="username" cssClass="error"/></td>
			 </tr>
         
		  <tr><td><b>Password</b></td>
		  <td><form:password path="password" /></td>
		  <td><form:errors path="password" cssClass="error"/></td>
		  </tr>
         
			<tr><td></td><td><input type="submit" value="Login">
			</td>
			</table>
			</form:form>
				</div>	
					
				</div>
				
	</body>
</html>