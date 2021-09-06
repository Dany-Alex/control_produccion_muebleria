package com.dxa.control_produccion_muebleria.Backend.Model.Clases;

import com.dxa.control_produccion_muebleria.Backend.Model.Clases.Exceptions.CustomException;
import static com.dxa.control_produccion_muebleria.Backend.Model.Connection.txtFileManager.validateDate;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Artist
 */
public class assembleFurniture {

    private String user, furniture;
    double cost;
    private Date date;

    public assembleFurniture() {
    }

    public assembleFurniture(String user, String furniture, double cost, Date date) {
        this.user = user;
        this.furniture = furniture;
        this.cost = cost;
        this.date = date;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) throws CustomException {

        if (validateUserNameChars(user)) {
            this.user = user;
        } else {
            throw new CustomException("el usuario: " + user + " no se puede crear, porque solo se pueden crear usuarios de indole alfa-numerico");
        }
    }

    public boolean validateUserNameChars(String string) {
        boolean flang = false;
        for (int i = 0; i < string.length(); i++) {
            if (Character.isDigit(string.charAt(i))) {
                flang = true;
            } else if (Character.isLetter(string.charAt(i))) {
                flang = true;
            } else {
                flang = false;
            }
        }
        return flang;
    }

    public String getFurniture() {
        return furniture;
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

    public void setFurniture(String furniture) throws CustomException {

        if (validateNameChars(furniture)) {
            this.furniture = furniture;

        } else {
            throw new CustomException("El nombre: " + furniture + " no se puede crear, porque solo se pueden crear nombres que incluyan solo letras");
        }
    }

    public double getCost() {
        return cost;
    }

    public void setCost(String cost) throws CustomException {
        this.cost = parseDouble(cost);
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

    public Date getDate() {

        return date;
    }

    public java.sql.Date getDateSQL() {
        return new java.sql.Date(date.getTime());
    }

    public void setDate(String date) throws CustomException {
        this.date = validateDate(date);
    }

    /**
     *
     * @param date Es la fecha obtenida en unda de las lineas del archivo txt,
     * con formato dd/MM/yyyy
     * @return retorna true si la fecha recibida en el parametro es compatible
     * con el formato dd/MM/yyyy, de lo contrario retorna un false
     */
    public Date validateDate(String date) throws CustomException {
        try {

            SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
            formatoFecha.setLenient(false);
            return (Date) formatoFecha.parse(dateFormat(date));
        } catch (Exception e) {
            System.out.println("e " + e);
            throw new CustomException("Error con el formato de fecha " + e.getMessage());
        }
    }

    /**
     * @param date Es la fecha obtenida en unda de las lineas del archivo txt,
     * con formato dd/MM/yyyy
     * @return retorna una fecha en formato yyyy-MM-dd, que es el formato
     * compatible con MYSQL
     */
    public String dateFormat(String date) {
        String string;
        if (!date.contains("/")) {
            string = date;
        } else {
            string = ((date.substring(6)).concat(date.substring(2, 6)).concat(date.substring(0, 2))).replace('/', '-');
        }
        return string;
    }

    @Override
    public String toString() {
        return "user=" + user + ", furniture=" + furniture + ", cost=" + cost + ", date=" + date;
    }

}
