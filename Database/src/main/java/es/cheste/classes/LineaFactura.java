package es.cheste.classes;

public class LineaFactura {
    private int numFactura;
    private short lineaFactura;
    private String concepto;
    private short cantidad;

    public LineaFactura(int numFactura, short lineaFactura, String concepto, short cantidad) {
        this.numFactura = numFactura;
        this.lineaFactura = lineaFactura;
        this.concepto = concepto;
        this.cantidad = cantidad;
    }

    public LineaFactura() {
        // Constructor vacío
    }

    public int getNumFactura() {
        return numFactura;
    }

    public void setNumFactura(int numFactura) {
        this.numFactura = numFactura;
    }

    public short getLineaFactura() {
        return lineaFactura;
    }

    public void setLineaFactura(short lineaFactura) {
        this.lineaFactura = lineaFactura;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public short getCantidad() {
        return cantidad;
    }

    public void setCantidad(short cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "Linea nº " + lineaFactura + " de la factura nº " + numFactura + " - Concepto: " + concepto + ", Cantidad: " + cantidad;
    }
}
