<%-- 
    Document   : admin_header
    Created on : Mar 9, 2023, 11:16:01 AM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Page</title>
        <link href="css/mycss.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <header>
            <ul>
                <li><a href="MainController?action=manageaccounts">Manage Account</a></li>
                <li><a href="MainController?action=manageorders">Manage Orders</a></li>
                <li><a href="MainController?action=manageplants">Manage Plant</a></li>
                <li><a href="MainController?action=managecategories">Manage categories</a></li>
                <li><a href="MainController?action=logout">Logout</a></li>
            </ul> 
            <h2>Welcome ${sessionScope.name}</h2>
        </header>
    </body>
</html>
