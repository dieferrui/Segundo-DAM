package es.cheste;

import java.io.*;
import java.util.*;
import java.util.concurrent.*;

public class NEOHelp {
    private static final String outputFile = "src/main/java/es/cheste/neos/";

    public static void main(String[] args) {
        String inputFile = "src/main/java/es/cheste/NEOs.txt";
        List<NEO> neos = readNEOData(inputFile);

        if (neos.isEmpty()) {
            System.out.println("No se encontraron datos de NEOs.");
            return;
        }

        int cores = Runtime.getRuntime().availableProcessors();
        ExecutorService executor = Executors.newFixedThreadPool(cores);
        System.out.println("Número de núcleos de procesamiento: " + cores);
        List<Future<String[]>> futures = new ArrayList<>();
        List<String[]> results = new ArrayList<>();

        long startTime = System.currentTimeMillis();

        for (NEO neo : neos) {
            futures.add(executor.submit(() -> {
                return processNEO(neo);
            }));
        }

        for (Future<String[]> future : futures) {
            try {
                String[] result = future.get();
                results.add(result);

            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        long endTime = System.currentTimeMillis();
        executor.shutdown();

        double totalTime = (endTime - startTime) / 1000.0;
        double averageTime = totalTime / neos.size();

        convertAndRedeem(results);

        System.out.printf("\nTiempo total de ejecución: %.2f segundos%n", totalTime);
        System.out.printf("Tiempo medio por NEO: %.2f segundos%n", averageTime);
    }

    private static List<NEO> readNEOData(String inputFile) {
        List<NEO> neos = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");

                if (parts.length == 3) {
                    String name = parts[0].trim();
                    double positionNEO = Double.parseDouble(parts[1].trim());
                    double velocityNEO = Double.parseDouble(parts[2].trim());
                    neos.add(new NEO(name, positionNEO, velocityNEO));
                }
            }

        } catch (IOException e) {
            e.printStackTrace();

        }
        return neos;
    }

    private static String[] processNEO(NEO neo) {
        double positionNEO = neo.getPositionNEO();
        double velocityNEO = neo.getVelocityNEO();
        double positionTierra = 1;
        double velocityTierra = 100;
        String[] results = new String[2];

        System.out.printf("Procesando %s en hilo %s%n", neo.getName(), Thread.currentThread().getName());

        for (int i = 0; i < (50 * 365 * 24 * 60 * 60); i++) {
            positionNEO = positionNEO + velocityNEO * i;
            positionTierra = positionTierra + velocityTierra * i;

        }

        double probability = 100 * Math.random()
                * Math.pow(((positionNEO - positionTierra) / (positionNEO + positionTierra)), 2);

        saveProbabilityToFile(neo.getName(), probability);

        results[0] = neo.getName();
        results[1] = String.format("%.2f", probability);

        return results;
    }

    private static void saveProbabilityToFile(String name, double probability) {
        try (PrintWriter writer = new PrintWriter(outputFile + name + ".txt")) {
            writer.printf("Probabilidad de colisión: %.2f%%%n", probability);

        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    private static void printAlertOrMessage(String name, double probability) {
        if (probability > 60) {
            System.out.println("ALERTA MUNDIAL: El NEO \"" + name + "\" tiene una probabilidad extrema de colisión (" + probability + "%).");

        } else if (probability > 10) {
            System.out.println("Alerta: El NEO \"" + name + "\" tiene una probabilidad alta de colisión (" + probability + "%).");

        } else if (probability < 1) {
            System.out.println("Información: El NEO \"" + name + "\" no colisionará con la Tierra.");

        } else {
            System.out.println("Información: El NEO \"" + name + "\" tiene bajas probabilidades de colisionar (" + probability + "%).");

        }
    }

    private static void convertAndRedeem(List<String[]> results) {
        for (String[] result : results) {
            String NEOName = result[0];
            String probabilityConverted = result[1].replace(",", ".");
            double probability = Double.parseDouble(probabilityConverted);

            printAlertOrMessage(NEOName, probability);
        }
    }
}

