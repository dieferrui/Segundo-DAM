import java.io.Serializable;

public class Modulo implements Serializable, Comparable<Modulo>{

    private static final long serialVersionUID = 19451997L;

    private String nombre;
    private int hora;
    private double nota;

    public Modulo(String nombre, int hora, double nota) {
        this.nombre = nombre;
        this.hora = hora;
        this.nota = nota;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getHora() {
        return hora;
    }

    public void setHoras(int hora) {
        this.hora = hora;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    @Override
    public String toString() {
        return "Módul de " + nombre + " (" + hora + " hores) - Nota: " + nota;
    }

    @Override
    public int compareTo(Modulo otroModulo) {
        return this.nombre.compareTo(otroModulo.nombre);
    }
}