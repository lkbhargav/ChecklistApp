<%@page import="todolist.databaseConnection"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Password Changed Form</title>
</head>
<body>

<%@ page import="java.sql.*" %>
<%@ page import="javax.sql.*" %>
<%@ page import="javax.mail.*" %>
<%@ page import="gmaillibrary.GmailLibrary" %>
<%@ page import="java.util.Random" %>
<%

String password = request.getParameter("pass1");

//Class.forName("com.mysql.jdbc.Driver");
databaseConnection dC = new databaseConnection();
HttpSession sess = request.getSession();
String ans = (String) sess.getAttribute("eID");
String query = "update registration set pwd = '"+password+"' where eid = '"+ans+"';";
dC.updateQuery(query);
//java.sql.Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3316/todo","root","lkbhargav123KING#@!$%");
//Statement st = conn.createStatement();

out.println("Password Changed Successfully");
out.println("<a href='index.html'>Home</a>");
%>
</body>
</html>