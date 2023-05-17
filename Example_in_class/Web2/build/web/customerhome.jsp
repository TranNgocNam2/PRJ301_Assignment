<%-- 
    Document   : customerhome
    Created on : Jan 31, 2023, 10:49:19 AM
    Author     : ADMIN
--%>

<%@page import="DTO.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
        User kq = (User)session.getAttribute("loginedUser");
        if(kq == null){
            response.sendRedirect("index.html");
        }
        else{
            out.print("<h1>Welcome " + kq.getFullname() + " comeback</h1>");
        }
        %>
    </body>
</html>
