<%-- 
    Document   : viewCart
    Created on : Mar 1, 2023, 4:00:09 PM
    Author     : ADMIN
--%>

<%@page import="namtn.dto.Plant"%>
<%@page import="namtn.dao.PlantDAO"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.HashMap"%>
<%@page import="namtn.dto.Account"%>
<%@page import="namtn.dao.AccountDAO"%>
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
        <p>You must login to view your cart!</p>
        <p><a href="login.jsp">Please login!</a></p>
        <%
        } else {%>
        <header>
            <nav>
                <ul>
                    <li><a href="personalPage.jsp">Home</a></li> 
                    <li><a href="MainController?action=logout">Log out</a></li>
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
            <h1>Welcome <%= email%> </h1>

            <font style="color: red"><%= (request.getAttribute("Warning") == null) ? "" : (String) request.getAttribute("Warning")%></font>
            <table width="100%" class="shopping">

                <%
                    HashMap<String, Integer> cart = (HashMap<String, Integer>) session.getAttribute("cart");
                    int totalMoney = 0;

                    if (cart != null) {
                %>
                <tr>
                    <td>Plant ID</td>
                    <td>Plant Name</td>
                    <td>Image</td>
                    <td>Price</td>
                    <td>Quantity</td>
                    <td>Action</td>
                </tr>
                <%
                    Set<String> pids = cart.keySet(); // Lấy key
                    for (String pid : pids) {
                        Plant plant = PlantDAO.getPlantByID(Integer.parseInt(pid));
                        int quantity = cart.get(pid); //lấy value là số lượng của key
                        String imgPath = plant.getImgpath();
                        String plantName = plant.getPlantName();
                        int price = plant.getPrice();
                        totalMoney += quantity * price;
                %>
                <form action="MainController" method="post">
                    <tr>
                        <td><input type="hidden" value="<%=pid%>" name="pid"><a href="GetPlantServlet?pid=<%=pid%>"><%= pid%></a></td>
                        <td><%= plantName%></td>
                        <td><img src="<%= imgPath%>" alt="This picture có lỗi" height="100px" width="100px"/></td>
                        <td><%= price%></td>
                        <td><input type="number" value="<%= quantity%>" name="quantity" min="1"></td>
                        <td><input type="hidden" value="<%=pid%>" name="pid"/>
                            <input type="submit" value="update" name="action"/><input type="submit" value="delete" name="action"/></td>      
                    </tr>
                </form>
                <%
                    }
                } else {
                %>
                <tr>
                    <td>Your cart is empty</td>
                </tr>
                <%
                    }
                %>
                <tr>
                    <td>Total money: <%= totalMoney%></td>

                </tr> 
            </table>
        </section>
        <section>
            <form action="MainController" method="post">
                <input type="submit" value="saveorder" name ="action" class="submitorder"/>
            </form>
        </section>
        <%}%>
    </body>
</html>
