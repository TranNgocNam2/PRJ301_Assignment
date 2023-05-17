<%-- 
    Document   : header
    Created on : Feb 26, 2023, 7:34:49 PM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="icon" href="image/3.png"/>
        <link  rel="stylesheet" href="css/index.css" type="text/css" />
    </head>
    <body>
        <header>
            <nav>
                <ul>
                    <li style="padding: 20px; height: 100px; width: 100px"><a href="personalPage.jsp">Home</a></li>
                    <li style="padding: 20px; height: 100px; width: 200px"><a href="changeProfile.jsp">Change profile</a></li>
                    <li style="padding: 20px; height: 100px; width: 400px">
                        <form action="MainController" name="action" method="post">
                            from<input type="date" name="fromDate" required/>to<input type="date" name="toDate" required/>
                            <input type="submit" name="action" value="sortbydate"/> 
                        </form>
                    </li>
                    <li style="padding: 20px; height: 100px; width: 100px"><a href="MainController?action=logout">Logout</a></li>
                    <li style="padding: 20px; height: 100px; width: 100px"><a href="viewCart.jsp">View</a></li>
                        <li style="padding: 20px"><form name="action" method="post" class="formsearch" action="MainController">
                                <input type="text" name="txtsearch">
                                <select name="searchby">
                                    <option value="byname">by name</option>
                                    <option value="bycate">by category</option>
                                </select>
                                <input type="submit" value="search" name="action" >
                            </form></li>
                </ul>
            </nav>
        </header>
    </body>
</html>
