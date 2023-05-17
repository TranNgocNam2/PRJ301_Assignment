<%-- 
    Document   : managePlants
    Created on : Mar 20, 2023, 3:35:14 PM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manage Plant</title>
        <link  rel="stylesheet" href="css/index.css" type="text/css" />   
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
                    <h1></h1>
                    <section>
                        <table class="order" style="border: 1px solid black; ">
                            <tr>
                                <th>Plant ID</th>
                                <th>Plant Name</th>
                                <th>Price</th>
                                <th>Image</th>
                                <th>Description</th>
                                <th>Status</th>
                                <th>Category ID</th>
                                <th>Action</th>
                            </tr>
                        <c:set var="plants" value="${empty plants ? sessionScope.plantList : sessionScope.plantList}" />
                        <c:forEach var="plant" items="${plants}">
                            <tr>
                                <td style="text-align: center; padding: 10px"><c:out value="${plant.getPlantId()}"/></td>
                                <td style="text-align: center; padding: 10px "><c:out value="${plant.getPlantName()}"/></td>
                                <td style="text-align: center; padding: 10px "><c:out value="${plant.getPrice()}"/></td>
                                <td style="text-align: center; padding: 10px "><img src="${plant.getImgpath()}" height="100px" width="100px"/></td>
                                <td style="text-align: center; padding: 10px "><c:out value="${plant.getDescription()}"/></td>
                                <td style="text-align: center; padding: 10px "><c:out value="${plant.getStatus()}"/></td>
                                <td style="text-align: center; padding: 10px "><c:out value="${plant.getCateID()}"/></td>
                                <td style="text-align: center; padding: 10px ">
                                    <form action="MainController" method="post">
                                        <input type="hidden" name="plantid" value="${plant.getPlantId()}"/>
                                        <input type="hidden" name="statusPlant" value="${plant.getStatus()}"/>
                                        <button type="submit" name="action" value="btnupdate">update</button>
                                        <button type="submit" name="action" value="setStatusPlant">Set Status</button>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                    <c:if test="${not empty requestScope.buttonUpdate}">
                        <h2>Update plant with Plant ID <c:out value="${sessionScope.plantid}"/></h2>
                    </c:if>
                    <c:if test="${not empty requestScope.mess}"> 
                        <p style="color: red; font-size: larger; margin-left: 30px"> <c:out value="${requestScope.mess}"/></p>
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
