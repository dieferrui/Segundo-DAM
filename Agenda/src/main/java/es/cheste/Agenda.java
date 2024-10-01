package es.cheste;

import java.util.TreeSet;
import java.util.Scanner;
import java.util.InputMismatchException;

public class Agenda {
    private final String CSV_PATH = "src/main/resources/contactos.csv";
    private final String XML_PATH = "src/main/resources/contactos.xml";
    private final String JSON_PATH = "src/main/resources/contactos.json";

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
            contactos.put(contacto);
            return true;

        } catch (InputMismatchException e) {
            System.out.println(lang.getString("error.invalidInput"));
            LOGGER.info("El usuario ha introducido un dato incorrecto.");
            return false;

        }
    }

    public String importarAgendaCSV() {

        try (BufferedReader br = new BufferedReader(new FileReader(CSV_PATH))) {
            br.readLine();
            String line = br.readLine();

            while (line != null) {
                String[] fields = line.split(";");
                Contacto contacto = new Contacto(fields[0], fields[1], fields[2], Integer.parseInt(fields[3]), Integer.parseInt(fields[4]), fields[5]);
                contactos.add(contacto);
                line = br.readLine();
            }

            return lang.getString("manager.importSuccess");

        } catch (FileNotFoundException e) {
            LOGGER.error("No se ha encontrado el archivo contactos.csv.");
            return lang.getString("error.fileNotFound");

        } catch (IOException e) {
            LOGGER.error("Error al leer el archivo contactos.csv.");
            return lang.getString("error.fileRead");

        } catch (NumberFormatException e) {
            LOGGER.error("Error al convertir un número del archivo contactos.csv.");
            return lang.getString("error.fileNumber");

        }
    }
}
