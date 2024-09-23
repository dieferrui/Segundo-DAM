import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.regex.Pattern;

public class Main {

    public static final String FILEPATH = "DeleteNumberedLines\\src\\resources\\numbered_text.txt";
    public static final String SAVEPATH = "DeleteNumberedLines\\src\\resources\\denumbered_text.txt";
    public static final int NEW_LINE_BYTE = 10; 
    public static final int[] NUMBER_BYTES = {48, 49, 50, 51, 52, 53, 54, 55, 56, 57};
    
    public static void main(String[] args) {

        System.out.println("Antes de ejecutar el programa, asegúrese de que el archivo\n" +
                         "a tratar se encuentra en la carpeta resources del proyecto\n" +
                         "y que el nombre del archivo sea 'numbered_text'.");

        boolean resultado = borrarNumLineas();

        if (resultado) {

            System.out.println("Los números de línea han sido borrados.");

        } else {

            System.out.println("Ha ocurrido un error al procesar el archivo.");

        }

    }

    public static boolean borrarNumLineas() {

        File input = new File(FILEPATH);
        File output = new File(SAVEPATH);
        Pattern patron = Pattern.compile("^\\d+\\s*");

        try (BufferedReader reader = new BufferedReader(new FileReader(input));
             BufferedWriter writer = new BufferedWriter(new FileWriter(output))) {

            String linea;

            while ((linea = reader.readLine()) != null) {
                
                String lineaSinNumeros = patron.matcher(linea).replaceFirst("");
                writer.write(lineaSinNumeros);
                writer.newLine();

            }

            return true;

        } catch (Exception e) {

            e.printStackTrace();
            return false;

        }
    }
}
