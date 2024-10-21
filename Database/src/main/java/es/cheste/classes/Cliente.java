package es.cheste.classes;

public class Cliente {
    private String dni;
    private String apellidos;
    private String cp;

    public Cliente(String dni, String apellidos, String cp) {
        this.dni = dni;
        this.apellidos = apellidos;
        this.cp = cp;
    }

    public Cliente() {
        // Constructor vacío
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    @Override
    public String toString() {
        return "Cliente con DNI nº " + dni + " - Apellidos: " + apellidos + ", CP: " + cp;
    }
}
