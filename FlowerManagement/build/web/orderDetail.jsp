<%-- 
    Document   : orderDetail
    Created on : Feb 22, 2023, 12:46:15 PM
    Author     : ADMIN
--%>

<%@page import="namtn.dao.OrderDAO"%>
<%@page import="namtn.dto.OrderDetail"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Order Detail</title>
    </head>
    <body>
        <%
            String name = (String) session.getAttribute("name");
            if (name == null) {
        %>
        <p>You must login to view this page!</p>
        <p><a href="login.jsp">Please login!</a></p>
        <%
        } else {%>
        <h1>Welcome <%=name%> </h1>
        <%
            }
        %>
        <a href="personalPage.jsp">View all orders</a>
        <section>
            <%
                String orderid = request.getParameter("orderid");
                if (orderid != null) {
                    int orderID = Integer.parseInt(orderid.trim());
                    ArrayList<OrderDetail> orderDetails = OrderDAO.getOrderDetail(orderID);
                    if (orderDetails != null && !orderDetails.isEmpty()) {
                        int totalMoney = 0;
                        for (OrderDetail ord : orderDetails) {
            %>
            <table class="order">
                <tr>
                    <td>Order ID</td>
                    <td>Plant ID</td>
                    <td>Plant Name</td>
                    <td>Image</td>
                    <td>Price</td>
                    <td>Quantity</td>
                </tr>
                <tr>
                    <td><%= ord.getOrderID()%></td>
                    <td><%= ord.getPlantID()%></td>
                    <td><%= ord.getPlantName()%></td>
                    <td><img src ="<%= ord.getImgPath()%>" class="plantImage" width="100px" height="100px"/></td>
                    <td><%= ord.getPrice()%></td>
                    <td><%= ord.getQuantity()%></td>
                    <% totalMoney = ord.getPrice() * ord.getQuantity(); %>
                </tr>
            </table>
            <%
                }%>
            <h3>Total Money: <%=totalMoney%></h3>
            <%
                    }
                }

            %>

        </section>



    </body>
</html>
