/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package namtn.controllers;

import java.io.IOException;
import java.io.PrintWriter;
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
public class ChangeProfileServlet extends HttpServlet {

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
            HttpSession session = request.getSession();
            Account acc = null;
            String email = (String) session.getAttribute("email");
            String newFullname = request.getParameter("newFullname");
            String currentPassword = request.getParameter("currentPassword");
            String newPassword = request.getParameter("newPassword");
            String newPhone = request.getParameter("newPhone");

            //If user does not want to input new full name
            if (newFullname == null || newFullname.equals("")) {
                newFullname = (String) session.getAttribute("name");
            }

            //Check valid new full name if user want to input new name
            if (newFullname != null && !newFullname.matches("^[a-z A-Z]+$")) {
                request.setAttribute("change", "Input name is invalid!");
                request.getRequestDispatcher("changeProfile.jsp").forward(request, response);
                return;
            }

            //Check if user does not input current password
            if (currentPassword == null || currentPassword.isEmpty()) {
                request.setAttribute("change", "Empty current password!");
                request.getRequestDispatcher("changeProfile.jsp").forward(request, response);
                return;
            }

            //If user does not want to input new phone
            if (newPhone == null || newPhone.equals("")) {
                newPhone = (String) session.getAttribute("phone");
            }

            //Check valid new phone if user want to input phone
            if (newPhone != null && !newPhone.matches("^[0-9]{9}$")) {
                request.setAttribute("change", "Invalid phone number. Input must be number!");
                request.getRequestDispatcher("changeProfile.jsp").forward(request, response);
                return;
            }

            //Check new password same current password
            if (currentPassword.equalsIgnoreCase(newPassword)) {
                request.setAttribute("change", "You can not input same password!");
                request.getRequestDispatcher("changeProfile.jsp").forward(request, response);
                return;
            }

            //Check if current password 
            if (AccountDAO.getAccount(email, currentPassword) != null) {
                //If user does not want to input new password
                if (newPassword == null || newPassword.equals("")) {
                    newPassword = currentPassword;
                }
                //Update information
                AccountDAO.updateAccount(email, newPassword, newFullname, newPhone);
                acc = AccountDAO.getAccount(email, newPassword);
                session.setAttribute("accInfo", acc);
                session.setAttribute("name", newFullname);
                session.setAttribute("phone", newPhone);
                request.setAttribute("mess", "Changed profile successfully!");
                request.getRequestDispatcher("personalPage.jsp").forward(request, response);

            } else {
                //Check if user enter wrong current password
                request.setAttribute("change", "Wrong current password!");
                request.getRequestDispatcher("changeProfile.jsp").forward(request, response);
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
