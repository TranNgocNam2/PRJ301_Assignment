<%-- 
    Document   : index.jsp
    Created on : Feb 16, 2023, 2:51:54 PM
    Author     : ADMIN
--%>

<%@page import="namtn.dao.PlantDAO"%>
<%@page import="namtn.dto.Plant"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Plant Shop</title>
        <link rel="icon" href="image/3.png"/>
        <link  rel="stylesheet" href="css/index.css" type="text/css" />
    </head>

    <body>
        <header>
            <nav>
                <ul>
                    <li><a href="index.jsp">Home</a></li>
                        <%
                            String name = (String) session.getAttribute("name");
                            if (name == null) {
                        %>
                    <li><a href="registration.jsp">Register</a></li>
                    <li><a href="login.jsp" >Login</a></li>
                        <%}%>
                    <li><a href="MainController?action=viewcart">View Cart</a></li>
                    <li><form name="action" method="post" class="formsearch" action="MainController">
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
        <section>

            <%
                ArrayList<Plant> allPlants = PlantDAO.getPlants("", "");
                String[] tmp = {"Out of stock", "Available"};
                if (allPlants != null && !allPlants.isEmpty()) {
                    for (Plant p1 : allPlants) {%>

            <table class="ListPlants">
                <tr>
                    <td>Image</td>
                    <td>Plant ID</td>
                    <td>Plant Name</td>
                    <td>Price</td>
                    <td>Status</td>
                    <td>Category</td>
                    <td>Action</td>
                </tr>
                <tr>
                    <td><img src="<%= p1.getImgpath()%>" height="100px" width="100px"/></td>
                    <td><%= p1.getPlantId()%></td>
                    <td><%= p1.getPlantName()%> </td>
                    <td><%= p1.getPrice()%></td>
                    <td><%= tmp[p1.getStatus()]%></td>
                    <td><%= p1.getCateName()%></td>
                    <td><a href="MainController?action=addtocart&pid=<%= p1.getPlantId()%>&src=indexPage">Add To Cart</a></td>
                </tr>
            </table>
            <%
                    }
                }
            %>
        </section>
        <footer>
        </footer>
    </body>
</html>
