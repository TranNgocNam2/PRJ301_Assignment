<%-- 
    Document   : page4
    Created on : Feb 17, 2023, 10:41:57 AM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <c:set var="x" value="100"/>
        <c:set var="y" value="200"/>
        <h1>Total: ${x+y}</h1>
        <c:if test="${x<y}">
            ${"em yeu co"}
        </c:if>

        <c:set var="kq" value="${sessionScope.list}"/>
        <c:if test="${kq != null}">
            <table>
                <tr>
                    <th>id</th>
                    <th>name</th>
                    <th>price</th>
                    <th>action</th>
                </tr>
                <c:forEach items="${kq}" var="it">
                    <tr>
                        <td>${it.id}</td>
                        <td>${it.name}</td>
                        <td>${it.price}</td>
                        <td><a href="page4.jsp?id=${it.id}&name=${it.name} &price=${it.price}">edit</a></td>
                    </tr>
                </c:forEach>
            </table>


        </c:if>
        <h1>Thong tin san pham</h1>
        <form action="MainController" method="post">
            <p><input type="text" name="tstid" value="${param.id}" readonly="" required=""/></p>
            <p><input type="text" name="tstname" value="${param.name}"  required=""/></p>
            <p><input type="number" name="intprice" value="${param.price}" min ="1" required=""/></p>
            <p><input type="submit" name="action" value="update"/></p>

        </form>
        <c:if test="${(sessionScope.time%2)==0}">
            <c:url var="mylink" value="page5.jsp"/>
            <a href="${mylink}">link</a>
        </c:if>
        <c:if test="${(sessionScope.time%2)!=0}">
            <c:url var="mylink" value="page6.jsp"/>
            <a href="${mylink}">link</a>
        </c:if>
    </body>
</html>
