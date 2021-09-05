package com.dxa.control_produccion_muebleria.Backend.Model.Clases;

import com.dxa.control_produccion_muebleria.Backend.Model.Clases.Exceptions.CustomException;
import com.dxa.control_produccion_muebleria.Backend.Model.Clases.Exceptions.exceptionPiece;

/**
 *
 * @author Artist
 */
public class typePiece {

    private String nameTypePiece;
    private int stock;

    public typePiece(String nameTypePiece, int stock) {
        this.nameTypePiece = nameTypePiece;
        this.stock = stock;
    }

    public typePiece() {
    }

    public String getNameTypePiece() {
        return nameTypePiece;
    }

    public void setNameTypePiece(String nameTypePiece) throws CustomException {
        if (validateTypeChars(nameTypePiece)) {
            this.nameTypePiece = nameTypePiece;
        } else {
            throw new CustomException("El tipo: " + nameTypePiece + " no se puede crear, porque solo se pueden crear tipos que incluyan solo letras");
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

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

}
