/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dxa.control_produccion_muebleria.Backend.Controller;

import com.dxa.control_produccion_muebleria.Backend.Model.Clases.Exceptions.CustomException;
import com.dxa.control_produccion_muebleria.Backend.Model.Clases.Exceptions.exceptionPiece;
import com.dxa.control_produccion_muebleria.Backend.Model.Clases.piece;
import com.dxa.control_produccion_muebleria.Backend.Model.Clases.sortPiece;
import com.dxa.control_produccion_muebleria.Backend.Model.Clases.typePiece;
import com.dxa.control_produccion_muebleria.Backend.Model.Query.pieceDAO;
import com.dxa.control_produccion_muebleria.Backend.Model.Query.typePieceDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
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
@WebServlet(name = "pieceController", urlPatterns = {"/pieceController"})
public class pieceController extends HttpServlet {

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
    String path = "/View/Factory/";
    String adminPiece = path + "admin-piece.jsp",
            adminUpdatePiece = path + "admin-update-piece.jsp",
            infoPiece = path + "info-piece.jsp",
            home = path + "factoryMenu.jsp";
    piece modelPiece;
    typePiece modelTypePiece;
    pieceDAO pieceDAO;
    typePieceDAO typePieceDAO;

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
                idPiece = request.getParameter("id-piece"),
                idTypePiece = request.getParameter("input-delete-id-type-piece"),
                typePiece = request.getParameter("input-type-piece-select"),
                newType = request.getParameter("input-new-type"),
                cost = request.getParameter("input-type-cost"), msg = "", error = "";
        List<typePiece> listTypePiece = null;
        List<piece> listPieces = null;
        modelPiece = new piece();
        modelTypePiece = new typePiece();
        pieceDAO = new pieceDAO();
        typePieceDAO = new typePieceDAO();

        if (action == null || action.isEmpty()) {
        } else {
            switch (action) {
                case "new-piece": {

                    try {
                        modelPiece.setType(typePiece.trim());
                        modelPiece.setCost(cost.trim());
                        pieceDAO.create(modelPiece);
                        reloadTypePieceList(session);

                        reloadTablePiece(listPieces, session);
                        listAllDataAvailable(session);

                        msg = "Pieza: " + modelPiece.getType() + " creada Exitosamente";

                    } catch (SQLException ex) {
                        error = ex.getMessage();
                    } catch (CustomException ex) {

                    }
                }
                session.setAttribute("msg", msg);
                session.setAttribute("err", error);

                request.getRequestDispatcher(adminPiece).forward(request, response);
                break;

                case "update-piece": {
                    try {
                        modelPiece = pieceDAO.searchByCode(idPiece);
                        session.setAttribute("updateModelPiece", modelPiece);
                        request.getRequestDispatcher(adminUpdatePiece).forward(request, response);
                    } catch (CustomException ex) {
                        error = ex.getMessage();
                        session.setAttribute("msg", msg);
                        session.setAttribute("err", error);
                        request.getRequestDispatcher(adminPiece).forward(request, response);
                    }
                }
                break;

                case "update-id-piece": {
                    try {
                        modelPiece.setId(request.getParameter("input-update-id-piece").trim());
                        modelPiece.setType(request.getParameter("input-update-type-piece-select").trim());
                        modelPiece.setCost(request.getParameter("input-update-type-cost").trim());
                        pieceDAO.update(modelPiece);
                        reloadTablePiece(listPieces, session);
                        listAllDataAvailable(session);

                        msg = "Pieza codigo: " + modelPiece.getId() + " modificado Exitosamente";
                    } catch (SQLException ex) {
                        error = ex.getMessage();
                    } catch (CustomException ex) {
                        error = ex.getMessage();
                    }
                }
                session.setAttribute("msg", msg);
                session.setAttribute("err", error);
                request.getRequestDispatcher(adminPiece).forward(request, response);
                break;

                case "delete-piece": {

                    try {
                        pieceDAO.delete(idPiece.trim());
                        reloadTablePiece(listPieces, session);
                        listAllDataAvailable(session);

                        msg = "Pieza codigo: " + idPiece + " eliminada Exitosamente";
                    } catch (SQLException ex) {
                        error = ex.getMessage();
                    } catch (CustomException ex) {
                        error = ex.getMessage();

                    }
                }
                session.setAttribute("msg", msg);
                session.setAttribute("err", error);

                request.getRequestDispatcher(adminPiece).forward(request, response);
                break;
                case "new-type-piece": {
                    try {
                        modelTypePiece.setNameTypePiece(newType.trim());
                        modelTypePiece.setStock(0);
                        typePieceDAO.create(modelTypePiece);
                        reloadTypePieceList(session);
                        reloadTablePiece(listPieces, session);
                        listAllDataAvailable(session);

                        msg = "Tipo de pieza: " + modelTypePiece.getNameTypePiece() + " creada Exitosamente";

                    } catch (SQLException ex) {
                        error = ex.getMessage();
                    } catch (CustomException ex) {
                        error = ex.getMessage();

                    }
                }
                session.setAttribute("msg", msg);
                session.setAttribute("err", error);

                request.getRequestDispatcher(adminPiece).forward(request, response);

                break;
                case "sort-type-piece": {
                    try {
                        String typeSort = request.getParameter("input-sort-piece").trim();
                        listTypePiece = typePieceDAO.sortTypePiece(typeSort);
                        session.setAttribute("listSortAllTypePiece", listTypePiece);
                        msg = "Ordenado  Exitoso";

                    } catch (CustomException ex) {
                        error = ex.getMessage();

                    }
                }
                session.setAttribute("msg", msg);
                session.setAttribute("err", error);

                request.getRequestDispatcher(infoPiece).forward(request, response);

                break;
                case "delete-typePiece": {

                    try {
                        typePieceDAO.delete(idTypePiece.trim());
                        reloadTypePieceList(session);
                        reloadTablePiece(listPieces, session);
                        listAllDataAvailable(session);
                        msg = "Typi de Pieza codigo: " + idTypePiece + " eliminada Exitosamente";
                    } catch (SQLException ex) {
                        error = ex.getMessage();
                    } catch (CustomException ex) {
                        error = ex.getMessage();

                    }
                }
                session.setAttribute("msg", msg);
                session.setAttribute("err", error);

                request.getRequestDispatcher(adminPiece).forward(request, response);
                break;
                case "back":
                    session.setAttribute("msg", "");
                    session.setAttribute("err", "");
                    request.getRequestDispatcher(adminPiece).forward(request, response);

                    break;

                default:
                    request.getRequestDispatcher(adminPiece).forward(request, response);
                    break;

            }
        }
    }

    private void listAllDataAvailable(HttpSession session) throws CustomException {
        pieceDAO pieceDAO = new pieceDAO();
        List<piece> listPieces = pieceDAO.listAllDataAvailable();
        session.setAttribute("listAllDataAvailable", listPieces);
    }

    public void reloadTablePiece(List<piece> listPieces, HttpSession session) throws CustomException {
        listPieces = pieceDAO.listAllData();
        session.setAttribute("listAllPieces", listPieces);
    }

    public void reloadTypePieceList(HttpSession session) {
        List<typePiece> listTypePiece = typePieceDAO.listAllData();
        session.setAttribute("listAllTypePiece", listTypePiece);
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
