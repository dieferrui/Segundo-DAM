import java.util.Properties;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Set;

public class Main {

    public static final String SAVEPATH = "C:\\Users\\alumno\\IdeaProjects\\Properties\\src\\angel_jose.properties";

    public static void main(String[] args) {

        guardarPropiedades(SAVEPATH);
        Properties pts = cargarPropiedades(SAVEPATH);
        mostrarPropiedades(pts);

    }

    public static Properties cargarPropiedades (String filepath) {

        Properties pts = new Properties();

        try {

            FileInputStream fis = new FileInputStream(filepath);
            pts.load(fis);
            fis.close();

        } catch (IOException e) {

            e.printStackTrace();

        }

        return pts;
    }

    public static void mostrarPropiedades (Properties pts) {

        pts.list(System.out);

        System.out.println(pts.toString());

        String propertiesListaPersonalizada = "";

        Set<Object> props = pts.keySet();
        for (Object prop : props) {
            propertiesListaPersonalizada += prop + ": " + pts.getProperty(prop.toString()) + "\n";
        }

        System.out.println(propertiesListaPersonalizada);

    }

    public static void guardarPropiedades(String savepath) {

        Properties pds = new Properties();
        pds.setProperty("nombre", "Angel Jose");
        pds.setProperty("edad", "21");
        pds.setProperty("sexo", "no");

        try (FileOutputStream fos = new FileOutputStream(savepath)) {

            pds.store(fos, "Propiedades de Ángel José");

        } catch (IOException e) {

            e.printStackTrace();

        }

    }
}
