<%-- 
    Document   : adminhome
    Created on : Jan 31, 2023, 10:34:38 AM
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
        User us = (User)session.getAttribute("loginedUser");
        if(us==null){
            //do client mở trang này trực tiếp từ trình duyệt
            response.sendRedirect("index.html");
        }
        else{
            out.print("<h1>Welcome " + us.getFullname() + "comeback </h1>");
            out.print("<h2>Other content");
        }
        %>
    </body>
</html>
