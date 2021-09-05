package com.dxa.control_produccion_muebleria.Backend.Controller;

import com.dxa.control_produccion_muebleria.Backend.Model.Clases.Exceptions.CustomException;
import com.dxa.control_produccion_muebleria.Backend.Model.Clases.user;
import com.dxa.control_produccion_muebleria.Backend.Model.Query.userDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
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
@WebServlet(name = "loginController", urlPatterns = {"/loginController"})
public class loginController extends HttpServlet {

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

    String path = "/View";
    String viewIndex = "index.jsp";

    String menuSale = path + "/Sale/saleMenu.jsp",
            menuFactoy = path + "/Factory/factoryMenu.jsp",
            menuAdmin = path + "/Admin/adminMenu.jsp";

    userDAO userDAO = new userDAO();

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
        String action = request.getParameter("action");
        String msgAlert = null;

        if (action == null || action.isEmpty()) {
            response.sendRedirect(viewIndex);
        } else {
            switch (action) {

                case "login":
                    String username = request.getParameter("inputUserLogin"),
                     password = request.getParameter("inputPasswordLogin"),
                     userType = request.getParameter("inputUserTypeLogin"),
                     url = "";
                    int type = 0;
                    switch (userType) {
                        case "Financiero":
                            type = 3;
                            break;
                        case "Vendedor":
                            type = 2;
                            break;
                        case "Ensamblador":
                            type = 1;
                            break;
                        default:
                    }

                    boolean user;
                    try {
                        user = userDAO.validate(username, password, type);

                        if (user != false) {

                            if (type == 1) {
                                url = menuFactoy;
                                session.setAttribute("typeUser", "1");
                            } else if (type == 2) {
                                url = menuSale;
                                session.setAttribute("typeUser", "2");
                            } else if (type == 3) {
                                url = menuAdmin;
                                session.setAttribute("typeUser", "3");
                            }
                            msgAlert = "";
                            session.setAttribute("msgAlert", msgAlert);
                            session.setAttribute("userAttribute", username);
                            request.getRequestDispatcher(url).forward(request, response);
                        } else {
                            msgAlert = "UserNull";
                            response.sendRedirect(
                                    String.format("%s?msg=%s&error=true", viewIndex, msgAlert)
                            );
                        }
                    } catch (CustomException ex) {
                        session.setAttribute("msgAlert", ex.getMessage());
                    }
                    break;
                case "logout":
                    response.sendRedirect(
                            String.format("%s?action=%s", "logoutController", "logout")
                    );

                    System.out.println("se cerro la sesion");
                    break;
                default:
                    session.invalidate();
                    response.sendRedirect(viewIndex);
                    System.out.println("sesion existente");
                    throw new AssertionError();

            }

        }
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
