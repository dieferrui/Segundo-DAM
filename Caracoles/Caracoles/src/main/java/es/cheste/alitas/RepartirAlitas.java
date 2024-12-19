package es.cheste.alitas;

import com.sun.source.tree.SynchronizedTree;

import java.util.Random;

public class RepartirAlitas implements Runnable {
    private static int totalAlitas = 200;
    private static final Object lock = new Object();

    public void consumirAlitas() {
        Random random = new Random();
        int alitasPorConsumir = random.nextInt(4) +1;

        synchronized (lock) {
            if (totalAlitas >= alitasPorConsumir) {
                totalAlitas -= alitasPorConsumir;
                System.out.println(Thread.currentThread().getName() + " ha consumido " + alitasPorConsumir + " alitas. Quedan " + totalAlitas + " alitas.");

            } else {
                System.out.println(Thread.currentThread().getName() + " ha consumido " + totalAlitas + " alitas. No quedan alitas.");
                totalAlitas = 0;
            }
        }
    }

    public void run() {
        while (totalAlitas > 0) {
            consumirAlitas();
        }
    }

    public static void main(String[] args) {
        RepartirAlitas repartirAlitas = new RepartirAlitas();

        Thread[] hilos = new Thread[30];
        for (int i = 0; i < 30; i++) {
            hilos[i] = new Thread(repartirAlitas, "Compañero-" + (i + 1));
            hilos[i].start();
        }

        for (int i = 0; i < 30; i++) {
            try {
                hilos[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("No quedan alitas.");
    }
}
