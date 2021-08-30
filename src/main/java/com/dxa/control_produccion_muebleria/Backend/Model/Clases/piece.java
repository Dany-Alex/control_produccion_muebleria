package com.dxa.control_produccion_muebleria.Backend.Model.Clases;

import com.dxa.control_produccion_muebleria.Backend.Model.Clases.Exceptions.exceptionPiece;

/**
 *
 * @author Artist
 */
public class piece {

    private int id;
    private String type;
    private double cost;

    public piece(String type, double cost) {
        this.type = type;
        this.cost = cost;

    }

    public int getId() {
        return id;
    }

    public void setId(String id) throws exceptionPiece {
        this.id = parseInt(id);
    }

    public piece() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(String cost) throws exceptionPiece {
        this.cost = parseDouble(cost);
    }

    public double parseDouble(String cost) throws exceptionPiece {
        double costDouble;
        try {
            costDouble = Double.parseDouble(cost);
        } catch (NumberFormatException e) {
            throw new exceptionPiece("Hay un problema con el costo ingresado");
        } catch (NullPointerException e) {
            throw new exceptionPiece("Hay un problema con el costo ingresado: es nulo");
        }
        return costDouble;
    }

    public int parseInt(String idPiece) throws exceptionPiece {
        int id;
        try {
            id = Integer.parseInt(idPiece);
        } catch (NumberFormatException e) {
            throw new exceptionPiece("Hay un problema con el id ingresado: " + idPiece);
        } catch (NullPointerException e) {
            throw new exceptionPiece("Hay un problema con el id ingresado: es nulo");
        }
        return id;
    }
}
