package es.cheste.semaforo;

public class Main {
    public static void main(String[] args) {
        final Semaforos controlSemaforo = new Semaforos();

        Thread semaforo1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    controlSemaforo.semaforoUnoVerde();

                } catch (InterruptedException e) {
                    e.printStackTrace();

                }
            }
        });

        Thread semaforo2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    controlSemaforo.semaforoDosVerde();

                } catch (InterruptedException e) {
                    e.printStackTrace();

                }
            }
        });

        semaforo1.start();
        semaforo2.start();

        try {
            semaforo1.join();
            semaforo2.join();

        } catch (InterruptedException e) {
            e.printStackTrace();

        }
    }
}
