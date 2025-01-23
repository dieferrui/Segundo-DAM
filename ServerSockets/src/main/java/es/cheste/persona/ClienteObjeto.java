package es.cheste.persona;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClienteObjeto {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        String host = "localhost";
        int numPuerto = 5000;

        System.out.println("CLIENTE >> Iniciando cliente...");
        Socket cliente = new Socket(host, numPuerto);
        ObjectInputStream ois = new ObjectInputStream(cliente.getInputStream());
        Persona p = (Persona) ois.readObject();

        System.err.println("CLIENTE >> Recibido objeto del servidor: " + p.getNombre() + " - " + p.getEdad());

        p.setNombre("Jose García");
        p.setEdad(20);

        ObjectOutputStream oos = new ObjectOutputStream(cliente.getOutputStream());
        oos.writeObject(p);

        System.out.println("CLIENTE >> Enviando objeto: " + p.getNombre() + " - " + p.getEdad());

        ois.close();
        oos.close();
        cliente.close();
    }
}
