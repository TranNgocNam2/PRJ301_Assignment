<%-- 
    Document   : login
    Created on : Feb 11, 2023, 11:35:36 AM
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
          <form action="LoginServlet" method="post">
            <input type="text" name="txtusername"/>
            <input type="password" name="txtpassword"/>
            <input type="submit" value="login"/>
        </form>
    </body>
</html>
