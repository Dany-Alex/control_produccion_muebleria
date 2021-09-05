/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dxa.control_produccion_muebleria.Backend.Controller;

import com.dxa.control_produccion_muebleria.Backend.Model.Clases.Exceptions.CustomException;
import com.dxa.control_produccion_muebleria.Backend.Model.Clases.Exceptions.exceptionPiece;
import com.dxa.control_produccion_muebleria.Backend.Model.Clases.furniture;
import com.dxa.control_produccion_muebleria.Backend.Model.Clases.user;
import com.dxa.control_produccion_muebleria.Backend.Model.Query.furnitureDAO;
import com.dxa.control_produccion_muebleria.Backend.Model.Query.userDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Artist
 */
@WebServlet(name = "adminPrincipalController", urlPatterns = {"/adminPrincipalController"})
public class adminPrincipalController extends HttpServlet {

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

    }
    String viewIndex = "/index.jsp";
    String path = "/View/Admin/";
    String creationNewTypeFurniture = path + "creation-new-type-furniture.jsp",
            creationNewUser = path + "creation-new-user.jsp",
            userCancellation = path + "user-cancellation.jsp",
            SalesReport = path + "sales-report.jsp",
            ProfitReport = path + "profit-report.jsp",
            ReturnSalesReport = path + "return-sales-report.jsp",
            userMostSalesReport = path + "user-most-sales-report.jsp",
            userMoreProfitReport = path + "user-more-profit-report.jsp",
            bestSellingFurnitureReport = path + "best-selling-furniture-report.jsp",
            leastSoldFurnitureReport = path + "least-sold-furniture-report.jsp",
            loadTxt = path + "load-txt.jsp",
            home = path + "adminyMenu.jsp";

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
        HttpSession session = request.getSession();
        String msg = "", error = "";
        String menu = request.getParameter("menu-admin");
        String action = request.getParameter("action");

        if (menu == null || menu.isEmpty()) {
            // request.getRequestDispatcher(viewIndex).forward(request, response);
        } else {
            switch (menu) {
                case "sales-report":
                    request.getRequestDispatcher(SalesReport).forward(request, response);
                    break;
                case "profit-report":
                    request.getRequestDispatcher(ProfitReport).forward(request, response);
                    break;
                case "return-sales-report":
                    request.getRequestDispatcher(ReturnSalesReport).forward(request, response);
                    break;
                case "user-most-sales-report":

                    request.getRequestDispatcher(userMostSalesReport).forward(request, response);
                    break;

                case "user-more-profit-report":

                    request.getRequestDispatcher(userMoreProfitReport).forward(request, response);
                    break;

                case "best-selling-furniture-report":
                    request.getRequestDispatcher(bestSellingFurnitureReport).forward(request, response);
                    break;

                case "least-sold-furniture-report":
                    request.getRequestDispatcher(leastSoldFurnitureReport).forward(request, response);
                    break;
                case "creation-new-type-furniture": {
                    try {

                        reloadListFurnitureType(session);
                    } catch (CustomException ex) {
                        session.setAttribute("err", ex.getMessage());
                    }
                }

                request.getRequestDispatcher(creationNewTypeFurniture).forward(request, response);
                break;
                case "creation-new-user": {
                    try {
                        reloadUserList(session);
                    } catch (CustomException ex) {
                        session.setAttribute("err", ex.getMessage());
                    }
                }

                request.getRequestDispatcher(creationNewUser).forward(request, response);
                break;

                case "user-cancellation": {
                    try {
                        reloadUserList(session);
                    } catch (CustomException ex) {
                        session.setAttribute("err", ex.getMessage());
                    }
                }
                request.getRequestDispatcher(userCancellation).forward(request, response);
                break;
                case "load-txt":
                    resetTables(session);
                    request.getRequestDispatcher(loadTxt).forward(request, response);
                    break;
                case "home":
                    request.getRequestDispatcher(home).forward(request, response);
                    break;
                default:

            }
        }
    }

    userDAO userDAO = new userDAO();

    public void resetTables(HttpSession session) {
        session.setAttribute("listLoadError", null);
        session.setAttribute("listLoadSave", null);
        session.setAttribute("err", "");
        session.setAttribute("msg", "");

    }

    public void reloadUserList(HttpSession session) throws CustomException {
        session.setAttribute("msg", "");
        session.setAttribute("err", "");
        List<user> listUsers = userDAO.listAllData();
        session.setAttribute("listAllUsers", listUsers);
    }

    public void reloadUserListWithoutUserCancel(HttpSession session) throws CustomException {
        session.setAttribute("msg", "");
        session.setAttribute("err", "");
        List<user> listUsers = userDAO.listDataWithoutUserCancel();
        session.setAttribute("listAllUsersWithoutUserCancel", listUsers);
    }

    furnitureDAO furnitureDAO = new furnitureDAO();

    public void reloadListFurnitureType(HttpSession session) throws CustomException {
        session.setAttribute("msg", "");
        session.setAttribute("err", "");
        List<furniture> listFurnitureType = furnitureDAO.listAllData();
        session.setAttribute("listFurnitureType", listFurnitureType);
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
