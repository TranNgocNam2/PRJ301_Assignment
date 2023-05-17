<%-- 
    Document   : login.jsp
    Created on : Feb 7, 2023, 11:14:53 AM
    Author     : ADMIN
--%>

<%@page import="namtn.controllers.MainController"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Login</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="css/login.css" />
        <link rel="icon" href="image/3.png"/>
    </head>
    <body>
        <%String name = (String) session.getAttribute("name");
            if(name != null){
                response.sendRedirect("personalPage.jsp");
            }
        %>
        <div class="container">
            <div class="screen">
                <div class="screen__content">
                    <div class="title">
                        <h1>Login Flower Management System</h1>
                    </div>
                    <form class="login" action="MainController" method="post">
                        <div class="login__field">
                            <i class="login__icon fas fa-user"></i>
                            <input type="email" class="login__input" placeholder="Email" name="txtEmail" required>
                        </div>
                        <div class="login__field">
                            <i class="login__icon fas fa-lock"></i>
                            <input type="password" class="login__input" placeholder="Password" name="txtPwd" required>
                        </div>
                        <button class="button login__submit" name="action" value="login" method="post">
                            <span class="button__text1">Log In Now</span>
                            <i class="button__icon fas fa-chevron-right"></i>
                        </button>
                        <p><input type="checkbox" value="savelogin" name="savelogin">Stayed signed in</p>
                    </form>




                    <div class="signup">
                        <%
                            String flag = (String) request.getAttribute("Error");
                            if (flag != null) {
                        %>
                        <p style  = "text-align:center; color: rgb(125, 29, 29); font-weight: bold; padding-bottom: 10px "><% out.print(flag);%></p><%
                            }
                        %>
                        <h3>You don't have account ?</h3>
                        <button class="button register_submit">
                            <span class="button__text2"><a class="signup_link" href="registration.jsp">Sign Up Now</a></span>
                            <i class="button__icon fas fa-chevron-right"></i>
                        </button>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
