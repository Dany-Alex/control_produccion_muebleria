package com.dxa.control_produccion_muebleria.Backend.Model.Clases;

/**
 *
 * @author Artist
 */
public class furniture {

    private String name;
    private double price;

    public furniture(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

}
