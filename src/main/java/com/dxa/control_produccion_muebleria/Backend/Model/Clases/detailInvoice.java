package com.dxa.control_produccion_muebleria.Backend.Model.Clases;

/**
 *
 * @author Artist
 */
public class detailInvoice {

    private String furniture;
    private int quantity, invoiceNumber;

    public detailInvoice(int invoiceNumber, String furniture, int quantity) {
        this.furniture = furniture;
        this.quantity = quantity;
        this.invoiceNumber = invoiceNumber;
    }

    public int getNoInvoice() {
        return invoiceNumber;
    }

    public void setNoInvoice(int invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public String getFurniture() {
        return furniture;
    }

    public void setFurniture(String furniture) {
        this.furniture = furniture;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
