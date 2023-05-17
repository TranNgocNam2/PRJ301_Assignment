<%-- 
    Document   : ViewHistory.jsp
    Created on : Feb 14, 2023, 10:57:46 AM
    Author     : ADMIN
--%>

<%@page import="dao.OrderDao"%>
<%@page import="dto.User"%>
<%@page import="dto.Order"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            User us = (User) session.getAttribute("loginUser");
            if (us == null) {
                response.sendRedirect("login.jsp");
            } else {
                ArrayList<Order> list = OrderDao.getOrders(us.getId());
                request.setAttribute("listorders", list);
                request.getRequestDispatcher("ViewHistory.jsp").forward(request, response);
            }
        %>
        <h1>Thong tin lich su mua hang</h1>
        <h2>Welcome <%= us.getName()%></h2>
        <h2><a href="MainController?action= logout"></a></h2>
        <%
            ArrayList<Order> list = (ArrayList) request.getAttribute("listorders");
            if (list != null && list.size() > 0) {
                //Hien thi order co status = 1

        %>
        <table>
            <%                for (Order o : list) {
                    if (o.getStatus() == 1) {
            %>
            <tr>
                <td><%= o.getOrderid()%></td>
                <td><%= o.getCreateDate()%></td>
                <td>pending</td>
                <td><a href="MainController?action=vieworderdetail&orderid"></a></td>


            </tr>


        </table>
        <%
                    }
                }
            }
        %>
        <%
            if (list != null && list.size() > 0) {
                //Hien thi order co status = 1
                for (Order o : list) {
                    if (o.getStatus() == 1) {

                    }
                }
            }
        %>


        <%
            if (list != null && list.size() > 0) {
                //Hien thi order co status = 1
                for (Order o : list) {
                    if (o.getStatus() == 1) {

                    }
                }
            }
        %>
    </body>
</html>
