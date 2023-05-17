<%-- 
    Document   : page2
    Created on : Feb 3, 2023, 9:59:42 AM
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
        <h1>Hello World!</h1>
        <h1><%
            int kq = (int)session.getAttribute("test");
            out.print(kq);
            %></h1>
    </body>
</html>
