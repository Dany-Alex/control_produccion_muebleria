/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dxa.control_produccion_muebleria.Backend.Controller;

import com.dxa.control_produccion_muebleria.Backend.Model.Clases.Exceptions.exceptionPiece;
import com.dxa.control_produccion_muebleria.Backend.Model.Clases.piece;
import com.dxa.control_produccion_muebleria.Backend.Model.Clases.typePiece;
import com.dxa.control_produccion_muebleria.Backend.Model.Query.pieceDAO;
import com.dxa.control_produccion_muebleria.Backend.Model.Query.typePieceDAO;
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
@WebServlet(name = "factoryPrincipalController", urlPatterns = {"/factoryPrincipalController"})
public class factoryPrincipalController extends HttpServlet {

    String viewIndex = "/index.jsp";
    String path = "/View/Factory/";
    String assembleFurniture = path + "assemble-furniture.jsp",
            registerFurniture = path + "register-furniture.jsp",
            infoFurniture = path + "info-furniture.jsp",
            adminPiece = path + "admin-piece.jsp",
            infoPiece = path + "info-piece.jsp",
            home = path + "factoryMenu.jsp";

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

        String menu = request.getParameter("menu-factory");
        String action = request.getParameter("action");

        if (menu == null || menu.isEmpty()) {
            // request.getRequestDispatcher(viewIndex).forward(request, response);
        } else {
            switch (menu) {
                case "assemble-furniture":
                    request.getRequestDispatcher(assembleFurniture).forward(request, response);
                    break;
                case "register-furniture":
                    request.getRequestDispatcher(registerFurniture).forward(request, response);
                    break;
                case "info-furniture":
                    request.getRequestDispatcher(infoFurniture).forward(request, response);
                    break;
                case "admin-piece": {
                    try {
                        reloadPieceAdmin(session);
                    } catch (exceptionPiece ex) {
                        session.setAttribute("err", ex.getMessage());
                    }
                }

                request.getRequestDispatcher(adminPiece).forward(request, response);
                break;

                case "info-piece":
                    request.getRequestDispatcher(infoPiece).forward(request, response);
                    break;
                case "home":
                    request.getRequestDispatcher(home).forward(request, response);
                    break;
                default:

            }
        }

    }

    public void reloadPieceAdmin(HttpSession session) throws exceptionPiece {
        session.setAttribute("msg", "");
        session.setAttribute("err", "");

        typePieceDAO typePieceDAO = new typePieceDAO();
        pieceDAO pieceDAO = new pieceDAO();

        List<typePiece> listTypePiece = typePieceDAO.listAllData();
        List<piece> listPieces = pieceDAO.listAllData();

        session.setAttribute("listAllTypePiece", listTypePiece);
        session.setAttribute("listAllPieces", listPieces);
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
