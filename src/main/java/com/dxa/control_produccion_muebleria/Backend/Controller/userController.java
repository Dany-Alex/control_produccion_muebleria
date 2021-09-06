/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dxa.control_produccion_muebleria.Backend.Controller;

import com.dxa.control_produccion_muebleria.Backend.Model.Clases.Exceptions.CustomException;
import com.dxa.control_produccion_muebleria.Backend.Model.Clases.user;
import com.dxa.control_produccion_muebleria.Backend.Model.Query.userDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
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
@WebServlet(name = "userController", urlPatterns = {"/userController"})
public class userController extends HttpServlet {

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
    user user;
    userDAO userDAO;
    String viewIndex = "/index.jsp";
    String path = "/View/Admin/";
    String creationNewUser = path + "creation-new-user.jsp",
            userCancellation = path + "user-cancellation.jsp",
            userMostSalesReport = path + "user-most-sales-report.jsp",
            userMoreProfitReport = path + "user-more-profit-report.jsp",
            home = path + "adminMenu.jsp";
    List<user> listUsers = null;

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
        HttpSession session = request.getSession();

        String action = request.getParameter("action"),
                inputTypeUserSelect = request.getParameter("input-type-user-select"),
                inputNameUser = request.getParameter("input-name-user"),
                inputPassword = request.getParameter("input-password"),
                inputConfirmPassword = request.getParameter("input-confirm-password"),
                msg = "", error = "";

        user = new user();
        userDAO = new userDAO();

        if (action == null || action.isEmpty()) {
        } else {
            switch (action) {
                case "new-user": {
                    try {
                        user.setName(inputNameUser.trim());
                        user.setPassword(inputPassword.trim(), inputConfirmPassword.trim());
                        user.setType(inputTypeUserSelect.trim());
                        userDAO.create(user);
                        reloadUserList(session);

                        msg = "Usuario: " + user.getName() + " creado Exitosamente";
                    } catch (CustomException ex) {
                        error = ex.getMessage();
                    } catch (SQLException ex) {
                        error = ex.getMessage();
                    }
                }
                session.setAttribute("msg", msg);
                session.setAttribute("err", error);
                request.getRequestDispatcher(creationNewUser).forward(request, response);
                break;

                case "update-user":

                    break;

                case "update-id-user":
                    //  request.getRequestDispatcher(adminPiece).forward(request, response);
                    break;

                case "cancel-user":

                    actionUser(session, inputNameUser, 0 + "");
                    request.getRequestDispatcher(userCancellation).forward(request, response);
                    break;

                case "restore-user":
                    actionUser(session, inputNameUser, 1 + "");
                    request.getRequestDispatcher(userCancellation).forward(request, response);
                    break;

                case "back":
                    session.setAttribute("msg", "");
                    session.setAttribute("err", "");

                    break;

                default:
                    // request.getRequestDispatcher(adminPiece).forward(request, response);
                    break;

            }
        }
    }

    public void actionUser(HttpSession session, String name, String status) {
        String msg = null, error = null;
        try {
            user.setName(name.trim());
            user.setStatus(status);
            userDAO.updateTypeUser(user);
            msg = "usuario: " + name + " cancelado exitosamente";
            reloadUserList(session);
        } catch (CustomException ex) {
            error = ex.getMessage();
        } catch (SQLException ex) {
            error = ex.getMessage();
        }

        session.setAttribute("msg", msg);
        session.setAttribute("err", error);

    }

    public void reloadUserList(HttpSession session) throws CustomException {
        session.setAttribute("msg", "");
        session.setAttribute("err", "");
        listUsers = userDAO.listAllData();
        session.setAttribute("listAllUsers", listUsers);
    }

    public void reloadUserListWithoutUserCancel(HttpSession session) throws CustomException {
        session.setAttribute("msg", "");
        session.setAttribute("err", "");
        listUsers = userDAO.listDataWithoutUserCancel();
        session.setAttribute("listAllUsersWithoutUserCancel", listUsers);
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
