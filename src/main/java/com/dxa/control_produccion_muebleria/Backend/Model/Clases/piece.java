package com.dxa.control_produccion_muebleria.Backend.Model.Clases;

/**
 *
 * @author Artist
 */
public class piece {

    private String type;
    private double cost;

    public piece(String type, double cost, int stock) {
        this.type = type;
        this.cost = cost;

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

    public void setCost(double cost) {
        this.cost = cost;
    }

}
