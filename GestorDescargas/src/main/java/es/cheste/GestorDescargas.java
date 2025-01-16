package es.cheste;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

public class GestorDescargas {
    public void descargarArchivo(String url, String fichero) {
        String urlDescargar = url;
        String nomArchivo = fichero;

        System.out.println("Descargando archivo " + fichero + " de " + url);

        try {
            URL laURL = new URL(urlDescargar);
            InputStream is = laURL.openStream();
            BufferedInputStream bis = new BufferedInputStream(is);
            FileOutputStream fos = new FileOutputStream(nomArchivo);

            while (bis.read() != -1) {
               fos.write(bis.read());
            }

            fos.close();
            bis.close();
            is.close();

        } catch (MalformedURLException e) {
            System.out.println("URL mal escrita");

        } catch (IOException e) {
            System.out.println("Fallo en la lectura del fichero");

        }
    }
}
