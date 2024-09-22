import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Main {

    private static final String DIRPATH = "src\\resources";

    public static void main(String[] args) {

        File salida = new File(DIRPATH + "\\salida.txt");
        File directorio = new File(DIRPATH);

        mergeTexto(directorio, salida);

    }

    private static void mergeTexto(File textosOriginales, File resultado) {

        try (FileWriter fw = new FileWriter(resultado)) {

            for (File archivotxt : textosOriginales.listFiles()) {

                if (archivotxt.getName() != "salida.txt") {

                    try (FileReader fr = new FileReader(archivotxt)) {

                        int caracter;

                        while ((caracter = fr.read()) != -1) {

                            fw.write(caracter);

                        }

                    } catch (IOException e) {

                        System.out.println("El archivo: " + archivotxt.getName() + " no es un archivo txt.");

                    }

                } else {

                    continue;

                }
            }

        } catch (IOException e) {

            System.out.println("Error de escritura.");

        }
    }
}