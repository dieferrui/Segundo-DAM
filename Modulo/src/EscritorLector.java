import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.util.Arrays;

public class EscritorLector {

    private static final String DATAPATH = "src/modulos.dat";
    private static final String CSVPATH = "src/modulos.csv";

    public static void guardarModulos(Modulo[] modulos) {

        ArrayList<Modulo> modulosList = new ArrayList<Modulo>(Arrays.asList(modulos));

        Collections.sort(modulosList);
        modulos = modulosList.toArray(new Modulo[0]);
        File modulosDat = new File(DATAPATH);

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(modulosDat))) {

            oos.writeObject(modulos);

        } catch (IOException e) {

            e.printStackTrace();

        }
    }

    public static Modulo[] cargarModulos() {

        Modulo[] modulos = null;

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(DATAPATH))) {

            modulos = (Modulo[]) ois.readObject();

        } catch (IOException | ClassNotFoundException e) {

            e.printStackTrace();

        }

        return modulos;
    }

    public static void guardarModulosCSV(Modulo[] modulos) {

        File modulosCSV = new File(CSVPATH);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(modulosCSV))) {

            bw.write("Nombre;Hora;Nota");
            bw.newLine();

            for (Modulo modulo : modulos) {

                bw.write(modulo.getNombre() + ";" + modulo.getHora() + ";" + modulo.getNota());
                bw.newLine();

            }

        } catch (IOException e) {

            e.printStackTrace();

        }
    }

    public static Modulo[] cargarModulosCSV() {

        ArrayList<Modulo> modulosList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(CSVPATH))) {

            String linea = br.readLine();

            while ((linea = br.readLine()) != null) {

                String[] datos = linea.split(";");
                String nombre = datos[0];
                int hora = Integer.parseInt(datos[1]);
                double nota = Double.parseDouble(datos[2]);

                Modulo modulo = new Modulo(nombre, hora, nota);
                modulosList.add(modulo);
            }

        } catch (IOException e) {

            e.printStackTrace();
            
        }

        return modulosList.toArray(new Modulo[0]);
    }
}
