package Potenciador;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Potencia {

    public static void main(String[] args) {
        if (args.length < 3) {
            System.out.println("Por favor, ingrese la base, el exponente y el nombre del archivo de salida.");
            return;
        }

        try {
            int base = Integer.parseInt(args[0]);
            int exponente = Integer.parseInt(args[1]);
            String archivoSalida = args[2];

            // Cálculo de la potencia
            int resultado = (int) Math.pow(base, exponente);

            // Escribir el resultado en el archivo de salida
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivoSalida))) {
                writer.write(String.valueOf(resultado));
            }

        } catch (IOException | NumberFormatException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
