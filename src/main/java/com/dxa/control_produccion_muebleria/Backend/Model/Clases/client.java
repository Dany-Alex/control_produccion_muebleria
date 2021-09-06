package com.dxa.control_produccion_muebleria.Backend.Model.Clases;

import com.dxa.control_produccion_muebleria.Backend.Model.Clases.Exceptions.CustomException;

/**
 *
 * @author Artist
 */
public class client {

    private String name, address, municipio, departamento, nit;

    public client(String name, String nit, String address, String municipio, String departamento) {
        this.nit = nit;
        this.name = name;
        this.address = address;
        this.municipio = municipio;
        this.departamento = departamento;
    }

    public client(String name, String nit, String address) {
        this.nit = nit;
        this.name = name;
        this.address = address;
    }

    public client() {
    }

    public String getNit() {
        return nit;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) throws CustomException {
        if (validateUserNameChars(municipio)) {
            this.municipio = municipio;
        } else {
            throw new CustomException("el usuario: " + municipio + " no se puede crear, porque solo se permiten datos con letras");
        }
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) throws CustomException {
        if (validateUserNameChars(departamento)) {
            this.departamento = departamento;
        } else {
            throw new CustomException("el usuario: " + departamento + " no se puede crear, porque solo se permiten datos con letras");
        }

    }

    public void setNit(String nit) throws CustomException {
        if (validateUserNameChars(nit)) {
            this.nit = nit;
        } else {
            throw new CustomException("el usuario: " + nit + " no se puede crear, porque solo se pueden crear usuarios de indole alfa-numerico");
        }
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

    public boolean validateLettersChars(String string) {
        boolean flang = false;
        for (int i = 0; i < string.length(); i++) {
            if (Character.isDigit(string.charAt(i))) {
                flang = true;
            } else {
                flang = false;
            }
        }
        return flang;
    }

    public boolean validateAdressChars(String string) {
        boolean flang = false;
        for (int i = 0; i < string.length(); i++) {
            if (Character.isDigit(string.charAt(i))) {
                flang = true;
            } else if (string.charAt(i) == '-') {
                flang = true;
            } else if (Character.isLetter(string.charAt(i))) {
                flang = true;
            } else {
                flang = false;
            }
        }
        return flang;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) throws CustomException {
        if (validateUserNameChars(address)) {
            this.address = address;
        } else {
            throw new CustomException("el usuario: " + address + " no se puede crear, porque solo se permiten datos con guiones y letras");
        }

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
        return "name=" + name + ", address=" + address + ", municipio=" + municipio + ", departamento=" + departamento + ", nit=" + nit;
    }

}
