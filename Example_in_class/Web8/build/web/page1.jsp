<%-- 
    Document   : page1
    Created on : Feb 21, 2023, 10:08:48 AM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Information</h1>
        <form action="Servlet1">
        <form>
            <p><input type="text" name="txtemail"/></p>
            <p><input type="text" name="txtpassword"/></p>
            <p><input type="text" name="txtfullname"/></p>
            <p><input type="submit" name="register"/></p>
        </form>
        <h3>${requestScope.Error}</h3>
        <a href="Servlet2">Click me to chill</a>
    </body>
</html>
