<%-- 
    Document   : index
    Created on : Feb 3, 2023, 10:35:33 AM
    Author     : ADMIN
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="basicobject.Item"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="MainController" method="POST">
            <input type="text" name="t1"/>
            <input type="text" name="t2"/>
            <input type="submit" value="login" name="action"/>
        </form>

        <%
            String flag = (String) request.getAttribute("Error");
            if (flag != null) {
                out.print(flag);
            }
        %>

        <hr/>
        Enter keyword to find items:
        <form action="MainController" method="POST">
            <input type="text" name="key"/>
            <input type="submit" value="find" name="action"/>
        </form>

        <!-- chỗ code này là để hiển thị kết quả sau khi find -->
        <%
            ArrayList<Item> list = (ArrayList) request.getAttribute("listofitem");
            if (list != null && list.size() > 0) {
        %>
        <table>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Image</th>
                <th>Price</th>
                <th>Choose</th>
            </tr>
            <%
                for (Item it : list) {
            %>
            <tr>
                <td><%=it.getId()%></td>
                <td><%=it.getName()%></td>
                <td><img src="<%=it.getImage()%>" width="100px" weight="100px"/></td>
                <td><%=it.getPrice() %></td>
                <td><a href="#">Buy</a></td>
            </tr>
            <%
                }
            %>
        </table>
        <%
            }
        %>
    </body>
</html>
