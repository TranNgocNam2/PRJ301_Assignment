<%-- 
    Document   : manageCategories
    Created on : Mar 17, 2023, 11:43:16 AM
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
                    <h1></h1>
                    <section>
                        <table class="order" style="border: 1px solid black; ">
                            <tr>
                                <th>Category ID</th>
                                <th>Category Name</th>
                                <th>Action</th>
                            </tr>
                        <c:set var="categories" value="${empty categories ? requestScope.result : requestScope.result}" />
                        <c:forEach var="cate" items="${categories}">
                            <tr>
                                <td style="text-align: center; padding: 10px"><c:out value="${cate.getCateID()}"/></td>
                                <td style="text-align: center; padding: 10px "><c:out value="${cate.getCateName()}"/></td>
                                <td style="text-align: center">
                                    <form action="MainController" method="post">
                                        <input type="text" name="CateName"/>
                                        <input type="hidden" name="cateID" value="${cate.getCateID()}"/>
                                        <input type="hidden" name="oldCateName" value="${cate.getCateName()}"/>
                                        <input type="submit" name="action" value="updateCate" style="margin-left: 30px"/>    
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                    <div><h1 style="margin-left: 20px">Create new Category</h1>
                        <form action="MainController" method="post">
                            <p style="font-size: larger; padding: 30px;padding-bottom: 10px; margin-left: 20px">Enter new Category name: 
                                <input type="text" name="newCateName"/>
                                <input type="hidden" name="oldCateName" value="${cate.getCateName()}"/>
                                <input type="submit" name="action" value="createCate" style="margin-left: 30px"/>    
                            </p>
                        </form>
                    </div>
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
