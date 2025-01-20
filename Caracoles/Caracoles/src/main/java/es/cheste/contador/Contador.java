package es.cheste.contador;

public class Contador implements Runnable {
    private String nombre;
    private int inicio;
    private int limite;

    public Contador(String nombre, int inicio, int limite) {
        this.nombre = nombre;
        this.inicio = inicio;
        this.limite = limite;
    }

    @Override
    public void run() {
        for (int i = inicio; i <= limite; i++) {
            System.out.println("El hilo " + nombre + " de inicio " + inicio + " y límite " + limite + " ha contado " + i);

        }
    }

    public static void main(String[] args) {
        int numHilos = 5;
        int[][] valoresContadores = {{1,20},{2, 40},{5, 50},{3, 69},{12, 106}};

        for (int i = 0; i < numHilos ; i++) {
            Contador contador = new Contador("H - " + i, valoresContadores[i][0], valoresContadores[i][1]);
            Thread hilo = new Thread(contador);
            hilo.start();
        }
    }
}
