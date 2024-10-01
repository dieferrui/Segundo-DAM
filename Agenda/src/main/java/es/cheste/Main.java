package es.cheste;

import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.ResourceBundle;

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

    private void menuAgenda() {

        int opcionAgenda = 0;

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

                switch (opcionAgenda) {

                    case 1:

                        break;

                    case 2:

                        break;

                    case 3:

                        break;

                    case 4:

                        break;

                    case 5:

                        break;

                    default:

                        break;
                }

            } catch (InputMismatchException e) {

                System.out.println(lang.getString("error.invalidInputMenu"));
                LOGGER.info("El usuario no ha introducido un número cuando se le ha solicitado.");
            }

        } while (opcionAgenda != 6);
    }
}