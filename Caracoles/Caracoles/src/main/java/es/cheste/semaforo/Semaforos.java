package es.cheste.semaforo;

public class Semaforos {
    private boolean semaforoUnoGo = true;

    public void semaforoUnoVerde() throws InterruptedException {
        while (true){
            synchronized (this){
                while (!semaforoUnoGo) {
                    wait();
                }

                semaforoUnoGo = false;
                System.out.println("El semáforo 1 está en verde");
                System.err.println("El semáforo 2 está en rojo");

                notify();
                Thread.sleep(5000);
            }
        }
    }

    public void semaforoDosVerde() throws InterruptedException{

        while (true){
            synchronized (this){
                while (semaforoUnoGo) {
                    wait();
                }

                semaforoUnoGo = true;
                System.out.println("El semáforo 2 está en verde");
                System.err.println("El semáforo 1 está en rojo");

                notify();
                Thread.sleep(5000);
            }
        }
    }
}
