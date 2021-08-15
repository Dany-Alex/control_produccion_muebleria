package com.dxa.control_produccion_muebleria.Backend.Model.Clases;

import java.sql.Date;

/**
 *
 * @author Artist
 */
public class invoice {

    private int nit;
    private Date date;

    public invoice(int nit, Date date) {
        this.nit = nit;
        this.date = date;
    }

    public int getNit() {
        return nit;
    }

    public void setNit(int nit) {
        this.nit = nit;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
