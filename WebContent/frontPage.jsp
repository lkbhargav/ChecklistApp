<!-- Sai Baba -->
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<title>Tasks Page</title>
</head>

<style>
	td {
		padding-top: 0px;
		padding-bottom: 5px;
		padding-left: 5px;
		padding-right: 10px;
	}
	
	span {
		font-weight: bold;
	}
	
	tr:hover {background-color: lightyellow; color:black}
</style>

<body>
<div class="container">
 <script type="text/javascript">
function check123(s)
{
	var vll = document.getElementById(s+'2');
	if(vll.checked)
	{
	document.getElementById(s).style.color = "lightgray";
	document.getElementById(s).style.textDecoration = "line-through";
	}
	else
	{
	document.getElementById(s).style.color = "black";
	document.getElementById(s).style.textDecoration = "none";
	}
}
</script>
<div class="row row-content">
<div class="col-xs-12">
<p> ${message } </p>

<form name="task_input" method="post" action="proCreTas"> Title: <input type="text" name="title"  placeholder="ex: Laundry" required> Description: <input type="text" name="description" placeholder="ex: Bring clothes back home" required> <input type="submit" value="Create Task" ></form>  
<form action="saveChecklist" method="post" id='VieDel'> <table class="table table-">  ${checkboxes } </table> <input class="btn btn-success" type="submit" value="Save"> </form> 
<form method="post" action="backCategoryList"> ${back }</form>
<a href='index.jsp'> Home</a>
<footer> <form method="post" action="logout" role="form"> <input class="btn btn-warning" type="submit" value="Logout"> </form> </footer>
<p id="error"></p>
<form action="descriptionValue" method="post" id='View1' role="form"></form>
<form action="deleteValue" method="post" id='Delete1' role="form"></form>
</div>
</div>
</div>
</body>
</html>