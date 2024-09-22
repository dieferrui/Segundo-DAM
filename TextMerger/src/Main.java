import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Main {

    private static String DIRPATH = "D:\\prac";
    private static String EXITPATH = "D:\\prac\\copy";

    public static void main(String[] args) {

        File salida = new File(EXITPATH + "\\salida.txt");
        File directorio = new File(DIRPATH);

        try (FileWriter fw = new FileWriter(salida)) {

            for (File archivotxt : directorio.listFiles()) {

                try (FileReader fr = new FileReader(archivotxt)) {

                    int caracter;

                    while ((caracter = fr.read()) != -1) {

                        fw.write(caracter);

                    }

                } catch (IOException e) {

                    System.out.println("El archivo: " + archivotxt.getName() + " no es un archivo txt.");

                }
            }

        } catch (IOException e) {

            System.out.println("Error de escritura.");

        }
    }
}