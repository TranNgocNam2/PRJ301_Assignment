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

/**
 *
 * @author ADMIN
 */
public class MainController extends HttpServlet {

    public static final String LOGIN = "login";
    public static final String REGISTER = "register";
    public static final String LOGINURL = "login.jsp";
    public static final String REGISTERURL = "registration.jsp";
    public static final String SEARCH = "search";
    public static final String LOGOUT = "logout";
    public static final String ADDTOCART = "addtocart";
    public static final String VIEWCART = "viewcart";
    public static final String UPDATE = "update";
    public static final String DELETE = "delete";
    public static final String SAVEORDER = "saveorder";
    public static final String MANAGEACCOUNTS = "manageaccounts";
    public static final String SEARCHACCOUNT = "searchAccount";
    public static final String UPDATEACCOUNTSTATUS = "updateStatusAccount";
    public static final String SORT = "sort";
    public static final String UPDATEORDER = "updateorder";
    public static final String UPDATEPROFILE = "updateprofile";
    public static final String MANAGEORDERS = "manageorders";
    public static final String SETSHIPDATE = "setshipdate";
    public static final String MANAGECATEGORIES = "managecategories";
    public static final String UPDATECATE = "updateCate";
    public static final String CREATECATE = "createCate";
    public static final String MANAGEPLANTS = "manageplants";
    public static final String SORTORDERSBYDATE = "sortbydate";
    public static final String BTNUPDATE = "btnupdate";
    public static final String SETSTATUSPLANT = "setStatusPlant";

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
            String ac = request.getParameter("action");
            String url = "";
            switch (ac) {
                case LOGIN:
                    url = "LoginServlet";
                    break;
                case REGISTER:
                    url = "RegisterServlet";
                    break;
                case SEARCH:
                    url = "SearchServlet";
                    break;
                case LOGOUT:
                    url = "LogoutServlet";
                    break;
                case ADDTOCART:
                    url = "AddToCartServlet";
                    break;
                case VIEWCART:
                    url = "viewCart.jsp";
                    break;
                case UPDATE:
                    url = "UpdateCartServlet";
                    break;
                case DELETE:
                    url = "DeleteCartServlet";
                    break;
                case SAVEORDER:
                    url = "SaveShoppingCartServlet";
                    break;
                case MANAGEACCOUNTS:
                    url = "ManageAccountsServlet";
                    break;
                case SEARCHACCOUNT:
                    url = "SearchAccountsServlet";
                    break;
                case UPDATEACCOUNTSTATUS:
                    url = "UpdateStatusAccountServlet";
                    break;
                case SORT:
                    url = "SortOrdersServlet";
                    break;
                case SORTORDERSBYDATE:
                    url = "SortOrdersByDateServlet";
                    break;
                case UPDATEORDER:
                    url = "UpdateOrderServlet";
                    break;
                case UPDATEPROFILE:
                    url = "ChangeProfileServlet";
                    break;
                case MANAGEORDERS:
                    url = "ManageOrdersServlet";
                    break;
                case SETSHIPDATE:
                    url = "SetShipDateServlet";
                    break;
                case MANAGECATEGORIES:
                    url = "ManageCategoriesServlet";
                    break;
                case CREATECATE:
                    url = "CreateCategoryServlet";
                    break;
                case UPDATECATE:
                    url = "UpdateCategoryServlet";
                    break;
                case MANAGEPLANTS:
                    url = "ManagePlantsServlet";
                    break;
                case BTNUPDATE:
                    url = "ButtonUpdateServlet";
                    break;
                 case SETSTATUSPLANT:
                     url="UpdatePlantStatus";
                     break;
                default:
                    url = LOGINURL;
            }
            request.getRequestDispatcher(url).forward(request, response);
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
