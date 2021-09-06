/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dxa.control_produccion_muebleria.Backend.Controller;

import com.dxa.control_produccion_muebleria.Backend.Model.Clases.Exceptions.CustomException;
import com.dxa.control_produccion_muebleria.Backend.Model.Clases.Exceptions.exceptionPiece;
import com.dxa.control_produccion_muebleria.Backend.Model.Clases.assemblagePiece;
import com.dxa.control_produccion_muebleria.Backend.Model.Clases.assembleFurniture;
import com.dxa.control_produccion_muebleria.Backend.Model.Clases.furniture;
import com.dxa.control_produccion_muebleria.Backend.Model.Clases.piece;
import com.dxa.control_produccion_muebleria.Backend.Model.Clases.sortPiece;
import com.dxa.control_produccion_muebleria.Backend.Model.Clases.typePiece;
import com.dxa.control_produccion_muebleria.Backend.Model.Clases.user;
import com.dxa.control_produccion_muebleria.Backend.Model.Query.assemblagePieceDAO;
import com.dxa.control_produccion_muebleria.Backend.Model.Query.assembleFornitureDAO;
import com.dxa.control_produccion_muebleria.Backend.Model.Query.furnitureDAO;
import com.dxa.control_produccion_muebleria.Backend.Model.Query.pieceDAO;
import com.dxa.control_produccion_muebleria.Backend.Model.Query.typePieceDAO;
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
    private typePieceDAO typePieceDAO = new typePieceDAO();

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
                case "assemble-furniture": {
                    try {
                        session.setAttribute("msg", "");
                        session.setAttribute("err", "");
                        reloadPieceAdmin(session);
                        reloadFurnitureTypeList(session);
                        reloadListlistAssemblagePiece(session);
                    } catch (CustomException ex) {
                        session.setAttribute("err", ex.getMessage());
                    }
                }
                request.getRequestDispatcher(assembleFurniture).forward(request, response);
                break;

                case "register-furniture": {
                    try {

                        session.setAttribute("msg", "");
                        session.setAttribute("err", "");

                        listAllDataAvailable(session);
                        reloadFurnitureTypeList(session);
                        reloadUserList(session);
                    } catch (CustomException ex) {
                        session.setAttribute("err", ex.getMessage());
                    }
                }

                request.getRequestDispatcher(registerFurniture).forward(request, response);
                break;

                case "info-furniture": {
                    try {
                        reloadSortDateAssembleFurnitureList(session);
                    } catch (CustomException ex) {
                        session.setAttribute("err", ex.getMessage());
                    }
                }
                request.getRequestDispatcher(infoFurniture).forward(request, response);
                break;

                case "admin-piece": {
                    try {
                        reloadPieceAdmin(session);
                        listAllDataAvailable(session);

                    } catch (CustomException ex) {
                        session.setAttribute("err", ex.getMessage());
                    }
                }

                request.getRequestDispatcher(adminPiece).forward(request, response);
                break;

                case "info-piece": {
                    try {
                        reloadSortPieceList(session);
                    } catch (CustomException ex) {
                        session.setAttribute("err", ex.getMessage());
                    }
                }
                request.getRequestDispatcher(infoPiece).forward(request, response);
                break;

                case "home":
                    request.getRequestDispatcher(home).forward(request, response);
                    break;
                default:

            }
        }

    }

    public void reloadPieceAdmin(HttpSession session) throws CustomException {
        session.setAttribute("msg", "");
        session.setAttribute("err", "");

        List<typePiece> listTypePiece = typePieceDAO.listAllData();
        session.setAttribute("listAllTypePiece", listTypePiece);
        reloadPieceList(session);
    }

    public void reloadPieceList(HttpSession session) throws CustomException {
        pieceDAO pieceDAO = new pieceDAO();
        List<piece> listPieces = pieceDAO.listAllData();
        session.setAttribute("listAllPieces", listPieces);
    }

    public void reloadUserList(HttpSession session) throws CustomException {
        userDAO userDAO = new userDAO();
        List<user> listUsers = userDAO.listAllData();
        session.setAttribute("listAllUsers", listUsers);
    }

    private void listAllDataAvailable(HttpSession session) throws CustomException {
        pieceDAO pieceDAO = new pieceDAO();
        List<piece> listPieces = pieceDAO.listAllDataAvailable();
        session.setAttribute("listAllDataAvailable", listPieces);
    }

    public void reloadListlistAssemblagePiece(HttpSession session) throws CustomException {
        assemblagePieceDAO assemblagePieceDAO = new assemblagePieceDAO();
        List<assemblagePiece> listAssemblagePiece = assemblagePieceDAO.listAllData();
        session.setAttribute("listAllAssemblagePiece", listAssemblagePiece);
    }

    public void reloadFurnitureTypeList(HttpSession session) throws CustomException {
        furnitureDAO furnitureDAO = new furnitureDAO();
        List<furniture> listFurnitureType = furnitureDAO.listAllData();
        session.setAttribute("listAllFurnitureType", listFurnitureType);
    }

    public void reloadSortPieceList(HttpSession session) throws CustomException {
        session.setAttribute("msg", "");
        session.setAttribute("err", "");
        List<typePiece> listSortPiece = typePieceDAO.sortTypePiece("MyMn");
        session.setAttribute("listSortAllTypePiece", listSortPiece);
    }

    public void reloadSortDateAssembleFurnitureList(HttpSession session) throws CustomException {
        session.setAttribute("msg", "");
        session.setAttribute("err", "");
        assembleFornitureDAO assembleFornitureDAO = new assembleFornitureDAO();
        List<assembleFurniture> listSortDateAssembleFurniture = assembleFornitureDAO.sortJoinDate("MyMn");
        session.setAttribute("listSortAllAssembleFurniture", listSortDateAssembleFurniture);
        for (assembleFurniture furniture1 : listSortDateAssembleFurniture) {
            System.out.println("reloadSortDateAssembleFurnitureList ---" + furniture1.toString());
        }
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
