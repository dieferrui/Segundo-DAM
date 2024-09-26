package es.cheste;

import java.util.TreeSet;
import java.util.Scanner;

public class Agenda {

    private final Scanner SCM = new Scanner(System.in);
    private TreeSet<Contacto> contactos;

    public Agenda() {
        contactos = new TreeSet<>();
    }

    public boolean guardarContacto() {

        System.out.println(lang.getString("manager.editName"));
        System.out.println(lang.getString("manager.editNumber1"));

        try {
            String nombre = SCM.next();
            int telefono1 = SCM.nextInt();

            Contacto contacto = new Contacto(nombre, telefono1);
            addToSCV(contacto);
            addToXML(contacto);
            addToJSON(contacto);
            return true;

        } catch (InputMismatchException e) {
            System.out.println(lang.getString("error.invalidInput"));
            LOGGER.info("El usuario ha introducido un dato incorrecto.");
            return false;

        } // TODO añadir al catch las excepciones que lancen los métodos add
    }

    public void addToSCV(Contacto contacto) throws Exception {
        // Add to CSV file
    }

    public void addToXML(Contacto contacto) throws Exception {
        // Add to XML file
    }

    public void addToJSON(Contacto contacto) throws Exception {
        // Add to JSON file
    }

}
