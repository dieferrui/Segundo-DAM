package Sumador;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Lanzador {

    // Método para lanzar un proceso de suma en un rango específico y guardarlo en un archivo
    public void lanzarProceso(int inicio, int fin, String archivoSalida) {
        ProcessBuilder pb = new ProcessBuilder("java", "Sumador", String.valueOf(inicio), String.valueOf(fin), archivoSalida);
        pb.directory(new File("."));

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
        Lanzador lanzador = new Lanzador();
        List<String> archivosSalida = new ArrayList<>();
        int sumaTotal = 0;

        // Definir los rangos y nombres de archivos para cada proceso
        int rangoPorProceso = 10;
        int inicioRango = 1;

        for (int i = 0; i < 5; i++) {
            int finRango = inicioRango + rangoPorProceso - 1;
            String archivoSalida = "suma_parcial_" + i + ".txt";
            archivosSalida.add(archivoSalida);

            // Lanzar un proceso Sumador para cada rango
            lanzador.lanzarProceso(inicioRango, finRango, archivoSalida);

            // Actualizar el inicio del próximo rango
            inicioRango = finRango + 1;
        }

        // Leer los resultados de los archivos y calcular la suma total
        for (String archivo : archivosSalida) {
            sumaTotal += lanzador.leerResultado(archivo);
        }

        System.out.println("La suma total de los números del 1 al 50 es: " + sumaTotal);
    }
}
