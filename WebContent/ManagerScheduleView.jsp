<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ page import="com.smartScheduler.connection.Database" %>
<%@ page import="com.smartScheduler.controller.admin" %>
<%@ page import="javax.servlet.jsp.jstl.sql.Result"
import = "javax.servlet.jsp.jstl.sql.ResultSupport" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html manifest="demo_html.appcache">
<head>
		<!-- Clockpicker and minified CSS -->
	<link rel="stylesheet" href="clockpicker-gh-pages/dist/bootstrap-clockpicker.min.css">
	
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" 
	integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css" 
	integrity="sha384-fLW2N01lMqjakBkx3l/M9EahuwpSfeNvV63J5ezn3uZzapT0u7EYsXMjQV+0En5r" crossorigin="anonymous">
	
	<!-- Ajax and Jquery -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.2/jquery.js"></script>
	<link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/themes/smoothness/jquery-ui.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/jquery-ui.js"></script>

	<!-- Latest compiled and minified JavaScript -->
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" integrity
	="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS" crossorigin="anonymous"></script>



<title>Work Schedule</title>
</head>
	<script>
		
	// the below script does not work as of now still in testing.
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
					<li><a href="/final_project/manager.jsp">Manager Home</a></li>
					<!--<li><a href="/final_project/managingtimeslot.jsp">Time Slot </a></li>  -->
					<li><a href="/final_project/ManagerWorkSchedule.jsp">Work Scheduler</a></li>
					<li><a id="view" href="/final_project/ManagerScheduleView.jsp">Schedule View</a></li>
					<li><a href="/final_project/ManagerSwitchingStatus.jsp">Switching Status</a></li>
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
	<form role="form" name="frm" class="col-xs-12" action ="Manager_Display" method="post" >
	
		<div class="form-group row col-xs-6">
			<button type="submit" class="btn btn-info" id="scheduled" value="schedule" name="click">SCHEDULED</button>
		</div>
		<div class="form-group row col-xs-6">
			<button type="submit" class="btn btn-info" value="not_schedule" name="click">NOT SCHEDULED</button>
		</div>
	</form>
		<div  style="height:70px;"></div>
		
		<div class= "container table-responsive" id="Admin_home">
		<table  class="table table-striped responsive">
            <!-- column headers -->
            
             <% String action = (String)request.getAttribute("verify");
             String schedule="schedule";
             String not_schedule="not_schedule";
             System.out.println(action);
				          %>
          		<thead>
            
								<tr>
									<th>Date</th>
									<th>Employee</th>
									<th>Start time</th>
									<th>End time<th>
								</tr>
							</thead>
							
				            <!-- column data  -->
				            
				            <% Result result = (Result)request.getAttribute("result");
				          %>
            		<tbody>
            			
				            <c:forEach var="row" items="${result.rows}">
				            	
					                <tr>
					                        <td><c:out value="${row.Date}"/></td>
					                        <td>
					                        <c:out value="${row.Schedule_ID}"/>
					            <c:choose>
            						<c:when test="${empty row.slot_id}">
            							<form action ="Manager_edit" method="POST">
					                         <c:out value="${row.FirstName}"/> 
					                        <button class="btn"  id="id" ng-controller="main" name="delete"  value="${row.Schedule_ID}" ng-click=edit>
													<span class="glyphicon glyphicon glyphicon-trash"></span>  
											</button>
											 <button class="btn"  id="id" ng-controller="main" name="edit"  value="${row.Schedule_ID}" ng-click=edit>
													<span class="glyphicon glyphicon-remove"></span>  
											</button>
										</form>
									</c:when>
									<c:when test="${not empty row.slot_id}">
							            <form action ="Manager_edit" method="POST">
					                         <strong>id</strong> <input class="input-sm"type="text" id="eid" name="eid" ></input>
					                        <button class="btn"  id="id" ng-controller="main" name="edit"  value="${row.Schedule_ID}" ng-click=edit>
													<span class="glyphicon glyphicon-ok"></span>  
											</button>
											<button class="btn"  id="id" ng-controller="main" name="delete"  value="${row.Schedule_ID}" ng-click=edit>
													<span class="glyphicon glyphicon-trash"></span>  
											</button>
											
										</form>
									</c:when>
					         	</c:choose>
					                        </td>
					                        <td><c:out value="${row.Start_time}"/></td>
					                        <td><c:out value="${row.end_time}"/></td>
					                </tr>
	 							
				            </c:forEach> 
				        
				  	 </tbody>

        </table>
        <form>
        
      <!--  <form1 action="Manager_edit" role="form" method="POST" id="popup"name="popup" class="col-xs-12" action ="Manager_edit" method="post" >
		    <label for="search">Search For:</label>
		    <input type="text" id="eid" name="eid">
		    <button type="submit">Submit</button>
        </form1> --> 
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
			
		/*	
			$(function(){
				$('button[name="edit"]').click(function(){
					$( 'form1' ).dialog({
				        open: function() {
				            // On open, hide the original submit button
				            $( this ).find( "[type=submit]" ).hide();
				        },
				        buttons: [
				            {
				                text: "Submit",
				                click: $.noop,
				                type: "submit",
				                form : "popup"// <-- Make the association
				            },
				            {
				                text: "Close",
				                click: function() {
				                    $( this ).dialog( "close" );
				                }
				            }
				        ]
				    });
				})
			})
*/
			</script>
	<script src="clockpicker-gh-pages/assets/js/bootstrap.min.js"></script>
	<script src="clockpicker-gh-pages/assets/js/highlight.min.js"></script>
	<script src="clockpicker-gh-pages/dist/jquery-clockpicker.min.js"></script>
	<script src="clockpik_add_element.js"></script>
	<script src="datepicker/js/bootstrap-datepicker.js"></script>
	<script src="datepicker/css/bootstrap-datepicker3.css"></script>

</html>