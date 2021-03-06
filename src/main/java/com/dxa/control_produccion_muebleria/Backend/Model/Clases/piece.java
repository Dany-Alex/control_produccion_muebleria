package com.dxa.control_produccion_muebleria.Backend.Model.Clases;

import com.dxa.control_produccion_muebleria.Backend.Model.Clases.Exceptions.CustomException;
import com.dxa.control_produccion_muebleria.Backend.Model.Clases.Exceptions.exceptionPiece;

/**
 *
 * @author Artist
 */
public class piece {

    private int id, available;
    private String type;
    private double cost;

    public piece(String type, double cost) {
        this.type = type;
        this.cost = cost;

    }

    public int getAvailable() {
        return available;
    }

    public void setAvailable(String available) throws CustomException {
        this.available = parseInt(available);
    }

    public int getId() {
        return id;
    }

    public void setId(String id) throws CustomException {
        this.id = parseInt(id);
    }

    public piece() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) throws CustomException {
        if (validateTypeChars(type)) {
            this.type = type;
        } else {
            throw new CustomException("El tipo: " + type + " no se puede crear, porque solo se pueden crear tipos que incluyan solo letras");
        }

    }

    public double getCost() {
        return cost;
    }

    public void setCost(String cost) throws CustomException {
        this.cost = parseDouble(cost);
    }

    @Override
    public String toString() {
        return "type=" + type + ", cost=" + cost;
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

    public double parseDouble(String cost) throws CustomException {
        double costDouble;
        try {
            costDouble = Double.parseDouble(cost);
        } catch (NumberFormatException e) {
            throw new CustomException("Hay un problema con el costo ingresado " + cost);
        } catch (NullPointerException e) {
            throw new CustomException("Hay un problema con el costo ingresado: es nulo");
        }
        return costDouble;
    }

    public int parseInt(String idPiece) throws CustomException {
        int id;
        try {
            id = Integer.parseInt(idPiece);
        } catch (NumberFormatException e) {
            throw new CustomException("Hay un problema con el dato ingresado: " + idPiece);
        } catch (NullPointerException e) {
            throw new CustomException("Hay un problema con el dato ingresado: es nulo");
        }
        return id;
    }
}
