<%-- 
    Document   : page1
    Created on : Feb 17, 2023, 9:51:39 AM
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
        <jsp:useBean id="t" class="dto.Item" scope="session">
            <jsp:setProperty property="id" value="2" name="t"></jsp:setProperty>
            <jsp:setProperty property="name" value="chan" name="t"/>
            <jsp:setProperty property="price" value="1000" name="t"></jsp:setProperty>
             
        </jsp:useBean>
        <a href="page2.jsp">read item </a>
        <h1>$("em yeu co")</h1>
        <h1>$(10+20)</h1>
        <h1>$(10>5)</h1>
        <h1>test xuat null: $(null)</h1>
        <h1>$(sessionScope.t.name ) </h1>
        
    </body>
</html>
