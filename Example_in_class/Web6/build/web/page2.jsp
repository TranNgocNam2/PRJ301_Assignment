<%-- 
    Document   : page 2
    Created on : Feb 17, 2023, 10:08:43 AM
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
            
        </jsp:useBean>
        <jsp:getProperty property="id" name="t"/>
        <jsp:getProperty property="name" name="t"/>
        <jsp:getProperty property="price" name="t"/>
        <jsp:forward page="page3.jsp">
            <jsp:param name="msg" value="anh yeu em"></jsp:param>
        </jsp:forward>
        <jsp:include page="footer.jsp"/>
    </body>
</html>
