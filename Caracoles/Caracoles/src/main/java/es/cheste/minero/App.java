package es.cheste.minero;

import java.util.Random;

public class App {
    private static final Random random = new Random();
    private static final int min = 1000;
    private static final int max = 3000; // Si tarda mucho en terminar, bajar este valor

    public static void main(String[] args) {
        int stockInicial = random.nextInt(max - min + 1) + min;
        Mina mina = new Mina(stockInicial);

        int numMineros = random.nextInt(30 - 10 + 1) + 10;
        Minero[] mineros = new Minero[numMineros];

        for (int i = 0; i < numMineros; i++) {
            mineros[i] = new Minero(mina, "Minero - " + (i + 1));
            mineros[i].start();
        }

        for (Minero minero : mineros) {
            try {
                minero.join();

            } catch (InterruptedException e) {
                e.printStackTrace();

            }
        }

        int totalRecolectado = 0;
        for (Minero minero : mineros) {
            totalRecolectado += minero.getBolsa();
        }

        System.out.println("\nResultados finales:");
        for (Minero minero : mineros) {
            System.out.println(minero.getNombre() + " recolectó: " + minero.getBolsa() + " oros.");
        }
        System.out.println("\nStock inicial: " + stockInicial);
        System.out.println("Número de mineros asignados: " + numMineros);
        System.out.println("Stock restante en la mina: " + mina.getStock());
        System.out.println("Total de oro recolectado por los mineros: " + totalRecolectado);
        System.out.println("Conteo " + (totalRecolectado == stockInicial ? "correcto" : "incorrecto"));
    }
}
