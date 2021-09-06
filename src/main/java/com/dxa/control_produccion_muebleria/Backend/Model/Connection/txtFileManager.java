/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dxa.control_produccion_muebleria.Backend.Model.Connection;

import com.dxa.control_produccion_muebleria.Backend.Model.Clases.Exceptions.CustomException;
import com.dxa.control_produccion_muebleria.Backend.Model.Clases.assemblagePiece;
import com.dxa.control_produccion_muebleria.Backend.Model.Clases.assembleFurniture;
import com.dxa.control_produccion_muebleria.Backend.Model.Clases.client;
import com.dxa.control_produccion_muebleria.Backend.Model.Clases.furniture;
import com.dxa.control_produccion_muebleria.Backend.Model.Clases.loadTxtError;
import com.dxa.control_produccion_muebleria.Backend.Model.Clases.loadTxtSuccess;
import com.dxa.control_produccion_muebleria.Backend.Model.Clases.piece;
import com.dxa.control_produccion_muebleria.Backend.Model.Clases.typePiece;
import com.dxa.control_produccion_muebleria.Backend.Model.Clases.user;
import com.dxa.control_produccion_muebleria.Backend.Model.Query.assemblagePieceDAO;
import com.dxa.control_produccion_muebleria.Backend.Model.Query.assembleFornitureDAO;
import com.dxa.control_produccion_muebleria.Backend.Model.Query.clientDAO;
import com.dxa.control_produccion_muebleria.Backend.Model.Query.furnitureDAO;
import com.dxa.control_produccion_muebleria.Backend.Model.Query.pieceDAO;
import com.dxa.control_produccion_muebleria.Backend.Model.Query.typePieceDAO;
import com.dxa.control_produccion_muebleria.Backend.Model.Query.userDAO;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Artist
 */
public class txtFileManager {

    private InputStream file;
    private boolean open = false;//bandera de control para saber si se abrio un archivo
    private ArrayList content = new ArrayList();//almacena los registros leidos de *.txt
    private ArrayList listErrors = new ArrayList();
    private ArrayList listLoadSuccess = new ArrayList();

    private List<piece> pieceList = new ArrayList();
    private List<furniture> furnitureList = new ArrayList();
    private List<user> userList = new ArrayList();
    private List<client> clientList = new ArrayList();
    private List<assemblagePiece> assemblePieceList = new ArrayList();
    private List<assembleFurniture> assembleFurnitureList = new ArrayList();

    private int index = 0; //lleva control del registro actualmente visible

    /**
     * *
     *
     * @param file recibe un objeto InputStream que contiene los datos que
     * deseamos leer
     * @throws CustomException se lanza si en caso algo falla en la lectura del
     * archivo
     */
    public txtFileManager(InputStream file) throws CustomException {
        this.file = file;
        readFile();
    }

