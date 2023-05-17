<%-- 
    Document   : ViewDetail
    Created on : Feb 10, 2023, 11:40:21 AM
    Author     : ADMIN
--%>

<%@page import="dto.Car"%>
<%@page import="dao.CarDAO"%>
<%@page import="java.util.HashMap"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            HashMap<String, Integer> cart = (HashMap) session.getAttribute("cart");
            if (cart != null && cart.size() > 0) {

        %>
        <h1>Thông tin giỏ hàng của bạn</h1>
        <table>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Quantity</th>
                <th>Chon</th>
            </tr>
            <%                
                for (String id : cart.keySet()) {
                    Car c = CarDAO.getCar(id);
            %>
            <tr>
                <td><%= c.getId()%></td>
                <td><%= c.getName()%></td>
                <td><input type="number" value="<%= cart.get(id)%>" min="1" max="10"/></td>
                

                
            </tr>
            <%
                }
            %>


        </table>
        <%            }


        %>
    </body>
</html>
