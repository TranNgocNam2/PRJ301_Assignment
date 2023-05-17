<%-- 
    Document   : changeProfileUser
    Created on : Mar 15, 2023, 11:18:21 AM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <title>JSP Page</title>
    </head>
    <body>
        <a href="personalPage.jsp">Home</a>
        <c:set var="email" value="${sessionScope.email}"/>
        <c:set var="account" value="${sessionScope.accInfo}"/>
        <c:set var="name" value="${account.getFullname()}"/>
        <c:set var="c" value="${cookie}}"/>
        <c:set var="login" value="${not empty name}"/>

        <c:forEach var="cookie" items="${c}">
            <c:if test="${cookie.name == 'selector'}">
                <c:set var="token" value="${cookie.value}"/>
                <c:set var="acc" value="${AccountDAO.getAccountByToken(token)}"/>
                <c:if test="${not empty acc}">
                    <c:set var="name" value="${acc.fullname}"/>
                    <c:set var="email" value="${acc.email}"/>
                    <c:set var="login" value="true"/>
                </c:if>
            </c:if>
        </c:forEach>
        <c:choose>
            <c:when test="${login}">
                <form action="MainController" method="post" name="action">
                    <table style="background-color: #999">
                        <tr>
                            <td style="padding: 10px; font-weight: bold">Information</td>
                            <td style="padding: 10px; font-weight: bold">Current</td>
                            <td style="padding: 10px; font-weight: bold">New</td>                    
                        </tr>
                        <tr>
                            <td style="padding: 10px; font-weight: bold">Name</td>
                            <td style="padding: 10px"><c:out value="${sessionScope.name}" /></td>
                            <td style="padding: 10px"><input type="text" name="newFullname"/></td>
                        </tr>
                        <tr>
                            <td style="padding: 10px; font-weight: bold">Password</td>
                            <td style="padding: 10px"><input type="password" name="currentPassword" required=/></td>
                            <td style="padding: 10px"><input type="password" name="newPassword"/></td>
                        </tr>
                        <tr>
                            <td style="padding: 10px; font-weight: bold">Phone</td>
                            <td style="padding: 10px"><c:out value="${account.getPhone()}"/></td>
                            <td style="padding: 10px"><input type="text" name="newPhone"/></td>                    
                        </tr>
                    </table>
                    <input type="submit" name="action" value="updateprofile"/>
                </form>
                            <c:if test="${not empty requestScope.change}">
                                <p style="color: red; font-size: larger"> <c:out value="${requestScope.change}"/></p>
                            </c:if>

            </c:when>
            <c:otherwise>
                <p>You must login to view this page!</p>
                <p><a href="login.jsp">Please login !</a></p>
            </c:otherwise>
        </c:choose>

    </body>
</html>
