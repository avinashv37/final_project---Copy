
<html>
<head>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" 
	integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css" 
	integrity="sha384-fLW2N01lMqjakBkx3l/M9EahuwpSfeNvV63J5ezn3uZzapT0u7EYsXMjQV+0En5r" crossorigin="anonymous">
    
    <style>
    @media (min-width: 1200px) {
    .container{
        max-width: 900px;
    }
}
    </style>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Smart Scheduler</title>

	<script type="text/javascript">
	function valid2()
	{
	var a=document.frm.name.value;
	if(a=="")
	{
	alert ("enter a user name");
	document.frm.name.focus();
	return false;
	}
	var b=document.frm.password.value;
	if(b=="")
	{
	alert ("enter a password");
	document.frm.password.focus();
	return false;
	}
	}
	</script>



</head>

<body>
		<div class="container" >
			<form role="form" name="frm"  action ="AdminLogin" method="post" 
			onSubmit="return valid2()">
	
	        <h3> ADMIN LOGIN </h3>
	
	          
				<div class="form-group">
			        <label for="name" >Username:</label>
			        <input type="text" name="name" class="form-control" placeholder="enter a username" >
		        </div>
		        <div class="form-group">
			        <label for="password" >Password:</label>
			        <input type="password" name="password" class="form-control" placeholder="enter a username" >
		        </div>
				<div class="form-group">
					<button type="submit" class="btn btn-info" name="submit">SUBMIT</button>
				</div>
				
		</div>

</form>
</body>
</html>
