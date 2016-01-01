<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<%@ page import="java.sql.*" %>
<%@ page import="javax.sql.*" %>
<%@ page import="javax.mail.*" %>
<%@ page import="gmaillibrary.GmailLibrary" %>
<%@ page import="java.util.Random" %>
<%

String password = request.getParameter("pass1");

Class.forName("com.mysql.jdbc.Driver");
java.sql.Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3316/todo","root","lkbhargav123KING#@!$%");
Statement st = conn.createStatement();

HttpSession sess = request.getSession();
String ans = (String) sess.getAttribute("eID");

st.executeUpdate("update registration set pwd = '"+password+"' where eid = '"+ans+"';");

out.println("Password Chnaged Successfully");
%>
</body>
</html>