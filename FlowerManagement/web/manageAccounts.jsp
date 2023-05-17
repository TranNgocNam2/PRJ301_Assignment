<%-- 
    Document   : manageAccounts
    Created on : Mar 9, 2023, 12:24:01 PM
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
        <form action="MainController" method="post" style="">
            <input type="text" name="txtSearch" required/>
            <input type="submit" value="searchAccount" name="action">
        </form>
        <h1></h1>
        <section>
            <table class="order">
                <tr>
                    <th>Account ID</th>
                    <th>Email</th>
                    <th>Full Name</td>
                    <th>Status</td>
                    <th>Phone</td>
                    <th>Role</td>
                    <th>Action</td>
                </tr>
                <c:set var="accounts" value="${empty accounts ? requestScope.accountList : requestScope.accounts}" />
                <c:forEach var="acc" items="${accounts}">
                    <tr>
                        <td><c:out value="${acc.getAccID()}"/></td>
                        <td><c:out value="${acc.getEmail()}"/></td>
                        <td><c:out value="${acc.getFullname()}"/></td>
                        <td><c:choose>
                                <c:when test="${acc.getStatus() eq 1}">Active</c:when>
                                <c:otherwise>Blocked</c:otherwise>
                            </c:choose></td>
                        <td><c:out value="${acc.getPhone()}"/></td>
                        <td><c:choose>
                                <c:when test="${acc.getRole() eq 1}">Admin</c:when>
                                <c:otherwise>User</c:otherwise>
                            </c:choose></td>
                        <td><c:if test="${acc.getRole() eq 0}">
                                <c:url var="mylink" value="MainController">
                                    <c:param name="email" value="${acc.getEmail()}"/>
                                    <c:param name="status" value="${acc.getStatus()}"/>
                                    <c:param name="action" value="updateStatusAccount"/>
                                </c:url>
                                <a href="${mylink}">Block/Unblock</a>
                            </c:if></td>
                    </tr>
                </c:forEach>
            </table>
        </section>
    </c:when>
    <c:otherwise>
        <p>You must login to view this page!</p>
        <p><a href="login.jsp">Please login with Administrator account!</a></p>
    </c:otherwise>
</c:choose>
    </body>
</html>
