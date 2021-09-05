package com.dxa.control_produccion_muebleria.Backend.Model.Clases;

/**
 *
 * @author Artist
 */
public class client {

    private int nit;
    private String name, address, municipio, departamento;

    public client(String name, int nit, String address, String municipio, String departamento) {
        this.nit = nit;
        this.name = name;
        this.address = address;
        this.municipio = municipio;
        this.departamento = departamento;
    }

    public client(String name, int nit, String address) {
        this.nit = nit;
        this.name = name;
        this.address = address;
    }

    public int getNit() {
        return nit;
    }

    public void setNit(int nit) {
        this.nit = nit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
