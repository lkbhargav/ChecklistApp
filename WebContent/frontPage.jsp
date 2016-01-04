<!-- Sai Baba -->
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Tasks Page</title>
</head>

<style>
	td {
		padding-top: 0px;
		padding-bottom: 5px;
		padding-left: 5px;
		padding-right: 10px;
	}
	
	tr:hover {background-color: black; color:white}
</style>

<body>
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
<p> ${message } </p>

<form name="task_input" method="post" action="proCreTas"> Title: <input type="text" name="title"  placeholder="ex: Laundry" required> Description: <input type="text" name="description" placeholder="ex: Bring clothes back home" required> <input type="submit" value="Create Task" ></form>  
<form action="saveChecklist" method="post" id='VieDel'> <table>  ${checkboxes } </table> <input type="submit" value="Save"> </form> 
<form method="post" action="backCategoryList"> ${back }</form>
<a href='index.jsp'> Home</a>
<footer> <form method="post" action="logout"> <input type="submit" value="Logout"> </form> </footer>
<p id="error"></p>
<form action="descriptionValue" method="post" id='View1'></form>
<form action="deleteValue" method="post" id='Delete1'></form>
</body>
</html>