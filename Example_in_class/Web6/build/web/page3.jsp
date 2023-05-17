<%-- 
    Document   : page3
    Created on : Feb 17, 2023, 10:20:05 AM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1><%= request.getParameter("msg") %></h1>
        <h1>$(param.msg)</h1>
    </body>
</html>
