package es.cheste.minero;

public class Minero extends Thread {
    public int bolsa = 0;
    private int tiempoExtraccion = 1000;
    private Mina mina;
    private String nombre;

    public Minero(Mina mina, String nombre) {
        this.mina = mina;
        this.nombre = nombre;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(tiempoExtraccion);

            } catch (InterruptedException e) {
                e.printStackTrace();

            }

            int recurso = mina.extraer();
            if (recurso == 0) {
                break;

            } else {
                bolsa++;
                System.out.println(nombre + " ha extraído un oro. Bolsa: " + bolsa);
            }
        }
    }

    public int getBolsa() {
        return bolsa;
    }

    public String getNombre() {
        return nombre;
    }
}
