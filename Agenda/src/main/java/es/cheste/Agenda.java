package es.cheste;

import java.io.*;
import java.util.*;
import org.w3c.dom.*;

import java.lang.reflect.Type;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.nio.file.Files;
import java.nio.file.Paths;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import org.xml.sax.SAXException;

import org.apache.logging.log4j.Logger;

public class Agenda {
    private final String CSV_PATH = "src/main/resources/contactos.csv";
    private final String XML_PATH = "src/main/resources/contactos.xml";
    private final String JSON_PATH = "src/main/resources/contactos.json";

    private final Scanner SCM = new Scanner(System.in);

    private Logger LOGGER = Main.LOGGER;
    private ResourceBundle lang = Main.lang;
    public TreeSet<Contacto> contactos;

    public Agenda() {
        contactos = new TreeSet<>();
        contactos = importarAgendaCSV();
        contactos = importarAgendaXML();
        contactos = importarAgendaJSON();
    }

    public boolean guardarContacto() {
        try {
            System.out.println(lang.getString("manager.editName"));
            String nombre = SCM.nextLine();
            System.out.println(lang.getString("manager.editNumber1"));
            String telefonoStr = SCM.nextLine();
            int telefono1;

            try {
                telefono1 = Integer.parseInt(telefonoStr);

            } catch (NumberFormatException e) {
                telefono1 = 0;
                LOGGER.info("El usuario ha introducido un dato incorrecto.");

            }

            Contacto contacto = new Contacto(nombre, telefono1);
            contactos.add(contacto);
            return true;

        } catch (InputMismatchException e) {
            System.out.println(lang.getString("error.invalidInput"));
            LOGGER.info("El usuario ha introducido un dato incorrecto.");
            return false;

        }
    }

    public ArrayList<Contacto> buscarContacto(String datos) {
        ArrayList<Contacto> contactosEncontrados = new ArrayList<>();

        for (Contacto contacto : contactos) {

            String matchNombre = contacto.getNombre();
            String matchTelefono1 = String.valueOf(contacto.getTelefono1());
            String matchTelefono2 = String.valueOf(contacto.getTelefono2());

            if (matchNombre.contains(datos) || matchTelefono1.contains(datos) || matchTelefono2.contains(datos)) {
                contactosEncontrados.add(contacto);
            }
        }

        return contactosEncontrados;
    }

    public boolean editarContacto(String nombre) {
        boolean contactoFound = false;

        try {
            for (Contacto contacto : contactos) {
                if (contacto.getNombre().equals(nombre)) {
                    System.out.println(lang.getString("manager.editName"));
                    contacto.setNombre(SCM.nextLine());
                    System.out.println(lang.getString("manager.editSurname"));
                    contacto.setApellidos(SCM.nextLine());
                    System.out.println(lang.getString("manager.editEmail"));
                    contacto.setEmail(SCM.nextLine());
                    System.out.println(lang.getString("manager.editNumber1"));
                    try {
                        contacto.setTelefono1(Integer.parseInt(SCM.nextLine()));

                    } catch (NumberFormatException e) {
                        contacto.setTelefono1(0);
                        LOGGER.info("El usuario ha introducido un dato incorrecto.");

                    }
                    System.out.println(lang.getString("manager.editNumber2"));
                    try {
                        contacto.setTelefono1(Integer.parseInt(SCM.nextLine()));

                    } catch (NumberFormatException e) {
                        contacto.setTelefono2(0);
                        LOGGER.info("El usuario ha introducido un dato incorrecto.");

                    }
                    System.out.println(lang.getString("manager.editAddress"));
                    contacto.setDireccion(SCM.nextLine());

                    contactoFound = true;
                }
            }

        } catch (InputMismatchException e) {
            System.out.println(lang.getString("error.invalidInput"));
            LOGGER.info("El usuario ha introducido un dato incorrecto.");

        }

        return contactoFound;
    }

    public boolean eliminarContacto(String nombre) {
        boolean exito = false;

        for (Contacto contacto : contactos) {
            if (contacto.getNombre().equals(nombre)) {
                contactos.remove(contacto);
                exito = true;
                break;

            } else {
                LOGGER.info("No hay contactos con el nombre especificado.");

            }
        }

        return exito;
    }

