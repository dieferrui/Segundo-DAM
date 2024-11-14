package Sumador;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Sumador {

    public static void main(String[] args) {
        if (args.length < 3) {
            System.out.println("Por favor, ingrese los valores de inicio, fin y el nombre del archivo de salida.");
            return;
        }

        try {
            // Parseo de los valores de inicio y fin
            int inicio = Integer.parseInt(args[0]);
            int fin = Integer.parseInt(args[1]);
            String archivoSalida = args[2];

            // Cálculo de la suma entre inicio y fin
            int suma = 0;
            for (int i = inicio; i <= fin; i++) {
                suma += i;
            }

            // Escribir el resultado en el archivo de salida
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivoSalida))) {
                writer.write(String.valueOf(suma));
            }

        } catch (IOException | NumberFormatException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
