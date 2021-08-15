package com.dxa.control_produccion_muebleria.Backend.Model.Clases;

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

    public String getNameTypePiece() {
        return nameTypePiece;
    }

    public void setNameTypePiece(String nameTypePiece) {
        this.nameTypePiece = nameTypePiece;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

}
