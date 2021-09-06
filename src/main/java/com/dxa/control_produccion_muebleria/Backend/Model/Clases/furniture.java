package com.dxa.control_produccion_muebleria.Backend.Model.Clases;

import com.dxa.control_produccion_muebleria.Backend.Model.Clases.Exceptions.CustomException;

/**
 *
 * @author Artist
 */
public class furniture {

    private String name;
    private double price;
    private int id, vendido;

    public furniture(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public furniture() {
    }

    public int getVendido() {
        return vendido;
    }

    public void setVendido(int vendido) {
        this.vendido = vendido;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws CustomException {
        if (validateNameChars(name)) {
            this.name = name;

        } else {
            throw new CustomException("El nombre: " + name + " no se puede crear, porque solo se pueden crear nombres que incluyan solo letras");
        }
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(String price) throws CustomException {

        this.price = parseDouble(price);
    }

    @Override
    public String toString() {
        return "name=" + name + ", price=" + price;
    }

    public double parseDouble(String price) throws CustomException {
        double priceDouble;
        try {
            priceDouble = Double.parseDouble(price);
        } catch (NumberFormatException e) {
            throw new CustomException("Hay un problema con el precio ingresado " + price);
        } catch (NullPointerException e) {
            throw new CustomException("Hay un problema con el precio ingresado: es nulo");
        }
        return priceDouble;
    }

    public boolean validateNameChars(String string) {
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
}
