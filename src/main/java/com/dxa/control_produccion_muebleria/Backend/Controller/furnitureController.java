/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dxa.control_produccion_muebleria.Backend.Controller;

import com.dxa.control_produccion_muebleria.Backend.Model.Clases.Exceptions.CustomException;
import com.dxa.control_produccion_muebleria.Backend.Model.Clases.furniture;
import com.dxa.control_produccion_muebleria.Backend.Model.Clases.piece;
import com.dxa.control_produccion_muebleria.Backend.Model.Query.furnitureDAO;
import com.dxa.control_produccion_muebleria.Backend.Model.Query.pieceDAO;
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
@WebServlet(name = "furnitureController", urlPatterns = {"/furnitureController"})
public class furnitureController extends HttpServlet {

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
    String viewIndex = "/index.jsp";
    String path = "/View/";
    String creationNewTypeFurniture = path + "Admin/creation-new-type-furniture.jsp",
            assembleFurniture = path + "Factory/assemble-furniture.jsp",
            home = path + "adminyMenu.jsp";
    furniture furniture;
    furnitureDAO furnitureDAO;
    List<furniture> listFurnitureType = null;

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
                typeFurniture = request.getParameter("input-type-furniture"),
                priceFurniture = request.getParameter("input-price-furniture"),
                msg = "", error = "";
        furniture = new furniture();
        furnitureDAO = new furnitureDAO();
        if (action == null || action.isEmpty()) {
        } else {
            switch (action) {
                case "new-type-furniture": {

                    try {
                        furniture.setName(typeFurniture.trim());
                        furniture.setPrice(priceFurniture.trim());
                        furnitureDAO.create(furniture);
                        reloadListFurnitureType(session);
                        msg = "Pieza: " + furniture.getName() + " creada Exitosamente";

                    } catch (SQLException ex) {
                        error = ex.getMessage();

                    } catch (CustomException ex) {
                        error = ex.getMessage();
                    }
                }
                session.setAttribute("msg", msg);
                session.setAttribute("err", error);

                request.getRequestDispatcher(creationNewTypeFurniture).forward(request, response);
                break;
                case "new-assemble-forniture": {
                    try {
                        String[] checkboxListIdFurnitureAssemble = request.getParameterValues("piece-checkbox");
                        String typeFurnitureAssemble = request.getParameter("input-type-furniture-select");
                        if (checkboxListIdFurnitureAssemble != null) {
                            for (String id : checkboxListIdFurnitureAssemble) {

                                System.out.println("typeFurnitureAssemble = " + typeFurnitureAssemble);
                                System.out.println("id = " + id);
                            }
                        } else {
                            error = "Debe seleccionar algua pieza";

                        }
                        reloadListFurnitureType(session);
                        reloadPieceList(session);

                    } catch (CustomException ex) {
                        error = ex.getMessage();
                    }
                }
                session.setAttribute("msg", msg);
                session.setAttribute("err", error);
                request.getRequestDispatcher(assembleFurniture).forward(request, response);

                break;

                case "back":
                    session.setAttribute("msg", "");
                    session.setAttribute("err", "");

                    break;

                default:
                    break;

            }
        }
    }

    public void reloadListFurnitureType(HttpSession session) throws CustomException {
        session.setAttribute("msg", "");
        session.setAttribute("err", "");
        listFurnitureType = furnitureDAO.listAllData();
        session.setAttribute("listAllFurnitureType", listFurnitureType);
    }

    public void reloadPieceList(HttpSession session) throws CustomException {
        pieceDAO pieceDAO = new pieceDAO();
        List<piece> listPieces = pieceDAO.listAllData();
        session.setAttribute("listAllPieces", listPieces);
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
