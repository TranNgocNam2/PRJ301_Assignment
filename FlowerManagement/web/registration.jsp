<%-- 
    Document   : registration
    Created on : Feb 7, 2023, 11:21:41 AM
    Author     : ADMIN
--%>
<%@page import="namtn.controllers.MainController"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <title>Registration</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="css/registration.css">
    </head>
    <body>
        <%
            String name = (String) session.getAttribute("name");
            if (name != null) {
                response.sendRedirect("personalPage.jsp");
            }
        %>
        <div class="signup__container">
            <div class="container__child signup__thumbnail">
                <div class="thumbnail__content text-center">
                    <h1 class="heading--primary">Welcome to My Plant Shop.</h1>
                </div>
                <div class="signup__overlay"></div>
            </div>
            <div class="container__child signup__form">
                <form action="MainController" method="post" class="register" accept-charset="utf-8">
                    <div class="form-group">
                        <label class="space" for="email">Email:</label>
                        <input class="form-control" type="email" name="txtEmal" placeholder="Your email"
                               required />
                    </div>
                    <div class="form-group">
                        <label class="space" for="password">Password:</label>
                        <input class="form-control" type="password" name="txtPwd" placeholder="********"
                               required />
                    </div>
                    <div class="form-group">
                        <label class="space" for="name">Full name:</label>
                        <input class="form-control" type="text" name="txtFullname" placeholder="Your fullname"
                               required />
                    </div>
                    <div class="form-group">
                        <label class="space" for="phone">Phone: </label>
                        <input class="form-control" type="text" name="txtPhone"
                               placeholder="Your phone number" required />
                    </div>
                    <div class="m-t-lg">
                        <ul class="list-inline">
                            <li>
                                <input class="btn btn--form" type="submit" value="register" name="action" />
                            </li>
                            <li>
                                <a class="signup__link" href="<%= MainController.LOGINURL%>">I am already a member</a>
                            </li>
                        </ul>
                    </div>
                </form>
            </div>
        </div>
        <%
            String flag = (String) request.getAttribute("Error");
            if (flag != null) {
        %>
        <p style  = "text-align:center; color: rgb(125, 29, 29); font-weight: bold; padding-bottom: 10px "><% out.print(flag);%></p><%
                            }
        %>
    </body>
</html>