    /* Lee linea por linea un archivo de texto y almacena los registros
     * en un ArrayList segun orden de lectura
     * 
     */
    public boolean readFile() throws CustomException {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(file));
            this.content.clear();
            String linea;
            while ((linea = reader.readLine()) != null) {
                this.content.add(linea);
            }
            //muestra el primer registro en la interfaz
            //Siguiente();
            reader.close();
            return true;
        } catch (IOException ex) {
            throw new CustomException("Error: " + ex.getMessage());
        } finally {
            try {
                reader.close();
            } catch (IOException ex) {
                throw new CustomException("Error: " + ex.getMessage());
            }
        }
    }

    public boolean validateExistenceParentheses(int line, String currentLine, String identifier) {
        boolean flag = false;
        if (currentLine.charAt(identifier.length()) == '(' && currentLine.charAt(currentLine.length() - 1) == ')') {
            flag = true;
        } else {
            flag = false;
            String errorType = "La estructura no es correcta";
            addNewError(line, identifier, errorType, currentLine);
        }
        return flag;
    }

    /**
     * es la funcion que permite crear datos iniciales a partir de un arrayList
     * obtenido al leer el archivo txt, tambien realiza que la estructura de
     * entrada este escrita, que cumpla con el siguente formato
     * IDENTICADOR("string",int,"date")
     */
    public boolean creatingInitData() {
        boolean flag = false;

        if (this.file != null) {
            int line = 0;
            //incrementa en 1 la variable "index", si se supera el tamaño de lineas, vuelve a valor 1
            Iterator It = content.iterator();
            //comienza busqueda
            while (It.hasNext()) {
                line++;
                String currentLine = It.next().toString();//es una linea leida 
                String identifierTmp = "";
                String[] structureTmp;

                for (int i = 0; i < currentLine.length(); i++) {
                    char charTmp = currentLine.charAt(i);
                    if (Character.isLetter(charTmp) || charTmp == '_') {
                        identifierTmp += charTmp;
                        switch (identifierTmp) {
                            case "USUARIO": {
                                try {

                                    System.out.println("+------------------< ESTRUCTURA ENCONTRADA >-------------------+");

                                    System.out.println("Identificador: " + identifierTmp);

                                    if (currentLine.charAt(identifierTmp.length()) == '(' && currentLine.charAt(currentLine.length() - 1) == ')') {

                                        structureTmp = currentLine.substring(i + 2, currentLine.length() - 1).split(",");

                                        if ((structureTmp[0].charAt(0) == '"' && structureTmp[0].charAt(structureTmp[0].length() - 1) == '"')
                                                && (structureTmp[1].charAt(0) == '"' && structureTmp[1].charAt(structureTmp[1].length() - 1) == '"')) {

                                            String nameUser = structureTmp[0].replace("\"", ""),
                                                    passUser = structureTmp[1].replace("\"", "");
                                            int typeUser = Integer.parseInt(structureTmp[2]);
                                            user user = new user();
                                            user.setName(nameUser);
                                            user.setPassword(passUser);
                                            user.setType(typeUser + "");
                                            userList.add(user);

                                            addNewSuccess(line, identifierTmp, user.toString());

                                        } else {
                                            String errorType = "La estructura no es correcta";
                                            addNewError(line, identifierTmp, errorType, currentLine);
                                        }
                                    } else {
                                        String errorType = "La estructura no es correcta";
                                        addNewError(line, identifierTmp, errorType, currentLine);
                                    }

                                } catch (CustomException ex) {
                                    addNewError(line, identifierTmp, ex.getMessage(), currentLine);
                                } finally {
                                    identifierTmp = "";
                                    System.out.println("+----------------------[ FIN ESTRUCTURA ]----------------------+");
                                }
                            }

                            break;
                            case "PIEZA": {
                                try {

                                    System.out.println("+------------------< ESTRUCTURA ENCONTRADA >-------------------+");

                                    System.out.println("Identificador: " + identifierTmp);
                                    if (currentLine.charAt(identifierTmp.length()) == '(' && currentLine.charAt(currentLine.length() - 1) == ')') {

                                        structureTmp = currentLine.substring(i + 2, currentLine.length() - 1).split(",");
                                        if ((structureTmp[0].charAt(0) == '"' && structureTmp[0].charAt(structureTmp[0].length() - 1) == '"')) {

                                            String typePiece = structureTmp[0].replace("\"", "");
                                            double costPiece = Double.parseDouble(structureTmp[1]);

                                            piece piece = new piece();
                                            piece.setType(typePiece);
                                            piece.setCost(costPiece + "");
                                            pieceList.add(piece);

                                            addNewSuccess(line, identifierTmp, piece.toString());

                                        } else {
                                            String errorType = "La estructura no es correcta";
                                            addNewError(line, identifierTmp, errorType, currentLine);
                                        }
                                    } else {
                                        String errorType = "La estructura no es correcta";
                                        addNewError(line, identifierTmp, errorType, currentLine);
                                    }

                                } catch (CustomException ex) {
                                    addNewError(line, identifierTmp, ex.getMessage(), currentLine);
                                } finally {
                                    identifierTmp = "";
                                    System.out.println("+----------------------[ FIN ESTRUCTURA ]----------------------+");
                                }
                            }

                            break;
                            case "MUEBLE": {
                                try {

                                    System.out.println("+------------------< ESTRUCTURA ENCONTRADA >-------------------+");

                                    System.out.println("Identificador: " + identifierTmp);
                                    if (currentLine.charAt(identifierTmp.length()) == '(' && currentLine.charAt(currentLine.length() - 1) == ')') {

                                        structureTmp = currentLine.substring(i + 2, currentLine.length() - 1).split(",");
                                        if ((structureTmp[0].charAt(0) == '"' && structureTmp[0].charAt(structureTmp[0].length() - 1) == '"')) {

                                            String nameFurniture = structureTmp[0].replace("\"", "");
                                            double priceFurniture = Double.parseDouble(structureTmp[1]);

                                            furniture furniture = new furniture();
                                            furniture.setName(nameFurniture);
                                            furniture.setPrice(priceFurniture + "");
                                            furnitureList.add(furniture);

                                            addNewSuccess(line, identifierTmp, furniture.toString());

                                        } else {
                                            String errorType = "La estructura no es correcta";
                                            addNewError(line, identifierTmp, errorType, currentLine);
                                        }
                                    } else {
                                        String errorType = "La estructura no es correcta";
                                        addNewError(line, identifierTmp, errorType, currentLine);
                                    }

                                } catch (CustomException ex) {
                                    addNewError(line, identifierTmp, ex.getMessage(), currentLine);
                                } finally {
                                    identifierTmp = "";
                                    System.out.println("+----------------------[ FIN ESTRUCTURA ]----------------------+");
                                }
                            }

                            break;
                            case "ENSAMBLE_PIEZAS": {
                                try {
                                    System.out.println("+------------------< ESTRUCTURA ENCONTRADA >-------------------+");

                                    System.out.println("Identificador: " + identifierTmp);
                                    if (currentLine.charAt(identifierTmp.length()) == '(' && currentLine.charAt(currentLine.length() - 1) == ')') {

                                        structureTmp = currentLine.substring(i + 2, currentLine.length() - 1).split(",");

                                        if ((structureTmp[0].charAt(0) == '"' && structureTmp[0].charAt(structureTmp[0].length() - 1) == '"')
                                                && (structureTmp[1].charAt(0) == '"' && structureTmp[1].charAt(structureTmp[1].length() - 1) == '"')) {

                                            String nameFurniture = structureTmp[0].replace("\"", ""),
                                                    typePiece = structureTmp[1].replace("\"", "");
                                            int amountPieces = Integer.parseInt(structureTmp[2]);

                                            assemblagePiece assemblagePiece = new assemblagePiece();

                                            assemblagePiece.setNamefurniture(nameFurniture);
                                            assemblagePiece.setTypePiece(typePiece);
                                            assemblagePiece.setAmountPieces(amountPieces + "");
                                            assemblePieceList.add(assemblagePiece);

                                            addNewSuccess(line, identifierTmp, assemblagePiece.toString());

                                        } else {
                                            String errorType = "La estructura no es correcta";
                                            addNewError(line, identifierTmp, errorType, currentLine);
                                        }
                                    } else {
                                        String errorType = "La estructura no es correcta";
                                        addNewError(line, identifierTmp, errorType, currentLine);
                                    }

                                } catch (CustomException ex) {
                                    addNewError(line, identifierTmp, ex.getMessage(), currentLine);
                                } finally {
                                    identifierTmp = "";
                                    System.out.println("+----------------------[ FIN ESTRUCTURA ]----------------------+");
                                }
                            }
                            break;
                            case "ENSAMBLAR_MUEBLE": {
                                try {
                                    System.out.println("+------------------< ESTRUCTURA ENCONTRADA >-------------------+");

                                    System.out.println("Identificador: " + identifierTmp);
                                    if (currentLine.charAt(identifierTmp.length()) == '(' && currentLine.charAt(currentLine.length() - 1) == ')') {

                                        structureTmp = currentLine.substring(i + 2, currentLine.length() - 1).split(",");

                                        if ((structureTmp[0].charAt(0) == '"' && structureTmp[0].charAt(structureTmp[0].length() - 1) == '"')
                                                && (structureTmp[1].charAt(0) == '"' && structureTmp[1].charAt(structureTmp[1].length() - 1) == '"')
                                                && (structureTmp[2].charAt(0) == '"' && structureTmp[2].charAt(structureTmp[2].length() - 1) == '"')) {

                                            String nameFurniture = structureTmp[0].replace("\"", ""),
                                                    nameUser = structureTmp[1].replace("\"", ""),
                                                    assemblyDate = structureTmp[2].replace("\"", "");

                                            assembleFurniture assembleFurniture = new assembleFurniture();

                                            assembleFurniture.setFurniture(nameFurniture);
                                            assembleFurniture.setUser(nameUser);
                                            assembleFurniture.setDate(assemblyDate + "");
                                            assembleFurnitureList.add(assembleFurniture);

                                            addNewSuccess(line, identifierTmp, assembleFurniture.toString());

                                        } else {
                                            String errorType = "La estructura no es correcta";
                                            addNewError(line, identifierTmp, errorType, currentLine);
                                        }
                                    } else {
                                        String errorType = "La estructura no es correcta";
                                        addNewError(line, identifierTmp, errorType, currentLine);
                                    }

                                } catch (CustomException ex) {
                                    addNewError(line, identifierTmp, ex.getMessage(), currentLine);
                                } finally {
                                    identifierTmp = "";
                                    System.out.println("+----------------------[ FIN ESTRUCTURA ]----------------------+");
                                }
                            }
                            break;

                            case "CLIENTE": {
                                try {
                                    System.out.println("+------------------< ESTRUCTURA ENCONTRADA >-------------------+");

                                    System.out.println("Identificador: " + identifierTmp);
                                    if (currentLine.charAt(identifierTmp.length()) == '(' && currentLine.charAt(currentLine.length() - 1) == ')') {

                                        structureTmp = currentLine.substring(i + 2, currentLine.length() - 1).split(",");

                                        if (structureTmp.length == 3) {
                                            if ((structureTmp[0].charAt(0) == '"' && structureTmp[0].charAt(structureTmp[0].length() - 1) == '"')
                                                    && (structureTmp[1].charAt(0) == '"' && structureTmp[1].charAt(structureTmp[1].length() - 1) == '"')
                                                    && (structureTmp[2].charAt(0) == '"' && structureTmp[2].charAt(structureTmp[2].length() - 1) == '"')) {

                                                String name = structureTmp[0].replace("\"", ""),
                                                        nit = structureTmp[1].replace("\"", ""),
                                                        direction = structureTmp[2].replace("\"", "");

                                                client client = new client();
                                                client.setNit(nit);
                                                client.setName(name);
                                                client.setAddress(direction);
                                                clientList.add(client);

                                                addNewSuccess(line, identifierTmp, client.toString());
                                            } else {
                                                String errorType = "La estructura no es correcta";
                                                addNewError(line, identifierTmp, errorType, currentLine);
                                            }

                                        } else if (structureTmp.length == 5) {
                                            if ((structureTmp[0].charAt(0) == '"' && structureTmp[0].charAt(structureTmp[0].length() - 1) == '"')
                                                    && (structureTmp[1].charAt(0) == '"' && structureTmp[1].charAt(structureTmp[1].length() - 1) == '"')
                                                    && (structureTmp[2].charAt(0) == '"' && structureTmp[2].charAt(structureTmp[2].length() - 1) == '"')
                                                    && (structureTmp[3].charAt(0) == '"' && structureTmp[3].charAt(structureTmp[3].length() - 1) == '"')
                                                    && (structureTmp[4].charAt(0) == '"' && structureTmp[4].charAt(structureTmp[4].length() - 1) == '"')) {

                                                String name = structureTmp[0].replace("\"", ""),
                                                        nit = structureTmp[1].replace("\"", ""),
                                                        direction = structureTmp[2].replace("\"", ""),
                                                        departamento = structureTmp[1].replace("\"", ""),
                                                        municipio = structureTmp[2].replace("\"", "");

                                                client client = new client();
                                                client.setNit(nit);
                                                client.setName(name);
                                                client.setAddress(direction);
                                                client.setDepartamento(departamento);
                                                client.setMunicipio(municipio);
                                                clientList.add(client);

                                                addNewSuccess(line, identifierTmp, client.toString());
                                            } else {
                                                String errorType = "La estructura no es correcta comillas";
                                                addNewError(line, identifierTmp, errorType, currentLine);
                                            }

                                        }
                                    } else {
                                        String errorType = "La estructura no es correcta Parentesis";
                                        addNewError(line, identifierTmp, errorType, currentLine);
                                    }

                                } catch (CustomException ex) {
                                    addNewError(line, identifierTmp, ex.getMessage(), currentLine);
                                } finally {
                                    identifierTmp = "";
                                    System.out.println("+----------------------[ FIN ESTRUCTURA ]----------------------+");
                                }
                            }

                            default:

                        }
                    }
                }
            }
            flag = true;
        }
        return flag;
    }

    public void insertsToBD() throws SQLException, CustomException {

        try {
            for (user user : userList) {
                userDAO userDAO = new userDAO();
                userDAO.create(user);

            }
        } catch (SQLException ex) {
            throw new CustomException("Error: " + ex.getMessage());
        } catch (CustomException ex) {
            throw new CustomException("Error: " + ex.getMessage());
        } finally {
            try {
                for (client client : clientList) {
                    clientDAO clientDAO = new clientDAO();
                    clientDAO.create(client);
                }
            } catch (SQLException ex) {
                throw new CustomException("Error: " + ex.getMessage());
            } catch (CustomException ex) {
                throw new CustomException("Error: " + ex.getMessage());
            } finally {
                try {
                    for (piece piece : pieceList) {
                        typePieceDAO typePieceDAO = new typePieceDAO();
                        typePiece typePiece = new typePiece();
                        typePiece.setNameTypePiece(piece.getType());
                        if (typePieceDAO.create(typePiece)) {
                            pieceDAO pieceDAO = new pieceDAO();
                            pieceDAO.create(piece);
                        }
                    }
                } catch (SQLException ex) {
                    throw new CustomException(" Error: " + ex.getMessage());
                } catch (CustomException ex) {
                    throw new CustomException(" Error: " + ex.getMessage());
                } finally {

                }
            }
        }

        for (furniture furniture : furnitureList) {
            furnitureDAO furnitureDAO = new furnitureDAO();
            furnitureDAO.create(furniture);
        }
        for (assemblagePiece piece : assemblePieceList) {
            assemblagePieceDAO assemblagePieceDAO = new assemblagePieceDAO();
            assemblagePieceDAO.create(piece);
        }
        for (assembleFurniture furniture : assembleFurnitureList) {
            assembleFornitureDAO assembleFornitureDAO = new assembleFornitureDAO();
            assembleFornitureDAO.create(furniture);
        }

    }

    public void addNewSuccess(int line, String identifier, String data) {
        loadTxtSuccess loadTxtSuccess = new loadTxtSuccess();
        loadTxtSuccess.setLine(line);
        loadTxtSuccess.setId(identifier);
        loadTxtSuccess.setData(data);
        listLoadSuccess.add(loadTxtSuccess);
        System.out.println(data);
    }

    public void addNewError(int line, String identifier, String errorType, String currentLine) {
        loadTxtError loadTxtError = new loadTxtError();
        loadTxtError.setLine(line);
        loadTxtError.setId(identifier);
        loadTxtError.setErrorType(errorType);
        loadTxtError.setData(currentLine);
        listErrors.add(loadTxtError);
        System.out.println(errorType);
        System.out.println("+----------------------[ FIN ESTRUCTURA ]----------------------+");
        identifier = "";

    }

    public void insertInitData() {

    }

    /**
     *
     * @param date Es la fecha obtenida en unda de las lineas del archivo txt,
     * con formato dd/MM/yyyy
     * @return retorna true si la fecha recibida en el parametro es compatible
     * con el formato dd/MM/yyyy, de lo contrario retorna un false
     */
    public static boolean validateDate(String date) {
        try {
            SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
            formatoFecha.setLenient(false);
            formatoFecha.parse(date);
        } catch (Exception e) {
            //System.out.println("e = " + e);
            return false;
        }
        return true;
    }

    /**
     * @param date Es la fecha obtenida en unda de las lineas del archivo txt,
     * con formato dd/MM/yyyy
     * @return retorna una fecha en formato yyyy-MM-dd, que es el formato
     * compatible con MYSQL
     */
    public String dateFormat(String date) {
        return ((date.substring(6)).concat(date.substring(2, 6)).concat(date.substring(0, 2))).replace('/', '-');
    }

    public ArrayList getListErrors() {
        return listErrors;
    }

    public void setListErrors(ArrayList listErrors) {
        this.listErrors = listErrors;
    }

    public ArrayList getListLoadSuccess() {
        return listLoadSuccess;
    }

    public void setListLoadSuccess(ArrayList listLoadSuccess) {
        this.listLoadSuccess = listLoadSuccess;
    }

    public void setAssembleFurnitureList(ArrayList assembleFurnitureList) {
        this.assembleFurnitureList = assembleFurnitureList;
    }

}
