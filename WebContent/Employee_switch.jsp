<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ page import="com.smartScheduler.connection.Database" %>
<%@ page import="com.smartScheduler.controller.admin" %>
<%@ page import="javax.servlet.jsp.jstl.sql.Result"
import = "javax.servlet.jsp.jstl.sql.ResultSupport" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html manifest="demo_html.appcache" ng-app="change">
<head>
		<!-- Clockpicker and minified CSS -->
	<link rel="stylesheet" href="clockpicker-gh-pages/dist/bootstrap-clockpicker.min.css">
	
	<link rel="stylesheet" href="style/style.css">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" 
	integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css" 
	integrity="sha384-fLW2N01lMqjakBkx3l/M9EahuwpSfeNvV63J5ezn3uZzapT0u7EYsXMjQV+0En5r" crossorigin="anonymous">
	
	<!-- Ajax and Jquery -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>

	<!-- Latest compiled and minified JavaScript -->
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" integrity
	="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS" crossorigin="anonymous"></script>
	
	<script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script>	


<title>Work Schedule</title>
</head>
	<script>
		$(".nav a").on("click", function(){
		   $(".nav").find(".active").removeClass("active");
		   $(this).parent().addClass("active");
		});
		
	</script>
<body data-spy="scroll" data-target=".navbar-collapse" >
	
		 <nav class="navbar navbar-default navbar-fixed-top" role="navigation">
	     <div class="container-fluid" id="navfluid">
	       <div class="navbar-header">
		       <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#navigationbar" >
			       <span class="sr-only">Toggle navigation</span>
			       <span class="icon-bar"></span>
			       <span class="icon-bar"></span>
			       <span class="icon-bar"></span>
			       <span class="icon-bar"></span>
		      </button>
	     
	    	</div>
			<div class="collapse navbar-collapse" id="navigationbar">
				<ul class="nav navbar-nav">
					<li><a href="/final_project/employer.jsp">Employer Home</a></li>
					<li><a href="/final_project/SwitchingStatus.jsp">Switching Status</a></li>
					<li><a href="/final_project/Employee_switch.jsp">Employee Switch</a></li>
					<li><a href="/final_project/EmployerWorkSchedule.jsp">Schedule Add</a></li>
				</ul>
				
				<ul class="nav navbar-nav navbar-right"> 
					<li>
		               <form name="submitForm" method="POST" action="Signout">
		    			<input type="hidden" name="param1" value="param1Value"><a href="login.jsp" class="navbar-brand"><span class="glyphicon glyphicon-log-out">
		    			</span> Log out</a></form>
            		</li>
				</ul>
			</div>
		</div>
	</nav>

	
	<div  style="height:70px;"></div>
	
	<form role="form" name="frm" ng-controller="main" action ="Employee_Switch_accept_request" method="post" >
		
		<div class="col-xs-12" >
		<div class="btn-group" id="move" role="group"> 	
			<button type="submit"  ng-click="submit1()"  class="btn btn-info" value="request_switch"  name="click">REQUEST SWITCH</button>
			<button type="submit" class="btn btn-info" ng-click="submit()" id="display" value="accept_switch" name="click">ACCEPTED SWITCH</button>
		</div>
		</div>
	
	</form>
	</div>
		<div  style="height:50px;"></div>
		<div class= "container table-responsive" id="Admin_home">
		<table  class="table table-striped responsive">
            <!-- column headers -->
            <thead>
				<tr>
					<th>Date</th>
					<th>Requesting Employer</th>
					<th>Start time</th>
					<th>End time<th>
					
				</tr>
			</thead>
			
            <!-- column data  -->
            <tbody>
            <% Result result = (Result)request.getAttribute("result");
          %>
            <c:forEach var="row" items="${result.rows}">
                <tr>
                        <td><c:if test="${not empty row.Date}"><c:out value="${row.Date}"/></c:if></td>
                        <td><c:if test="${not empty row.FirstName}"><c:out value="${row.FirstName}"/></c:if></td>
                        <td><c:if test="${not empty row.Start_time}"><c:out value="${row.Start_time}"/></c:if></td>
                        <td><c:if test="${not empty row.end_time}"><c:out value="${row.end_time}"/></c:if></td>
                        <td>
                       
                        		<c:choose>
	                        		<c:when test="${ empty row.Schedule_ID}" >
	                        		
									</c:when>
									
									<c:when test="${not empty row.Schedule_ID}" >
									<div class="form-group row col-xs-6">
									<c:out value="${row.Schedule_ID}"/>
				                        <form role="form" class="" name="form1"  ng-controller="main" action ="Employee_Switch_add" method="post" >
				                        <button class="btn"   ng-controller="main" name="edit" value="${row.Schedule_ID}" ng-click=edit>
												<span class="glyphicon glyphicon-plus"></span>  
										</button>	
										</form>
									</div>
									</c:when>
								</c:choose>
                        </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        
	</div>
	</body>
	<script type="text/javascript">
			
		
			
		
			</script>
	

</html>