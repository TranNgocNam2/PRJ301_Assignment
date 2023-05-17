<%-- 
    Document   : welcome
    Created on : Feb 3, 2023, 10:53:43 AM
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
        <h3>Welcome 
            <%
                String us = (String)session.getAttribute("loginUser");
                if(us == null){
                    response.sendRedirect("index.jsp");
                }
                else{
                    out.print(us);
                }
            %>
        </h3>
    </body>
</html>
