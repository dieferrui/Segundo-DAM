package es.cheste;

import java.util.Objects;

public class Contacto implements Comparable<Contacto> {

    private String nombre;
    private String apellidos;
    private String email;
    private int telefono1;
    private int telefono2;
    private String direccion;

    public Contacto(String nombre, String apellidos, String email, int telefono1, int telefono2, String direccion) {

        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
        this.telefono1 = telefono1;
        this.telefono2 = telefono2;
        this.direccion = direccion;
    }

    public Contacto(String nombre, int telefono1) {

        this.nombre = nombre;
        this.telefono1 = telefono1;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getTelefono1() {
        return telefono1;
    }

    public void setTelefono1(int telefono1) {
        this.telefono1 = telefono1;
    }

    public int getTelefono2() {
        return telefono2;
    }

    public void setTelefono2(int telefono2) {
        this.telefono2 = telefono2;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contacto contacto = (Contacto) o;
        return getTelefono1() == contacto.getTelefono1() && getTelefono2() == contacto.getTelefono2() && Objects.equals(getNombre(), contacto.getNombre()) && Objects.equals(getApellidos(), contacto.getApellidos()) && Objects.equals(getEmail(), contacto.getEmail()) && Objects.equals(getDireccion(), contacto.getDireccion());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getNombre(), getApellidos(), getEmail(), getTelefono1(), getTelefono2(), getDireccion());
    }

    @Override
    public String toString() {

        return "Contacto{" +
                "nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", email='" + email + '\'' +
                ", telefono1=" + telefono1 +
                ", telefono2=" + telefono2 +
                ", direccion='" + direccion + '\'' +
                '}';
    }

    @Override
    public int compareTo(Contacto otroContacto) {
        return this.nombre.compareTo(otroContacto.nombre);
    }
}
