package es.cheste;

import java.util.Objects;

public class ContactoWrapper {

    private Contacto contacto;

    public ContactoWrapper(Contacto contacto) {
        this.contacto = contacto;
    }

    public Contacto getContacto() {
        return contacto;
    }

    public void setContacto(Contacto contacto) {
        this.contacto = contacto;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactoWrapper that = (ContactoWrapper) o;
        return Objects.equals(getContacto(), that.getContacto());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getContacto());
    }

    @Override
    public String toString() {
        return "ContactoWrapper{" +
                "contacto=" + contacto +
                '}';
    }
}
