/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package namtn.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import namtn.dao.AccountDAO;
import namtn.dto.Account;

/**
 *
 * @author ADMIN
 */
public class RegisterServlet extends HttpServlet {

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
            String email = request.getParameter("txtEmal");
            String password = request.getParameter("txtPwd");
            String fullname = request.getParameter("txtFullname");
            String phone = request.getParameter("txtPhone");
            ArrayList<Account> accounts = AccountDAO.getAccounts("");
            for (Account acc : accounts) {
                if (acc.getEmail().equals(email)) {
                    request.setAttribute("Error", "Duplicate account found !");
                    request.getRequestDispatcher("registration.jsp").forward(request, response);
                }
            }
            int status = 1;
            int role = 0;
            if (email == null || email.trim().equals("") || !email.matches("^[a-zA-Z0-9._%+-]+@gmail\\.com$")) {
                request.setAttribute("Error", "Invalid email!");
                request.getRequestDispatcher("registration.jsp").forward(request, response);
                return;
            }
            if (password == null || password.trim().equals("")) {
                request.setAttribute("Error", "Invalid password!");
                request.getRequestDispatcher("registration.jsp").forward(request, response);

            }
            if (fullname == null || fullname.trim().equals("") || !fullname.matches("^[a-zA-Z]+$")) {
                request.setAttribute("Error", "Invalid name!");
                request.getRequestDispatcher("registration.jsp").forward(request, response);
            }
            if (phone == null || phone.equals("") || !phone.matches("\\d[0-9]{9}$")) {
                request.setAttribute("Error", "Invalid phone number. Input must be number!");
            }
            if (AccountDAO.insertAccount(email, password, fullname, phone, status, role)) {
                Account acc = AccountDAO.getAccount(email, password);
                HttpSession session = request.getSession();
                session.setAttribute("name", acc.getFullname());
                session.setAttribute("email", email);
                session.setAttribute("role", acc.getRole());
                session.setAttribute("accInfo", acc);
                response.sendRedirect("personalPage.jsp");
            } else {
                request.setAttribute("Error", "");
                request.getRequestDispatcher("registration.jsp").forward(request, response);
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
