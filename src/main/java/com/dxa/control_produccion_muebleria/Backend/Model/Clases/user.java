package com.dxa.control_produccion_muebleria.Backend.Model.Clases;

import com.dxa.control_produccion_muebleria.Backend.Model.Clases.Exceptions.CustomException;
import com.dxa.control_produccion_muebleria.Backend.Model.Clases.Exceptions.exceptionPiece;

/**
 *
 * @author Artist
 */
public class user {

    private String name, password;
    private int type, status;

    public user(String name, String password, int type) {
        this.name = name;
        this.password = password;
        this.type = type;
    }

    public user() {
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(String status) throws CustomException {
        this.status = parseInt(status);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws CustomException {
        if (validateUserNameChars(name)) {
            this.name = name;
        } else {
            throw new CustomException("el usuario: " + name + " no se puede crear, porque solo se pueden crear usuarios de indole alfa-numerico");
        }
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password, String passwordConfirm) throws CustomException {
        if (comparePass(password, passwordConfirm)) {
            this.password = password;
        } else {
            throw new CustomException("Las contraseñas deben coincidir");
        }
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getType() {
        return type;
    }

    public void setType(String type) throws CustomException {
        this.type = parseInt(type);
    }

    public boolean comparePass(String password, String passwordConfirm) {
        boolean flang = false;
        if (password.equalsIgnoreCase(passwordConfirm)) {
            flang = true;
        } else {
            flang = false;
        }
        return flang;
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

    public int parseInt(String number) throws CustomException {
        try {
            return Integer.parseInt(number);
        } catch (NumberFormatException e) {
            throw new CustomException("Hay un problema con el id ingresado: " + number);
        } catch (NullPointerException e) {
            throw new CustomException("Hay un problema con el id ingresado: es nulo");
        }
    }

    @Override
    public String toString() {
        return "name=" + name + ", password=" + password + ", type=" + type;
    }

}
