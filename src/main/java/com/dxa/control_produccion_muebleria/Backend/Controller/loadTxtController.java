/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dxa.control_produccion_muebleria.Backend.Controller;

import com.dxa.control_produccion_muebleria.Backend.Model.Clases.Exceptions.CustomException;
import com.dxa.control_produccion_muebleria.Backend.Model.Connection.txtFileManager;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/**
 *
 * @author Artist
 */
@WebServlet(name = "loadTxtController", urlPatterns = {"/loadTxtController"})

@MultipartConfig
//@MultipartConfig(location = "C:\\Users\\Artist\\AppData\\Local\\Temp")
public class loadTxtController extends HttpServlet {

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

    }
    String path = "/View/Admin/";
    String loadTxt = path + "load-txt.jsp";
    txtFileManager txtFileManager;

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
        String msg = "", error = "";

        try {
            Part filePart = request.getPart("datafile");
            String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
            InputStream fileInputStream = filePart.getInputStream();
            txtFileManager = new txtFileManager(fileInputStream);
            if (txtFileManager.creatingInitData()) {
                msg = String.format("Lectura de %s finalizada con exito ", fileName);
                txtFileManager.insertsToBD();

            } else {
                error = "Carga de datos ha fallado ";
            }
        } catch (CustomException ex) {
            error = ex.getMessage();
        } catch (IOException ex) {
            error = ex.getMessage();
        } catch (SQLException ex) {
            error = ex.getMessage();
        }
        session.setAttribute("listLoadError", txtFileManager.getListErrors());
        session.setAttribute("listLoadSave", txtFileManager.getListLoadSuccess());
        session.setAttribute("msg", msg);
        session.setAttribute("err", error);
        request.getRequestDispatcher(loadTxt).forward(request, response);
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
