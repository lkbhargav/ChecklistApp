<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<p> Enter a checklist name </p>
	<form method="post" action="catCre" name="categoryList"> <input type="text" name="categoryName"> <input type="submit" value="Create Checklist"> </form>
	<p> Following is your different checklists </p>
	<form method="post" action="displayTasks" name="truelist"> ${category} </form>
</body>
</html>