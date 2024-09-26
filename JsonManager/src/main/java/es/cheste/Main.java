package es.cheste;

import com.google.gson.Gson;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) {

        Personaje[] personajes = deserializarJSON();

    }

    private static Personaje[] deserializarJSON() {

        PersonajeWrapper personajes = null;

        try (Reader reader = Files.newBufferedReader(Paths.get("src/main/resources/SW.json"))){

            Gson gson = new Gson();
            personajes = gson.fromJson(reader, PersonajeWrapper.class);

        } catch (Exception e) {

            e.printStackTrace();

        }

        return personajes.getPersonatges();
    }

    private static void printNoConductores(Personaje[] personajes) {

        for (Personaje personaje : personajes) {

            if (personaje.getVehicles().isEmpty()) {

                System.out.println(personaje.getName());

            }

        }
    }

    private static void ordenarPorNumPelicula(Personaje[] personajes) {
        return;
    }
}