package es.cheste;

import java.util.Arrays;
import java.util.Objects;

public class PersonajeWrapper {

    protected Personaje[] personatges;

    public PersonajeWrapper() {
        // Constructor vacío
    }

    public PersonajeWrapper(Personaje[] personatges) {
        this.personatges = personatges;
    }

    public Personaje[] getPersonatges() {
        return personatges;
    }

    public void setPersonatges(Personaje[] personatges) {
        this.personatges = personatges;
    }

    @Override
    public String toString() {
        return "PersonajeWrapper{" +
                "personatges=" + Arrays.toString(personatges) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonajeWrapper that = (PersonajeWrapper) o;
        return Objects.deepEquals(getPersonatges(), that.getPersonatges());
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(getPersonatges());
    }
}
