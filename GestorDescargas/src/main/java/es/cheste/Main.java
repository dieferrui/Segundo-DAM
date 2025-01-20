package es.cheste;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Main {
    public static void main(String[] args) {
        /*
        GestorDescargas gd = new GestorDescargas();
        String url = "http://localhost:80" + "/psp/texto.txt";
        String fichero = "texto.txt";
        gd.descargarArchivo(url, fichero);
        */

        String destino = "www.google.com";
        int puertoDestino = 80;
        Socket socket = new Socket();

        InetSocketAddress direccion = new InetSocketAddress(destino, puertoDestino);
        try {
            socket.connect(direccion);
            InputStream is = socket.getInputStream();
            OutputStream os = socket.getOutputStream();
            System.out.println("Conexión establecida con " + destino + " en el puerto " + puertoDestino);

            // Crear buffer para solicitud HTTP
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            // Hacer la solicitud GET request
            String request = "GET / HTTP/1.1\nHost: " + destino + "\n" + "Connection: close\n\n";
            bw.write(request);
            bw.flush(); // Enviar la solicitud

            String line;

            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }

            socket.close();

        } catch (IOException e) {
            System.out.println("No se ha podido establecer la conexión");
            e.printStackTrace();

        }
    }
}