package es.cheste.tank;

import es.cheste.tank.Tanque;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorObjeto {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        int numPuerto = 5000;

        ServerSocket servidor = new ServerSocket(numPuerto);
        System.err.println("SERVIDOR >> Esperando al cliente...");

        Socket cliente = servidor.accept();
        ObjectOutputStream oos = new ObjectOutputStream(cliente.getOutputStream());
        Tanque t = new Tanque("Por defecto", "Clase", 0, 0, 0);
        oos.writeObject(t);

        System.err.println("SERVIDOR >> Enviando tanque: " + t.getNickname());
        ObjectInputStream ois = new ObjectInputStream(cliente.getInputStream());
        Tanque tMod = (Tanque) ois.readObject();

        System.out.println("SERVIDOR >> Recibido vehículo apodado \"" + tMod.getNickname() +"\":\n" +
            "Clase - " + tMod.getClase() + "\n" +
            "Número de serie - " + tMod.getNumSerial() + "\n" +
            "Peso (ton) - " + tMod.getPeso() + "\n" +
            "Calibre del cañón (mm) - " + tMod.getCalibreCannon());

        oos.close();
        ois.close();
        cliente.close();
        servidor.close();
    }
}
