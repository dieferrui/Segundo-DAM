import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Main {

    private static String DIRPATH = "D:\\prac";
    private static String SAVEPATH = "D:\\prac\\copy";

    public static void Main(String[] args) {

        // Aquí daremos opción de elegir el archivo a copiar listando los archivos del directorio DIRPATH

        String archivoACopiar = DIRPATH + "\\archivocool"; // Placeholder
        String archivoCopiado = SAVEPATH + "\\archivocoolCopia";

        File original = new File(archivoACopiar);
        File copia = File.createNewFile(archivoCopiado);

        InputStream input = new InputStream(original);
        OutputStream output = new OutputStream(copia);

        while (input.read() != -1) {

            int byteLeido = input.read();
            output.write(byteLeido);

        }
    }
}
