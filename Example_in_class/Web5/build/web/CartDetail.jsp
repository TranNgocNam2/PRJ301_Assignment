<%-- 
    Document   : CartDetail
    Created on : Feb 10, 2023, 11:39:58 AM
    Author     : user
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
          //doc cart trong session
          HashMap<String, Integer> cart=(HashMap)session.getAttribute("cart");
          if(cart!=null && cart.size()>0){
        %>
        <h1>Thong tin gio hang cua ban</h1>     
        <table>
            <tr>
                <th>ID</th>
                <th>NAME</th>
                <th>PRICE</th>
                <th>QUANTITY</th>
                <th>CHON</th>
            </tr>
            <%
               float total=0; 
               for(String id: cart.keySet()){
                   Car c=CarDao.getCar(id);
            %>
            <tr> <form action="MainController" method="post">
                <td><%= c.getId() %></td>
                <td><%= c.getName()%></td>
                <td><%= c.getPrice()%></td>                
                <td><input type="number" value="<%= cart.get(id)%>" min="1" max="10" name="quantity"></td>
                <td><input type="submit" value="update" name="action">
                    <input type="submit" value="remove" name="action">
                </td>
                <input type="hidden" value="<%= c.getId() %>" name="carid"/>
                </form>
            </tr>
            <%
                  total=total+ c.getPrice()* cart.get(id);
               }
            %>
        </table>
        <h2>Tong tien can thanh toan: <%= total %> vnd</h2>
        <form action="MainController" method="post">
            <input type="submit" value="checkout" name="action">
        </form>
        <%
          }
         else{
                 out.print("<h1>Gio hang trong</h2>");
         } 
       %>
    </body>
</html>
