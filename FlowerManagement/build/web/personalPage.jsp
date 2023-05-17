<%-- 
    Document   : personalPage
    Created on : Feb 16, 2023, 2:52:33 PM
    Author     : ADMIN
--%>

<%@page import="java.sql.Date"%>
<%@page import="namtn.dto.Account"%>
<%@page import="namtn.dao.AccountDAO"%>
<%@page import="namtn.dao.OrderDAO"%>
<%@page import="namtn.dto.Order"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Personal Page</title>
    </head>
    <body>
        <%
            String name = (String) session.getAttribute("name");
            String email = (String) session.getAttribute("email");
            Cookie[] c = request.getCookies();
            boolean login = false;
            if (name != null) {
                //Nếu đã đăng nhập thì thoát khỏi hàm
                login = true;
            }
            //Nếu vào từ trang khác, không phải trang login.jsp thì sẽ kiểm tra người dùng dựa trên Cookie
            String token = "";
            for (Cookie aCookie : c) {
                if (aCookie.getName().equals("selector")) { //Lấy Cookie dựa theo tên
                    token = aCookie.getValue(); //Token lấy giá trị của cookie
                    Account acc = AccountDAO.getAccountByToken(token); //Tìm Object Account dựa trên token
                    if (acc != null) {
                        acc.getFullname();
                        acc.getEmail();
                        login = true;
                        break;
                    }
                }
            }
        %>

        <%
            if (login == false) {
        %>
        <p>You must login to view this page!</p>
        <p><a href="login.jsp">Please login!</a></p>
        <%
        } else {%>
        <h1>Welcome <%=name%> </h1>
        <h1>Welcome <%=email%> </h1>
        <header>
            <%@include file="header_Login.jsp" %>

        </header>
        <section>
            <%
                ArrayList<Order> orders = (ArrayList<Order>) request.getAttribute("orderList");
                if (orders == null) {
                    //If user come personal Page the first time
                    orders = OrderDAO.getAllOrdersByEmail(email);
                } else {
                    orders = (ArrayList<Order>) request.getAttribute("orderList");
                }
            %>
            <form action="MainController" method="post">
                <select name="sortordersby">
                    <option value="all">All</option>
                    <option value="process">In process</option>
                    <option value="completed">Completed</option>
                    <option value="canceled">Canceled</option>
                </select>
                <input type="submit" name="action" value="sort"/>
            </form>
            <%
                String[] status = {"", "processing", "completed", "canceled"};
                if (orders != null && !orders.isEmpty()) {
                    for (Order ord : orders) {%>
            <table>
                <tr>
                    <td style="padding: 10px; font-weight: bold">Order ID</td>
                    <td style="padding: 10px; font-weight: bold">Order Date</td>
                    <td style="padding: 10px; font-weight: bold">Ship Date</td>
                    <td style="padding: 10px; font-weight: bold">Order Status</td>
                    <td style="padding: 10px; font-weight: bold">More</td>
                    <td style="padding: 10px; font-weight: bold">Action</td>
                </tr>
                <tr>
                    <td style="padding: 10px; text-align: center; font-size: larger"><%= ord.getOrderID()%></td>
                    <td style="padding: 10px; text-align: center; font-size: larger"><%= ord.getOrderDate()%></td>
                    <td style="padding: 10px; text-align: center; font-size: larger"><%= ord.getShipDate()%></td>
                    <td style="padding: 10px; text-align: center; font-size: larger"><%= status[ord.getStatus()]%></td>
                    <td style="padding: 10px; text-align: center"><a href="orderDetail.jsp?orderid=<%= ord.getOrderID()%> ">Detail</a></td>
                    <td style="padding: 10px; text-align: center"><% if (ord.getStatus() == 1) {
                        %><a href="MainController?action=updateorder&orderID=<%= ord.getOrderID()%>&status=<%= ord.getStatus()%>">Cancel order</a><%} else if (ord.getStatus() == 2) {%>
                        <p style="text-align: center; font-size: larger">None</p> 
                        <%} else {
                        %><a href="MainController?action=updateorder&orderID=<%= ord.getOrderID()%>&status=<%= ord.getStatus()%>">Order again</a> <%}%></td>
                </tr>

            </table>
            <%
                }
                if (request.getAttribute("mess") != null) {
            %><p style="color: red; font-size: larger"><%out.print(request.getAttribute("mess")); %></p><%
                        }
                    }
                }
            %>
        </section>

    </body>
</html>
