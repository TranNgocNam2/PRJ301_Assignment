<%-- 
    Document   : search.jsp
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
                    <li><a href="registration.jsp">Register</a></li>
                    <li><a href="login.jsp" >Login</a></li>
                    <li><form action="MainController" method="post" class="formsearch">
                            <input type="text" name="txtsearch">
                            <select name="searchby">
                                <option value="byname">by name</option>
                                <option value="bycate">by category</option>
                            </select>
                            <input type="submit" name="action" value="search"/> 
                        </form></li>
                </ul>
            </nav>
        </header>
        <section>


            <%
                String flag2 = (String) request.getAttribute("nullStringError");
                if (flag2 != null) {
            %>
            <p><%= flag2%></p>
            <%
            } else {
                flag2 = "";
                ArrayList<Plant> plants = (ArrayList<Plant>) request.getAttribute("plantList");
                String[] tmp = {"Out of stock", "Available"};
                if (plants != null && !plants.isEmpty()) {
                    for (Plant p : plants) {%>
            <h2>The results</h2>
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
                    <td><img src="<%= p.getImgpath()%>" height="100px" width="100px"/></td>
                    <td><%= p.getPlantId()%></td>
                    <td><%= p.getPlantName()%> </td>
                    <td><%= p.getPrice()%></td>
                    <td><%= tmp[p.getStatus()]%></td>
                    <td><%= p.getCateName()%></td>
                    <td><a href="MainController?action=addtocart&pid=<%= p.getPlantId()%>&src=searchPage">Add To Cart</a></td>
                </tr>
            </table>
            <%
                }
            %>
            <%
            } else {
            %>
            <p>Không có sản phẩm</p>
            <%
                    }
                }
            %>

        </section>

    </body>
</html>
