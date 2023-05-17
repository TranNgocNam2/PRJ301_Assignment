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
import namtn.dao.CategoryDAO;
import namtn.dto.Category;

/**
 *
 * @author ADMIN
 */
public class UpdateCategoryServlet extends HttpServlet {

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
        try {
            ArrayList<Category> categories = null;
            categories = CategoryDAO.getAllCategories();
            if (session != null) {
                String newCateName = request.getParameter("CateName");
                if (newCateName == null || newCateName.trim().isEmpty()) {
                    categories = CategoryDAO.getAllCategories();
                    request.setAttribute("result", categories);
                    request.setAttribute("mess", "Empty name!");
                    request.getRequestDispatcher("manageCategories.jsp").forward(request, response);
                    return;
                }
                for (Category cate : categories) {
                    if (newCateName.equalsIgnoreCase(cate.getCateName())) {
                        categories = CategoryDAO.getAllCategories();
                        request.setAttribute("mess", "Category cannot have the same name!");
                        request.setAttribute("result", categories);
                        request.getRequestDispatcher("manageCategories.jsp").forward(request, response);
                        return;
                    }
                }
                int cateID = Integer.parseInt(request.getParameter("cateID"));
                String oldCateName = request.getParameter("oldCateName");
                if (newCateName.equalsIgnoreCase(oldCateName)) {
                    categories = CategoryDAO.getAllCategories();
                    request.setAttribute("mess", "Category cannot have the same name!");
                    request.setAttribute("result", categories);
                    request.getRequestDispatcher("manageCategories.jsp").forward(request, response);
                    return;
                } else {
                    CategoryDAO.updateCategory(cateID, newCateName);
                    categories = CategoryDAO.getAllCategories();
                    request.setAttribute("mess", "Update successfully!");
                    request.setAttribute("result", categories);
                    request.getRequestDispatcher("manageCategories.jsp").forward(request, response);
                }
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
