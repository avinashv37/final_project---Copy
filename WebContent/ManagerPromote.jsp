<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ page import="com.smartScheduler.connection.Database" %>
<%@ page import="com.smartScheduler.controller.admin" %>
<%@ page import="javax.servlet.jsp.jstl.sql.Result"
import = "javax.servlet.jsp.jstl.sql.ResultSupport" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" 
	integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css" 
	integrity="sha384-fLW2N01lMqjakBkx3l/M9EahuwpSfeNvV63J5ezn3uZzapT0u7EYsXMjQV+0En5r" crossorigin="anonymous">
	
	<!-- Ajax and Jquery -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>

	<!-- Latest compiled and minified JavaScript -->
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" integrity
	="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS" crossorigin="anonymous"></script>



<title>Admin page</title>
</head>
	<script>
		$(".nav a").on("click", function(){
		   $(".nav").find(".active").removeClass("active");
		   $(this).parent().addClass("active");
		});
	</script>
<body data-spy="scroll" data-target=".navbar-collapse">
	
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
					<li><a href="/final_project/admin_main.jsp">Admin Home</a></li>
					<li><a href="/final_project/ManagerPromote.jsp">Employer to Manager</a></li>
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
		
	<form role="form" name="frm" class="col-xs-12" action ="admin_search" method="post" >
		<div class="form-group">
		
		  <label for="name" >EmployerID</label>
		  <input type="text" name="employee" class="form-control" placeholder="enter a username" >
		</div>
		<div class="form-group">
		
		  <label for="name" >ManagerID</label>
		  <input type="text" name="manage" class="form-control" placeholder="enter a username" >
		</div>
		<div class="form-group row col-xs-12">
				<button type="submit" class="btn btn-info" value="setmanager" name="click">SET MANAGER</button>
				<button type="submit" class="btn btn-info" value="displaymanager" name="click">DISPLAY MANAGER</button>
				<button type="submit" class="btn btn-info" value="Employer" name="click">DISPLAY EMPLOYERS</button>
							</div>
	</form>
	<div class= "container table-responsive" id="Admin_home">
		<table  class="table table-striped responsive">
            <!-- column headers -->
            <thead>
				<tr>
					<th>Employement ID</th>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Phone Number</th>
				</tr>
			</thead>
			
            <!-- column data  -->
            <tbody>
            <% Result result = (Result)request.getAttribute("result");
          %>
            <c:forEach var="row" items="${result.rows}">
                <tr>
                		<td><c:out value="${row.employee_id}"/></td>
                        <td><c:out value="${row.firstname}"/></td>
                        <td><c:out value="${row.lastname}"/></td>
                        <td><c:out value="${row.phonenumber}"/></td>
                        
                </tr>
            </c:forEach>
            </tbody>
        </table>
	</div>
	
</body>
</html>