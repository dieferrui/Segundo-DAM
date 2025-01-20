package es.cheste.minero;

public class Mina {
    public int stock;

    public Mina(int stock) {
        this.stock = stock;
    }

    public synchronized int extraer() {
        if (stock > 0) {
            stock--;
            return 1;

        } else {
            return 0;

        }
    }

    public int getStock() {
        return stock;
    }
}
