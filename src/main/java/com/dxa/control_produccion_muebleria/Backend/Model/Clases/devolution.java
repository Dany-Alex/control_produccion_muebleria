package com.dxa.control_produccion_muebleria.Backend.Model.Clases;

import java.sql.Date;

/**
 *
 * @author Artist
 */
public class devolution {

    private int invoiceNumber;
    private Date date;

    public devolution(int invoiceNumber, Date date) {
        this.invoiceNumber = invoiceNumber;
        this.date = date;
    }

    public int getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(int invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