    protected void guardarAgendaCSV() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(CSV_PATH))) {
            bw.write("nombre;apellidos;email;telefono1;telefono2;direccion\n");

            for (Contacto contacto : contactos) {
                bw.write(contacto.getNombre() + ";" + contacto.getApellidos() + ";" + contacto.getEmail() + ";" + contacto.getTelefono1() + ";" + contacto.getTelefono2() + ";" + contacto.getDireccion() + "\n");
            }

            LOGGER.info("Se ha guardado la agenda en CSV correctamente.");

        } catch (IOException e) {
            LOGGER.error("Se ha producido un error al guardar la agenda en formato CSV.");

        }
    }

    protected void guardarAgendaXML() {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            Document doc = builder.newDocument();

            Element elementoRaiz = doc.createElement("agenda");
            doc.appendChild(elementoRaiz);

            for (Contacto contacto : contactos) {
                Element contactoE = doc.createElement("contacto");
                elementoRaiz.appendChild(contactoE);

                Element nombreE = doc.createElement("nombre");
                nombreE.appendChild(doc.createTextNode(contacto.getNombre()));
                contactoE.appendChild(nombreE);

                Element apellidosE = doc.createElement("apellidos");
                apellidosE.appendChild(doc.createTextNode(contacto.getApellidos()));
                contactoE.appendChild(apellidosE);

                Element emailE = doc.createElement("email");
                emailE.appendChild(doc.createTextNode(contacto.getEmail()));
                contactoE.appendChild(emailE);

                Element telefono1E = doc.createElement("telefono1");
                telefono1E.appendChild(doc.createTextNode(String.valueOf(contacto.getTelefono1())));
                contactoE.appendChild(telefono1E);

                Element telefono2E = doc.createElement("telefono2");
                telefono2E.appendChild(doc.createTextNode(String.valueOf(contacto.getTelefono2())));
                contactoE.appendChild(telefono2E);

                Element direccionE = doc.createElement("direccion");
                direccionE.appendChild(doc.createTextNode(contacto.getDireccion()));
                contactoE.appendChild(direccionE);
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");

            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(XML_PATH));

            transformer.transform(source, result);

            LOGGER.info("Archivo XML creado exitosamente.");

        } catch (Exception e) {
            LOGGER.error("Se ha producido un error al guardar la agenda en formato XML.");

        }
    }


    protected void guardarAgendaJSON() {
        try (Writer writer = Files.newBufferedWriter(Paths.get(JSON_PATH))) {
            Gson gson = new GsonBuilder().create();
            gson.toJson(contactos, writer);

            LOGGER.info("Archivo JSON guardado con éxito.");

        } catch (Exception e) {
            LOGGER.error("Se ha producido un error al guardar la agenda en formato JSON.");

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

        } catch (FileNotFoundException e) {
            LOGGER.error("No se ha encontrado el archivo contactos.csv.");

        } catch (IOException e) {
            LOGGER.error("Error al leer el archivo contactos.csv.");

        } catch (NumberFormatException e) {
            LOGGER.error("Error al convertir un número del archivo contactos.csv.");

        }

        return contactos;
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
                    Element elemento = (Element) nodo;

                    String nombre = elemento.getElementsByTagName("nombre").item(0).getTextContent();
                    String apellidos = elemento.getElementsByTagName("apellidos").item(0).getTextContent();
                    String email = elemento.getElementsByTagName("email").item(0).getTextContent();
                    int telefono1 = Integer.parseInt(elemento.getElementsByTagName("telefono1").item(0).getTextContent());
                    int telefono2 = Integer.parseInt(elemento.getElementsByTagName("telefono2").item(0).getTextContent());
                    String direccion = elemento.getElementsByTagName("direccion").item(0).getTextContent();

                    Contacto contacto = new Contacto(nombre, apellidos, email, telefono1, telefono2, direccion);
                    contactos.add(contacto);
                }
            }

        } catch (FileNotFoundException e) {
            LOGGER.error("No se ha encontrado el archivo contactos.xml.");

        } catch (IOException e) {
            LOGGER.error("Error al leer el archivo contactos.xml.");

        } catch (ParserConfigurationException e) {
            LOGGER.error("Error al configurar el parser del archivo contactos.xml.");

        } catch (NumberFormatException e) {
            LOGGER.error("Error al convertir un número del archivo contactos.xml.");

        } catch (SAXException e) {
            LOGGER.error("Error al parsear el archivo contactos.xml.");

        }

        return contactos;
    }


    public TreeSet<Contacto> importarAgendaJSON() {
        TreeSet<Contacto> contactos = new TreeSet<>();

        try (Reader reader = Files.newBufferedReader(Paths.get(JSON_PATH))) {
            Gson gson = new Gson();

            Type tipoTreeSetContacto = new TypeToken<TreeSet<Contacto>>() {}.getType();
            contactos = gson.fromJson(reader, tipoTreeSetContacto);

        } catch (Exception e) {
            LOGGER.error("Error al leer el archivo contactos.json.");
        }

        return contactos;
    }
}
