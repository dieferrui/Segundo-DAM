package Potenciador;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LanzadorPotencias {

    // Método para lanzar un proceso de potencia en un rango específico y guardarlo en un archivo
    public void lanzarProceso(int base, int exponente, String archivoSalida) {
        ProcessBuilder pb = new ProcessBuilder("java", "Potencia", String.valueOf(base), String.valueOf(exponente), archivoSalida);
        pb.directory(new File("."));  // Usa el directorio actual

        try {
            Process proceso = pb.start();
            proceso.waitFor(); // Esperar a que el proceso termine
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Método para leer el valor del archivo de salida
    public int leerResultado(String archivo) {
        int resultado = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            resultado = Integer.parseInt(reader.readLine());
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
        return resultado;
    }

    public static void main(String[] args) {
        LanzadorPotencias lanzador = new LanzadorPotencias();
        List<String> archivosSalida = new ArrayList<>();
        int base = 5; // Puedes cambiar la base si es necesario

        // Define los exponentes y nombres de archivos
        int[] exponentes = {2, 3, 4};
        for (int exponente : exponentes) {
            String archivoSalida = "potencia_" + exponente + ".txt";
            archivosSalida.add(archivoSalida);

            // Lanzar un proceso Potencia para cada exponente
            lanzador.lanzarProceso(base, exponente, archivoSalida);
        }

        // Leer los resultados de los archivos y mostrarlos en pantalla
        for (int i = 0; i < exponentes.length; i++) {
            int resultado = lanzador.leerResultado(archivosSalida.get(i));
            System.out.println("Resultado de " + base + "^" + exponentes[i] + " es: " + resultado);
        }
    }
}
