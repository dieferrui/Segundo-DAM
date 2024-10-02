package es.cheste;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.TreeSet;
import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.ResourceBundle;

import com.google.gson.Gson;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Agenda {
    private final String CSV_PATH = "src/main/resources/contactos.csv";
    private final String XML_PATH = "src/main/resources/contactos.xml";
    private final String JSON_PATH = "src/main/resources/contactos.json";

    private final Scanner SCM = new Scanner(System.in);

    private Logger LOGGER = Main.LOGGER;
    private ResourceBundle lang = Main.lang;
    private TreeSet<Contacto> contactos;

    public Agenda() {
        // contactos = importarAgendaCSV();
        // contactos = importarAgendaXML();
        // contactos = importarAgendaJSON();
    }

    public boolean guardarContacto() {

        System.out.println(lang.getString("manager.editName"));
        System.out.println(lang.getString("manager.editNumber1"));

        try {
            String nombre = SCM.next();
            int telefono1 = SCM.nextInt();

            Contacto contacto = new Contacto(nombre, telefono1);
            contactos.add(contacto);
            return true;

        } catch (InputMismatchException e) {
            System.out.println(lang.getString("error.invalidInput"));
            LOGGER.info("El usuario ha introducido un dato incorrecto.");
            return false;

        }
    }

    public TreeSet<Contacto> importarAgendaCSV() {
        TreeSet<Contacto> contactos = new TreeSet<>();

        try (BufferedReader br = new BufferedReader(new FileReader(CSV_PATH))) {
            br.readLine();
            String line = br.readLine();

            while (line != null) {
                String[] fields = line.split(";");
                Contacto contacto = new Contacto(fields[0], fields[1], fields[2], Integer.parseInt(fields[3]), Integer.parseInt(fields[4]), fields[5]);
                contactos.add(contacto);
                line = br.readLine();
            }

            LOGGER.info("Se ha cargado la agenda desde CSV correctamente.");

        } catch (FileNotFoundException e) {
            LOGGER.error("No se ha encontrado el archivo contactos.csv.");

        } catch (IOException e) {
            LOGGER.error("Error al leer el archivo contactos.csv.");

        } catch (NumberFormatException e) {
            LOGGER.error("Error al convertir un número del archivo contactos.csv.");

        } finally {
            return contactos;

        }
    }

    public TreeSet<Contacto> importarAgendaXML() {
        TreeSet<Contacto> contactos = new TreeSet<>();

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(XML_PATH);

            document.getDocumentElement().normalize();
            NodeList nodos = document.getElementsByTagName("contacto");

            for (int i = 0; i < nodos.getLength(); i++) {
                Node nodo = nodos.item(i);

                if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                    Element elemento = (Element)nodo;
                    Contacto contacto = new Contacto(elemento.getElementsByTagName("nombre").item(0).getTextContent(),
                            elemento.getElementsByTagName("apellidos").item(0).getTextContent(),
                            elemento.getElementsByTagName("email").item(0).getTextContent(),
                            Integer.parseInt(elemento.getElementsByTagName("telefono1").item(0).getTextContent()),
                            Integer.parseInt(elemento.getElementsByTagName("telefono2").item(0).getTextContent()),
                            elemento.getElementsByTagName("direccion").item(0).getTextContent());
                    contactos.add(contacto);
                }
            }

        } catch (FileNotFoundException e) {
            LOGGER.error("No se ha encontrado el archivo contactos.xml.");

        } catch (IOException e) {
            LOGGER.error("Error al leer el archivo contactos.xml.");

        } catch (ParserConfigurationException e) {
            LOGGER.error("Error al parsear el archivo contactos.xml.");

        } catch (NumberFormatException e) {
            LOGGER.error("Error al convertir un número del archivo contactos.xml.");

        } finally {
            return contactos;

        }
    }

    public TreeSet<Contacto> importarAgendaJSON() {
        TreeSet<Contacto> contactos = new TreeSet<>();

        try (Reader reader = Files.newBufferedReader(Paths.get(JSON_PATH))){

            Gson gson = new Gson();
            contactos = gson.fromJson(reader, Contacto.class);

        } catch (Exception e) {

            e.printStackTrace();

        }

        return contactos;
    }
    
    public boolean guardarAgendaCSV() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(CSV_PATH))) {
            bw.write("nombre;apellidos;email;telefono1;telefono2;direccion\n");
            
            for (Contacto contacto : contactos) {
                bw.write(contacto.getNombre() + ";" + contacto.getApellidos() + ";" + contacto.getEmail() + ";" + contacto.getTelefono1() + ";" + contacto.getTelefono2() + ";" + contacto.getDireccion() + "\n");
            }
            
            LOGGER.info("Se ha guardado la agenda en CSV correctamente.");
            return true;
            
        } catch (IOException e) {
            LOGGER.error("Error al escribir el archivo contactos.csv.");
            return false;
        }
    }
}
