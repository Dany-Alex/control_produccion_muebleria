package com.dxa.control_produccion_muebleria.Backend.Model.Clases;

/**
 *
 * @author Artist
 */
public class assemblagePiece {

    private String furniture;
    private int typePiece;

    public assemblagePiece(String furniture, int typePiece) {
        this.furniture = furniture;
        this.typePiece = typePiece;
    }

    public String getFurniture() {
        return furniture;
    }

    public void setFurniture(String furniture) {
        this.furniture = furniture;
    }

    public int getTypePiece() {
        return typePiece;
    }

    public void setTypePiece(int typePiece) {
        this.typePiece = typePiece;
    }

}
