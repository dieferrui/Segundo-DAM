import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class Main {

    private static String DIRPATH = "D:\\prac";
    private static String SAVEPATH = "D:\\prac\\copy";

    public static void main(String[] args) {

        try {

            String archivoACopiar = DIRPATH + "\\archivocool.txt";
            String archivoCopiado = SAVEPATH + "\\archivocoolCopiaDecorada.txt";

            File original = new File(archivoACopiar);
            File copia = new File(archivoCopiado);

            if (!copia.exists()) {
                copia.createNewFile();
            }

            InputStream input = new FileInputStream(original);
            OutputStream output = new FileOutputStream(copia);
            OutputStreamWriter writer = new OutputStreamWriter(output); // Para escribir texto

            int bytes;
            int linea = 1;

            writer.write(String.valueOf(linea) + " ");
            writer.flush(); // Aparentemente es necesario para que escriba inmediatamente, y no al final del documento

            while ((bytes = input.read()) != -1) {

                output.write(bytes);

                if (bytes == 10) {
                    linea++;
                    writer.write(String.valueOf(linea) + " ");
                    writer.flush();
                }
            }

            input.close();
            writer.close();
            output.close();

        } catch (IOException e) {

            e.printStackTrace();

        }
    }
}
