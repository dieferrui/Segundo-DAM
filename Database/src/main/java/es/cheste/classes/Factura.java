package es.cheste.classes;

public class Factura {
    private int numFactura;
    private String dniCliente;

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
        return "Factura{" +
                "numFactura=" + numFactura +
                ", dniCliente='" + dniCliente + '\'' +
                '}';
    }
}
