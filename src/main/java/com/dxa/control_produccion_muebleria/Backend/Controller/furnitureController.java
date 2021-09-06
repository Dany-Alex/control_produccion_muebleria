/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dxa.control_produccion_muebleria.Backend.Controller;

import com.dxa.control_produccion_muebleria.Backend.Model.Clases.Exceptions.CustomException;
import com.dxa.control_produccion_muebleria.Backend.Model.Clases.assemblagePiece;
import com.dxa.control_produccion_muebleria.Backend.Model.Clases.assembleFurniture;
import com.dxa.control_produccion_muebleria.Backend.Model.Clases.furniture;
import com.dxa.control_produccion_muebleria.Backend.Model.Clases.piece;
import com.dxa.control_produccion_muebleria.Backend.Model.Clases.user;
import com.dxa.control_produccion_muebleria.Backend.Model.Query.assemblagePieceDAO;
import com.dxa.control_produccion_muebleria.Backend.Model.Query.assembleFornitureDAO;
import com.dxa.control_produccion_muebleria.Backend.Model.Query.furnitureDAO;
import com.dxa.control_produccion_muebleria.Backend.Model.Query.pieceDAO;
import com.dxa.control_produccion_muebleria.Backend.Model.Query.userDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
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
            registerFurniture = path + "Factory/register-furniture.jsp",
            infoFurniture = path + "Factory/info-furniture.jsp",
            home = path + "adminyMenu.jsp";
    furniture furniture;
    furnitureDAO furnitureDAO = new furnitureDAO();
    List<furniture> listFurnitureType = null;
    List<assemblagePiece> listAssemblagePiece = null;
    assemblagePieceDAO assemblagePieceDAO = new assemblagePieceDAO();

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
                case "sort-date": {
                    try {
                        String typeSort = request.getParameter("input-sort-date").trim();
                        assembleFornitureDAO assembleFornitureDAO = new assembleFornitureDAO();
                        List<assembleFurniture> listSortDateAssembleFurniture = assembleFornitureDAO.sortJoinDate(typeSort);
                        session.setAttribute("listSortAllAssembleFurniture", listSortDateAssembleFurniture);
                        for (assembleFurniture furniture1 : listSortDateAssembleFurniture) {
                            System.out.println("sort-date ---" + furniture1.toString());
                        }
                        msg = "Ordenado  Exitoso";

                    } catch (CustomException ex) {
                        error = ex.getMessage();

                    }
                }
                session.setAttribute("msg", msg);
                session.setAttribute("err", error);

                request.getRequestDispatcher(infoFurniture).forward(request, response);
                break;
                case "new-assemble-piece": {
                    try {
                        String typeFurnitureAssemble = request.getParameter("input-type-furniture-select"),
                                typePieceAssemble = request.getParameter("input-type-piece-select"),
                                amountPieceAssemble = request.getParameter("input-amount-piece");
                        assemblagePiece assemblagePiece = new assemblagePiece();

                        assemblagePiece.setNamefurniture(typeFurnitureAssemble.trim());
                        assemblagePiece.setTypePiece(typePieceAssemble.trim());
                        assemblagePiece.setAmountPieces(amountPieceAssemble.trim());

                        assemblagePieceDAO.create(assemblagePiece);
                        reloadListlistAssemblagePiece(session);
                        msg = "Ensamble pieza creado Exitosamente";

                    } catch (CustomException ex) {
                        error = ex.getMessage();
                    } catch (SQLException ex) {
                        error = ex.getMessage();
                    }
                }

                session.setAttribute("msg", msg);
                session.setAttribute("err", error);
                request.getRequestDispatcher(assembleFurniture).forward(request, response);

                break;

                case "register-assemble-furniture": {

                    try {

                        String[] checkboxListIdFurnitureAssemble = request.getParameterValues("piece-checkbox");
                        String nameUser = request.getParameter("input-name-user-select"),
                                date = request.getParameter("input-date"),
                                typeFurnitureSelect = request.getParameter("input-type-furniture-select");

                        System.out.println("date = " + date);

                        if (checkboxListIdFurnitureAssemble != null) {
                            assemblagePieceDAO assemblagePieceDAO = new assemblagePieceDAO();
                            List<assemblagePiece> assemblagePieces = assemblagePieceDAO.listMaterials(typeFurnitureSelect);
                            String[] typesPieces = new String[assemblagePieces.size()];
                            int[] amountTypePiece = new int[assemblagePieces.size()];

                            for (int i = 0; i < assemblagePieces.size(); i++) {
                                typesPieces[i] = assemblagePieces.get(i).getTypePiece();
                                amountTypePiece[i] = assemblagePieces.get(i).getAmountPieces();
                            }

                            double cost = 0;
                            for (String id : checkboxListIdFurnitureAssemble) {
                                piece piece = pieceDAO.searchByCode(id);

                                for (int i = 0; i < typesPieces.length; i++) {
                                    if (typesPieces[i].equalsIgnoreCase(piece.getType())) {
                                        if (amountTypePiece[i] > 0) {
                                            cost += piece.getCost();
                                            piece.setAvailable(1 + "");
                                            pieceDAO.updateAviable(piece);

                                            amountTypePiece[i]--;
                                        } else {
                                            error = "no puede utilizar mas piezas en este mueble, solo se utilizara las necesarias para la creacion";
                                        }
                                    }
                                }
                            }

                            boolean missingPieces = true;
                            for (int i = 0; i < typesPieces.length; i++) {

                                if (amountTypePiece[i] > 0) {
                                    missingPieces = true;
                                } else {
                                    missingPieces = false;
                                }
                            }

                            if (!missingPieces) {

                                assembleFurniture assembleFurnitureModel = new assembleFurniture();
                                assembleFurnitureModel.setUser(nameUser.trim());
                                assembleFurnitureModel.setFurniture(typeFurnitureSelect.trim());
                                assembleFurnitureModel.setDate(date.trim());
                                assembleFurnitureModel.setCost(cost + "");
                                assembleFornitureDAO assembleFornitureDAO = new assembleFornitureDAO();

                                if (assembleFornitureDAO.create(assembleFurnitureModel)) {
                                    msg = "Mueble Creado exitosamente";

                                } else {
                                    rollbackPiecesAviable(checkboxListIdFurnitureAssemble, pieceDAO);
                                }

                            } else {

                                rollbackPiecesAviable(checkboxListIdFurnitureAssemble, pieceDAO);

                                error = "Debe seleccionar todas las piezas necesarias para la creacion del mueble, de lo contrarion no se procesara la creacion del mueble. \nLista para creacion de mueble: " + typeFurnitureSelect;

                                for (int i = 0; i < assemblagePieces.size(); i++) {
                                    error += String.format("\nPieza: %s Cantidad: %s", assemblagePieces.get(i).getTypePiece(), assemblagePieces.get(i).getAmountPieces());
                                }
                            }

                        } else {
                            error = "Debe seleccionar las piezas para el mueble";
                        }

                        reloadUserList(session);
                        reloadListFurnitureType(session);
                        reloadPieceList(session);

                    } catch (CustomException ex) {
                        error = ex.getMessage();
                    } catch (SQLException ex) {
                        error = ex.getMessage();
                    }
                }

                session.setAttribute("msg", msg);
                session.setAttribute("err", error);
                request.getRequestDispatcher(registerFurniture).forward(request, response);

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

    public void rollbackPiecesAviable(String[] listIdFurnitureAssemble, pieceDAO pieceDAO) throws CustomException, SQLException {
        for (String id : listIdFurnitureAssemble) {
            piece piece = pieceDAO.searchByCode(id);
            piece.setAvailable(0 + "");
            pieceDAO.update(piece);
        }
    }

    public void reloadListFurnitureType(HttpSession session) throws CustomException {
        session.setAttribute("msg", "");
        session.setAttribute("err", "");
        listFurnitureType = furnitureDAO.listAllData();
        session.setAttribute("listAllFurnitureType", listFurnitureType);
    }

    public void reloadListlistAssemblagePiece(HttpSession session) throws CustomException {
        session.setAttribute("msg", "");
        session.setAttribute("err", "");
        listAssemblagePiece = assemblagePieceDAO.listAllData();
        session.setAttribute("listAllAssemblagePiece", listAssemblagePiece);
    }
    userDAO userDAO = new userDAO();

    public void reloadUserList(HttpSession session) throws CustomException {
        List<user> listUsers = userDAO.listAllData();
        session.setAttribute("listAllUsers", listUsers);
    }
    pieceDAO pieceDAO = new pieceDAO();

    public void reloadPieceList(HttpSession session) throws CustomException {
        List<piece> listPieces = pieceDAO.listAllDataAvailable();
        session.setAttribute("listAllDataAvailable", listPieces);
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
