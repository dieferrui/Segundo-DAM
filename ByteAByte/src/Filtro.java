import java.io.File;
import java.io.FileFilter;

public class Filtro implements FileFilter {

    @Override
    public boolean accept(File archivo) {

        
        return archivo.isFile();

    }
}

