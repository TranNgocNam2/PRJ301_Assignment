<%-- 
    Document   : viewPlant
    Created on : Mar 8, 2023, 10:37:09 PM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Plant Detail</title>
</head>
<%
    //Cách 1: Sử dụng javabean
    
    /* 
    - JavaBean là 1 Object Java cơ bản, có các thuộc tính private được truy cập thông qua các phương thức setter và getter
    - JavaBean có thể được sử dụng để lưu trữ và truy xuất giá trị dữ liệu của một Object trong Java
    - JavaBean tuân thủ quy ước chuẩn như tên lớp phải viết hoa chữ cái đầu, có 1 constructor không tham số (no-argument constructor), 
    các thuộc tính phải được khai báo private và có các phương thức như getter và setter để truy xuất hoặc thiết lập giá trị
    - JavaBean phải implements Serializable
    */
%>
<jsp:useBean id="plantObj" class="namtn.dto.Plant" scope="request"/>
<table>
    <tr><td rowspan="8"><img src="<jsp:getProperty name="plantObj" property="imgpath"></jsp:getProperty>" height="150px" width="150px"/></td></tr>
    <tr><td>Plant id: <jsp:getProperty name="plantObj" property="plantId"></jsp:getProperty></td></tr>
    <tr><td>Plant Name: <jsp:getProperty name="plantObj" property="plantName"></jsp:getProperty></td></tr>
    <tr><td>Price: <jsp:getProperty name="plantObj" property="price"></jsp:getProperty></td></tr>
    <tr><td>Description: <jsp:getProperty name="plantObj" property="description"></jsp:getProperty></td></tr>
    <tr><td>Status: <jsp:getProperty name="plantObj" property="status"></jsp:getProperty></td></tr>
    <tr><td>Category ID: <jsp:getProperty name="plantObj" property="cateID"></jsp:getProperty></td></tr>
    <tr><td>Category Name: <jsp:getProperty name="plantObj" property="cateName"></jsp:getProperty></td></tr>
    </table>
    
    
    
    
<!--
<%
    //Cách 2: Sử dụng EL   
    /*
    - EL(Expression Language là ngôn ngữ biểu thức dùng để truy xuất và thao tác dữ liệu trên trang (JavaServer Pages) hoặc XML.
    - EL cung cấp cú pháp để truy xuất thuộc tính của Object Java, lưu giá trị vào các biến và thực hiện phép toán
     */
%>
<table>
    <tr><td rowspan="8"><img src="${plantObj.imgpath}" height="100px" width="100px"/></td></tr>
    <tr><td>Plant ID: ${plantObj.plantId}</td></tr>
    <tr><td>Plant Name: ${plantObj.plantName}</td></tr>
    <tr><td>Price: ${plantObj.price}</td></tr>
    <tr><td>Description: ${plantObj.description}</td></tr>
    <tr><td>Status: ${plantObj.status}</td></tr>
    <tr><td>Category ID: ${plantObj.cateID}</td></tr>
    <tr><td>Category Name: ${plantObj.cateName}</td></tr>

</table>
-->





