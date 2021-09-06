package com.dxa.control_produccion_muebleria.Backend.Model.Clases;

import com.dxa.control_produccion_muebleria.Backend.Model.Clases.Exceptions.CustomException;

/**
 *
 * @author Artist
 */
public class assemblagePiece {

    private String namefurniture, typePiece;
    private int amountPieces;

    public assemblagePiece() {
    }

    public int getAmountPieces() {
        return amountPieces;
    }

    public void setAmountPieces(String amountPieces) throws CustomException {
        this.amountPieces = parseInt(amountPieces);

    }

    public int parseInt(String string) throws CustomException {
        int idTypePiece;
        try {
            idTypePiece = Integer.parseInt(string);
        } catch (NumberFormatException e) {
            throw new CustomException("Hay un problema con el dato ingresado: " + string);
        } catch (NullPointerException e) {
            throw new CustomException("Hay un problema con el dato ingresado: es nulo");
        }
        return idTypePiece;
    }

    public String getNamefurniture() {
        return namefurniture;
    }

    public void setNamefurniture(String namefurniture) throws CustomException {

        if (validateTypeChars(namefurniture)) {
            this.namefurniture = namefurniture;
        } else {
            throw new CustomException("el tipo: " + namefurniture + " no se puede crear, porque solo se pueden crear tipos que incluyan solo letras");
        }
    }

    public String getTypePiece() {
        return typePiece;
    }

    public void setTypePiece(String typePiece) throws CustomException {
        if (validateTypeChars(typePiece)) {
            this.typePiece = typePiece;
        } else {
            throw new CustomException("el tipo: " + typePiece + " no se puede crear, porque solo se pueden crear tipos que incluyan solo letras");
        }
    }

    public boolean validateTypeChars(String string) {
        boolean flang = false;
        for (int i = 0; i < string.length(); i++) {
            if (Character.isLetter(string.charAt(i))) {
                flang = true;
            } else {
                flang = false;
            }
        }
        return flang;
    }

    @Override
    public String toString() {
        return "namefurniture=" + namefurniture + ", typePiece=" + typePiece + ", amountPieces=" + amountPieces;
    }

}
