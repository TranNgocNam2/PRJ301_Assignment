<%-- 
    Document   : manageOrders
    Created on : Mar 16, 2023, 12:10:19 AM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/mycss.css" rel="stylesheet" type="text/css"/>
        <title>JSP Page</title>

    </head>
    <body>
        <c:set var="name" value="${sessionScope.name}"/>
        <c:set var="email" value="${sessionScope.email}"/>
        <c:set var="role" value="${sessionScope.role}"/>
        <c:set var="c" value="${cookie}"/>
        <c:set var="login" value="${not empty name and role == 1}"/>

        <c:forEach var="cookie" items="${c}">
            <c:if test="${cookie.name == 'selector'}">
                <c:set var="token" value="${cookie.value}"/>
                <c:set var="acc" value="${AccountDAO.getAccountByToken(token)}"/>
                <c:if test="${not empty acc and role == 1}">
                    <c:set var="name" value="${acc.fullname}"/>
                    <c:set var="email" value="${acc.email}"/>
                    <c:set var="login" value="true"/>
                </c:if>
            </c:if>
        </c:forEach>
        <c:choose>
            <c:when test="${login}">
                <c:import url="admin_header.jsp"></c:import>
                   
                <form action="MainController" name="action" method="post">
                    from<input type="date" name="fromDate" required/>to<input type="date" name="toDate" required/>
                        <input type="submit" name="action" value="sortbydate"/> 
                    </form>
                    <h1></h1>
                    <section>
                        <table class="order">
                            <tr>
                                <th>Order ID</th>
                                <th>Order date</th>
                                <th>Ship date</td>
                                <th>Status</td>
                                <th>Account ID</td>
                            </tr>
                        <c:set var="orders" value="${empty orders ? requestScope.listOrder : requestScope.listOrder}" />
                        <c:forEach var="ord" items="${orders}">
                            <tr>
                                <td style="text-align: center"><c:out value="${ord.getOrderID()}"/></td>
                                <td style="text-align: center"><c:out value="${ord.getOrderDate()}"/></td>
                                <td style="text-align: center"><c:choose>
                                        <c:when test="${empty ord.getShipDate()}">
                                            <form action="MainController" method="post">
                                                <input type="date" name="shipDate" required/>
                                                <input type="hidden" name="orderID" value="${ord.getOrderID()}"/>
                                                <input type="hidden" name="orderDate" value="${ord.getOrderDate()}"/>
                                                <input type="submit" name="action" value="setshipdate" />
                                            </form>

                                        </c:when>

                                        <c:otherwise><c:out value="${ord.getShipDate()}"/></c:otherwise>
                                    </c:choose></td>

                                <td style="text-align: center"><c:choose>
                                        <c:when test="${ord.getStatus() eq 1}">In process
                                            <form action="MainController" method="post">
                                                <input type="hidden" name="orderID" value="${ord.getOrderID()}"/>
                                                <input type="hidden" name="status" value="${ord.getStatus()}"/>
                                                <input type="submit" name="action" value="updateorder"/>
                                            </form>
                                        </c:when>
                                        <c:when test="${ord.getStatus() eq 2}">Completed</c:when>
                                        <c:otherwise>Canceled</c:otherwise>
                                    </c:choose></td>
                                <td style="text-align: center"><c:out value="${ord.getAccID()}"/></td>
                            </tr>
                        </c:forEach>
                    </table>
                    <c:if test="${not empty requestScope.mess}"> 
                        <p style="color: red; font-size: larger"> Order ID: <c:out value="${requestScope.orderid}"/> was <c:out value="${requestScope.mess}"/></p>
                    </c:if>
                </section>
            </c:when>
            <c:otherwise>
                <p>You must login to view this page!</p>
                <p><a href="login.jsp">Please login with Administrator account!</a></p>
            </c:otherwise>
        </c:choose>
    </body>
</html>
