import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Scanner;
import java.io.File;

public class Main {

    private static final String DIRPATH = "src\\resources";
    private static final int BYTESIZE = 32;
    private static final Scanner SC = new Scanner(System.in);
    private static Filtro FILTRO_ARCH = new Filtro();

    public static void main(String[] args) {

        File carpeta = new File(DIRPATH);
        System.out.println(leerCarpeta(carpeta));

        int archivoSelect = SC.nextInt();

        String respuesta = selectArchivo(archivoSelect, carpeta);
        System.out.println(respuesta);

    }

    private static String leerCarpeta (File dirpath) {

        StringBuilder sb = new StringBuilder();
        

        File[] archivos = dirpath.listFiles(FILTRO_ARCH);

        if (archivos != null) {

            sb.append("Seleccione un archivo: \n");
            int ciclo = 1;

            for (File archivo : archivos) {

                sb.append(ciclo + ". " + archivo.getName() + "\n");
                ciclo++;

            }

            return sb.toString();
            
        } else {

            return "No se encontraron archivos en la carpeta.";

        }
    }

    private static String selectArchivo (int select, File dirpath) {

        boolean success = false;

        for (int i = 0; i < dirpath.listFiles(FILTRO_ARCH).length; i++) {

            if (select == i + 1) {

                File archivo = dirpath.listFiles()[i];
                success = copiarArchivo(archivo);

            }
        }

        if (success) {

            return "Archivo copiado exitosamente.";

        } else {

            return "No se pudo copiar el archivo.";

        }

    }

    private static boolean copiarArchivo (File archivo) {

        File copiaArchivo = new File(DIRPATH + "\\" + archivo.getName() + "_copia");

        try (InputStream is = new FileInputStream(archivo);
             OutputStream os = new FileOutputStream(copiaArchivo)) {

            byte[] buffer = new byte[BYTESIZE];
            int length;

            while ((length = is.read(buffer)) > 0) {

                os.write(buffer, 0, length);

            }

            return true;

        } catch (IOException e) {

            e.printStackTrace();
            return false;

        }
    }
}

