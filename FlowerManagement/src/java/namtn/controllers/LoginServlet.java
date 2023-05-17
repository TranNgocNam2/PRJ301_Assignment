/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package namtn.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import namtn.dao.AccountDAO;
import namtn.dto.Account;
import namtn.utils.MyUtils;

/**
 *
 * @author ADMIN
 */
public class LoginServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            //Get email, password, and save login information from request parameters
            String email = request.getParameter("txtEmail");
            String pwd = request.getParameter("txtPwd");
            String saveAcc = request.getParameter("savelogin");
            Account acc = null;

            //Check if email or password is null or empty
            if (email == null || email.equals("") || pwd == null || pwd.equals("")) {
                Cookie[] c = request.getCookies();
                String token = "";
                //If no cookie found, return an error message and ask user to provide email and password//Nếu như kiểm tra không thấy cookie thì trả Error về và yêu cầu người dùng nhập email, password
                if (c != null) {
                    //If a cookie is found, get its value by name = selector and store it in token
                    for (Cookie aCookie : c) {
                        if (aCookie.getName().equals("selector")) {
                            token = aCookie.getValue();
                            break;
                        }
                    }
                    //If token is not empty, retrieve account by token
                    if (!token.isEmpty()) {
                        acc = AccountDAO.getAccountByToken(token);
                        email = acc.getEmail();
                        pwd = acc.getPassword();
                    } else {
                        request.setAttribute("Error", "Empty email or password!");
                        request.getRequestDispatcher("login.jsp").forward(request, response);
                    }
                }
            }
            //Get account by email and password
            acc = AccountDAO.getAccount(email, pwd);
            //Create a cookie and attach it to the response object if "save login" checkbox is checked and account exists
            if (saveAcc != null && acc != null) {
                String token = MyUtils.generateToken();
                AccountDAO.updateToken(token, email);
                //Create a cookie with name = selector and value = token and set its expiration time to 1 hour
                Cookie cookie = new Cookie("selector", token);
                cookie.setMaxAge(3600);
                response.addCookie(cookie);
            }
            if (acc != null) {
                //If account exists, create a session and redirect to appropriate page based on user's role
                HttpSession session = request.getSession();
                session.setAttribute("name", acc.getFullname());
                session.setAttribute("email", email);
                session.setAttribute("role", acc.getRole());
                session.setAttribute("phone", acc.getPhone());
                session.setAttribute("accInfo", acc);
                if (acc.getRole() == 1) {
                    response.sendRedirect("adminPage.jsp");

                } else {
                    response.sendRedirect("personalPage.jsp");
                }
            } else {
                //If account does not exist, return an error message and ask user to provide valid email and password
                request.setAttribute("Error", "Invalid email or password!");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
