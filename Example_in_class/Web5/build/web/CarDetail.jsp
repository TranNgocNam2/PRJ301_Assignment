<%-- 
    Document   : CarDetail
    Created on : Feb 10, 2023, 11:03:46 AM
    Author     : user
--%>

<%@page import="dto.Car"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
           Car c=(Car)request.getAttribute("cardetail");
           if(c!=null){
        %>
        <div>
            <p><%= c.getId() %></p>
            <p><%= c.getName() %></p>
            <p><%= c.getDescription() %></p>
            <p><%= c.getSpeed() %></p>
            <p><%= c.getPrice()%></p>
            <p><a href="MainController?action=addtocart&id=<%=c.getId() %>">Buy</a></p>
            
        </div>       
        <%
           }
        %>
    </body>
</html>
