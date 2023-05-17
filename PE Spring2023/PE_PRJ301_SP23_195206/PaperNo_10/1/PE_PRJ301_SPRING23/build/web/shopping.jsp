<%-- 
    Document   : index
    Created on : Feb 28, 2023, 9:01:26 PM
    Author     : Hoadnt
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shopping Page</title>
    </head>
    <body>
        <h1>Welcome to Thinh's shop</h1>
        <!--your code here-->
        <form action="MainController" method="post">
            <button type="submit" name="action" value="search">Search all</button>
             <button type="submit" name="action" value="viewCart">View cart</button>
        </form>
        <c:set var="proList" value="${empty proList ? requestScope.carList: requestScope.proList}"/>
        <c:forEach var="pro" items="${proList}" varStatus="counter">
            <table>
                <tr>
                    <th>No</th>
                    <th>Product ID</th>
                    <th>Product Name</th>
                    <th>Description</th>
                    <th>Price</th>
                    <th>Add</th>
                </tr>
                <tr>
                    <td>${counter.count}</td>
                    <td>${pro.getProductID()}</td>
                    <td>${pro.getProductName()}</td>
                    <td>${pro.getDescription()}</td>
                    <td>${pro.getPrice()}</td>
                    <td><form action="MainController" method="post">
                            <input type="hidden" name ="productID" value="${pro.getProductID()}"/>
                            <input type="hidden" name ="productName" value="${pro.getProductName()}"/>
                            <input type="hidden" name ="price" value="${pro.getPrice()}"/>
                            <button type="submit" name="action" value="add">Add</button>
                        </form></td>
                </tr>
            </table>

        </c:forEach>
        <c:if test="${not empty requestScope.mess}">
            <c:out value="${requestScope.mess}"/>
        </c:if>
    </body>
</html>
