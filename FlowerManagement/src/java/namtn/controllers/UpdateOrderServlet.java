/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package namtn.controllers;

import java.io.IOException;
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
public class UpdateOrderServlet extends HttpServlet {

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
        HttpSession session = request.getSession();
        int role = (int) session.getAttribute("role");
        try {
            int orderid = Integer.parseInt(request.getParameter("orderID").trim());
            int status = Integer.parseInt(request.getParameter("status").trim());
            if (status == 1 && role == 1) {
                OrderDAO.updateOrder(orderid, 2);
                ArrayList<Order> orders = OrderDAO.getAllOrders();
                request.setAttribute("listOrder", orders);
                request.setAttribute("orderid", orderid);
                request.setAttribute("mess", "updated successfully!");
                request.getRequestDispatcher("manageOrders.jsp").forward(request, response);
            }
            if (status == 1 && role == 0) {
                OrderDAO.updateOrder(orderid, 3);
                request.getRequestDispatcher("personalPage.jsp").forward(request, response);

            } else if (status == 3 && role == 0) {
                OrderDAO.updateOrderStatusAndDate(orderid, 1);
                request.getRequestDispatcher("personalPage.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("errorPage.jsp");
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
