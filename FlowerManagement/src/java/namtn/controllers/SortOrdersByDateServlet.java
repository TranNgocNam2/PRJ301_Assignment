/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package namtn.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import namtn.dao.OrderDAO;
import namtn.dto.Order;

/**
 *
 * @author ADMIN
 */
public class SortOrdersByDateServlet extends HttpServlet {

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
            String dateString1 = request.getParameter("fromDate");
            String dateString2 = request.getParameter("toDate");
            if (session != null) {
                int role = (int) session.getAttribute("role");
                if (dateString1 == null || dateString1.isEmpty() || dateString2 == null || dateString2.isEmpty()) {
                    request.setAttribute("mess", "Empty date!");
                    if (role == 0) {
                        request.getRequestDispatcher("personalPage.jsp").forward(request, response);
                    } else {
                        request.getRequestDispatcher("manageOrders.jsp").forward(request, response);
                    }
                }
                ArrayList<Order> orders = null;
                String email = (String) session.getAttribute("email");
                Date fromDate = Date.valueOf(request.getParameter("fromDate"));
                Date toDate = Date.valueOf(request.getParameter("toDate"));
                if (fromDate.after(toDate)) {
                    orders = OrderDAO.getAllOrdersByEmail(email);
                    request.setAttribute("mess", "From date must before to date!");
                    request.setAttribute("orderList", orders);
                    request.getRequestDispatcher("personalPage.jsp").forward(request, response);
                } else {
                    if (role == 1) {
                        orders = OrderDAO.getOrdersByDateForAdmin(fromDate, toDate);
                        request.setAttribute("listOrder", orders);
                        request.setAttribute("mess", "The result from " + dateString1 + " to " + dateString2);
                        request.getRequestDispatcher("manageOrders.jsp").forward(request, response);
                    } else {
                        orders = OrderDAO.getOrderByDate(email, fromDate, toDate);
                        request.setAttribute("orderList", orders);
                        request.setAttribute("mess", "The result from " + dateString1 + " to " + dateString2);
                        request.getRequestDispatcher("personalPage.jsp").forward(request, response);
                    }
                }
                if (orders == null || orders.isEmpty()) {

                    if (role == 1) {
                        request.setAttribute("mess", "Do not have results!");
                        request.getRequestDispatcher("manageOrders.jsp").forward(request, response);
                    } else {
                        request.setAttribute("mess", "Do not have results!");
                        request.getRequestDispatcher("personalPage.jsp").forward(request, response);
                    }
                }
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
