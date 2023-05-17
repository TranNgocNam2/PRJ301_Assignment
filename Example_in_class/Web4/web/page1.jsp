<%-- 
    Document   : page1
    Created on : Feb 3, 2023, 9:47:46 AM
    Author     : ADMIN
--%>

<%@page import="java.util.Date"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="tester.jsp" %>
<%@page errorPage = "Error.jsp" %>
<!-- directive -->
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <% //scriplet
            int x = Integer.parseInt("mkfsads");
            //Tạo 1 data và lưu nó vào vùng nhớ session 
            session.setAttribute("test", 100);
        %>


        <a href="page2.jsp">Open page 2</a>
        <%!//declaration
            //tạo field trong class page1_jsp,java
            String message = "jsajda";

            //Tạo method trong class
            public String getMsg() {
                return message;
            }
        %>
        <h1>
        <%
            out.print(getMsg());
        %>
        
        <%
            Date d = new Date();
            out.print(d.toString());
        %>
        
        
        
        </h1>
    </body>
</html>
