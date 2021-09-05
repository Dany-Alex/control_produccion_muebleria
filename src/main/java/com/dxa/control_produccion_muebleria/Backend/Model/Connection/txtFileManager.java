/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dxa.control_produccion_muebleria.Backend.Model.Connection;

import com.dxa.control_produccion_muebleria.Backend.Model.Clases.Exceptions.CustomException;
import com.dxa.control_produccion_muebleria.Backend.Model.Clases.assemblagePiece;
import com.dxa.control_produccion_muebleria.Backend.Model.Clases.furniture;
import com.dxa.control_produccion_muebleria.Backend.Model.Clases.loadTxtError;
import com.dxa.control_produccion_muebleria.Backend.Model.Clases.loadTxtSuccess;
import com.dxa.control_produccion_muebleria.Backend.Model.Clases.piece;
import com.dxa.control_produccion_muebleria.Backend.Model.Clases.user;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
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

    private ArrayList pieceList = new ArrayList();
    private ArrayList furnitureList = new ArrayList();
    private ArrayList userList = new ArrayList();
    private ArrayList assemblePieceList = new ArrayList();
    private ArrayList assembleFurnitureList = new ArrayList();

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
                                            assembleFurnitureList.add(assemblagePiece);

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
                            case "ENSAMBLAR_MUEBLE":
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
                                        if (validateDate(assemblyDate)) {
                                            String assemblyDateFormat = dateFormat(assemblyDate);
                                            System.out.println("datos: " + nameFurniture + " - " + nameUser + " - " + assemblyDateFormat);
                                        } else {
                                            System.out.println("datos: " + nameFurniture + " - " + nameUser + " -  Error con el formato de fecha dd/MM/yyyy");
                                        }
                                        identifierTmp = "";
                                        System.out.println("+----------------------[ FIN ESTRUCTURA ]----------------------+");
                                    } else {
                                        System.out.println("la estructura no es correcta");
                                        System.out.println("+----------------------[ FIN ESTRUCTURA ]----------------------+");
                                        identifierTmp = "";
                                    }
                                } else {
                                    System.out.println("la estructura no es correcta");
                                    System.out.println("+----------------------[ FIN ESTRUCTURA ]----------------------+");
                                    identifierTmp = "";
                                }
                                break;

                            default:

                        }
                    }
                }
            }
            flag = true;
        }
        return flag;
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

    public ArrayList getPieceList() {
        return pieceList;
    }

    public void setPieceList(ArrayList pieceList) {
        this.pieceList = pieceList;
    }

    public ArrayList getFurnitureList() {
        return furnitureList;
    }

    public void setFurnitureList(ArrayList furnitureList) {
        this.furnitureList = furnitureList;
    }

    public ArrayList getUserList() {
        return userList;
    }

    public void setUserList(ArrayList userList) {
        this.userList = userList;
    }

    public ArrayList getAssemblePieceList() {
        return assemblePieceList;
    }

    public void setAssemblePieceList(ArrayList assemblePieceList) {
        this.assemblePieceList = assemblePieceList;
    }

    public ArrayList getAssembleFurnitureList() {
        return assembleFurnitureList;
    }

    public void setAssembleFurnitureList(ArrayList assembleFurnitureList) {
        this.assembleFurnitureList = assembleFurnitureList;
    }

}
