package com.dxa.control_produccion_muebleria.Backend.Model.Clases;

import java.sql.Date;

/**
 *
 * @author Artist
 */
public class assembleFurniture {

    private String user, furniture;
    double cost;
    private Date date;

    public assembleFurniture(String user, String furniture, double cost, Date date) {
        this.user = user;
        this.furniture = furniture;
        this.cost = cost;
        this.date = date;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getFurniture() {
        return furniture;
    }

    public void setFurniture(String furniture) {
        this.furniture = furniture;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
