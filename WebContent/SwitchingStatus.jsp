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
	
	<form role="form" name="frm" ng-controller="main" action ="EmployeeWorkScheduleSwitch" method="post" >
		
		<div class="col-xs-12" >
		<div class="btn-group" id="move" role="group"> 
			<button type="submit"    class="btn btn-info" value="waiting"  name="click">WAITING REQUESTS</button>	
			<button type="submit" class="btn btn-info"  value="accepted" name="click">ACCEPTED SCHEDULE</button>
			<span id="chk_span">
			<button type="submit"  class="btn btn-info" value="rejected"  name="click">REJECTED SCHEDULE</button>
		</div>
		</div>
	
	</form>
	</div>
		<div  style="height:50px;"></div>
		{{response}}
		<div class= "container table-responsive" id="Admin_home">
		<table  class="table table-striped responsive">
            <!-- column headers -->
            <thead>
				<tr>
					<th>Switch Date</th>
					<th>Switch Employer</th>
					<th>Slot Date</th>
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
                        <td><c:if test="${not empty row.switchdate}"><c:out value="${row.switchdate}"/></c:if></td>
                        <td><c:if test="${not empty row.FirstName}"><c:out value="${row.FirstName}"/></c:if></td>
                        <td><c:if test="${not empty row.date}"><c:out value="${row.date}"/></c:if></td>
                        <td><c:if test="${not empty row.Start_time}"><c:out value="${row.Start_time}"/></c:if></td>
                        <td><c:if test="${not empty row.end_time}"><c:out value="${row.end_time}"/></c:if></td>
                        <td>
                       
                        		<c:out value="${row.switch_ID}"/>
                        		<c:choose>
	                        		
									<c:when test="${empty row.status}" >
									<c:out value="${row.switche_ID}"/>
									<div class="form-group row col-xs-6">
				                        <form role="form" class="" name="form1"  ng-controller="main" action ="EmployeeWorkScheduleSwitchEdit" method="post" >
				                        <button class="btn"   ng-controller="main" name="edit" value="${row.switch_ID}" ng-click=edit>
												<span class="glyphicon glyphicon-remove"></span>  
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
			jQuery(function($) {
				$('.clockpicker').clockpicker({
				    placement: 'bottom',
				    align: 'right',
				    donetext: 'Done'
				});
				
		// The line below "closes" the document ready function
		});
		
			var app = angular.module('change', []);

			app.controller('main', function($scope,$http) {
			    $scope.display = "DISPLAY SCHEDULE";
			    $scope.open = "OPEN SCHEDULE";
			    $scope.add = "ADD";
			    $scope.drop = "DROP";
			   
			    $scope.submit = function () {
		            $scope.text = 'ADD';
		    };
		    
		      $scope.submit1 = function () {
		            $scope.text = 'DROP';
		    };
			
			 
			});
			
			
		
			</script>
	<script src="clockpicker-gh-pages/assets/js/bootstrap.min.js"></script>
	<script src="clockpicker-gh-pages/assets/js/highlight.min.js"></script>
	<script src="clockpicker-gh-pages/dist/jquery-clockpicker.min.js"></script>
	<script src="clockpik_add_element.js"></script>
	<script src="datepicker/js/bootstrap-datepicker.js"></script>
	<script src="datepicker/css/bootstrap-datepicker3.css"></script>

</html>