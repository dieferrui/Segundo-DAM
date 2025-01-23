package es.cheste.persona;

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
        Persona p = new Persona("Nombre", 0);
        oos.writeObject(p);

        System.err.println("SERVIDOR >> Enviando objeto: " + p.getNombre() + " - " + p.getEdad());
        ObjectInputStream ois = new ObjectInputStream(cliente.getInputStream());
        Persona pMod = (Persona) ois.readObject();

        System.err.println("SERVIDOR >> Recibido objeto: " + pMod.getNombre() + " - " + pMod.getEdad());

        oos.close();
        ois.close();
        cliente.close();
        servidor.close();
    }
}
