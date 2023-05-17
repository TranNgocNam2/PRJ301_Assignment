<%-- 
    Document   : adminPage
    Created on : Mar 9, 2023, 11:15:10 AM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/mycss.css" rel="stylesheet" type="text/css"/>
        <title>Admin Page</title>
    </head>
    <body>
        <c:set var="name" value="${sessionScope.name}"/>
        <c:set var="email" value="${sessionScope.email}"/>
        <c:set var="role" value="${sessionScope.role}"/>
        <c:set var="c" value="${cookie}"/>
        <c:set var="login" value="false"/>
        <c:if test="${not empty name and role == 1}">
            <c:set var="login" value="true"/>
        </c:if>
        <c:forEach var="cookie" items="${c}">
            <c:if test="${cookie.name == 'selector'}">
                <c:set var="token" value="${cookie.value}"/>
                <c:set var="acc" value="${AccountDAO.getAccountByToken(token)}"/>
                <c:if test="${not empty acc and acc.role ==1}">
                    <c:set var="name" value="${acc.fullname}"/>
                    <c:set var="email" value="${acc.email}"/>
                    <c:set var="login" value="true"/>
                </c:if>
            </c:if>
        </c:forEach>
        <c:if test="${login == false}">
            <p>You must login and must be Administrator to view this page!</p>
            <p><a href="login.jsp">Please login with Administrator account!</a></p>
        </c:if>
        <c:if test="${login == true}">
            <header>
                <c:import url="admin_header.jsp"></c:import>
                    <section class="right">

                    </section>
                </header>
        </c:if>
    </body>
</html>
