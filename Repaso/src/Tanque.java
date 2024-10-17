public class Tanque {
    private String nombre;
    private int peso;
    private TipoTanque tipo;

    public Tanque(String nombre, int peso, TipoTanque tipo) {
        this.nombre = nombre;
        this.peso = peso;
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public int getPeso() {
        return peso;
    }

    public TipoTanque getTipo() {
        return tipo;
    }

    @Override
    public String toString() {
        return "Tanque " + nombre + ": Peso - " + peso + " Tipo - " + tipo;
    }
}
