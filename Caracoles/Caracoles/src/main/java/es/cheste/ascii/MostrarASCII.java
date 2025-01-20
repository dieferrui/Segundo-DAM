package es.cheste.ascii;

public class MostrarASCII implements Runnable {
    private int tipo;

    public MostrarASCII(int tipo) {
        this.tipo = tipo;
    }

    @Override
    public void run() {
        for (int i = 0; i <= 256; i++) {
            if (tipo == 2 && i % 2 == 0) {
                System.out.println("Índice de carácter ASCII par: " + (char) i);

            } else if (tipo == 1 && i % 2 != 0) {
                System.out.println("Índice de carácter ASCII impar: " + (char) i);
            }
        }
    }

    public static void main(String[] args) {
        MostrarASCII asciiPar = new MostrarASCII(1);
        MostrarASCII asciiImpar = new MostrarASCII(2);

        Thread[] hilos = {new Thread(asciiPar), new Thread(asciiImpar)};

        for(Thread hilo : hilos){
            hilo.start();
        }
    }
}
