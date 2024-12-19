package es.cheste.caracol;

import java.util.Random;

public class Caracol implements Runnable {
    private double velocidad;
    private String nombre;

    public Caracol(String nombre, double velocidad) {
        this.nombre = nombre;
        this.velocidad = velocidad;
    }

    public void run() {
        double distanciaTotal = 1.0;
        double distanciaRecorrida = 0;
        Random random = new Random();

        while (distanciaRecorrida < distanciaTotal) {
            double avance = velocidad * random.nextDouble();
            distanciaRecorrida += avance;

            if (distanciaRecorrida > distanciaTotal) {
                distanciaRecorrida = distanciaTotal;
            }

            double porcentaje = distanciaRecorrida / distanciaTotal * 100;
            System.out.printf("%s ha recorrido %.2f%% de la distancia total.\n", nombre, porcentaje);
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(nombre + " ha llegado a la meta.");
    }

    public static void main(String[] args) {
        Caracol caracol1 = new Caracol("Caracol 1", 0.05);
        Caracol caracol2 = new Caracol("Caracol 2", 0.08);
        Caracol caracol3 = new Caracol("Caracol 3", 0.06);
        Caracol caracol4 = new Caracol("Caracol 4", 0.07);
        Caracol caracol5 = new Caracol("Caracol 5", 0.09);

        Thread hilo1 = new Thread(caracol1);
        Thread hilo2 = new Thread(caracol2);
        Thread hilo3 = new Thread(caracol3);
        Thread hilo4 = new Thread(caracol4);
        Thread hilo5 = new Thread(caracol5);

        hilo1.start();
        hilo2.start();
        hilo3.start();
        hilo4.start();
        hilo5.start();
    }
}
