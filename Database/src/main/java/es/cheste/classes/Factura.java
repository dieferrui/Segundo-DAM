package es.cheste.classes;

public class Factura {
    private int numFactura;
    private String dniCliente;

    // Constructor para inserciones en BD (numFactura se genera automáticamente)
    public Factura(String dniCliente) {
        this.dniCliente = dniCliente;
    }

    // Constructor para consultas en BD
    public Factura(int numFactura, String dniCliente) {
        this.numFactura = numFactura;
        this.dniCliente = dniCliente;
    }

    public Factura() {
        // Constructor vacío
    }

    public int getNumFactura() {
        return numFactura;
    }

    public void setNumFactura(int numFactura) {
        this.numFactura = numFactura;
    }

    public String getDniCliente() {
        return dniCliente;
    }

    public void setDniCliente(String dniCliente) {
        this.dniCliente = dniCliente;
    }

    @Override
    public String toString() {
        return "Factura con ID nº " + numFactura + " - DNI Cliente: " + dniCliente;
    }
}
