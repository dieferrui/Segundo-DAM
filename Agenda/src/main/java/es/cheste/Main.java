package es.cheste;

import java.util.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    private static final Scanner SC = new Scanner(System.in);
    public static final Logger LOGGER = LogManager.getLogger();
    public static ResourceBundle lang;
    public static Locale locale;

    public static void main(String[] args) {
        locale = new Locale("es");
        lang = ResourceBundle.getBundle("lang", locale);

        mostrarMenu();
        menuAgenda();
    }

    private static void mostrarMenu() {
        System.out.println(lang.getString("menu.title"));
        System.out.println(lang.getString("menu.selectLanguage"));
        System.out.println(lang.getString("menu.optionMenu1"));
        System.out.println(lang.getString("menu.optionMenu2"));
        System.out.println(lang.getString("menu.optionMenu3"));

        try {
            int idioma = SC.nextInt();

            switch (idioma) {

                case 1:
                    locale = new Locale("es");
                    lang = ResourceBundle.getBundle("lang", locale);
                    break;

                case 2:
                    locale = new Locale("en");
                    lang = ResourceBundle.getBundle("lang", locale);
                    break;

                default:
                    locale = new Locale("ca");
                    lang = ResourceBundle.getBundle("lang", locale);
                    break;
            }

            System.out.println(lang.getString("menu.title"));

        } catch (InputMismatchException e) {

            System.out.println(lang.getString("error.invalidInputMenu"));
            LOGGER.info("El usuario no ha introducido un número cuando se le ha solicitado.");
        }
    }

    private static void menuAgenda() {

        int opcionAgenda = 0;
        Agenda agenda = new Agenda();

        do {
            System.out.println(lang.getString("agenda.title"));
            System.out.println(lang.getString("agenda.show"));
            System.out.println(lang.getString("agenda.search"));
            System.out.println(lang.getString("agenda.edit"));
            System.out.println(lang.getString("agenda.add"));
            System.out.println(lang.getString("agenda.delete"));
            System.out.println(lang.getString("agenda.exit"));

            try {
                opcionAgenda = SC.nextInt();
                SC.nextLine();

                switch (opcionAgenda) {

                    case 1:
                        int numContactos = agenda.contactos.size();

                        try {
                            for (Contacto contacto : agenda.contactos) {
                                System.out.println(contacto);
                            }

                        } catch (NullPointerException e) {
                            System.out.println(lang.getString("agenda.emptyList"));
                            LOGGER.info("La lista de contactos está vacía.");
                        }

                        if (numContactos == 0) {
                            System.out.println(lang.getString("agenda.emptyList"));
                        }

                        break;

                    case 2:
                        ArrayList<Contacto> matches;

                        System.out.println(lang.getString("agenda.searchQuery"));
                        String datos = SC.nextLine();
                        matches = agenda.buscarContacto(datos);

                        for (Contacto contacto : matches) {
                            System.out.println(contacto);
                        }

                        if (matches.isEmpty()) {
                            System.out.println(lang.getString("agenda.searchFail"));
                        }

                        break;

                    case 3:
                        boolean editado;

                        System.out.println(lang.getString("agenda.editQuery"));

                        for (Contacto contacto : agenda.contactos) {
                            System.out.println(contacto.getNombre());

                        }

                        String contactoElegido = SC.nextLine();

                        editado = agenda.editarContacto(contactoElegido);

                        if (editado) {
                            System.out.println(lang.getString("agenda.editSuccess"));

                        } else {
                            System.out.println(lang.getString("agenda.editFail"));

                        }

                        break;

                    case 4:
                        boolean creado = agenda.guardarContacto();
                        if (creado) {
                            System.out.println(lang.getString("agenda.addSuccess"));
                        } else {
                            System.out.println(lang.getString("agenda.addFail"));
                        }
                        break;

                    case 5:
                        System.out.println(lang.getString("agenda.deleteQuery"));

                        for (Contacto contacto : agenda.contactos) {
                            System.out.println(contacto.getNombre());

                        }

                        String eliminar = SC.next();

                        boolean eliminacion = agenda.eliminarContacto(eliminar);
                        if (eliminacion) {
                            System.out.println(lang.getString("agenda.deleteSuccess"));
                        } else {
                            System.out.println(lang.getString("agenda.deleteFail"));
                        }
                        break;

                    default:
                        System.out.println(lang.getString("agenda.exitMessage"));
                        break;
                }

            } catch (InputMismatchException e) {

                System.out.println(lang.getString("error.invalidInputMenu"));
                LOGGER.info("El usuario no ha introducido un número cuando se le ha solicitado.");
            }

        } while (opcionAgenda != 6);

        agenda.guardarAgendaCSV();
        agenda.guardarAgendaXML();
        agenda.guardarAgendaJSON();

        SC.close();
    }
}